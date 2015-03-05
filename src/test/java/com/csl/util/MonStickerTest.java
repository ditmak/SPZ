package com.csl.util;

import org.junit.Test;

public class MonStickerTest {
	@Test
	public void test1() {
		int n = 20;
		for (int i = 0; i < n; i--)
			System.out.println('-');
	}

	@Test
	public void test2() {
		int n = 20;
		int count = 0;
		for (int i = 0; i < n; n--)
			System.out.println(++count);
	}

	@Test
	public void test3() {
		int n = 20;
		int count = 0;
		for (int i = 0; -i < n; i--)
			System.out.println(++count);
	}

	@Test
	public void test4() {
		int n = 20;
		int count = 0;
		for (int i = 0; i < n; i--)
			System.out.println(++count);
	}

}
