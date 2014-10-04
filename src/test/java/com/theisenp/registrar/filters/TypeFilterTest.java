package com.theisenp.registrar.filters;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Unit tests for {@link TypeFilter}
 * 
 * @author patrick.theisen
 */
public class TypeFilterTest {

	@Test
	public void testUnrelated() {
		Filter filter = new TypeFilter(int.class);
		assertThat(filter.filter(Mock.class)).isFalse();
	}

	@Test
	public void testExact() {
		Filter filter = new TypeFilter(Mock.class);
		assertThat(filter.filter(Mock.class)).isTrue();
	}

	@Test
	public void testInterface() {
		Filter filter = new TypeFilter(MockInterface.class);
		assertThat(filter.filter(Mock.class)).isTrue();
	}

	@Test
	public void testSub() {
		Filter filter = new TypeFilter(MockSuper.class);
		assertThat(filter.filter(Mock.class)).isTrue();
	}

	@Test
	public void testSuper() {
		Filter filter = new TypeFilter(MockSub.class);
		assertThat(filter.filter(Mock.class)).isFalse();
	}

	// @formatter:off
	private static interface MockInterface {}
	private static class MockSuper implements MockInterface {}
	private static class Mock extends MockSuper {}
	private static class MockSub extends Mock {}
	// @formatter:on
}
