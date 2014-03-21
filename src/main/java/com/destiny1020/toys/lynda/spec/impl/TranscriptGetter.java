package com.destiny1020.toys.lynda.spec.impl;

import com.destiny1020.toys.lynda.spec.ITranscript;

public class TranscriptGetter implements ITranscript {

	private static final String SELECTOR_TRANSCRIPT = ".video-transcript .transcript";

	public String getTranscriptSelector() {
		return SELECTOR_TRANSCRIPT;
	}

}
