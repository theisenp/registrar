package com.theisenp.registrar.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A composite {@link Filter} that passes if any of its child filters passes
 * 
 * @author patrick.theisen
 */
public class DisjunctiveFilter implements Filter {
	private final List<Filter> filters = new ArrayList<>();

	/**
	 * @param filters
	 */
	public DisjunctiveFilter(Filter... filters) {
		this(Arrays.asList(filters));
	}

	/**
	 * @param filters
	 */
	public DisjunctiveFilter(Collection<Filter> filters) {
		this.filters.addAll(filters);
		if(filters.isEmpty()) {
			String message = "You must provide at least one child filter";
			throw new IllegalArgumentException(message);
		}
	}

	@Override
	public boolean filter(Class<?> clazz) {
		for(Filter filter : filters) {
			if(filter.filter(clazz)) {
				return true;
			}
		}
		return false;
	}
}
