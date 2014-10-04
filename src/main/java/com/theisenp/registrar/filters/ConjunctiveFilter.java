package com.theisenp.registrar.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * A composite {@link Filter} that only passes if each of its child filters
 * passes
 * 
 * @author patrick.theisen
 */
public class ConjunctiveFilter implements Filter {
	private final Collection<Filter> filters;

	/**
	 * @param filters
	 */
	public ConjunctiveFilter(Filter... filters) {
		this(Arrays.asList(filters));
	}

	/**
	 * @param filters
	 */
	public ConjunctiveFilter(Collection<Filter> filters) {
		this.filters = new ArrayList<>(filters);
		if(filters.isEmpty()) {
			String message = "You must provide at least one child filter";
			throw new IllegalArgumentException(message);
		}
	}

	@Override
	public boolean filter(Class<?> clazz) {
		for(Filter filter : filters) {
			if(!filter.filter(clazz)) {
				return false;
			}
		}
		return true;
	}
}
