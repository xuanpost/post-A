package com.test.concurrent;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ������-������: һ�����������ߴ���ĳЩ�������������ڻ�����������У�һ�����������߻�Ӷ����л����Щ���������֮��
 * ����Ļ�������������ٽ���Դ��������������з�����ʱ��������ᱻ������
 * �������������Ϊ�յ�ʱ�������߻ᱻ�����������ߺ������ߵĵ�����ͨ�������໥�����ź���ɵġ�
 * 
 * BlockingQueueʵ��
 * 
 * @author zx2015
 */
public class BlockingQueueCal {

	public static void main(String[] args) {
		BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>(10);
		ExecutorService es = Executors.newFixedThreadPool(5);

		// ������
		for (int i = 0; i < 2; i++) {
			es.execute(new Producer(taskQueue));
		}
		// ������
		for (int i = 0; i < 3; i++) {
			es.execute(new Consumer(taskQueue));
		}
	}
}

/**
 * ��������
 * 
 * @author zx2015
 *
 */
class Task {
	// ������
	private String id;

	public Task() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public String toString() {
		return "Task[" + id + "]";
	}
}

/**
 * ������
 * 
 * @author zx2015
 *
 */
class Consumer implements Runnable {
	private BlockingQueue<Task> taskQueue;

	public Consumer(BlockingQueue<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Task task = taskQueue.take();
				System.out.println("Consumer[" + Thread.currentThread().getName() + "] take " + task);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * ������
 * 
 * @author zx2015
 *
 */
class Producer implements Runnable {
	private BlockingQueue<Task> taskQueue;

	public Producer(BlockingQueue<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Task task = new Task();
				taskQueue.put(task);
				System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + task);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}