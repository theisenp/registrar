package com.theisenp.registrar.filters;

/**
 * A {@link Filter} that passes classes in a given {@link Package}
 * 
 * @author patrick.theisen
 */
public class PackageFilter implements Filter {
	private final Package targetPackage;

	/**
	 * @param target
	 */
	public PackageFilter(Package targetPackage) {
		this.targetPackage = targetPackage;
	}

	@Override
	public boolean filter(Class<?> clazz) {
		return clazz.getPackage().equals(targetPackage);
	}
}
