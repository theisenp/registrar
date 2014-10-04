package com.theisenp.registrar.filters;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

/**
 * Unit tests for {@link ConjunctiveFilter}
 * 
 * @author patrick.theisen
 */
public class ConjunctiveFilterTest {

	@Test
	public void testConstructVarargs() {
		Filter filter = new ConjunctiveFilter(mockFilter(true), mockFilter(true));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testConstructCollection() {
		Filter filter = new ConjunctiveFilter(Arrays.asList(mockFilter(true), mockFilter(true)));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testSingleFilterFail() {
		Filter filter = new ConjunctiveFilter(mockFilter(false));
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testSingleFilterPass() {
		Filter filter = new ConjunctiveFilter(mockFilter(true));
		assertThat(filter.filter(int.class)).isTrue();
	}

	@Test
	public void testMultipleFiltersAllFail() {
		Filter filter = new ConjunctiveFilter(mockFilter(false), mockFilter(false));
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testMultipleFiltersSingleFail() {
		Filter filter = new ConjunctiveFilter(mockFilter(true), mockFilter(false));
		assertThat(filter.filter(int.class)).isFalse();
	}

	@Test
	public void testMultipleFiltersAllPass() {
		Filter filter = new ConjunctiveFilter(mockFilter(true), mockFilter(true));
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