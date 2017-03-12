package com.test.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IDÉú³ÉÆ÷
 * @author zx2015
 *
 */
public class IdGenerator {

	private final static AtomicLong sequenceNumber = new AtomicLong(100);
	
	public static long next() {
		return sequenceNumber.getAndIncrement();
	}
}
