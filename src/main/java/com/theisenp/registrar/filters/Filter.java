package com.theisenp.registrar.filters;

/**
 * Passes or rejects class types
 * 
 * @author patrick.theisen
 */
public interface Filter {

	/**
	 * @param clazz
	 * @return True if the given {@link Class} passes the filter. Else, false.
	 */
	public boolean filter(Class<?> clazz);
}
