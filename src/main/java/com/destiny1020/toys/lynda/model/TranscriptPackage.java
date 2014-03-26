package com.destiny1020.toys.lynda.model;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class TranscriptPackage {

	/**
	 * <Timeline, <Translated Text, Original Text>>
	 */
	private List<Pair<String, Pair<String, String>>> transcripts;

	public TranscriptPackage(
			List<Pair<String, Pair<String, String>>> transcripts) {
		this.transcripts = transcripts;
	}

	public List<Pair<String, Pair<String, String>>> getTranscripts() {
		return transcripts;
	}

	public void setTranscripts(
			List<Pair<String, Pair<String, String>>> transcripts) {
		this.transcripts = transcripts;
	}

}
