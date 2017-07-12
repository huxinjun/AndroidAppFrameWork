package com.app.presenter.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.app.annotation.request.AccessSettings.RequestMethods;
import com.app.presenter.IActivityPresenterBridge;
import com.app.presenter.IImagePresenter;
import com.app.presenter.IPersistentPresenter;
import com.app.presenter.IPersistentPresenterBridge;
import com.app.presenter.IRequestPresenter.RequestInfo;
import com.app.presenter.IRequestPresenter.ResultType;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.presenter.impl.request.RequestPresenter;

/**
 * 图片管理器
 * @author xinjun
 *
 */
public class ImagePresenter implements IImagePresenter {

	private Option globleOption=new Option();
	private MyLruCache mMemoryCache;
	private NoRepeatTaskHashMap taskHistory = new NoRepeatTaskHashMap();
	
	public ImagePresenter() {
		int maxMemory = ((ActivityManager) (getContext().getSystemService(Context.ACTIVITY_SERVICE))).getMemoryClass();
		int cacheSize = maxMemory * 1024 * 1024 / 8;
		mMemoryCache=new MyLruCache(cacheSize);
	}
	
	
	@Override
	public void setMemorySize(long size) {
		mMemoryCache.clear();
		mMemoryCache=new MyLruCache(size);
	}
	
	@Override
	public void setGlobleOption(Option option) {
		this.globleOption=option;
	}
	
	@Override
	public Option getGlobleOption() {
		return globleOption;
	}

	@Override
	public Bitmap getImage(String url, OnImageDownloadOkListener listener) {
		return null;
	}

	@Override
	public void setImage(ImageView target, String url) {
		Option option = (Option) target.getTag(LayoutCreater.TAG_IMAGE_OPTION);
		if(option==null){
			//没有配置全局注解或者单个字段注解时image的tag为null
			option=this.globleOption;
		}
		if(option.getRect()==null){
			Rect maxRectByImageView = getMaxRectByImageView(target);
			option.setRect(maxRectByImageView);
		}
	}

	
	
	
	
	
	/**
	 * 自己实现的一个内存图片缓存
	 * 添加元素时会根据缓存的大小删除最不常使用的图片
	 * @author XINJUN
	 *
	 */
	@SuppressWarnings("serial")
	class MyLruCache extends LinkedHashMap<String,Bitmap>{

		long maxCacheSize;
		long currCacheSize;
		
		public MyLruCache(long cacheSize) {
			this.maxCacheSize=cacheSize;
		}
		
		@Override
		public Bitmap put(String key, Bitmap value) {
			int byteCount = value.getByteCount();
			super.put(key, value);
			currCacheSize+=byteCount;
			if(currCacheSize>maxCacheSize)
				while(currCacheSize>maxCacheSize){
					remove(keySet().toArray()[0]);
				}
//			MyLog.outInnerLogDetail("MyLruCache:最大容量"+((int)(maxCacheSize*1.0/1024/1024))+"MB  当前使用:"+((int)(currCacheSize*1.0/1024/1024))+"MB");
			return value;
		}
		
		@Override
		public Bitmap remove(Object key) {
			Bitmap bitmap = get(key);
			int byteCount = bitmap.getByteCount();
			Bitmap remove = super.remove(key);
			currCacheSize-=byteCount;
//			MyLog.outInnerLogDetail("MyLruCache:移除最老元素后 当前使用:"+((int)(currCacheSize*1.0/1024/1024))+"MB");
			return remove;
		}
		
		
	}
	/**
	 * 这个hashmap主要解决重复的异步任务
	 * @author XINJUN
	 *
	 */
	@SuppressWarnings("serial")
	class NoRepeatTaskHashMap extends HashMap<String,ImageAsyncTask>{
		
		
		@Override
		public ImageAsyncTask put(String key, ImageAsyncTask value) {
			if(get(key)==null)
				return super.put(key, value);
			return null;
		}
		
		public void addNewTarget(String key,ImageView newTarget){
			if(get(key)!=null)
				get(key).targets.add(newTarget);
		}
		
		/**
		 * 不同的ImageView设置同一个url
		 * 这种情况我们不需要再次发起一个网络请求
		 * 因为已经有一个任务再进行了
		 * @return 是不是有相同的URL请求正在进行
		 */
		public boolean checkSameUrl(String url,ImageView target){
			if(containsKey(url)){
				boolean hasDiffTarget=true;
				for(ImageView iv:get(url).targets)
					if(iv.equals(target)){
						hasDiffTarget=false;
						break;
					}
				if (hasDiffTarget)
					addNewTarget(url, target);
				return true;
			}
			return false;
		}
		
		/**
		 * 同一个Imageview设置不同的Url
		 * 一般是listview中复用的imageview发起的
		 * 常见于快速的滑动listview
		 * 这种情况我们将之前的请求任务查找出来，取消那个旧的任务，开始新的任务
		 */
		public void checkSameImageView(String url,ImageView target){
			//获取所有任务
			Iterator<Entry<String, ImageAsyncTask>> iterator = entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, ImageAsyncTask> entry = iterator.next(); 
				//当前遍历到的任务
				ImageAsyncTask value=entry.getValue();
				//获取当前任务的目标ImageView们
				Iterator<ImageView> targetIterator = value.targets.iterator();
				while(targetIterator.hasNext()){
					ImageView iv=targetIterator.next();
					if(iv==target){
						//不同的URL,但是发现有一个任务中有和此次请求相同的ImageView
						//这个ImageView多次的设置了图片,我们以最后一次设置为准
						//移除这个imageview
						//移除后再看其他的任务中有没有这个ImageView在等待请求网络
						//最后本次的请求是最新的,所以还是要响应的哦!!!
						targetIterator.remove();
					}
				}
				//如果删除了就得target后size变为0,说明这个任务只为一个ImageView服务而且是和本次请求相同的ImageView,那么把旧的任务中断
//				if(value.targets.size()==0)
//					value.cancel(true);
			}
		}
		
		@Override
		public String toString() {
			String result="NoRepeatTaskHashMap[";
			Iterator<Entry<String, ImageAsyncTask>> iterator = entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, ImageAsyncTask> entry = iterator.next(); 
				ImageAsyncTask value=entry.getValue();
				result+=("ImageAsyncTask-"+getObejctHashCode(value)+"[targets[");
				Iterator<ImageView> targetIterator = value.targets.iterator();
				while(targetIterator.hasNext()){
					ImageView iv=targetIterator.next();
					result+=(getObejctHashCode(iv)+",");
				}
				result+="]]";
			}
			result+="]";
			return result;
		}
		
		private String getObejctHashCode(Object obj){
			return Integer.toHexString(System.identityHashCode(obj));
		}
	}

	/**
	 * 这个异步任务类只是为了记住图片地址和图片控件
	 * 
	 * @author xinjun
	 * @CreateTime 2015-8-12 上午11:37:43
	 */
	private abstract class ImageAsyncTask extends AsyncTask<Void, Void, Bitmap> {
		public String url;
		public ArrayList<ImageView> targets=new ArrayList<ImageView>();
	}


	/**
	 * 加载本地图片使用线程加载,加载完成后才通知去使用
	 * @author XINJUN
	 *
	 */
	private abstract class LocalImageLoadCallBack{
		abstract void onImageLodeSuccess(ImageAsyncTask imageAsyncTask, Bitmap bitmap);
		abstract void onImageLodeFaild(String uri,ImageView target);
	}
	
	
	//---------------------------------------本地异步加载使用新的Excutor,因为网络图片加载太慢-------------------------------
		private static final ThreadFactory sThreadFactory = new ThreadFactory() {
	        private final AtomicInteger mCount = new AtomicInteger(1);

	        public Thread newThread(Runnable r) {
	            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
	        }
	    };
	    public static final Executor SERIAL_EXECUTOR = new SerialExecutor();
	    private static final BlockingQueue<Runnable> sPoolWorkQueue =
	    		new LinkedBlockingQueue<Runnable>(10);
	    
	    public static final Executor THREAD_POOL_EXECUTOR
	    = new ThreadPoolExecutor(5, 128, 1,
	            TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
	    private static class SerialExecutor implements Executor {
	        final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
	        Runnable mActive;

	        public synchronized void execute(final Runnable r) {
	            mTasks.offer(new Runnable() {
	                public void run() {
	                    try {
	                        r.run();
	                    } finally {
	                        scheduleNext();
	                    }
	                }
	            });
	            if (mActive == null) {
	                scheduleNext();
	            }
	        }

	        protected synchronized void scheduleNext() {
	            if ((mActive = mTasks.poll()) != null) {
	                THREAD_POOL_EXECUTOR.execute(mActive);
	            }
	        }
	    }
	    //------------------------------------------------------------------------------------------------------------------
		
	
	
	
	/**
	 * 设置图片
	 * @param target 目标控件
	 * @param url 目标url
	 * @param animation 设置时的动画
	 * @param rect 缩放区域
	 */
	public void setImage(final ImageView target, final String url,final Option option) {
		if (url == null || "".equals(url))
			return;
		if(target.getTag()!=null && url.equals(target.getTag().toString()))
			return;
		setLoadingImage(target,option);
		//有相同的任务时不需要重新创建任务了
		if(taskHistory.checkSameUrl(url, target))
			return;
		taskHistory.checkSameImageView(url, target);
		

		
		getBitmapInCacheAsynchronized(url,target,option.getRect(),new LocalImageLoadCallBack() {
			
			@Override
			void onImageLodeSuccess(ImageAsyncTask imageAsyncTask,Bitmap localbitmap) {
				for(ImageView target:imageAsyncTask.targets){
					target.setBackgroundResource(0);
					target.setImageBitmap(localbitmap);
					target.setTag(url);
					if (option.getAnimation() != null)
						target.startAnimation(option.getAnimation());
				}
				return;
				
			}
			@Override
			void onImageLodeFaild(String imageurl,final ImageView targetimage) {
				//有相同的任务时不需要重新创建任务了
				if(taskHistory.checkSameUrl(imageurl, targetimage))
					return;
				taskHistory.checkSameImageView(imageurl, targetimage);
				
				ImageAsyncTask task = new ImageAsyncTask() {
					
					@Override
					protected Bitmap doInBackground(Void... params) {
						Bitmap bitmap = getBitmapFromMemCache(this.url);
						if (bitmap == null) {
							
							RequestInfo info=new RequestInfo();
							info.mRequestMethod=RequestMethods.GET;
							info.mResultType=ResultType.IMAGE;
							info.mExcuteCount=option.getRetryCount();
							info.mBaseUrl="";
							info.mUrlPattener="";
							
							bitmap = getRequester().getImage(info);
							if(bitmap==null)
								return null;
							try {
								// 存储大图到本地
								String localPath = getPersistent().saveBitmap(this.url,bitmap);
								
								if (TextUtils.isEmpty(localPath)) {
									bitmap = zoomBitmap(bitmap, option.getRect().maxWidth, option.getRect().maxHeight);
									addBitmapToMemoryCache(this.url, bitmap);
									return bitmap;
								}
								
								bitmap.recycle();
								// 加载本地大图，并缩小
								return getBitmapInCache(this.url, option.getRect());
							} catch (Throwable ex) {
								ex.printStackTrace();
							}
							return bitmap;
						}
						return bitmap;
					}
					
					@Override
					protected void onPostExecute(final Bitmap result) {
						if (result == null) {
							setFaildLoadImage(targetimage,option);
							return;
						}
						for(ImageView target:targets){
							target.setBackgroundResource(0);
							target.setImageBitmap(result);
							target.setTag(url);
							if (option.getAnimation() != null)
								target.startAnimation(option.getAnimation());
						}
						taskHistory.remove(this.url);
					}
					
					protected void onPreExecute() {
						setLoadingImage(targetimage,option);
					};
				};
				task.url = imageurl;
				task.targets.add(targetimage);
				taskHistory.put(imageurl, task);
				task.execute();
				
			}
		});
	}
	
	
	private void setLoadingImage(ImageView target,Option option) {
		target.setBackgroundResource(option.getWaittingResId());
		target.setImageDrawable(null);
		
		if(target.getBackground() instanceof AnimationDrawable)
			((AnimationDrawable)target.getBackground()).start();
	}

	private void setFaildLoadImage(ImageView target,Option option) {
		target.setBackgroundResource(option.getFaildResId());
		target.setImageDrawable(null);
		
		if(target.getBackground() instanceof AnimationDrawable)
			((AnimationDrawable)target.getBackground()).start();
	}
	
	private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null && bitmap != null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	private Bitmap getBitmapFromMemCache(String key) {
		return mMemoryCache.get(key);
	}

	
	/**
	 * 异步加载本地图片
	 * @param uri 图片地址
	 * @param target 最大宽度
	 * @param rect 最大高度
	 * @param callBack 加载完成后的回调
	 */
	private void getBitmapInCacheAsynchronized(final String uri, final ImageView target, final Rect rect,final LocalImageLoadCallBack callBack) {
		
		ImageAsyncTask task = new ImageAsyncTask() {
			
			@Override
			protected Bitmap doInBackground(Void... params) {
				return getBitmapInCache(uri, rect);
			}
			@Override
			protected void onPostExecute(Bitmap result) {    
				super.onPostExecute(result);
				taskHistory.remove(uri);
				if (result != null){
					addBitmapToMemoryCache(uri, result);
					callBack.onImageLodeSuccess(this,result);
				}else
					callBack.onImageLodeFaild(uri,target);
			}
		};
		if(target!=null)
			task.targets.add(target);
		task.url=uri;
		taskHistory.put(uri, task);
		task.executeOnExecutor(SERIAL_EXECUTOR);
	}
	
	/**
	 * 同步加载本地图片
	 * @param uri
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 */
	private Bitmap getBitmapInCache(String url,Rect rect) {
		if (getBitmapFromMemCache(url) != null) {
			return getBitmapFromMemCache(url);
		}
		Bitmap bitmap = getPersistent().getBitmap(url, rect);
		if(bitmap!=null)
			addBitmapToMemoryCache(url, bitmap);
		return bitmap;
	}
	

	/**
	 * 获取imageview可以占用的最大空间
	 * 
	 * @param target
	 * @return
	 */
	private Rect getMaxRectByImageView(ImageView target) {
		Rect rect = new Rect(0, 0);
		target.measure(0, 0);
		if (target.getMeasuredWidth() > 0 && target.getMeasuredHeight() > 0) {
			rect.maxWidth = target.getMeasuredWidth();
			rect.maxHeight = target.getMeasuredHeight();
			return rect;
		}
		LayoutParams params = target.getLayoutParams();
		if (params != null) {
			if (params.width > 0)
				rect.maxWidth = params.width;
			else if (params.width == ViewGroup.LayoutParams.MATCH_PARENT)
				rect.maxWidth = target.getWidth();

			if (params.height > 0)
				rect.maxHeight = params.height;
			else if (params.height == ViewGroup.LayoutParams.MATCH_PARENT)
				rect.maxHeight = target.getHeight();
		}

		if (rect.maxWidth > 0 && rect.maxHeight > 0)
			return rect;

		rect.maxWidth = getImageViewFieldValue(target, "mMaxWidth");
		rect.maxHeight = getImageViewFieldValue(target, "mMaxHeight");

		if (rect.maxWidth > 0 && rect.maxHeight > 0)
			return rect;

		rect.maxWidth = getDisplayWidth();
		rect.maxHeight = getDisplayHeight();

		return rect;

	}

	/**
	 * 获取imageview的字段值
	 * 
	 * @param target
	 * @param fieldName
	 * @return
	 */
	private int getImageViewFieldValue(ImageView target, String fieldName) {
		int value = 0;
		try {
			Field field = ImageView.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			int fieldValue = (Integer) field.get(target);
			if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
				value = fieldValue;
			}
		} catch (Throwable e) {
		}
		return value;
	}
	
	
	/**
	 * 获取图片压缩比例
	 * 
	 * 用法： opts.inJustDecodeBounds = true; 设置加载图片不分配内存
	 * 
	 * opts.inSampleSize = ImageUtils.calculateInSampleSize(opts,50,50);
	 * 
	 * opts.inJustDecodeBounds = false; 设置加载图片分配内存 Bitmap bitmap =
	 * BitmapFactory.decodeFile(imageUrl, opts); 将参数传入.
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public int calculateInSampleSize(int outWidth, int outHeight, int reqWidth, int reqHeight) {
		// 源图片的高度和宽度
		int inSampleSize = 1;
		if (outHeight > reqHeight || outWidth > reqWidth) {
			// 计算出实际宽高和目标宽高的比率
			final int heightRatio = Math.round((float) outHeight / (float) reqHeight);
			final int widthRatio = Math.round((float) outWidth / (float) reqWidth);
			// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
			// 一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public Bitmap compressImage(String imageUrl, Rect rect) {
		if (rect==null || (rect.maxWidth<= 0 && rect.maxHeight <= 0))
			return BitmapFactory.decodeFile(imageUrl);
		
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		opts.inJustDecodeBounds = true;
		opts.inPurgeable = true;
		BitmapFactory.decodeFile(imageUrl, opts);
//		MyLog.outInnerLogDetail("原图片大小:" + opts.outWidth + "x" + opts.outHeight);
		if(rect.maxWidth==getDisplayWidth()){
			rect.maxHeight=(int) (opts.outHeight*1.0*rect.maxWidth/opts.outWidth);
		}
//		MyLog.outInnerLogDetail("计算的最小大小:" + width + "   maxHeight:" + height);
		opts.inSampleSize = calculateInSampleSize(opts.outWidth, opts.outHeight, rect.maxWidth, rect.maxHeight);
		
		opts.inJustDecodeBounds = false;
		Bitmap bitmap_small = BitmapFactory.decodeFile(imageUrl, opts);
		return bitmap_small;
	}

	
	/**
	 * 缩放Bitmap图片
	 * 
	 * @param bitmap
	 *            要处理用的图片
	 * @param width
	 *            所选用图片的宽度
	 * @param height所选用图片的高度
	 * @return 处理之后的图片
	 */
	public Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		// 利用矩阵进行缩放不会造成内存溢出
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		bitmap.recycle();
		return newbmp;
	}
	
	
	
	/**
	 * 屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public int getDisplayWidth() {
		DisplayMetrics outMetrics = new DisplayMetrics();
		((Activity)getContext()).getWindowManager().getDefaultDisplay()
				.getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public int getDisplayHeight() {
		DisplayMetrics outMetrics = new DisplayMetrics();
		((Activity)getContext()).getWindowManager().getDefaultDisplay()
				.getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private RequestPresenter getRequester(){
		IRequestPresenterBridge findPresenter = PresenterManager.getInstance().findPresenter(IRequestPresenterBridge.class);
		Method deffaultSource;
		try {
			deffaultSource = findPresenter.getClass().getMethod("deffaultSource");
			RequestPresenter invoke = (RequestPresenter) deffaultSource.invoke(findPresenter);
			return invoke;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private IPersistentPresenter getPersistent(){
		return PresenterManager.getInstance().findPresenter(IPersistentPresenterBridge.class);
	}
	private Context getContext(){
		return PresenterManager.getInstance().findPresenter(IActivityPresenterBridge.class).getContext();
	}




	

	
	

}
