package com.destiny1020.toys.lynda.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.destiny1020.toys.lynda.constant.Constants;
import com.destiny1020.toys.lynda.spec.ITranscript;
import com.destiny1020.toys.lynda.util.TimeUtils;
import com.gtranslate.Translator;

public class Section extends ResourceBase implements ITranscript {

	// attribute keys
	private static final String ATTR_DATA_DURATION = "data-duration";

	// selectors
	private static final String SELECTOR_TRANSCRIPT_CONTENT = "span.transcript";

	private final Chapter parentChapter;
	private final String url;
	private TranscriptPackage tp;

	public Section(int number, String title, String url, Element section,
			Chapter parentChapter) {
		super(number, title, section);
		this.url = url;
		this.parentChapter = parentChapter;
	}

	public TranscriptPackage getTp() {
		return tp;
	}

	public void setTp(TranscriptPackage tp) {
		this.tp = tp;
	}

	public Chapter getParentChapter() {
		return parentChapter;
	}

	public String getUrl() {
		return url;
	}

	public void fetchTranscripts(String langCd) {
		Document doc;
		int retryNumber = 5;
		while (retryNumber > 0) {
			try {
				doc = Jsoup.connect(url).get();
				Elements transcripts = doc.select(SELECTOR_TRANSCRIPT_CONTENT);
				TranscriptPackage tp = generateTranscriptPackage(transcripts,
						langCd);
				// fill in the tp field for the section instance
				this.setTp(tp);
				return;
			} catch (IOException e) {
				retryNumber--;
			}
		}
	}

	public void fetchTranscripts() {
		fetchTranscripts(Constants.LANG_OUTPUT);
	}

	private TranscriptPackage generateTranscriptPackage(Elements transcripts,
			String langCd) {
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
					langCd);

			results.add(Pair.of(timeline, Pair.of(subtitle1, subtitle2)));
		}

		return new TranscriptPackage(results);
	}

}
