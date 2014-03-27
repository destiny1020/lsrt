package com.destiny1020.toys.lynda.model;

import java.util.List;

import org.jsoup.nodes.Element;

public class Chapter extends ResourceBase {

	private Course parentCourse;
	private List<Section> subSections;

	public Chapter(int number, String title, Element chapter,
			Course parentCourse) {
		super(number, title, chapter);
		this.parentCourse = parentCourse;
	}

	public List<Section> getSubSections() {
		return subSections;
	}

	public void setSubSections(List<Section> subSections) {
		this.subSections = subSections;
	}

	public Course getParentCourse() {
		return parentCourse;
	}

	public void setParentCourse(Course parentCourse) {
		this.parentCourse = parentCourse;
	}

}
