package springbook.learningtest.template;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calculator;
	String numFilepah;
	
	@Before public void setUp() {
		this.calculator = new Calculator();
		this.numFilepah = getClass().getResource("numbers.txt").getPath();
	}
	
	
	@Test
	public void sumOfNumbers() throws IOException {
		assertThat(calculator.calcSum(this.numFilepah), is(10));
	}
	
	@Test
	public void multiplyIfNumbers() throws IOException {
		assertThat(calculator.calcMultiply(this.numFilepah), is(24));
	}
}
