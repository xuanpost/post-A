package com.test.patter.singleton;

/**
 * ����ģʽ
 * @author zx2015
 *
 */
public class Singleton {
	
	/**
	 * ����˽�о�̬ʵ������ֹ�����ã��˴���ֵΪnull��Ŀ����ʵ���ӳټ���
	 */
	private static Singleton singleton = null;

	/**
	 * ˽�й��췽������ֹ��ʵ����
	 */
	private Singleton() {}
	
	/**
	 * 1.��̬���̷���������ʵ��
	 * @return
	 */
	public static Singleton getSingleton() {
		if (null == singleton) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	/**
	 * 2.��̬���̷���������ʵ�����̰߳�ȫ���������synchronized
	 * @return
	 */
	public static synchronized Singleton getSingletonSyn() {
		if (null == singleton) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	/**
	 * 3.��̬���̷���������ʵ�����̰߳�ȫ��ֻ�ڴ�������ʱ���synchronized
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
	 * 4.��̬���̷���������ʵ�����̰߳�ȫ��������������ȡ�������롣ֻ�贴������synchronized
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
	 * 5.����ģʽ����̬�ڲ��෽��ʵ��
	 */
	private static class SingletonFactory {
		private static final Singleton INTANCE = new Singleton();
	}
	public static Singleton getSingletonIntance() {
		return SingletonFactory.INTANCE;
	}
	
	
	/**
	 * 6.����ģʽ������ClassLoader���Ʊ����˶��̵߳�ͬ������
	 */
	private static Singleton singletonLoder = new Singleton();
	
	public static Singleton getSingletonLoder() {
		return singletonLoder;
	}
	
}