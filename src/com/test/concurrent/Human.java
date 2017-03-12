package com.test.concurrent;

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

public class Human {

	public static void main(String[] args) throws Exception {
		try {
			try {
				throw new Sneeze();
			} catch (Annoyance a) {
				System.out.println("Caught Annoyance Exception");
				throw a;
			}
		} catch (Sneeze s) {
			System.out.println("Caught Sneeze Exception");
			return;
		} finally {
			System.out.println("Hello World!");
		}
	}
}
