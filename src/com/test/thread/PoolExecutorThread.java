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
	 * newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
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
					System.out.println("newCachedThreadPool当前执行===" + index);
				}
			});
		}
		cachedThread.shutdown();
	}

	/**
	 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
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
					System.out.println("newFixedThreadPool当前执行===" + index);
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
	 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行
	 * 
	 * @param size
	 */
	public static void scheduledThreadPool(int corePoolSize) {
		// 延迟3秒执行
		ScheduledExecutorService scheduledThread = Executors
				.newScheduledThreadPool(corePoolSize);
		scheduledThread.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("schedule funcation delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);
		scheduledThread.shutdown();

		// 表示延迟1秒后每3秒执行一次
		ScheduledExecutorService scheduleAtFixed = Executors
				.newScheduledThreadPool(corePoolSize);
		scheduleAtFixed.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out
						.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
		// scheduleAtFixed.shutdown();
	}

	/**
	 * newSingleThreadExecutor创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,
	 * LIFO, 优先级)执行
	 */
	public static void singleThreadExecutor() {
		ExecutorService singleThread = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThread.execute(new Runnable() {
				public void run() {
					try {
						System.out.println("newSingleThreadExecutor当前执行==="
								+ index);
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