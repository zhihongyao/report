package com.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.report.Const;
import com.report.word.IPoiExtractContent;
import com.report.word.PoiHwpfExtractContentImpl;
import com.report.word.PoiXwpfExtractContentImpl;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	public static final String inputFile = "C:\\temp\\test.docx";
	
	@RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
	public String entrance(Model model, HttpServletRequest request, HttpServletResponse response){
		try {
			//getWordAndStyle(inputFile);
			IPoiExtractContent wordService = null;
			if(inputFile.substring(inputFile.lastIndexOf(".")+1).equalsIgnoreCase("docx")){
				wordService = new PoiXwpfExtractContentImpl();
	        }else{
	        	wordService = new PoiHwpfExtractContentImpl();
	        }
			String result = wordService.getContent(inputFile);
			response.setContentType(Const.CONTENTTYPE_TEXT);
			response.getWriter().write(result);
			response.getWriter().flush();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
