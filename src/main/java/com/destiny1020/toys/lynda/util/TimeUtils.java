package com.destiny1020.toys.lynda.util;

/**
 * A series of utilities. 
 * 
 * @author destiny1020
 *
 */
public class TimeUtils {

	/**
	 * Convert the seconds into format like: 00:00:08,200 --> 00:00:12,200
	 * 
	 * @param seconds
	 * @return
	 */
	public static String convertSeconds(String sStartSeconds, String sEndSeconds) {
		double dStartSeconds = Double.parseDouble(sStartSeconds);
		double dEndSeconds = Double.parseDouble(sEndSeconds);

		return String.format("%s --> %s", innerConvert(dStartSeconds),
				innerConvert(dEndSeconds));
	}

	private static String innerConvert(double dSeconds) {
		int hours = (int) (dSeconds / 3600);
		int minutes = (int) ((dSeconds - 3600 * hours) / 60);
		int seconds = (int) (dSeconds % 60);
		int decimals = (int) ((dSeconds - (int) dSeconds) * 1000);

		return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds,
				decimals);
	}

}
