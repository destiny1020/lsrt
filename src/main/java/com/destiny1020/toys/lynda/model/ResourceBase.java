package com.destiny1020.toys.lynda.model;

import org.jsoup.nodes.Element;

public class ResourceBase {

	private int number;
	private String title;
	private final Element element;

	public ResourceBase(int number, String title, Element element) {
		this.number = number;
		this.title = title;
		this.element = element;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Element getElement() {
		return element;
	}

}
