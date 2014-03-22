package com.destiny1020.toys.lynda.model;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.destiny1020.toys.lynda.spec.IToc;

public class Course implements IToc {

	// selectors below
	private static final String SELECTOR_CHAPTER = "#course-toc-outer > li";
	private static final String SELECTOR_CHAPTER_TITLE = "h3 a";
	private static final String SELECTOR_CHAPTER_SECTION = "ol > li > dl > dt a";

	private String name;
	private String url;
	private List<Chapter> chapters;

	public Course(String url) {
		this.url = url;
	}

	/**
	 * Return an empty list when there is any exception or fetched nothing.
	 */
	@SuppressWarnings("unchecked")
	public void fetchChapters() {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements chapters = doc.select(SELECTOR_CHAPTER);
			List<Chapter> results = new LinkedList<Chapter>();

			Element chapter;
			String chapterTitle;
			Chapter chapterInstance;
			for (int idx = 0; idx < chapters.size(); idx++) {
				chapter = chapters.get(idx);
				chapterTitle = chapter.select(SELECTOR_CHAPTER_TITLE).first()
						.text();

				// parse the sections under current chapter
				chapterInstance = new Chapter(idx, chapterTitle, chapter);
				results.add(chapterInstance);
				parseSections(chapterInstance);
			}

			this.chapters = results;
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.chapters = Collections.EMPTY_LIST;
	}

	private void parseSections(Chapter chapter) {
		Elements sections = chapter.getElement().select(
				SELECTOR_CHAPTER_SECTION);
		List<Section> sectionList = new LinkedList<Section>();
		Element section;
		for (int idx = 0; idx < sections.size(); idx++) {
			section = sections.get(idx);
			sectionList.add(new Section(idx, section.text(), section
					.attr("href"), section, chapter));
		}

		// correlate the chapter with its sections
		chapter.setSubSections(sectionList);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

}
