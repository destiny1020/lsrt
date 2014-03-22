package com.destiny1020.toys.lynda.spec.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Assert;
import org.junit.Test;

import com.destiny1020.toys.lynda.model.TranscriptPackage;
import com.destiny1020.toys.lynda.spec.ITranscript;
import com.destiny1020.toys.lynda.spec.impl.TranscriptGetter;
import com.gtranslate.Translator;

public class TranscriptGetterTest {

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
		ITranscript transcript = new TranscriptGetter();

		String url = "http://www.lynda.com/Bootstrap-tutorials/Adding-JavaScript-Bootstrap-HTML-file/133339/151278-4.html";

		TranscriptPackage tp = transcript.getTranscriptsWithTimeline(url);
		String expectedTitle = "Adding JavaScript to a Bootstrap HTML file";
		Assert.assertEquals(expectedTitle, tp.getTitle());
		Assert.assertEquals(143, tp.getTranscripts().size());
	}
}
