package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {
	private MyMath math = new MyMath();

	@Test
	void calculateSum_ThreeMemberArray() {
		// Absence of failure is success: 실패조건 걸지마
		// Test condition of Assert: 이걸로 해라

		assertEquals(5, math.calculateSum(new int[] { 1, 2, 3 })); // 답은 맞지만 기대값이랑 달라서 오류냄

	}

	@Test
	void calculate_ZeroLengthArray() {
		assertEquals(0, math.calculateSum(new int[] {})); // 정답(오류X)

	}

}
