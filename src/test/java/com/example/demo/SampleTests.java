package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleTests {
    	private Samples samples;

	float[] arr = { 1, 2, 3, 4, 5, 1.3f, 2.3f, 3.3f, 4.3f, 5.3f };

	@BeforeEach
	void setUp() throws Exception {
		this.samples = new Samples();
	}

	@Test
	@DisplayName("Test getArea in happy path")
	void testGetArea() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i; j++) {
				assertEquals(arr[i] * arr[j], samples.getArea(arr[i], arr[j]));
			}
		}
		// assertEquals(6, samples.getArea(2, 3));
	}

	@Test
	@DisplayName("Test getArea with at least one negative value")
	void testGetArea2() {
		try{
		assertEquals(0, samples.getArea(-1, 2));
		}
		catch(IllegalArgumentException e){
			assertEquals("Negative sizes are not allowed", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test getArea with at least one zero value")
	void testGetArea3() {
		try{
		assertEquals(0, samples.getArea(0, 2));
		}
		catch(IllegalArgumentException e){
			assertEquals("Zero sizes are not allowed", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test getArea with both values equal to 10000")
	void testGetArea4() {
		assertEquals(10000 * 10000, samples.getArea(10000, 10000));
	}

	@Test
	@DisplayName("Test getArea with both values greater than 10000")
	void testGetArea5() {
		try{
		assertEquals(0, samples.getArea(1, 10001));
		}
		catch(IllegalArgumentException e){
			assertEquals("Sizes greater than 10000 are not allowed", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test getArea for at least one value infinity")
	void testGetArea6() {
		try{
		assertEquals(0, samples.getArea(1, Float.POSITIVE_INFINITY));
		}
		catch(IllegalArgumentException e){
			assertEquals("Sizes greater than 10000 are not allowed", e.getMessage());
		}
	}
}
