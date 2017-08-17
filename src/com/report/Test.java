package com.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Test {

	public static void main(String[] args) {
		try {
			readWord2003("D:\\temp\\test03.doc");
			readWord2007("D:\\temp\\test07.docx");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readWord2003(String path) throws IOException {
		File file = new File(path);
		String str = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			HWPFDocument doc = new HWPFDocument(fis);
			String doc1 = doc.getDocumentText();
			System.out.println(doc1);
			StringBuilder doc2 = doc.getText();
			System.out.println(doc2);
			Range rang = doc.getRange();
			String doc3 = rang.text();
			System.out.println(doc3);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readWord2007(String path) throws IOException {
		File file = new File(path);
		String str = "";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			XWPFDocument xdoc = new XWPFDocument(fis);
			XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			String doc1 = extractor.getText();
			System.out.println(doc1.replaceAll(" ", ""));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}
	}
}
