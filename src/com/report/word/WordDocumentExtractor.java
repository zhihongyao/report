package com.report.word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordDocumentExtractor {

	public static void main(String[] args) {
		//整体测试输出
		System.out.println("-----------2007-----------");
		String path = "E:\\Medical\\exam\\丁锦章.docx";
		List<List> list = getResult(path);
		for (List<String> item : list) {
			for(String str : item) {
				//System.out.println(str);
			}
		}
//		System.out.println("-----------2003-----------");
//		String path1 = "D:\\temp\\test03.doc";
//		List<String> result1 = getResult(path1);
//		for (String str : result1) {
//			System.out.println(str);
//		}
		//单个测试输出
		System.out.println("-----------2007-----------");
//		path = "E:\\Medical\\exam\\杜西琴.docx";
		System.out.println(extractContent(getDocument(path), getPatternTest2()));
//		System.out.println("-----------2003-----------");
//		String path3 = "D:\\temp\\test03.doc";
//		System.out.println(extractContent(getDocument(path3), getPatternTest()));
	}

	
	public static List getResult(String path) {
		String content = getDocument(path);
		//System.out.println(content);
		Pattern r = Pattern.compile("姓名(.*)性别");
		Matcher m = r.matcher(content);
		List<Integer> indexs = new ArrayList<Integer>();
		while (m.find()) {
			indexs.add(m.start());
		}
		List<List> list = new ArrayList<List>();
		for (int i=0; i<indexs.size(); i++) {
			int begin = indexs.get(i);
			int end = content.length();
			if (i+1 < indexs.size()) {
				end = indexs.get(i+1);
			}
			list.add(getResultSingle(content.substring(begin, end)));
		}
		return list;
	}
	
	public static List getResultSingle(String content) {
		List<String> result = new ArrayList<String>();
		for (String pattern : Constant.Patterns) {
			result.add(extractContent(content, pattern));
		}
		return result;
	}
	
	public static String getDocument(String path) {
		IPoiExtractContent wordService = null;
		if (path.endsWith(".docx")) { // 2007
			wordService = new PoiXwpfExtractContentImpl();
		} else { // 2003
			wordService = new PoiHwpfExtractContentImpl();
		}
		String content = wordService.getContent(path);
		content = content.replaceAll(" ", "");
		//System.out.println(content);
		return content;
	}
	
	public static String extractContent(String content, String pattern) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(content);
		String str = "";
		if (m.find()) {
			//System.out.println(m.group(1));
			str = m.group(1).replaceAll("\t", " ");
			//System.out.println(str);
		}
		return str;
	}
	
	public static String getPatternTest2() {
		String pattern = "";
		// 一般信息
//		pattern = "姓名(.*)[\t]*[\r\n]*性别";
//		pattern = "性别(.*)[\t]*[\r\n]*年龄";
//		pattern = "年龄(.*)[\t]*[\r\n]*部门";
//		pattern = "部门(.*)[\t]*[\r\n]*登记号";
//		pattern = "登记号(.*)[\t]*[\r\n]*日期";
//		pattern = "日期(.*)[\t]*[\r\n]*单位";
//		pattern = "单位(.*)[\t]*[\r\n]*总检结论及建议";
//		// 总检结论及建议
//		pattern = "总检结论及建议:([\\s\\S]*?总检医生:.*[\r\n])";
//		// 内科检查
//		pattern = "身高(.*)[\t\r\n]?";
//		pattern = "体重(.*)[\t\r\n]?";
//		pattern = "BMI(.*)[\t\r\n]?";
//		pattern = "发育(.*)[\t\r\n]?";
//		pattern = "营养[\t](.*)[\r\n]";
		pattern = "嗜好([\\s\\S]+?).*?[\\t]{1}";
//		pattern = "药物过敏史(.*)[\t\r\n]?";
		pattern = "主要病史([\\s\\S]+?).*?[\\t]{1}";
//		pattern = "家族史(.*)[\t\r\n]?";
//		pattern = "心脏(.*)[\t\r\n]?";
//		pattern = "肺部检查(.*)[\t\r\n]?";
//		pattern = "腹部(.*)[\t\r\n]?";
//		pattern = "肝脏(.*)[\t\r\n]?";
//		pattern = "脾脏(.*)[\t\r\n]?";
//		pattern = "胆囊(.*)[\t\r\n]?";
//		pattern = "双肾(.*)[\t\r\n]?";
//		pattern = "收缩压(.*)[\t\r\n]?";
//		pattern = "舒张压(.*)[\t\r\n]?";
//		// 外科检查
//		pattern = "皮肤粘膜(.*)[\t\r\n]?";
//		pattern = "甲状腺(.*)[\t\r\n]?";
//		pattern = "颈部淋巴结(.*)[\t\r\n]?";
//		pattern = "颈椎(.*)[\t\r\n]?";
//		pattern = "四肢(.*)[\t\r\n]?";
//		pattern = "脊柱(.*)[\t\r\n]?";
//		pattern = "四肢关节(.*)[\t\r\n]?";
//		// 妇科检查 没有文档 占位
//		pattern = "本科既往史和现患疾病(.*)[\t\r\n]?";
//		pattern = "外阴(.*)[\t\r\n]?";
//		pattern = "阴道(.*)[\t\r\n]?";
//		pattern = "阴道分泌物(.*)[\t\r\n]?";
//		pattern = "宫颈(.*)[\t\r\n]?";
//		pattern = "子宫体(.*)[\t\r\n]?";
//		pattern = "附件(.*)[\t\r\n]?";
//		// 血型 没有文档 占位
//		pattern = "ABO[\t](.*)[\t\r\n]?";
//		pattern = "Rh\\(D\\)血型(.*)[\r\n]";
//		// 风湿检验四项
//		pattern = "CRP[\t]{1,}(.*?mg/L)[\t]{1,}";
//		pattern = "anti-CCP[\t]{1,}(.*?U/mL)[\t]{1,}";
//		pattern = "ASO[\t]{1,}(.*?IU/mL)[\t]{1,}";
//		pattern = "RF[\t]{1,}(.*?IU/mL)[\t]{1,}";
//		// 血沉 没有文档 占位
//		pattern = "ESR[\t]{1,}(.*?mm/60min)[\t]{1,}";
//		pattern = "红细胞沉降率测定(.*)[\t\r\n]?";
//		// 胃功能 没有文档 占位
//		pattern = "胃蛋白酶原I(.*)[\t\r\n]?";
//		pattern = "胃蛋白酶原II(.*)[\t\r\n]?";
//		// 甲状腺功能检测
//		pattern = "FT4[\t]{1,}(.*?pmol/L)[\t]{1,}";
//		pattern = "FT3[\t]{1,}(.*?pmol/L)[\t]{1,}";
//		pattern = "TSH[\t]{1,}(.*?uIU/mL)[\t]{1,}";
//		pattern = "ATPO[\t]{1,}(.*?IU/mL)[\t]{1,}";
//		pattern = "TG-Ab[\t]{1,}(.*?IU/mL)[\t]{1,}";
//		// 肝炎全套 没有文档 占位
//		pattern = "HAV-IgM[\t]{1,}(.*)[\t]{1}.*?[\r\n]{1}";
//		pattern = "(?:HCV-IgG|abc-IgG)[\t]{1,}(.*)[\t]{1}.*?[\r\n]{1}";
//		pattern = "乙肝表面抗体(.*)[\t\r\n]?";
//		pattern = "乙肝e抗原(.*)[\t\r\n]?";
//		pattern = "乙肝e抗体(.*)[\t\r\n]?";
//		pattern = "戊肝抗体IgM(.*)[\t\r\n]?";
//		pattern = "乙肝核心抗体(.*)[\t\r\n]?";
//		pattern = "(乙肝表面抗原.*)[\t\r\n]?";
//		// 生化全套 部分没有 占位
//		pattern = "二氧化碳[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "α-L-岩藻糖苷酶[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "亮氨酸转肽酶[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "ALB[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "α1球蛋白[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "α2球蛋白[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "β球蛋白[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "尿素氮/肌酐[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "γ球蛋白[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "阴离子间隙[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "渗透压[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "ALT[\t]{1,}(.*?U/L)[\t]{1,}";
//		pattern = "AST[\t]{1,}(.*?U/L)[\t]{1,}";
//		pattern = "肌酸激酶[\t]{1,}(.*?U/L)[\t]{1,}";
//		pattern = "乳酸脱氢酶[\t]{1,}(.*?U/L)[\t]{1,}";
//		pattern = "TP[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "ALB[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "GGT[\t]{1,}(.*?U/L)[\t]{1,}";
//		pattern = "碱性磷酸酶[\t]{1,}(.*?U/L)[\t]{1,}";
//		pattern = "总胆红素[\t]{1,}(.*?U/L)[\t]{1,}";
//		pattern = "直接胆红素[\t]{1,}(.*?U/L)[\t]{1,}";
//		pattern = "BUN[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "CREA[\t]{1,}(.*?umol/L)[\t]{1,}";
//		pattern = "GLU[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "磷[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "T-CHO[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "Trig[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "钾[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "钠[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "氯[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "HDL-C[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "URIC[\t]{1,}(.*?umol/L)[\t]{1,}";
//		pattern = "脂蛋白a[\t]{1,}(.*?umol/L)[\t]{1,}";
//		pattern = "LDL-C[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "Ca[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "ALT\\/AST[\t]{1,}([\\d.\\t]*)[\t]{1,}";
//		pattern = "球蛋白[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "白/球比[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "间接胆红素[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "腺苷脱氨酶[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "镁[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "前白蛋白[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "肌酸激酶同工酶[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "α-羟丁酸脱氢酶[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "胆碱酯酶[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		pattern = "果糖胺[\t]{1,}(.*?mmol/L)[\t]{1,}";
//		// 未分类
//		pattern = "AFP[\t]{1,}(.*?ng/mL)[\t]{1,}";
//		pattern = "CEA[\t]{1,}(.*?ng/mL)[\t]{1,}";
//		pattern = "CA50[\t]{1,}(.*?IU/mL)[\t]{1,}";
//		pattern = "CA72-4[\t]{1,}(.*?U/mL)[\t]{1,}";
//		pattern = "CA19-9[\t]{1,}(.*?U/mL)[\t]{1,}";
//		pattern = "CA242[\t]{1,}(.*?IU/mL)[\t]{1,}";
//		pattern = "CYFRA21-1[\t]{1,}(.*?ng/mL)[\t]{1,}";
//		pattern = "NSE[\t]{1,}(.*?ng/mL)[\t]{1,}";
//		pattern = "I[\t]{1,}(.*?ug/L)[\t]{1,}";
//		// 宫颈癌早期筛查 没有文档 占位
//		pattern = "HPV6(.*)[\t\r\n]?";
//		pattern = "HPV11(.*)[\t\r\n]?";
//		pattern = "HPV42(.*)[\t\r\n]?";
//		pattern = "HPV43(.*)[\t\r\n]?";
//		pattern = "HPV81(.*)[\t\r\n]?";
//		pattern = "HPV16(.*)[\t\r\n]?";
//		pattern = "HPV18(.*)[\t\r\n]?";
//		pattern = "HPV31(.*)[\t\r\n]?";
//		pattern = "HPV33(.*)[\t\r\n]?";
//		pattern = "HPV35(.*)[\t\r\n]?";
//		pattern = "HPV39(.*)[\t\r\n]?";
//		pattern = "HPV45(.*)[\t\r\n]?";
//		pattern = "HPV51(.*)[\t\r\n]?";
//		pattern = "HPV52(.*)[\t\r\n]?";
//		pattern = "HPV53(.*)[\t\r\n]?";
//		pattern = "HPV56(.*)[\t\r\n]?";
//		pattern = "HPV58(.*)[\t\r\n]?";
//		pattern = "HPV59(.*)[\t\r\n]?";
//		pattern = "HPV66(.*)[\t\r\n]?";
//		pattern = "HPV68(.*)[\t\r\n]?";
//		pattern = "HPV73(.*)[\t\r\n]?";
//		pattern = "HPV82(.*)[\t\r\n]?";
//		pattern = "HPV83(.*)[\t\r\n]?";
//		// 未分类 部分没有文档 占位
//		pattern = "糖类抗原125[\t]{1,}(.*?ug/L)[\t]{1,}";
//		pattern = "糖类抗原15-3[\t]{1,}(.*?ug/L)[\t]{1,}";
//		pattern = "特异β人绒毛膜促性腺激素[\t]{1,}(.*?ug/L)[\t]{1,}";
//		pattern = "HCY[\t]{1,}(.*?umol/L)[\t]{1,}";
//		pattern = "胸苷激酶1[\t]{1,}(.*?pmol/L)[\t]{1,}";
//		// 酒精基因检测 没有文档 占位
//		pattern = "ALDH2基因型检测[\t]{1,}(.*?pmol/L)[\t]{1,}";
//		// 前列腺特异性抗原检查 没有文档 占位
//		pattern = "游离前列腺特异性抗原[\t]{1,}(.*?pmol/L)[\t]{1,}";
//		pattern = "总前列腺特异性抗原[\t]{1,}(.*?pmol/L)[\t]{1,}";
//		// 血常规
//		pattern = "WBC[\t]{1,}(.*?109/L)[\t]{1,}";
//		pattern = "NEUT#[\t]{1,}(.*?109/L)[\t]{1,}";
//		pattern = "MONO#[\t]{1,}(.*?109/L)[\t]{1,}";
//		pattern = "LYMPH#[\t]{1,}(.*?109/L)[\t]{1,}";
//		pattern = "RBC[\t]{1,}(.*?1012/L)[\t]{1,}";
//		pattern = "HGB[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "MCV[\t]{1,}(.*?fL)[\t]{1,}";
//		pattern = "EO#[\t]{1,}(.*?109/L)[\t]{1,}";
//		pattern = "BASO#[\t]{1,}(.*?109/L)[\t]{1,}";
//		pattern = "P-LCR[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "NEUT%[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "LYMPH%[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "MONO%[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "EO%[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "BASO%[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "红细胞比积[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "MCH[\t]{1,}(.*?pg)[\t]{1,}";
//		pattern = "MCHC[\t]{1,}(.*?g/L)[\t]{1,}";
//		pattern = "RDW-C[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "PLT[\t]{1,}(.*?109/L)[\t]{1,}";
//		pattern = "PDW[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "MPV[\t]{1,}(.*?fL)[\t]{1,}";
//		pattern = "PCT[\t]{1,}(.*?%)[\t]{1,}";
//		pattern = "RDW-S[\t]{1,}(.*?fL)[\t]{1,}";
//		// 尿常规
//		pattern = "VC[\t]{1,}(.*?fL)[\t]{1,}";
//		pattern = "GLU[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		pattern = "KET[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		pattern = "BIL[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		pattern = "PRO[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		pattern = "NIT[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		pattern = "PH[\t]{1,}([\\d.]*)[\t]{1,3}";
//		pattern = "SG[\t]{1,}([\\d.]*)[\t]{1,3}";
//		pattern = "BLD[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		pattern = "URO[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		pattern = "WBC[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		// 未分类 部分没有 占位
//		pattern = "HbA1c[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "乳腺彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "腋窝淋巴结彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "乳腺钼钯[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "心脏彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "甲状腺彩色多普勒超声检查[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "泌尿系彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "经腹部妇科彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = ".*心电图.*?[\r\n]{1,}(诊断意见.*)[\r\n]{1}";
//		pattern = "骨密度测定[\r\n]{1,}([\\s\\S]*?)报告";
//		pattern = "妇科液基薄层细胞学检查与诊断.*[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "胸部正侧位[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "胸部CT平扫[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "颅脑CT平扫[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "颅脑MR平扫成像+DWI成像[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "经颅彩色多普勒超声检查[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "HP-IgG[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}";
//		pattern = "C13呼气试验[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "C14呼气试验[\r\n]{1,}(.*)[\r\n]{1}";
		return pattern;
	}
}