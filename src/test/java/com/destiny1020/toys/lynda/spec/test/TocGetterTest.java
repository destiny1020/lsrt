package com.destiny1020.toys.lynda.spec.test;

import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import com.destiny1020.toys.lynda.spec.IToc;
import com.destiny1020.toys.lynda.spec.impl.TocGetter;

public class TocGetterTest {

	@Test
	public void testGetChapters() {
		IToc toc = new TocGetter();

		String url = "http://www.lynda.com/Bootstrap-tutorials/Up-Running-Bootstrap-3/133339-2.html";

		String chapter0 = "Introduction";
		String chapter1 = "1. Bootstrap Introduction and Download";
		String chapter2 = "2. The Bootstrap Grid System";
		String chapter3 = "3. CSS Overview";
		String chapter4 = "4. Navigation Systems";
		String chapter5 = "5. JavaScript Effects";
		String chapterE = "Conclusion";

		String[] expectedResults = new String[] { chapter0, chapter1, chapter2,
				chapter3, chapter4, chapter5, chapterE };

		Elements chapters = toc.getChapters(url);
		String actual = null;
		for (int idx = 0; idx < chapters.size(); idx++) {
			actual = toc.getChapterHeader(chapters.get(idx));
			Assert.assertEquals(expectedResults[idx], actual);
		}
	}

}
