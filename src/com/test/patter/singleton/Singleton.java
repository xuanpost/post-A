package com.test.patter.singleton;

/**
 * 单例模式
 * @author zx2015
 *
 */
public class Singleton {
	
	/**
	 * 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
	 */
	private static Singleton singleton = null;

	/**
	 * 私有构造方法，防止被实例化
	 */
	private Singleton() {}
	
	/**
	 * 1.静态工程方法，创建实例
	 * @return
	 */
	public static Singleton getSingleton() {
		if (null == singleton) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	/**
	 * 2.静态工程方法，创建实例。线程安全，方法添加synchronized
	 * @return
	 */
	public static synchronized Singleton getSingletonSyn() {
		if (null == singleton) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	/**
	 * 3.静态工程方法，创建实例。线程安全，只在创建对象时添加synchronized
	 * @return
	 */
	public static Singleton getSingletonSynObj() {
		if (null == singleton) {
			synchronized (singleton) {
				if (null == singleton) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	
	/**
	 * 4.静态工程方法，创建实例。线程安全，创建方法、获取方法分离。只需创建方法synchronized
	 * @return
	 */
	private static synchronized void singletonInit() {
		if (null == singleton) {
			singleton = new Singleton();
		}
	}
	public static Singleton getSingletonInit() {
		if (null == singleton) {
			singletonInit();
		}
		return singleton;
	}
	
	
	/**
	 * 5.单例模式：静态内部类方法实现
	 */
	private static class SingletonFactory {
		private static final Singleton INTANCE = new Singleton();
	}
	public static Singleton getSingletonIntance() {
		return SingletonFactory.INTANCE;
	}
	
	
	/**
	 * 6.饿汉模式，基于ClassLoader机制避免了多线程的同步问题
	 */
	private static Singleton singletonLoder = new Singleton();
	
	public static Singleton getSingletonLoder() {
		return singletonLoder;
	}
	
}