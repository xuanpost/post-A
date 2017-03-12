package com.test.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 哲学家进餐: 五个哲学家围坐在一张圆桌周围，每个哲学家面前都有一盘通心粉。由于通心粉很滑，所以需要两把叉子才能夹住。
 * 相邻两个盘子之间放有一把叉子如下图所示。哲学家的生活中有两种交替活动时段：即吃饭和思考。
 * 当一个哲学家觉得饿了时，他就试图分两次去取其左边和右边的叉子，每次拿一把，但不分次序。 如果成功地得到了两把叉子，就开始吃饭，吃完后放下叉子继续思考。
 * 
 * 用信号量来解决哲学家进餐问题的代码，使用并发工具包中的Semaphore类
 * 
 * @author zx2015
 */
public class SemaphoreCal {

	public static void main(String[] args) {
		String[] names = { "张一", "张二", "张三", "张四", "张五" };
		for (int i = 0, len = names.length; i < len; i++) {
			new Thread(new People(i, names[i])).start();
		}
	}
}

/**
 * 存放线程共享信号量的上下文
 * 
 * @author zx2015
 *
 */
class AppContext {
	// 叉子数量(资源)
	public static final int NUM_OF_FORKS = 5;
	// 哲学家数量(线程)
	public static final int NUM_OF_PHILO = 5;

	// 叉子的信号量
	public static Semaphore[] forks;
	// 哲学家的信号量
	public static Semaphore counter;

	static {
		forks = new Semaphore[NUM_OF_FORKS];
		for (int i = 0, len = forks.length; i < len; i++) {
			// 每个叉子的信号量为1
			forks[i] = new Semaphore(1);
		}

		// 如果有N个哲学家，最多只允许N-1人同时取叉子
		counter = new Semaphore(NUM_OF_PHILO - 1);
	}

	/**
	 * 取得叉子
	 * 
	 * @param index
	 *            第几个哲学家
	 * @param leftFirst
	 *            是否先取得左手的叉子
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
	 * 放回叉子
	 * 
	 * @param index
	 *            第几个哲学家
	 * @param leftFirst
	 *            是否先取得左手的叉子
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
 * 哲学家
 * 
 * @author zx2015
 *
 */
class People implements Runnable {
	// 编号
	private int index;
	// 姓名
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
				System.out.println(name + "正在吃通心粉。。。");
				AppContext.putDownFork(index, leftFirst);
				AppContext.counter.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}