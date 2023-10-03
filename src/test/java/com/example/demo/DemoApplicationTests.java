package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.demo.calculator.CalculatorService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

	@Autowired
	private CalculatorService calculatorService;
 
	@Mock
	CalculatorService mockedService;


	@Test
	void contextLoads() {

	}

	@BeforeEach
	void setUp() throws Exception {
		this.mockedService = Mockito.mock(CalculatorService.class);
	}

	@Test
	@DisplayName("Test BooksService calculate method")
	void test() {
		assertEquals( 3f, this.calculatorService.calculate(1, 2, "+"));
		assertEquals( -1f, this.calculatorService.calculate(1, 2, "-"));
		assertEquals( 2f, this.calculatorService.calculate(1, 2, "*"));
		assertEquals( 0.5f, this.calculatorService.calculate(1, 2, "/"));
	}

	@Test
	public void whenNotUseMockAnnotation_thenCorrect() {
		when(this.mockedService.calculate(1, 2, "+")).thenReturn(3f);
		assertEquals(3f, this.mockedService.calculate(1, 2, "+"));
	}


}
