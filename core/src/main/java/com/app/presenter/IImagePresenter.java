package com.app.presenter;

import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * 图片加载器
 * @author xinjun
 *
 */
public interface IImagePresenter extends IPresenter {

	/**
	 * 图像最大长宽
	 * @author xinjun
	 *
	 */
	public static class Rect {
		public int maxWidth;
		public int maxHeight;

		public Rect(int maxWidth, int maxHeight) {
			super();
			this.maxWidth = maxWidth;
			this.maxHeight = maxHeight;
		}

	}
	
	/**
	 * 下载图片回调
	 * @author XINJUN
	 *
	 */
	public static interface OnImageDownloadOkListener {
		void onImageDownloadOk(Bitmap bitmap);

		void onImageDownloadFaild(String result);
	}
	
	/**
	 * 加载图片时的设置选项
	 * @author XINJUN
	 *
	 */
	public class Option{
		
		private int faildResId=0;
		private int waittingResId=0;
		private Animation anim;
		private int roundPix=0;
		private boolean cacheInMem=true;
		private boolean cacheInDisc=true;
		private int retryCount=3;
		private Rect maxRect=new Rect(0, 0);
		
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		
		//--------------------------------setter----------------------------
		public Option setFaildResId(int id){
			this.faildResId=id;
			return this;
		}
		
		public Option setWaittingResId(int id){
			this.waittingResId=id;
			return this;
		}
		
		public Option setRoundPix(int pix){
			this.roundPix=pix;
			return this;
		}
		public Option setIsCacheInMem(boolean is){
			this.cacheInMem=is;
			return this;
		}
		public Option setIsCacheInDisc(boolean is){
			this.cacheInDisc=is;
			return this;
		}
		public Option setRetryCount(int count){
			this.retryCount=count;
			return this;
		}
		public Option setAnimation(Animation anim){
			this.anim=anim;
			return this;
		}
		public Option setRect(Rect rect){
			this.maxRect=rect;
			return this;
		}
		
		
		//--------------------------------getter----------------------------

		public int getFaildResId() {
			return faildResId;
		}

		public int getWaittingResId() {
			return waittingResId;
		}

		public int getRoundPix() {
			return roundPix;
		}

		public boolean isCacheInMem() {
			return cacheInMem;
		}

		public boolean isCacheInDisc() {
			return cacheInDisc;
		}

		public int getRetryCount() {
			return retryCount;
		}
		public Animation getAnimation() {
			return anim;
		}
		public Rect getRect() {
			return maxRect;
		}
		
		
		
	}
	
	public abstract void setMemorySize(long size);
	
	public abstract void setGlobleOption(Option option);
	
	public abstract Option getGlobleOption();
	
	public abstract Bitmap getImage(String url,OnImageDownloadOkListener listener);
	
	public abstract void setImage(ImageView target,String url);
	
	public abstract Bitmap compressImage(String imageUrl,Rect rect);
}
