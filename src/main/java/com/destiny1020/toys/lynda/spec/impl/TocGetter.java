package com.destiny1020.toys.lynda.spec.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.destiny1020.toys.lynda.spec.IToc;

/**
 * An implementation for the IToc.
 * 
 * @author Ruixiang Jiang
 *
 */
public class TocGetter implements IToc {

	private static final String SELECTOR_CHAPTER = "#course-toc-outer > li";
	private static final String SELECTOR_CHAPTER_HEADER = "h3 a";

	public String getChapterSelector() {
		return SELECTOR_CHAPTER;
	}

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

}
