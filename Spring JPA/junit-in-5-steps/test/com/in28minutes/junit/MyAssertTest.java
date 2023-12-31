package com.in28minutes.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MyAssertTest {

	List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");

	@Test
	void test() {
		boolean test = todos.contains("AWS"); // result
		boolean test2 = todos.contains("GCP"); // result

		// assertEquals(true, test);
		assertTrue(test);
		assertFalse(test2);
		
		// assertNull, assertNotNull

		assertArrayEquals(new int[] { 1, 2 }, new int[] { 2, 1 });

		assertEquals(3, todos.size());

	}

}
