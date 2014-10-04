package com.theisenp.registrar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.theisenp.registrar.filters.Filter;

/**
 * A collection of class finding utilities
 * 
 * @author patrick.theisen
 */
public class Registrar {
	public static final String JAR_PATTERN = "^.*\\.(?i)(jar)$";
	public static final String CLASS_PATTERN = "^.*\\.(?i)(class)$";
	public static final String DEFAULT_CLASSPATH;
	static {
		StringBuilder builder = new StringBuilder();
		builder.append(System.getenv("CLASSPATH"));
		builder.append(File.pathSeparator);
		builder.append(System.getProperty("java.class.path"));
		DEFAULT_CLASSPATH = builder.toString();
	}

	/**
	 * @return A {@link Set} of every accessible class on the default classpath
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Set<Class<?>> find() throws IOException, ClassNotFoundException {
		return find(Filter.PASS, DEFAULT_CLASSPATH);
	}

	/**
	 * @param filter
	 * @return A {@link Set} of every accessible class on the default classpath
	 * that passes the given {@link Filter}
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Set<Class<?>> find(Filter filter) throws IOException, ClassNotFoundException {
		return find(filter, DEFAULT_CLASSPATH);
	}

	/**
	 * @param filter
	 * @return A {@link Set} of every accessible class on the given classpath
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Set<Class<?>> find(String classpath) throws IOException, ClassNotFoundException {
		return find(Filter.PASS, classpath);
	}

	/**
	 * @param filter
	 * @return A {@link Set} of every accessible class on the given classpath
	 * that passes the given {@link Filter}
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Set<Class<?>> find(Filter filter, String classpath) throws IOException,
			ClassNotFoundException {
		Set<Class<?>> result = new HashSet<Class<?>>();

		// Build a list of all items on the classpath
		List<String> items = new ArrayList<String>();
		items.addAll(Arrays.asList(classpath.split(File.pathSeparator)));

		// Build a class loader for the full classpath
		URL[] urls = new URL[items.size()];
		for(int i = 0; i < urls.length; i++) {
			urls[i] = new File(items.get(i)).toURI().toURL();
		}
		URLClassLoader loader = new URLClassLoader(urls);

		// Process each item on the classpath
		for(String item : items) {
			// Check file permissions
			File file = new File(item);
			if(!file.canRead()) {
				continue;
			}

			// Check for jars
			if(item.matches(JAR_PATTERN)) {
				addJar(new JarFile(file), loader, filter, result);
			}
		}

		return result;
	}

	/**
	 * Adds any classes found in the given jar to the provided result
	 * {@link Set}
	 * 
	 * @param jar
	 * @param loader
	 * @param filter
	 * @param result
	 * @throws ClassNotFoundException
	 */
	private static void addJar(JarFile jar, ClassLoader loader, Filter filter, Set<Class<?>> result)
			throws ClassNotFoundException {
		for(Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();) {
			String path = entries.nextElement().getName();
			if(path.matches(CLASS_PATTERN)) {
				String name = path.replace(".class", "").replace("/", ".").replace("\\", ".");
				Class<?> clazz = loader.loadClass(name);
				if(clazz != null) {
					result.add(clazz);
				}

			}
		}
	}
}
