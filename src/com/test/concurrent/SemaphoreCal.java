package com.test.concurrent;

import java.util.concurrent.Semaphore;

/**
 * ��ѧ�ҽ���: �����ѧ��Χ����һ��Բ����Χ��ÿ����ѧ����ǰ����һ��ͨ�ķۡ�����ͨ�ķۺܻ���������Ҫ���Ѳ��Ӳ��ܼ�ס��
 * ������������֮�����һ�Ѳ�������ͼ��ʾ����ѧ�ҵ������������ֽ���ʱ�Σ����Է���˼����
 * ��һ����ѧ�Ҿ��ö���ʱ��������ͼ������ȥȡ����ߺ��ұߵĲ��ӣ�ÿ����һ�ѣ������ִ��� ����ɹ��صõ������Ѳ��ӣ��Ϳ�ʼ�Է����������²��Ӽ���˼����
 * 
 * ���ź����������ѧ�ҽ�������Ĵ��룬ʹ�ò������߰��е�Semaphore��
 * 
 * @author zx2015
 */
public class SemaphoreCal {

	public static void main(String[] args) {
		String[] names = { "��һ", "�Ŷ�", "����", "����", "����" };
		for (int i = 0, len = names.length; i < len; i++) {
			new Thread(new People(i, names[i])).start();
		}
	}
}

/**
 * ����̹߳����ź�����������
 * 
 * @author zx2015
 *
 */
class AppContext {
	// ��������(��Դ)
	public static final int NUM_OF_FORKS = 5;
	// ��ѧ������(�߳�)
	public static final int NUM_OF_PHILO = 5;

	// ���ӵ��ź���
	public static Semaphore[] forks;
	// ��ѧ�ҵ��ź���
	public static Semaphore counter;

	static {
		forks = new Semaphore[NUM_OF_FORKS];
		for (int i = 0, len = forks.length; i < len; i++) {
			// ÿ�����ӵ��ź���Ϊ1
			forks[i] = new Semaphore(1);
		}

		// �����N����ѧ�ң����ֻ����N-1��ͬʱȡ����
		counter = new Semaphore(NUM_OF_PHILO - 1);
	}

	/**
	 * ȡ�ò���
	 * 
	 * @param index
	 *            �ڼ�����ѧ��
	 * @param leftFirst
	 *            �Ƿ���ȡ�����ֵĲ���
	 * @throws InterruptedException
	 */
	public static void putOnFork(int index, boolean leftFirst) throws InterruptedException {
		if (leftFirst) {
			forks[index].acquire();
			forks[(index + 1) % NUM_OF_PHILO].acquire();
		} else {
			forks[(index + 1) % NUM_OF_PHILO].acquire();
			forks[index].acquire();
		}
	}

	/**
	 * �Żز���
	 * 
	 * @param index
	 *            �ڼ�����ѧ��
	 * @param leftFirst
	 *            �Ƿ���ȡ�����ֵĲ���
	 * @throws InterruptedException
	 */
	public static void putDownFork(int index, boolean leftFirst) throws InterruptedException {
		if (leftFirst) {
			forks[index].release();
			forks[(index + 1) % NUM_OF_PHILO].release();
		} else {
			forks[(index + 1) % NUM_OF_PHILO].release();
			forks[index].release();
		}
	}
}

/**
 * ��ѧ��
 * 
 * @author zx2015
 *
 */
class People implements Runnable {
	// ���
	private int index;
	// ����
	private String name;

	public People(int index, String name) {
		this.index = index;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			try {
				AppContext.counter.acquire();
				boolean leftFirst = (index % 2 == 0);
				AppContext.putOnFork(index, leftFirst);
				System.out.println(name + "���ڳ�ͨ�ķۡ�����");
				AppContext.putDownFork(index, leftFirst);
				AppContext.counter.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}