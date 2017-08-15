package com.idaez.medical.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
	
	public static final String inputFile = "E:\\Medical\\test.doc";
	
	@RequestMapping(value="/entrance",method={RequestMethod.GET,RequestMethod.POST})
	public String entrance(Model model, HttpServletRequest request, HttpServletResponse response){
		try {
			//getWordAndStyle(inputFile);
			IPoiExtractContent wordService = null;
			if(inputFile.substring(inputFile.lastIndexOf(".")+1).equalsIgnoreCase("docx")){
				wordService = new PoiXwpfExtractContentImpl();
	        }else{
	        	wordService = new PoiHwpfExtractContentImpl();
	        }
			wordService.getContent(inputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
