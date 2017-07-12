package com.app.presenter;

import com.app.presenter.IRequestPresenter.ParamPool;
import com.app.presenter.IRequestPresenter.RequestStatus;

/**
 * 数据请求与分发调度的业务类
 * @author xinjun
 *
 */
public interface IDataPresenter extends IPresenter {

	//----------------------type---------------------------------------------------------------------------------------------------------
	
	public class RequestDataCommand{
		
		public static final int TYPE_SINGLE_OBJECT=0;
		public static final int TYPE_LIST_OBJECT=1;
		
		/**
		 * 某一次请求的标识
		 */
		private String requestName;
		/**
		 * 组成最小单位的数据类型
		 * 如果目标是一个对象,那就是这个对象的类型
		 * 如果目标是一个List,那这个就是List条目的类型
		 */
		private Class<?> atomicType;
		/**
		 * 请求的是单个对象还是list中的元素或者整个list?
		 */
		private int type;
		/**
		 * 为-1时请求整个list
		 * 为大于0的值时请求的是list中的某一个索引下的对象
		 */
		private int listIndex;
		
		/**
		 * 数据到来时的回调
		 */
		private DataInnerCallBack mCallBack;
		
		
		/**
		 * 可以附带数据
		 */
		private Object tag;
		
		public RequestDataCommand(String requestName,Class<?> atomicType,DataInnerCallBack dataCallBack) {
			this.setRequestName(requestName);
			this.setAtomicType(atomicType);
			this.setCallBack(dataCallBack);
			this.type=TYPE_SINGLE_OBJECT;
			this.listIndex=-1;
		}
		//-----------------------setter----------------------
		public void setAtomicType(Class<?> atomicType) {
			this.atomicType = atomicType;
		}
		public RequestDataCommand setType(int type){
			this.type=type;
			return this;
		}
		public RequestDataCommand setIndex(int listIndex){
			this.listIndex=listIndex;
			return this;
		}
		public void setCallBack(DataInnerCallBack mCallBack) {
			this.mCallBack = mCallBack;
		}
		public void setRequestName(String requestName) {
			this.requestName = requestName;
		}
		public RequestDataCommand setTag(Object tag) {
			this.tag = tag;
			return this;
		}
		//-----------------------getter----------------------
		public int getType(){
			return type;
		}
		public int getIndex(){
			return listIndex;
		}
		public Class<?> getAtomicType() {
			return atomicType;
		}
		public DataInnerCallBack getCallBack() {
			return mCallBack;
		}
		public String getRequestName() {
			return requestName;
		}
		public Object getTag() {
			return tag;
		}
	}
	
	/**
	 * 内部请求方式:替换,追加
	 * @author xinjun
	 *
	 */
	public enum Option{
		REPLICE,APPEND
	}
	
	
	/**
	 * 这个回调是内部回调,由LayoutCreater创建时向DataPresenter发起的内部请求,当内部请求被响应后会执行此回调
	 * @author xinjun
	 *
	 */
	public static abstract class DataInnerCallBack{
		/**
		 * 数据获取到了
		 * @param command 发送的命令
		 * @param data 数据
		 */
		public abstract void onDataComming(RequestDataCommand command,Object data);
	}
	
	
	/**
	 * 请求监听器
	 * @author xinjun
	 *
	 */
	public interface RequestListener{
		
		/**
		 * 请求状态回调方法
		 * @param status
		 * @param msg 上传参数时类型为com.app.presenter.impl.request.CustomMultipartEntity.ProgressListener
		 * 			      下载文件时为xxx类型
		 * 			      其他错误状态均为java.lang.String类型
		 */
		public void onStatusChanged(RequestStatus status,Object msg);
		
	}
	/**
	 * 数据更新监听器
	 * @author xinjun
	 *
	 */
	public interface DataChangedHandler{
		/**
		 * 当数据变化时会回调这个接口
		 * @param fieldName 变化的字段名称
		 */
		public void onDataChanged(String fieldName);
	}
	
	//----------------------abstract method---------------------------------------------------------------------------------------------------------
	
	/**
	 * 发送一个内部请求数据的命令,这个数据可能没有(发起网络请求),可能已经有了(内部包装代理对象)
	 * @param command 命令
	 */
	public abstract void sendRequestDataCommand(RequestDataCommand command);
	 
	/**
	 * 添加一个数据更新处理器
	 * @param requestName 请求名称
	 * @param attentionDataClass 关注的数据类型
	 */
	public abstract void addNotifyHandler(String requestName,Class<?> attentionDataClass,DataChangedHandler handler); 
	
	/**
	 * 发起一个网络数据的请求
	 * @param requestName 配置的请求名称
	 * @param option 请求附加操作,目前支持替换和追加
	 * @param paramPool 参数列表池
	 */
	public abstract void request(String requestName,Option option,ParamPool paramPool);
	
	

}
