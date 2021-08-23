//$Id$
package com.java.functionalIntf;

@FunctionalInterface
public interface Consumer<T> {

	void accept(T t);
	
}
