package com.destiny1020.toys.lynda.spec;

/**
 * Used to define the supported operations for extracting the Table of Contents.
 * 
 * @author destiny1020
 */
public interface IToc {

	/**
	 * Get the chapters by the chapter selector.
	 * @return A list of Chapter instances.
	 */
	void fetchChapters();

}
