package com.alramz.qa.utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.alramz.qa.base.TestBase;

public class PdfReader extends TestBase {

	public static String getPdfdata(String url) throws IOException {

		URL pdfURL = new URL(url);

		InputStream is = pdfURL.openStream();

		BufferedInputStream bis = new BufferedInputStream(is);

		PDDocument doc = PDDocument.load(bis);

		PDFTextStripper strip = new PDFTextStripper();

		String stripText = strip.getText(doc);

		System.out.println(stripText);

		doc.close();

		return stripText;

	}

}
