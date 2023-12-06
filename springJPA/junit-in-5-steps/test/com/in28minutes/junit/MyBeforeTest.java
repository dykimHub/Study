package com.in28minutes.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

public class MyBeforeTest {
	
	// before(after)all은 모든 클래스가 실행되기전(후) 실행하기 때문에 static 
	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("BeforeEach");
	}
	
	
	@Test
	void test1() {
		System.out.println("test1");
	}

	@Test
	void test2() {
		System.out.println("test2");
	}

	@Test
	void test3() {
		System.out.println("test3");
	}
	
	@AfterEach
	void AfterEach() {
		System.out.println("AfterEach");
	}
	
	@AfterAll
	static void AfterAll() {
		System.out.println("AfterAll");
	}
	

}
