Registrar
=========

[![Build Status](https://travis-ci.org/theisenp/registrar.png?branch=master)](https://travis-ci.org/theisenp/registrar)

Registrar is a library for automatically discovering and loading Java classes on the classpath. Clients can optionally supply targeted search paths and class filters to control which classes are returned. Registrar borrows some of its internal search logic from the [LCM][1] library.

Usage
-----

### Filters ###

Registrar uses the `Filter` interface to prune its search results. A `Filter` accepts a class as input and then returns true if it should be included in the result. Clients can provide their own filter implementations, but some common ones are included in the library:

- `Filter.PASS` - Passes all classes
- `Filter.FAIL` - Fails all classes
- `TypeFilter` - Passes classes of a single type
- `PackageFilter` - Passes classes in a single package
- `ConjunctiveFilter` - Passes classes that pass all of a set of multiple filters
- `DisjunctiveFilter` - Passes classes that pass any of a set of multiple filters

### Execution ###

Registrar only provides one basic function: finding classes on a given classpath. To find all classes on the default classpath (the Java execution classpath plus the contents of the CLASSPATH environment variable), use:

	Set<Class<?>> classes = Registrar.find();
	
To find all classes on the default classpath that pass a given filter, use:

	Filter filter = new TypeFilter(String.class);
	Set<Class<?>> classes = Registrar.find(filter);
	
To find all classes on a particular classpath, use:

	String classpath = "/path/to/jars";
	Set<Class<?>> classes = Registrar.find(classpath);
	
To find all classes on a particular classpath that pass a given filter, use:

	Filter filter = new TypeFilter(String.class);
	String classpath = "/path/to/jars";
	Set<Class<?>> classes = Registrar.find(filter, classpath);

Build
-----

Registrar is built with [Maven][2]. To build the project and install it in your local Maven repositiory, type:

	mvn clean install

Download
--------

To include Registrar in another Maven project, add the following dependency to the project's `pom.xml`:

```xml
<dependency>
  <groupId>com.theisenp</groupId>
  <artifactId>registrar</artifactId>
  <version>(latest version)</version>
</dependency>
```

Developed By
------------

* Patrick Theisen - <theisenp@gmail.com>

License
-------

    Copyright 2014 Patrick Theisen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: lcm.googlecode.com
[2]: http://maven.apache.org/