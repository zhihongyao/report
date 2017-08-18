package com.report.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.report.Const;
import com.report.util.DateUtil;
import com.report.word.IPoiExtractContent;
import com.report.word.PoiHwpfExtractContentImpl;
import com.report.word.PoiXwpfExtractContentImpl;

@Controller
@RequestMapping("/report")
public class ReportController {

	public static final String inputFile = "C:\\temp\\test.docx";

	@RequestMapping(value = "entrance", method = { RequestMethod.GET, RequestMethod.POST })
	public String entrance(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "/upload.jsp";
	}

	@RequestMapping(value = "upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String upload(@RequestParam(value = "wordFile", required = false) MultipartFile[] files, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] filePath = new String[files.length];
			String[] fileNames = new String[files.length];
	        String path = request.getSession().getServletContext().getRealPath("upload") + "\\" + UUID.randomUUID().toString().replaceAll("-", "");
	        int i = 0;
	        for(MultipartFile file : files){
	        	String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));  
		        String fileName = DateUtil.getStrNowDate("yyyyMMddHHmmssSSS") + suffix;
		        File targetFile = new File(path, fileName);
		        if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        }
		        //保存
		        try {
		            file.transferTo(targetFile);  
		        } catch (Exception e) {  
		            e.printStackTrace();
		        }
		        filePath[i] = path + "\\" + fileName;
		        fileNames[i] = fileName;
		        i++;
	        }
	        for(String s : filePath){
	        	System.out.println(s);
	        }
	        model.addAttribute("filePath",path);
	        model.addAttribute("fileName",fileNames[0]);
	        return "download";
			// getWordAndStyle(inputFile);
			/*
			IPoiExtractContent wordService = null;
			if (inputFile.substring(inputFile.lastIndexOf(".") + 1).equalsIgnoreCase("docx")) {
				wordService = new PoiXwpfExtractContentImpl();
			} else {
				wordService = new PoiHwpfExtractContentImpl();
			}
			String result = wordService.getContent(inputFile);
			response.setContentType(Const.CONTENTTYPE_TEXT);
			response.getWriter().write(result);
			response.getWriter().flush();
			System.out.println(result);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 文件下载
     * @Description: 
     * @param fileName
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public String downloadFile(Model model, HttpServletRequest request, HttpServletResponse response) {
    	String fileName = request.getAttribute("fileName")+"";
        if (fileName != null) {
            String realPath = request.getAttribute("filePath")+"";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
