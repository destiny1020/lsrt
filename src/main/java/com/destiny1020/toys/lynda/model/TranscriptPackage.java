package com.destiny1020.toys.lynda.model;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class TranscriptPackage {

	private String title;
	private List<Pair<String, Pair<String, String>>> transcripts;

	public TranscriptPackage(String title,
			List<Pair<String, Pair<String, String>>> transcripts) {
		this.title = title;
		this.transcripts = transcripts;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Pair<String, Pair<String, String>>> getTranscripts() {
		return transcripts;
	}

	public void setTranscripts(
			List<Pair<String, Pair<String, String>>> transcripts) {
		this.transcripts = transcripts;
	}

}
