package com.destiny1020.toys.lynda.spec;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Used to define the supported operations for extracting the Table of Contents.
 * 
 * @author destiny1020
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
	 * Get the CSS selector for the chapter header, should be used under a certain chapter element.
	 * @return
	 */
	String getChapterHeaderSelector();

	/**
	 * Get the Chapter Header for a certain chapter.
	 * @param chapter
	 * @return
	 */
	String getChapterHeader(Element chapter);

	/**
	 * Get the CSS selector for the sections under a certain chapter, should be used under a certain chapter element.
	 * @return
	 */
	String getSectionSelector();

	/**
	 * Get the list for all the sections under the chapter.
	 * @param chapter
	 * @return
	 */
	List<Pair<String, String>> getSections(Element chapter);
}
