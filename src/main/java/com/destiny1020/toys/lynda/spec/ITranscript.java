package com.destiny1020.toys.lynda.spec;

import org.jsoup.nodes.Element;

import com.destiny1020.toys.lynda.model.TranscriptPackage;

/**
 * Used to define the supported operations for extracting the transcripts.
 * 
 * @author destiny1020
 *
 */
public interface ITranscript {

	String getTranscriptSelector();

	Element getTranscripts(String url);

	TranscriptPackage getTranscriptsWithTimeline(String url);

}
