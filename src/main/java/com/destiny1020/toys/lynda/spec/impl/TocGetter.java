package com.destiny1020.toys.lynda.spec.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.destiny1020.toys.lynda.spec.IToc;

/**
 * An implementation for the IToc.
 * 
 * @author destiny1020
 *
 */
public class TocGetter implements IToc {

	private static final String SELECTOR_CHAPTER = "#course-toc-outer > li";
	private static final String SELECTOR_CHAPTER_HEADER = "h3 a";
	private static final String SELECTOR_CHAPTER_SECTION = "ol > li > dl > dt a";

	public String getChapterSelector() {
		return SELECTOR_CHAPTER;
	}

	/**
	 * Return null when there is any exception.
	 */
	public Elements getChapters(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements chapters = doc.select(getChapterSelector());
			return chapters;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getChapterHeaderSelector() {
		return SELECTOR_CHAPTER_HEADER;
	}

	public String getChapterHeader(Element chapter) {
		return chapter.select(getChapterHeaderSelector()).first().text();
	}

	public String getSectionSelector() {
		return SELECTOR_CHAPTER_SECTION;
	}

	public List<Pair<String, String>> getSections(Element chapter) {
		Elements sections = chapter.select(getSectionSelector());
		List<Pair<String, String>> sectionsList = new LinkedList<Pair<String, String>>();
		String title, url;
		for (Element section : sections) {
			title = section.text();
			url = section.attr("href");
			sectionsList.add(Pair.of(title, url));
		}

		return sectionsList;
	}

}
