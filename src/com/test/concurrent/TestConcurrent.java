package com.test.concurrent;

public class TestConcurrent {

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			final int index = i;
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/

			new Thread() {
				public void run() {
					System.out.println("��" + index + "���̻߳�ȡID===" + IdGenerator.next());
				};
			}.start();
		}
		
	}

}
