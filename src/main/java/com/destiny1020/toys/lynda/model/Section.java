package com.destiny1020.toys.lynda.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import com.destiny1020.toys.lynda.util.FileNameUtils;
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

		// means thr retryNumber has become to 0...
		// TODO
	}

	public void fetchTranscripts() {
		fetchTranscripts(Constants.NO_TRANS_OUTPUT);
	}

	private TranscriptPackage generateTranscriptPackage(Elements transcripts,
			String langCd) {
		// duplicate the last one with extended timeline
		Element last = transcripts.last();
		transcripts.add(last.clone());
		last = transcripts.last();
		last.attr(ATTR_DATA_DURATION, String.valueOf(Double.parseDouble(last
				.attr(ATTR_DATA_DURATION)) + 60.0));
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
			if (langCd.isEmpty()) {
				subtitle1 = "";
			} else {
				subtitle1 = translator.translate(subtitle2,
						Constants.LANG_INPUT, langCd);
			}

			results.add(Pair.of(timeline, Pair.of(subtitle1, subtitle2)));
		}

		return new TranscriptPackage(results);
	}

	public void output() {
		if (tp == null) {
			// TODO: show the error msg
			return;
		}

		String fileDir = String.format("%s/%s", FileNameUtils
				.replaceInvalidChars(this.parentChapter.getParentCourse()
						.getName()), FileNameUtils
				.replaceInvalidChars(this.parentChapter.getTitle()));
		String filePath = String.format("%s/%d.%s.%s", fileDir, getNumber(),
				FileNameUtils.replaceInvalidChars(getTitle()),
				Constants.SRT_EXT);

		BufferedWriter bw;
		File destDir = new File(fileDir);
		File destFile = new File(filePath);
		try {
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			if (!destFile.exists()) {
				destFile.createNewFile();
			}
			FileWriter fw = new FileWriter(destFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			// start to output the tp instance to the srt file
			List<Pair<String, Pair<String, String>>> transcripts = tp
					.getTranscripts();
			Pair<String, Pair<String, String>> snippet;
			for (int idx = 1; idx <= transcripts.size(); idx++) {
				snippet = transcripts.get(idx - 1);
				writeSnippet(bw, snippet, idx);
			}

			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeSnippet(BufferedWriter bw,
			Pair<String, Pair<String, String>> snippet, int idx)
			throws IOException {
		String lang1 = snippet.getRight().getLeft();
		bw.write(String.valueOf(idx));
		bw.write(Constants.FILE_SEPARATOR);
		bw.write(snippet.getLeft());
		bw.write(Constants.FILE_SEPARATOR);
		if (!lang1.isEmpty()) {
			bw.write(lang1);
			bw.write(Constants.FILE_SEPARATOR);
		}
		bw.write(snippet.getRight().getRight());
		bw.write(Constants.FILE_SEPARATOR);
		bw.write(Constants.FILE_SEPARATOR);
	}
}
