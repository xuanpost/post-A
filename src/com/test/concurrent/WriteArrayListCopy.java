package com.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CopyOnWriteArrayList线程安全
 * 
 * @author zx2015
 *
 */
public class WriteArrayListCopy {

	public static void main(String[] args) {
		// List<Double> list = new ArrayList<>();
		List<Double> list = new CopyOnWriteArrayList<>();

		ExecutorService fixThread = Executors.newFixedThreadPool(5);
		fixThread.execute(new AddThread(list));
		fixThread.execute(new AddThread(list));
		fixThread.execute(new AddThread(list));
		fixThread.execute(new AddThread(list));
		fixThread.execute(new AddThread(list));
		fixThread.shutdown();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("List集合size==" + list.size());

	}

}

class AddThread implements Runnable {
	private List<Double> list;

	public AddThread(List<Double> list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			list.add(Math.random());
		}
	}
}
