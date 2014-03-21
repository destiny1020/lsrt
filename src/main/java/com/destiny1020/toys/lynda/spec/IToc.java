package com.destiny1020.toys.lynda.spec;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

	/**
	 * Get the chapters by the chapter selector.
	 * @return Jsoup Elements Object
	 */
	Elements getChapters(String url);
	
	/**
	 * Get the CSS selector for the chapter header, should be used for a certain chapter.
	 * @return
	 */
	String getChapterHeaderSelector();
	
	/**
	 * Get the Chapter Header for a certain chapter.
	 * @param chapter
	 * @return
	 */
	String getChapterHeader(Element chapter);
}
