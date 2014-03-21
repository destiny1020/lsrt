package com.destiny1020.toys.lynda.spec;

/**
 * Used to define the supported operations for extracting the Table of Contents.
 * 
 * @author Ruixiang Jiang
 */
public interface IToc {

	/**
	 * Get the CSS selector for the chapters, usually the result of selection is an array of li.
	 * @return CSS selector
	 */
	String getChapterSelector();
}
