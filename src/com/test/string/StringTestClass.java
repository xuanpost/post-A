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
	 * MAP���ϱ�����keySet()
	 * 
	 * @param map
	 */
	public static void sortMapOne(Map<String, String> map) {
		System.out.println("��һ�֣�ͨ��Map.keySet����key��value");

		for (String key : map.keySet()) {
			System.out.println(key + "��" + map.get(key));
		}
	}

	/**
	 * MAP���ϱ�����iterator()
	 * 
	 * @param map
	 */
	public static void sortMapTow(Map<String, String> map) {
		System.out.println("�ڶ��֣�ͨ��Map.entrySetʹ��iterator����key��value");

		Iterator<Entry<String, String>> entrySet = map.entrySet().iterator();
		while (entrySet.hasNext()) {
			Entry<String, String> entry = entrySet.next();
			System.out.println(entry.getKey() + "��" + entry.getValue());
		}
	}

	/**
	 * MAP���ϱ�����entrySet()
	 * 
	 * @param map
	 */
	public static void sortMapThree(Map<String, String> map) {
		System.out.println("�����֣�ͨ��Map.entrySet����key��value");

		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "��" + entry.getValue());
		}
	}

	/**
	 * ����1-2+3-4+5-6+7...+n
	 * 
	 * @param n
	 */
	public static void sumToatl(int n) {
		int sum = 0;
		if (n < 1) {
			System.out.println("���������д�");
		} else {
			for (int i = 1; i <= n; i++) {
				if (i % 2 == 0) {
					sum = sum - i;
				} else {
					sum = sum + i;
				}
			}
			System.out.println("�������֣�" + n + ";������Ϊ��" + sum);
		}
	}

	/**
	 * ����1-2+3-4+5-6+7...+n����������
	 * 
	 * @param n
	 */
	public static void sumToatlTop(int n) {
		int sum = 0;
		if (n < 1) {
			System.out.println("���������д�");
		} else {
			if (n % 2 == 0) {
				sum = -(n / 2);
			} else {
				sum = n - (n - 1) / 2;
			}
			System.out.println("�������ܡ��������֣�" + n + ";������Ϊ��" + sum);
		}
	}

}
