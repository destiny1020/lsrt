package com.destiny1020.toys.lynda.spec.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Assert;
import org.junit.Test;

import com.destiny1020.toys.lynda.model.Course;
import com.destiny1020.toys.lynda.model.Section;
import com.gtranslate.Translator;

public class SectionTest {

	@Test
	public void testTranslator() throws UnsupportedEncodingException {
		Translator translator = Translator.getInstance();

		String text = "whatever you like to do it's fine.:::You can call it respond.min.js or respond.js,";
		String encodedText = URLEncoder.encode(text, "utf-8");
		String translated = translator.translate(encodedText, "en", "zh-CN");

		String expected = "不管你喜欢做它的罚款。 :::你可以把它叫做respond.min.js或respond.js ，";
		Assert.assertEquals(expected, translated);
	}

	@Test
	public void testGetTranscript() {
		String url = "http://www.lynda.com/Bootstrap-tutorials/Up-Running-Bootstrap-3/133339-2.html";
		Course course = new Course(url);

		// get the section of "Adding JavaScript to a Bootstrap HTML file"
		Section section = course.getChapters().get(1).getSubSections().get(4);

		// fetch the tp
		section.fetchTranscripts();
		String expectedTitle = "Adding JavaScript to a Bootstrap HTML file";
		int expectedSize = 143;
		Assert.assertEquals(expectedTitle, section.getTitle());
		Assert.assertEquals(expectedSize, section.getTp().getTranscripts()
				.size());
	}

	@Test
	public void testOutputTranscript() {
		String url = "http://www.lynda.com/Bootstrap-tutorials/Up-Running-Bootstrap-3/133339-2.html";
		Course course = new Course(url);

		// get the section of "Adding JavaScript to a Bootstrap HTML file"
		Section section = course.getChapters().get(1).getSubSections().get(4);

		// fetch the tp
		section.fetchTranscripts();
		section.output();
	}
}
