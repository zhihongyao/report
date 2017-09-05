package com.report.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.medical.compress.DecompressionUtil;
import com.report.Const;
import com.report.excel.ProjectToExcel;
import com.report.util.DateUtil;
import com.report.util.FileUtil;
import com.report.word.IPoiExtractContent;
import com.report.word.PoiHwpfExtractContentImpl;
import com.report.word.PoiXwpfExtractContentImpl;

@Controller
@RequestMapping("/report")
public class ReportController {

	@RequestMapping(value = "entrance", method = { RequestMethod.GET, RequestMethod.POST })
	public String entrance(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "/upload.jsp";
	}

	@RequestMapping(value = "upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String upload(@RequestParam(value = "wordFile", required = false) MultipartFile[] files, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		try {
	        //String path = request.getSession().getServletContext().getRealPath("upload") + "\\" + UUID.randomUUID().toString().replaceAll("-", "");
			String path = File.separator + "data" + File.separator + "medical" + File.separator + "export" + File.separator
					+ UUID.randomUUID().toString().replaceAll("-", "");
			if (System.getProperty("os.name") != null
					&& System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {
				path = System.getProperty("user.home") + File.separator + "medical" + File.separator + "export"
						+ File.separator + UUID.randomUUID().toString().replaceAll("-", "");
			}
	        String tempPath = path + File.separator + "temp";
	        for(MultipartFile file : files){
	        	String fileName = file.getOriginalFilename();
	        	File targetFile = new File(tempPath, fileName);
	        	if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
		        //保存
		        file.transferTo(targetFile);
	        }
	        //解压文件
	        List<String> filePathListTemp = FileUtil.traverseFolder(tempPath);
	        for(int j = 0; j < filePathListTemp.size(); j++){
	        	String suffix = filePathListTemp.get(j).substring(filePathListTemp.get(j).lastIndexOf("."));
	        	String fileName = new File(filePathListTemp.get(j)).getName();
	        	String fileNameNoSuffix = fileName.substring(0,fileName.lastIndexOf("."));
	        	if(".zip".equalsIgnoreCase(suffix)){
	        		if(!new File(tempPath + File.separator + fileNameNoSuffix + suffix.substring(1)).exists()){
						new File(tempPath + File.separator + fileNameNoSuffix + suffix.substring(1)).mkdirs();
					}
		        	DecompressionUtil.extractZipFiles(filePathListTemp.get(j), tempPath + File.separator + fileNameNoSuffix + suffix.substring(1));
		        }else if(".rar".equalsIgnoreCase(suffix)){
		        	if(!new File(tempPath + File.separator + fileNameNoSuffix + suffix.substring(1)).exists()){
						new File(tempPath + File.separator + fileNameNoSuffix + suffix.substring(1)).mkdirs();
					}
		        	DecompressionUtil.extractRarFiles(filePathListTemp.get(j), tempPath + File.separator + fileNameNoSuffix + suffix.substring(1));
		        }
	        }
	        //复制文件
	        List<String> filePathList = FileUtil.traverseFolder(tempPath);
	        String[] filePath = new String[filePathList.size()];
			String[] fileNames = new String[filePathList.size()];
	        for(int j = 0; j < filePathList.size(); j++){
	        	String suffix = filePathList.get(j).substring(filePathList.get(j).lastIndexOf("."));
	        	String fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
	        	//重命名和复制文件
	        	FileUtil.copy(filePathList.get(j), path + File.separator + fileName);
	        	filePath[j] = path + File.separator + fileName;
		        fileNames[j] = fileName;
	        }
	        String transformPath = ProjectToExcel.creatExcel(path, filePath);
	        File transformFile = new File(transformPath);
	        String transformFileName = transformFile.getName();
	        model.addAttribute("filePath",transformPath.substring(0,transformPath.indexOf(transformFileName)));
	        model.addAttribute("fileName",transformFileName);
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
