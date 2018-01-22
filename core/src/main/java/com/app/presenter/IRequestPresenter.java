package com.app.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.app.SmartDialog;
import com.app.annotation.request.AccessSettings.RequestMethods;
import com.app.presenter.IDataPresenter.RequestListener;
import com.app.presenter.IEntityProxyPresenter.BeanProxyInfo;

/**
 * 网络请求
 * @author xinjun
 *
 */
public interface IRequestPresenter extends IPresenter {

	static final String ENCODING="UTF-8";
	
	static Map<String,RequestInfo> mAllRequests=new HashMap<String,RequestInfo>();

	static Setting GLOBLE=new Setting();
	
	/**
	 * 所有请求需要用到的信息，这些信息使用注解配置
	 * 需要配置在能被解释道的注解目标上，比如Application，Activity，Fragment，LayoutCreater
	 * @author XINJUN
	 *
	 */
	public class Setting{
		
		/**配置请求的类全路径名*/
		public Class dataClass;
		
		/**配置请求url段的类全路径名*/
		public Class urlClass;
		
		/**配置json协议模板的类全路径名*/
		public String localJsonPackage;

		/**配置json协议模板的类全路径名*/
		public String requestBaseUrl;


		@Override
		public String toString() {
			return "Setting{" +
					"dataClass=" + dataClass + "\n"+
					", urlClass=" + urlClass + "\n"+
					", localJsonPackage='" + localJsonPackage + '\'' +"\n"+
					", requestBaseUrl='" + requestBaseUrl + '\'' +
					'}';
		}
	}
	/**
	 * 一次具体的请求包含的所有信息
	 * @author xinjun
	 *
	 */
	public class RequestInfo{

		/**请求基地址*/
		public String mBaseUrl;
		/**
		 * 返回值类型:字符串,图像,文件3中类型
		 */
		public ResultType mResultType;
		
		/**请求方式*/
		public RequestMethods mRequestMethod;
		
		/**这次请求的注释*/
		public String mDescription;
		
		/**请求唯一name*/
		public String mRequestName;
		
		/**参数们*/
		public ParamPool mParamPool;
		
		/**请求时显示的dialog,null时不显示*/
		public SmartDialog mDialog;
		/**是否显示dialog*/
		public boolean isShowDialog;

		/**关联的请求字段配置名称*/
		public String mRequestSettingName;
		
		/**本地的json模板*/
		public boolean isUseTempleteData;
		
		/**本地的json模板们所在的包路径*/
		public String mTempleteDataPackage;
		
		/**本地的json模板文件名*/
		public String mTempleteDataFileName;
		
		
		/**是否持久化,默认true*/
		public boolean isPersistence;
		
		/**是否使用磁盘缓存*/
		public boolean isUseDiscCache;

		/**请求的地址*/
		public String mRequestUrl;
		
		/**请求状态监听器*/
		public Set<RequestListener> mListeners=new HashSet<IDataPresenter.RequestListener>();
		
		/**实体类型*/
		public Class<?> mEntityType;
		
		/**追加数据的类型*/
		public Class<?> mAppendType;
		
		/**数据解析器*/
		public Class<? extends IParserPresenter> mParser;
		
		/**结果数据回调*/
		public DataCallBack mCallBack;
		
		/**连接超时时间*/
		public int mConnectionTimeOut;
		
		/**服务器超时时间*/
		public int mServerTimeOut;
		
		/**重试次数*/
		public int mRetryCount;
		
		/**当前请求是第几次请求*/
		public int mExcuteCount;
		
		/**本地缓存数据*/
		public Object mDiscResult;
		
		/**网络请求的数据*/
		public Object mServerResult;
		
		/**数据代理描述类*/
		public BeanProxyInfo mDataProxy;
		
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		@Override
		public String toString() {
			return "RequestInfo{" +
					"mResultType=" + mResultType +"\n"+
					", mRequestMethod=" + mRequestMethod +"\n"+
					", mDescription='" + mDescription + '\'' +"\n"+
					", mRequestName='" + mRequestName + '\'' +"\n"+
					", mParamPool=" + mParamPool +"\n"+
					", mDialog=" + mDialog +"\n"+
					", isShowDialog=" + isShowDialog +"\n"+
					", mRequestSettingName='" + mRequestSettingName + '\'' +"\n"+
					", isUseTempleteData=" + isUseTempleteData +"\n"+
					", mTempleteDataPackage='" + mTempleteDataPackage + '\'' +"\n"+
					", mTempleteDataFileName='" + mTempleteDataFileName + '\'' +"\n"+
					", isPersistence=" + isPersistence +"\n"+
					", isUseDiscCache=" + isUseDiscCache +"\n"+
					", mBaseUrl='" + mBaseUrl + '\'' +"\n"+
					", mRequestUrl='" + mRequestUrl + '\'' +"\n"+
					", mListeners=" + mListeners +"\n"+
					", mEntityType=" + mEntityType +"\n"+
					", mAppendType=" + mAppendType +"\n"+
					", mParser=" + mParser +"\n"+
					", mCallBack=" + mCallBack +"\n"+
					", mConnectionTimeOut=" + mConnectionTimeOut +"\n"+
					", mServerTimeOut=" + mServerTimeOut +"\n"+
					", mRetryCount=" + mRetryCount +"\n"+
					", mExcuteCount=" + mExcuteCount +"\n"+
					", mDiscResult=" + mDiscResult +"\n"+
					", mServerResult=" + mServerResult +"\n"+
					", mDataProxy=" + mDataProxy +
					'}';
		}
	}
	
	/**
	 * 参数池
	 * 用后即销毁
	 * @author xinjun
	 *
	 */
	public class ParamPool{
		
		public static ThreadLocal<ParamPool> INSTANCE_TEMP;
		private List<Param> mParams=new ArrayList<Param>();
		
		public static ParamPool obtain(){
			INSTANCE_TEMP=new ThreadLocal<ParamPool>();
			INSTANCE_TEMP.set(new ParamPool());
			return INSTANCE_TEMP.get();
		}
		
		private ParamPool putParam(String key,String value,ParamType type){
			if(INSTANCE_TEMP.get()==null)
				throw new RuntimeException("先调用obtain方法获取一个ParamPool对象");
			INSTANCE_TEMP.get().mParams.add(new Param(key,value,type));
			return INSTANCE_TEMP.get();
		}
		
		public ParamPool putParam(String key,String value){
			return putStringParam(key, value);
		}
		
		public ParamPool putStringParam(String key,String value){
			return putParam(key, value, ParamType.VALUE);
		}
		public ParamPool putFileParam(String key,String value){
			return putParam(key, value, ParamType.FILE);
		}
		
		public List<Param> getParams(){
			return mParams;
		}

		@Override
		public String toString() {
			return "ParamPool [mParams=" + mParams + "]";
		}
	}
	
	
	
	/**
	 * 一个参数对象
	 * @author xinjun
	 *
	 */
	public class Param{
		private String key;
		private String value;
		private ParamType type;
		
		public Param(String key, String value,ParamType type) {
			super();
			this.key = key;
			this.value = value;
			this.setType(type);
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public ParamType getType() {
			return type;
		}
		public void setType(ParamType type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return "Param{" +
					"key='" + key + '\'' +
					", value='" + value + '\'' +
					", type=" + type +
					'}';
		}
	}
	
	/**
	 * 参数类型
	 * @author xinjun
	 *
	 */
	public enum ParamType{
		VALUE,FILE
	}
	/**
	 * 返回值类型
	 * @author xinjun
	 *
	 */
	public enum ResultType{
		STRING,IMAGE,FILE
	}
	
	/**
	 * 请求错误代号
	 * @author xinjun
	 *
	 */
	public enum RequestStatus{
		/**
		 * 暂未发起请求
		 */
		INIT,
		/**
		 * 上传本地文件
		 */
		UPLOADING,
		/**
		 * 获取服务器返回字符串
		 */
		DOWNLOADING,
		/**
		 * 请求成功
		 */
		SUCCESS,
		/**
		 * 链接超时
		 */
		CONNECTION_TIME_OUT,
		/**
		 * 无网络
		 */
		NO_NETWORK,
		/**
		 * 服务器内部错误
		 */
		SERVER_ERROR,
		/**
		 * 没有这个请求
		 */
		NO_REQUEST,
		/**
		 * 请求结束
		 */
		COMPLETED
	}
	
	/**
	 * 网络请求的回调
	 * @author xinjun
	 *
	 */
	public static abstract class DataCallBack{
		
		/**
		 * 数据获取到了
		 * @param object 数据
		 */
		public abstract void onDataComming(Object object);
		
		public void onCacheComming(Object object){};
	}
	
	
	//----------------------abstract method---------------------------------------------------------------------------------------------------------
	
	
	/**
	 * 发起网络请求
	 * @param requestInfo
	 */
	public abstract void request(RequestInfo requestInfo);
	/**
	 * 同步发起网络请求
	 * @param requestInfo
	 */
	public abstract Object requestSync(RequestInfo requestInfo);

	/**
	 * 添加一个数据监听器
	 * @param listener 监听器
	 */
	public abstract void addRequestStatusListenner(String requestName,RequestListener listener);

	/**
	 * 通知注册的数据监听器
	 * @param requestName
	 * @param status
	 * @param data
	 */
	public void notifyRequestStatusListenner(String requestName,RequestStatus status,Object data);
}
