package com.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoolExecutorThread {

	public static void main(String[] args) {

		// cachedThreadPool();
		// fixedThreadPool(3);
		// scheduledThreadPool(5);
		singleThreadExecutor();

	}

	/**
	 * newCachedThreadPool ����һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̡߳�
	 */
	public static void cachedThreadPool() {
		ExecutorService cachedThread = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			cachedThread.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("newCachedThreadPool��ǰִ��===" + index);
				}
			});
		}
		cachedThread.shutdown();
	}

	/**
	 * newFixedThreadPool ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ�
	 * 
	 * @param size
	 */
	public static void fixedThreadPool(int size) {
		ExecutorService fixedThread = Executors.newFixedThreadPool(size);

		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThread.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("newFixedThreadPool��ǰִ��===" + index);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		fixedThread.shutdown();
	}

	/**
	 * newScheduledThreadPool ����һ�������̳߳أ�֧�ֶ�ʱ������������ִ��
	 * 
	 * @param size
	 */
	public static void scheduledThreadPool(int corePoolSize) {
		// �ӳ�3��ִ��
		ScheduledExecutorService scheduledThread = Executors.newScheduledThreadPool(corePoolSize);
		scheduledThread.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("schedule funcation delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);
		scheduledThread.shutdown();

		// ��ʾ�ӳ�1���ÿ3��ִ��һ��
		ScheduledExecutorService scheduleAtFixed = Executors.newScheduledThreadPool(corePoolSize);
		scheduleAtFixed.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
		// scheduleAtFixed.shutdown();
	}

	/**
	 * newSingleThreadExecutor����һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ�����񣬱�֤����������ָ��˳��(FIFO,
	 * LIFO, ���ȼ�)ִ��
	 */
	public static void singleThreadExecutor() {
		ExecutorService singleThread = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThread.execute(new Runnable() {
				public void run() {
					try {
						System.out.println("newSingleThreadExecutor��ǰִ��==="+index);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		singleThread.shutdown();
	}

}
