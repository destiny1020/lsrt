package com.destiny1020.toys.lynda.model;

import java.util.List;

import org.jsoup.nodes.Element;

public class Chapter extends ResourceBase {

	private List<Section> subSections;

	public Chapter(int number, String title, Element chapter) {
		super(number, title, chapter);
	}

	public List<Section> getSubSections() {
		return subSections;
	}

	public void setSubSections(List<Section> subSections) {
		this.subSections = subSections;
	}

}
