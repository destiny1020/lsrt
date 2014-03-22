package com.destiny1020.toys.lynda.spec;

/**
 * Used to define the supported operations for extracting the transcripts.
 * 
 * @author destiny1020
 *
 */
public interface ITranscript {

	/**
	 * Fill in the TranscriptPackage instance in the passed in section.
	 * @param section
	 */
	void fetchTranscripts();

	/**
	 * Fill in the TranscriptPackage instance in the passed in section, meanwhile translating will be executed by langCd.
	 * @param section
	 * @param langCd
	 */
	void fetchTranscripts(String langCd);

}
