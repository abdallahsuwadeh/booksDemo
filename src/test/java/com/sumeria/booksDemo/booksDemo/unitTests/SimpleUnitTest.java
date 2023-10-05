package com.sumeria.booksDemo.booksDemo.unitTests;

import com.sumeria.booksDemo.booksDemo.services.BooksServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleUnitTest {

	private int x;

	@BeforeEach
	void init(){
		x = 1+1;
	}

	@Test
	void equalsTwo() {
		assertThat(x).isEqualTo(2);
	}

	@Test
	void doesNotEqualThree() {
		assertThat(x).isNotEqualTo(3);
	}

	@AfterEach
	void destroy(){
		x = 0;
	}
}
