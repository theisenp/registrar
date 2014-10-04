package com.theisenp.registrar.filters;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for {@link ConjunctiveFilter}
 * 
 * @author patrick.theisen
 */
public class ConjunctiveFilterTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void testConstructVarargsEmpty() {
		thrown.expect(IllegalArgumentException.class);
		new ConjunctiveFilter();
	}

	@Test
	public void testConstructVarargs() {
		Filter filter = new ConjunctiveFilter(Filter.PASS, Filter.PASS);
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testConstructCollectionEmpty() {
		thrown.expect(IllegalArgumentException.class);
		new ConjunctiveFilter(new ArrayList<Filter>());
	}

	@Test
	public void testConstructCollection() {
		Filter filter = new ConjunctiveFilter(Arrays.asList(Filter.PASS, Filter.PASS));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testSingleFilterFail() {
		Filter filter = new ConjunctiveFilter(Filter.FAIL);
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testSingleFilterPass() {
		Filter filter = new ConjunctiveFilter(Filter.PASS);
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testMultipleFiltersAllFail() {
		Filter filter = new ConjunctiveFilter(Filter.FAIL, Filter.FAIL);
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testMultipleFiltersSingleFail() {
		Filter filter = new ConjunctiveFilter(Filter.PASS, Filter.FAIL);
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testMultipleFiltersAllPass() {
		Filter filter = new ConjunctiveFilter(Filter.PASS, Filter.PASS);
		assertThat(filter.filter(int.class)).isTrue();
	}
}
