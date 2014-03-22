package com.destiny1020.toys.lynda.spec.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.destiny1020.toys.lynda.constant.Constants;
import com.destiny1020.toys.lynda.model.TranscriptPackage;
import com.destiny1020.toys.lynda.spec.ITranscript;
import com.destiny1020.toys.lynda.util.TimeUtils;
import com.gtranslate.Translator;

public class TranscriptGetter implements ITranscript {

	// attribute keys
	private static final String ATTR_DATA_DURATION = "data-duration";

	private static final String SELECTOR_TRANSCRIPT = ".video-transcript";
	private static final String SELECTOR_TRANSCRIPT_TITLE = "> h2";
	private static final String SELECTOR_TRANSCRIPT_CONTENT = "span.transcript";

	public String getTranscriptSelector() {
		return SELECTOR_TRANSCRIPT;
	}

	public Element getTranscripts(String url) {
		Document doc;
		int retryNumber = 5;
		while (retryNumber > 0) {
			try {
				doc = Jsoup.connect(url).get();
				Element transcripts = doc.select(getTranscriptSelector())
						.first();
				return transcripts;
			} catch (IOException e) {
				retryNumber--;
			}
		}

		return null;
	}

	public TranscriptPackage getTranscriptsWithTimeline(String url) {
		Element t = getTranscripts(url);
		if (t == null) {
			return null;
		}

		// retrieve the title
		System.out.println(t.html());
		String title = t.select(SELECTOR_TRANSCRIPT_TITLE).first().text();

		// retrieve the content
		Elements transcripts = t.select(SELECTOR_TRANSCRIPT_CONTENT);

		transcripts.add(transcripts.get(transcripts.size() - 1));
		List<Pair<String, Pair<String, String>>> results = new LinkedList<Pair<String, Pair<String, String>>>();

		Translator translator = Translator.getInstance();

		String timeline, subtitle1, subtitle2;
		Element current, next;
		for (int idx = 0; idx < transcripts.size() - 1; idx++) {
			current = transcripts.get(idx);
			next = transcripts.get(idx + 1);
			timeline = TimeUtils.convertSeconds(
					current.attr(ATTR_DATA_DURATION),
					next.attr(ATTR_DATA_DURATION));
			subtitle2 = current.text();
			subtitle1 = translator.translate(subtitle2, Constants.LANG_INPUT,
					Constants.LANG_OUTPUT);

			results.add(Pair.of(timeline, Pair.of(subtitle1, subtitle2)));
		}

		return new TranscriptPackage(title, results);
	}
}
