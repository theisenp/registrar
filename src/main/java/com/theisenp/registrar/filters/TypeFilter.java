package com.theisenp.registrar.filters;

/**
 * A {@link Filter} that passes classes and subclasses of a given type
 * 
 * @author patrick.theisen
 */
public class TypeFilter implements Filter {
	private final Class<?> type;

	/**
	 * @param type
	 */
	public TypeFilter(Class<?> type) {
		this.type = type;
	}

	@Override
	public boolean filter(Class<?> clazz) {
		return type.isAssignableFrom(clazz);
	}
}
