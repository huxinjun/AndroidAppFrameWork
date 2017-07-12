package com.app.presenter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 实体代理管理类
 * @author xinjun
 *
 */
public interface IEntityProxyPresenter extends IPresenter {

	/**
	 * 一个对象的代理信息
	 * @author xinjun
	 *
	 */
	public class BeanProxyInfo{
		/**源对象类型*/
		public Class<?> originalClass;
		/**源对象*/
		public Object original;
		/**源对象代理*/
		public Object proxy;
		/**每一个字段类型为对象的BeanProxyInfo描述对象*/
		public List<BeanProxyInfo> childs=new ArrayList<BeanProxyInfo>();
		
		
		//1.获取数据的handler://主要负责getXXX时返回代理对象或者执行原数据的方法
		private MethodInterceptor obtainHandler;
		//2.修改数据的handler://主要负责数据更新后通知,不由JavaBeanProxy创建,是别的地方set的
		private List<MethodInterceptor> notifyHandlers=new ArrayList<MethodInterceptor>();
		
		public MethodInterceptor[] handlers=new MethodInterceptor[]{
			new MethodInterceptor(){

				public Object intercept(Object arg0, Method arg1,
						Object[] arg2, MethodProxy arg3) throws Throwable {
					if(obtainHandler!=null)
						return obtainHandler.intercept(arg0,arg1,arg2,arg3);
					return null;
								
				}
				
			},
			new MethodInterceptor(){

				public Object intercept(Object arg0, Method arg1,
						Object[] arg2, MethodProxy arg3) throws Throwable {
					Object result = null;
					for(MethodInterceptor notifyHandler:notifyHandlers){
						if(notifyHandler!=null)
							result = notifyHandler.intercept(arg0,arg1,arg2,arg3);
					}
					return result;
				}
				
			}
			
		};
		
		public MethodInterceptor getObtainHandler() {
			return obtainHandler;
		}
		
		public void setObtainHandler(MethodInterceptor obtainHandler) {
			this.obtainHandler=obtainHandler;
			
		}
		
		public List<MethodInterceptor> getNotifyHandlers() {
			return notifyHandlers;
		}
		
		public void addNotifyHandler(MethodInterceptor notifyHandler) {
			this.notifyHandlers.add(notifyHandler);
		}
		
	}
	
	/**
	 * 创建一个javabean的树形代理
	 * @param clazz javabean类型
	 * @param bean 数据
	 * @return 代理描述类
	 */
	public BeanProxyInfo creatJavaProxy(Class<?> clazz,Object bean);

	/**
	 * 在一个代理描述对象中寻找某个代理对象所对应的描述对象
	 * @param root 根描述对象
	 * @param proxy 代理类
	 * @return
	 */
	public BeanProxyInfo findJavaBeanProxy(BeanProxyInfo root,Object proxy);
}
