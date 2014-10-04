package com.theisenp.registrar.filters;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Unit tests for {@link Filter}
 * 
 * @author patrick.theisen
 */
public class FilterTest {

	@Test
	public void testPass() {
		assertThat(Filter.PASS.filter(int.class)).isTrue();
	}

	@Test
	public void testFail() {
		assertThat(Filter.FAIL.filter(int.class)).isFalse();
	}
}
