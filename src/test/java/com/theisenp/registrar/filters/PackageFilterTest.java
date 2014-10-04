package com.theisenp.registrar.filters;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

/**
 * Unit tests for {@link PackageFilter}
 * 
 * @author patrick.theisen
 */
public class PackageFilterTest {

	@Test
	public void testDifferentPackage() {
		Filter filter = new PackageFilter(Integer.class.getPackage());
		assertThat(filter.filter(List.class)).isFalse();
	}

	@Test
	public void testSamePackage() {
		Filter filter = new PackageFilter(Integer.class.getPackage());
		assertThat(filter.filter(Float.class)).isTrue();
	}
}
