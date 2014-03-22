package com.destiny1020.toys.lynda.util.test;

import org.junit.Assert;
import org.junit.Test;

import com.destiny1020.toys.lynda.util.TimeUtils;

public class TimeUtilsTest {

	@Test
	public void testConvertSeconds1() {
		String sStartSeconds = "0.56";
		String sEndSeconds = "4.18";

		String converted = TimeUtils.convertSeconds(sStartSeconds, sEndSeconds);
		String expected = "00:00:00,560 --> 00:00:04,179";
		Assert.assertEquals(expected, converted);
	}

	@Test
	public void testConvertSeconds2() {
		String sStartSeconds = "169.06";
		String sEndSeconds = "172.36";

		String converted = TimeUtils.convertSeconds(sStartSeconds, sEndSeconds);
		String expected = "00:02:49,060 --> 00:02:52,360";
		Assert.assertEquals(expected, converted);
	}

}
