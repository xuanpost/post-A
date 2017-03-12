package com.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * �߳�ͬ��������
 * 
 * @author zx2015
 *
 */
public class LockThread {

	class Bank {

		private int account = 100;
		// ��Ҫ���������
		private Lock lock = new ReentrantLock();

		public int getAccount() {
			return account;
		}

		// ���ﲻ����Ҫsynchronized
		public void save(int money) {
			lock.lock();
			try {
				account += money;
			} finally {
				lock.unlock();
			}
		}
	}

	class NewThread implements Runnable {
		private Bank bank;

		public NewThread(Bank bank) {
			this.bank = bank;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				// bank.saveTo(10);
				bank.save(10);
				System.out.println(i + "�˻����Ϊ��" + bank.getAccount());
			}
		}

	}

	/**
	 * �����̣߳������ڲ���
	 */
	public void useThread() {
		Bank bank = new Bank();
		NewThread new_thread = new NewThread(bank);
		System.out.println("�߳�1");
		Thread thread1 = new Thread(new_thread);
		thread1.start();
		System.out.println("�߳�2");
		Thread thread2 = new Thread(new_thread);
		thread2.start();
	}

	/**
	 * main����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LockThread st = new LockThread();
		st.useThread();
	}

}
