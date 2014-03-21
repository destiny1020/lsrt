package com.destiny1020.toys.lynda.spec.test;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
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

	@Test
	public void testGetSections() {
		IToc toc = new TocGetter();

		String url = "http://www.lynda.com/Bootstrap-tutorials/Up-Running-Bootstrap-3/133339-2.html";

		String section21 = "Exploring Bootstrap's grid system";
		String section22 = "Creating new rows and cells";
		String section23 = "Adjusting column widths using offset";
		String section24 = "Changing column order using push and pull";
		String section25 = "Nesting columns";
		String section26 = "Creating a JumboTron-style layout";
		String section27 = "Challenge: Working with grids";
		String section28 = "Solution: Working with grids";

		String[] expectedResults1 = new String[] { section21, section22,
				section23, section24, section25, section26, section27,
				section28 };

		String sectionUrl21 = "http://www.lynda.com/Bootstrap-tutorials/Exploring-Bootstraps-grid-system/133339/151280-4.html";
		String sectionUrl22 = "http://www.lynda.com/Bootstrap-tutorials/Creating-new-rows-cells/133339/151281-4.html";
		String sectionUrl23 = "http://www.lynda.com/Bootstrap-tutorials/Adjusting-column-widths-using-offset/133339/151282-4.html";
		String sectionUrl24 = "http://www.lynda.com/Bootstrap-tutorials/Changing-column-order-using-push-pull/133339/151283-4.html";
		String sectionUrl25 = "http://www.lynda.com/Bootstrap-tutorials/Nesting-columns/133339/151284-4.html";
		String sectionUrl26 = "http://www.lynda.com/Bootstrap-tutorials/Creating-JumboTron-style-layout/133339/151285-4.html";
		String sectionUrl27 = "http://www.lynda.com/Bootstrap-tutorials/Challenge-Working-grids/133339/151286-4.html";
		String sectionUrl28 = "http://www.lynda.com/Bootstrap-tutorials/Solution-Working-grids/133339/151287-4.html";

		String[] expectedResults2 = new String[] { sectionUrl21, sectionUrl22,
				sectionUrl23, sectionUrl24, sectionUrl25, sectionUrl26,
				sectionUrl27, sectionUrl28 };

		Elements chapters = toc.getChapters(url);
		Element chapter2 = chapters.get(2);
		List<Pair<String, String>> sections = toc.getSections(chapter2);
		for (int idx = 0; idx < sections.size(); idx++) {
			Pair<String, String> pair = sections.get(idx);
			Assert.assertEquals(expectedResults1[idx], pair.getLeft());
			Assert.assertEquals(expectedResults2[idx], pair.getRight());
		}
	}

}
