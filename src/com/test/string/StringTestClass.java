package com.test.string;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class StringTestClass {

	public static void main(String[] args) {

		// sumToatl(998);
		// sumToatlTop(998);

		// StringBuffer st1 = new StringBuffer();

		// Map map = new HashMap();
		// map.put("1", "A");
		// map.put("2", "B");
		// map.put("3", "C");
		// map.put("4", "D");
		// sortMapOne(map);
		// sortMapTow(map);
		// sortMapThree(map);

		for (int i = 0; i < 1000; i++) {
			new Thread() {
				public void run() {
					StringTestClass.countInt();
				}
			}.start();
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(StringTestClass.count);

	}

	public static int count = 0;

	public synchronized static void countInt() {
		count++;
	}

	/**
	 * MAP集合遍历：keySet()
	 * 
	 * @param map
	 */
	public static void sortMapOne(Map<String, String> map) {
		System.out.println("第一种：通过Map.keySet遍历key和value");

		for (String key : map.keySet()) {
			System.out.println(key + "：" + map.get(key));
		}
	}

	/**
	 * MAP集合遍历：iterator()
	 * 
	 * @param map
	 */
	public static void sortMapTow(Map<String, String> map) {
		System.out.println("第二种：通过Map.entrySet使用iterator遍历key和value");

		Iterator<Entry<String, String>> entrySet = map.entrySet().iterator();
		while (entrySet.hasNext()) {
			Entry<String, String> entry = entrySet.next();
			System.out.println(entry.getKey() + "：" + entry.getValue());
		}
	}

	/**
	 * MAP集合遍历：entrySet()
	 * 
	 * @param map
	 */
	public static void sortMapThree(Map<String, String> map) {
		System.out.println("第三种：通过Map.entrySet遍历key和value");

		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "：" + entry.getValue());
		}
	}

	/**
	 * 计算1-2+3-4+5-6+7...+n
	 * 
	 * @param n
	 */
	public static void sumToatl(int n) {
		int sum = 0;
		if (n < 1) {
			System.out.println("输入数字有错！");
		} else {
			for (int i = 1; i <= n; i++) {
				if (i % 2 == 0) {
					sum = sum - i;
				} else {
					sum = sum + i;
				}
			}
			System.out.println("输入数字：" + n + ";计算结果为：" + sum);
		}
	}

	/**
	 * 计算1-2+3-4+5-6+7...+n。考虑性能
	 * 
	 * @param n
	 */
	public static void sumToatlTop(int n) {
		int sum = 0;
		if (n < 1) {
			System.out.println("输入数字有错！");
		} else {
			if (n % 2 == 0) {
				sum = -(n / 2);
			} else {
				sum = n - (n - 1) / 2;
			}
			System.out.println("考虑性能。输入数字：" + n + ";计算结果为：" + sum);
		}
	}

}
