package com.report.excel;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.report.util.DateUtil;
import com.report.word.WordDocumentExtractor;

public class ProjectToExcel {
	
	public static String creatExcel(String basePath, String[] filePath) {
		String path = null;
		HSSFWorkbook workbook = null;
		try {
			// 声明一个工作薄
			workbook = new HSSFWorkbook();
			workbook.createInformationProperties();
			workbook.getDocumentSummaryInformation().setCompany("JAVA EXPORT");
			SummaryInformation si = workbook.getSummaryInformation();
			si.setAuthor("JAVA EXPORT"); // 填加xls文件作者信息
			si.setApplicationName("JAVA EXPORT"); // 填加xls文件创建程序信息
			si.setLastAuthor("JAVA EXPORT"); // 填加xls文件最后保存者信息
			si.setComments("JAVA EXPORT"); // 填加xls文件作者信息
			si.setTitle("JAVA EXPORT"); // 填加xls文件标题信息
			si.setSubject("JAVA EXPORT");// 填加文件主题信息
			si.setCreateDateTime(new Date());
			path = createContent(workbook, basePath, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(workbook != null){
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path;
	}
	
	public static void creatData(HSSFWorkbook workBook,int rowIndex, List list) {
		try {
			Sheet sheet = workBook.getSheetAt(0);
			Row dataRow = sheet.createRow(rowIndex);
			for(int i = 0; i < list.size(); i++){
				Cell cell = dataRow.createCell(i);
				cell.setCellValue(list.get(i)+"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String createContent(HSSFWorkbook workBook,String basePath, String[] filePath) {
		OutputStream os = null;
		String path = null;
		try {
			Sheet sheet = workBook.createSheet();
			Row titleRow = sheet.createRow(0);
			Row subTitleRow = sheet.createRow(1);
			/*
			 * 设定合并单元格区域范围
			 * firstRow 0-based 
			 * lastRow 0-based 
			 * firstCol 0-based
			 * lastCol 0-based
			 */
			CellRangeAddress yiBanXinXi = new CellRangeAddress(0, 0, 0, 5);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(yiBanXinXi);
			Cell cell = titleRow.createCell(0);
			cell.setCellValue("一般信息");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("一般信息").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("一般信息").get(i));
			}
			
			Cell zongJianJieLunJiJianYiCell = subTitleRow.createCell(6);
			zongJianJieLunJiJianYiCell.setCellValue("总检结论及建议");
			
			CellRangeAddress neiKeJianCha = new CellRangeAddress(0, 0, 7, 24);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(neiKeJianCha);
			Cell cell2 = titleRow.createCell(7);
			cell2.setCellValue("内科检查");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("内科检查").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+7);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("内科检查").get(i));
			}
			
			CellRangeAddress waiKeJianCha = new CellRangeAddress(0, 0, 25, 31);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(waiKeJianCha);
			Cell cell3 = titleRow.createCell(25);
			cell3.setCellValue("外科检查");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("外科检查").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+25);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("外科检查").get(i));
			}
			
			CellRangeAddress fuKeJianCha = new CellRangeAddress(0, 0, 32, 38);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(fuKeJianCha);
			Cell cell4 = titleRow.createCell(32);
			cell4.setCellValue("妇科检查");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("妇科检查").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+32);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("妇科检查").get(i));
			}
			
			CellRangeAddress xueXing = new CellRangeAddress(0, 0, 39, 40);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(xueXing);
			Cell cell5 = titleRow.createCell(39);
			cell5.setCellValue("血型");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("血型").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+39);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("血型").get(i));
			}
			
			CellRangeAddress fengShiJianYanSiXiang = new CellRangeAddress(0, 0, 41, 44);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(fengShiJianYanSiXiang);
			Cell cell6 = titleRow.createCell(41);
			cell6.setCellValue("风湿检验四项");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("风湿检验四项").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+41);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("风湿检验四项").get(i));
			}
			
			CellRangeAddress xueChen = new CellRangeAddress(0, 0, 45, 46);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(xueChen);
			Cell cell7 = titleRow.createCell(45);
			cell7.setCellValue("血沉");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("血沉").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+45);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("血沉").get(i));
			}
			
			CellRangeAddress weiGongNeng = new CellRangeAddress(0, 0, 47, 48);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(weiGongNeng);
			Cell cell8 = titleRow.createCell(47);
			cell8.setCellValue("胃功能");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("胃功能").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+47);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("胃功能").get(i));
			}
			
			CellRangeAddress jiaZhuangXianGongNengJianChe = new CellRangeAddress(0, 0, 49, 53);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(jiaZhuangXianGongNengJianChe);
			Cell cell9 = titleRow.createCell(49);
			cell9.setCellValue("甲状腺功能检测");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("甲状腺功能检测").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+49);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("甲状腺功能检测").get(i));
			}
			
			CellRangeAddress ganYanQuanTao = new CellRangeAddress(0, 0, 54, 61);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(ganYanQuanTao);
			Cell cell10 = titleRow.createCell(54);
			cell10.setCellValue("肝炎全套");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("肝炎全套").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+54);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("肝炎全套").get(i));
			}
			
			CellRangeAddress shengHuaQuanTao = new CellRangeAddress(0, 0, 62, 110);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(shengHuaQuanTao);
			Cell cell11 = titleRow.createCell(62);
			cell11.setCellValue("生化全套");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("生化全套").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+62);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("生化全套").get(i));
			}
			
			Cell jiaTaiDanBaiCell = subTitleRow.createCell(111);
			jiaTaiDanBaiCell.setCellValue("甲胎蛋白");
			
			Cell aiPeiKangYuanCell = subTitleRow.createCell(112);
			aiPeiKangYuanCell.setCellValue("癌胚抗原");
			
			Cell tangLeiKangYuan50Cell = subTitleRow.createCell(113);
			tangLeiKangYuan50Cell.setCellValue("糖类抗原50");
			
			Cell tangLeiKangYuan724Cell = subTitleRow.createCell(114);
			tangLeiKangYuan724Cell.setCellValue("糖类抗原72-4");
			
			Cell tangLeiKangYuan199Cell = subTitleRow.createCell(115);
			tangLeiKangYuan199Cell.setCellValue("糖类抗原19-9");
			
			Cell tangLeiKangYuan242Cell = subTitleRow.createCell(116);
			tangLeiKangYuan242Cell.setCellValue("糖类抗原242");
			
			Cell xiBaoJiaoDanBai19PianDuanCell = subTitleRow.createCell(117);
			xiBaoJiaoDanBai19PianDuanCell.setCellValue("细胞角蛋白19片段");
			
			Cell shenJingYuanTeYiXingXiChunHuaMeiCell = subTitleRow.createCell(118);
			shenJingYuanTeYiXingXiChunHuaMeiCell.setCellValue("神经元特异性烯醇化酶");
			
			Cell niaoDianCell = subTitleRow.createCell(119);
			niaoDianCell.setCellValue("尿碘");
			
			CellRangeAddress gongJingAiZaoQiShaiCha = new CellRangeAddress(0, 0, 120, 142);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(gongJingAiZaoQiShaiCha);
			Cell cell12 = titleRow.createCell(117);
			cell12.setCellValue("宫颈癌早期筛查");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("宫颈癌早期筛查").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+117);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("宫颈癌早期筛查").get(i));
			}
			
			Cell tangLeiKangYuan125Cell = subTitleRow.createCell(143);
			tangLeiKangYuan125Cell.setCellValue("糖类抗原125");
			
			Cell aiPeiKangYuan153Cell = subTitleRow.createCell(144);
			aiPeiKangYuan153Cell.setCellValue("糖类抗原15-3");
			
			Cell teYiBetaRenRongMaoMoCuXingXianJiSuCell = subTitleRow.createCell(145);
			teYiBetaRenRongMaoMoCuXingXianJiSuCell.setCellValue("特异β人绒毛膜促性腺激素");
			
			Cell tongXingBanGuangAnSuanCell = subTitleRow.createCell(146);
			tongXingBanGuangAnSuanCell.setCellValue("同型半胱氨酸");
			
			Cell xiongGanJiMeiCell = subTitleRow.createCell(147);
			xiongGanJiMeiCell.setCellValue("胸苷激酶1");
			
			Cell cell13 = titleRow.createCell(148);
			cell13.setCellValue("酒精基因检测");
			
			Cell aldh2JiYinXingJianCeCell = subTitleRow.createCell(148);
			aldh2JiYinXingJianCeCell.setCellValue("ALDH2基因型检测");
			
			CellRangeAddress qianLieXianTeYiXingKangYuanJianCe = new CellRangeAddress(0, 0, 149, 150);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(qianLieXianTeYiXingKangYuanJianCe);
			Cell cell14 = titleRow.createCell(146);
			cell14.setCellValue("前列腺特异性抗原检查");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("前列腺特异性抗原检查").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+146);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("前列腺特异性抗原检查").get(i));
			}
			
			CellRangeAddress xueChangGui = new CellRangeAddress(0, 0, 151, 174);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(xueChangGui);
			Cell cell15 = titleRow.createCell(151);
			cell15.setCellValue("血常规");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("血常规").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+148);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("血常规").get(i));
			}
			
			CellRangeAddress niaoChangGui = new CellRangeAddress(0, 0, 175, 185);
			// 在sheet里增加合并单元格
			sheet.addMergedRegion(niaoChangGui);
			Cell cell16 = titleRow.createCell(175);
			cell16.setCellValue("尿常规");
			for(int i = 0; i < ProjectToExcelTitleMap.getProjectMap().get("尿常规").size(); i++){
				Cell subTitleCell = subTitleRow.createCell(i+172);
				subTitleCell.setCellValue(ProjectToExcelTitleMap.getProjectMap().get("尿常规").get(i));
			}
			
			Cell hba1cCell = subTitleRow.createCell(186);
			hba1cCell.setCellValue("HbA1c");
			
			Cell runXianCaiSeDuoPuLeChaoShengJianChaCell = subTitleRow.createCell(187);
			runXianCaiSeDuoPuLeChaoShengJianChaCell.setCellValue("乳腺彩色多普勒超声检查");
			
			Cell yeWoLinBaJieCaiSeDuoPuLeChaoShengJianChaCell = subTitleRow.createCell(188);
			yeWoLinBaJieCaiSeDuoPuLeChaoShengJianChaCell.setCellValue("腋窝淋巴结彩色多普勒超声检查");
			
			Cell runXianMuBaCell = subTitleRow.createCell(189);
			runXianMuBaCell.setCellValue("乳腺钼钯");
			
			Cell xinZangCaiSeDuoPuLeChaoShengCell = subTitleRow.createCell(190);
			xinZangCaiSeDuoPuLeChaoShengCell.setCellValue("心脏彩色多普勒超声");
			
			Cell jiaZhuangXianCaiSeDuoPuLeChaoShengJianChaCell = subTitleRow.createCell(191);
			jiaZhuangXianCaiSeDuoPuLeChaoShengJianChaCell.setCellValue("甲状腺彩色多普勒超声检查");
			
			Cell miNiaoXiCaiSeDuoPuLeChaoShengJianChaCell = subTitleRow.createCell(192);
			miNiaoXiCaiSeDuoPuLeChaoShengJianChaCell.setCellValue("泌尿系彩色多普勒超声检查");
			
			Cell jingFuBuFuKeCaiSeDuoPuLeChaoShengJianChaCell = subTitleRow.createCell(193);
			jingFuBuFuKeCaiSeDuoPuLeChaoShengJianChaCell.setCellValue("经腹部妇科彩色多普勒超声检查");
			
			Cell ganDanYiPiCaiSeDuoPuLeChaoShengJianChaCell = subTitleRow.createCell(194);
			ganDanYiPiCaiSeDuoPuLeChaoShengJianChaCell.setCellValue("肝胆胰脾彩色多普勒超声检查");
			
			Cell xinDianTuCell = subTitleRow.createCell(195);
			xinDianTuCell.setCellValue("心电图");
			
			Cell guMiDuCeDingCell = subTitleRow.createCell(196);
			guMiDuCeDingCell.setCellValue("骨密度测定");
			
			Cell fuKeYeJiBoCengXiBaoXueJianChaYuZhenDuanCell = subTitleRow.createCell(197);
			fuKeYeJiBoCengXiBaoXueJianChaYuZhenDuanCell.setCellValue("妇科液基薄层细胞学检查与诊断");
			
			Cell xiongBuZhengCeWeiCell = subTitleRow.createCell(198);
			xiongBuZhengCeWeiCell.setCellValue("胸部正侧位");
			
			Cell xiongBuCTPingSaoCell = subTitleRow.createCell(199);
			xiongBuCTPingSaoCell.setCellValue("胸部CT平扫");
			
			Cell luNaoCTPingSaoCell = subTitleRow.createCell(200);
			luNaoCTPingSaoCell.setCellValue("颅脑CT平扫");
			
			Cell luNaoMRPingSaoChengXiangAndDWIChengXiangCell = subTitleRow.createCell(201);
			luNaoMRPingSaoChengXiangAndDWIChengXiangCell.setCellValue("颅脑MR平扫成像+DWI成像");
			
			Cell jingLuCaiSeDuoPuLeChaoShengJianChaCell = subTitleRow.createCell(202);
			jingLuCaiSeDuoPuLeChaoShengJianChaCell.setCellValue("经颅彩色多普勒超声检查");
			
			Cell youMenLuoGanJunIgGKangTiCell = subTitleRow.createCell(203);
			youMenLuoGanJunIgGKangTiCell.setCellValue("幽门螺杆菌IgG抗体");
			
			Cell c13HuQiShiYanCell = subTitleRow.createCell(204);
			c13HuQiShiYanCell.setCellValue("C13呼气试验");
			
			Cell c14HuQiShiYanCell = subTitleRow.createCell(205);
			c14HuQiShiYanCell.setCellValue("C14呼气试验");
			
			Cell shangXiaoHuaDaoBeiCanCell = subTitleRow.createCell(206);
			shangXiaoHuaDaoBeiCanCell.setCellValue("上消化道钡餐");
			
			Cell naoBuDuoPuLeChaoShengJianChaCell = subTitleRow.createCell(207);
			naoBuDuoPuLeChaoShengJianChaCell.setCellValue("脑多普勒超声检查");
			
			int rowIndex = 2;
			for(String s : filePath){
				String suffix = s.substring(s.lastIndexOf("."));
				if(!".docx".equalsIgnoreCase(suffix) && !".doc".equalsIgnoreCase(suffix)){
	        		continue;
	        	}
				List<List> list = WordDocumentExtractor.getResult(s);
				for (List dataList : list) {
					creatData(workBook, rowIndex, dataList);
					rowIndex++;
				}
	        }
			path = basePath + File.separator + "transform" + File.separator + DateUtil.getStrNowDate("yyyyMMddHHmmssSSSSSS") + ".xls";
			File transformFile = new File(path);
			if(!transformFile.exists()){
				transformFile.getParentFile().mkdirs();
				transformFile.createNewFile();
			}
			os = new FileOutputStream(path);
			workBook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}
}

