package com.theisenp.registrar.filters;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for {@link DisjunctiveFilter}
 * 
 * @author patrick.theisen
 */
public class DisjunctiveFilterTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void testConstructVarargsEmpty() {
		thrown.expect(IllegalArgumentException.class);
		new DisjunctiveFilter();
	}

	@Test
	public void testConstructVarargs() {
		Filter filter = new DisjunctiveFilter(Filter.PASS, Filter.PASS);
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testConstructCollectionEmpty() {
		thrown.expect(IllegalArgumentException.class);
		new DisjunctiveFilter(new ArrayList<Filter>());
	}

	@Test
	public void testConstructCollection() {
		Filter filter = new DisjunctiveFilter(Arrays.asList(Filter.PASS, Filter.PASS));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testSingleFilterFail() {
		Filter filter = new DisjunctiveFilter(Filter.FAIL);
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testSingleFilterPass() {
		Filter filter = new DisjunctiveFilter(Filter.PASS);
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testMultipleFiltersAllFail() {
		Filter filter = new DisjunctiveFilter(Filter.FAIL, Filter.FAIL);
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testMultipleFiltersSingleFail() {
		Filter filter = new DisjunctiveFilter(Filter.PASS, Filter.FAIL);
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testMultipleFiltersAllPass() {
		Filter filter = new DisjunctiveFilter(Filter.PASS, Filter.PASS);
		assertThat(filter.filter(int.class)).isTrue();
	}
}
