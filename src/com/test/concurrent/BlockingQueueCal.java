package com.test.concurrent;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者-消费者: 一个或多个生产者创建某些工作并将其置于缓冲区或队列中，一个或多个消费者会从队列中获得这些工作并完成之。
 * 这里的缓冲区或队列是临界资源。当缓冲区或队列放满的时候，生产这会被阻塞；
 * 而缓冲区或队列为空的时候，消费者会被阻塞。生产者和消费者的调度是通过二者相互交换信号完成的。
 * 
 * BlockingQueue实现
 * 
 * @author zx2015
 */
public class BlockingQueueCal {

	public static void main(String[] args) {
		BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>(10);
		ExecutorService es = Executors.newFixedThreadPool(5);

		// 生产者
		for (int i = 0; i < 2; i++) {
			es.execute(new Producer(taskQueue));
		}
		// 消费者
		for (int i = 0; i < 3; i++) {
			es.execute(new Consumer(taskQueue));
		}
	}
}

/**
 * 工作任务
 * 
 * @author zx2015
 *
 */
class Task {
	// 任务编号
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
 * 消费者
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
 * 生产者
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