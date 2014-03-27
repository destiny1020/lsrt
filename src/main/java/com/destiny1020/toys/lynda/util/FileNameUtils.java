package com.destiny1020.toys.lynda.util;

public class FileNameUtils {

	private static final String INVALID_CHARS = "[^A-Za-z0-9 _.]*";
	private static final String SUBSTITUTE_CHAR = "";

	public static String replaceInvalidChars(String origin) {
		return origin.replaceAll(INVALID_CHARS, SUBSTITUTE_CHAR);
	}
}
