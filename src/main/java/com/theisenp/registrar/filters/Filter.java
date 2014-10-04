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

	/**
	 * A {@link Filter} that always passes
	 */
	public static Filter PASS = new Filter() {

		@Override
		public boolean filter(Class<?> clazz) {
			return true;
		}
	};

	/**
	 * A {@link Filter} that always fails
	 */
	public static Filter FAIL = new Filter() {

		@Override
		public boolean filter(Class<?> clazz) {
			return false;
		}
	};
}
