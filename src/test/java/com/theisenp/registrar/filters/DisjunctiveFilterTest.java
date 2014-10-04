package com.theisenp.registrar.filters;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		Filter filter = new DisjunctiveFilter(mockFilter(true), mockFilter(true));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testConstructCollectionEmpty() {
		thrown.expect(IllegalArgumentException.class);
		new DisjunctiveFilter(new ArrayList<Filter>());
	}

	@Test
	public void testConstructCollection() {
		Filter filter = new DisjunctiveFilter(Arrays.asList(mockFilter(true), mockFilter(true)));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testSingleFilterFail() {
		Filter filter = new DisjunctiveFilter(mockFilter(false));
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testSingleFilterPass() {
		Filter filter = new DisjunctiveFilter(mockFilter(true));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testMultipleFiltersAllFail() {
		Filter filter = new DisjunctiveFilter(mockFilter(false), mockFilter(false));
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testMultipleFiltersSingleFail() {
		Filter filter = new DisjunctiveFilter(mockFilter(true), mockFilter(false));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testMultipleFiltersAllPass() {
		Filter filter = new DisjunctiveFilter(mockFilter(true), mockFilter(true));
		assertThat(filter.filter(int.class)).isTrue();
	}

	/**
	 * @param pass
	 * @return A {@link Filter} that always passes or fails
	 */
	private static Filter mockFilter(boolean pass) {
		Filter result = mock(Filter.class);
		when(result.filter(any(Class.class))).thenReturn(pass);
		return result;
	}
}
