//$Id$
package com.java.functionalIntf;

@FunctionalInterface
public interface Comparator<T> {

	//2 non-default functions
	 int compare(T o1, T o2);

	 boolean equals(Object obj);
}
