package com.report.word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordDocumentExtractor {

	public static void main(String[] args) {
		//整体测试输出
		System.out.println("-----------2007-----------");
		String path = "D:\\temp\\test07.docx";
		List<String> result = getResult(path);
		for (String str : result) {
			System.out.println(str);
		}
		System.out.println("-----------2003-----------");
		String path1 = "D:\\temp\\test03.doc";
		List<String> result1 = getResult(path1);
		for (String str : result1) {
			System.out.println(str);
		}
		//单个测试输出
		System.out.println("-----------2007-----------");
		String path7 = "D:\\temp\\test07.docx";
		System.out.println(extractContent(getDocument(path7), getPatternTest()));
		System.out.println("-----------2003-----------");
		String path3 = "D:\\temp\\test03.doc";
		System.out.println(extractContent(getDocument(path3), getPatternTest()));
	}

	
	public static List<String> getResult(String path) {
		String content = getDocument(path);
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
			str = m.group(1).replaceAll("\t", " ");
		}
		return str;
	}
	
	public static String getPatternTest() {
		String pattern = "";
		//一般信息
		pattern = "姓名(.*)[\t]*[\r\n]*性别";
//		pattern = "性别(.*)[\t]*[\r\n]*年龄";
//		pattern = "年龄(.*)[\t]*[\r\n]*部门";
//		pattern = "部门(.*)[\t]*[\r\n]*登记号";
//		pattern = "登记号(.*)[\t]*[\r\n]*日期";
//		pattern = "日期(.*)[\t]*[\r\n]*单位";
//		pattern = "单位(.*)[\t]*[\r\n]*总检结论及建议";
		//总检结论及建议
//		pattern = "总检结论及建议:([\\s\\S]*总检医生:.*[\r\n])";
		//内科检查
//		pattern = "身高(.*)[\t]*[\r\n]*体重";
//		pattern = "体重(.*)[\t]*[\r\n]*BMI";
//		pattern = "BMI(.*)[\t]*[\r\n]*发育";
//		pattern = "发育(.*)[\t]*[\r\n]*营养";
//		pattern = "嗜好(.*)[\t]*[\r\n]*药物过敏史";
//		pattern = "药物过敏史(.*)[\t]*[\r\n]*主要病史";
//		pattern = "主要病史(.*)[\t]*[\r\n]*家族史";
//		pattern = "家族史(.*)[\t]*[\r\n]*心脏";
//		pattern = "心脏(.*)[\t]*[\r\n]*肺部检查";
//		pattern = "肺部检查(.*)[\t]*[\r\n]*腹部";
//		pattern = "腹部(.*)[\t]*[\r\n]*肝脏";
//		pattern = "肝脏(.*)[\t]*[\r\n]*脾脏";
//		pattern = "脾脏(.*)[\t]*[\r\n]*胆囊";
//		pattern = "胆囊(.*)[\t]*[\r\n]*双肾";
//		pattern = "双肾(.*)[\t]*[\r\n]*收缩压";
//		pattern = "收缩压(.*)[\t]*[\r\n]*舒张压";
//		pattern = "舒张压(.*)[\t]*[\r\n]*医生";
		//外科检查
//		pattern = "皮肤粘膜(.*)[\t]*[\r\n]*甲状腺";
//		pattern = "甲状腺(.*)[\t]*[\r\n]*颈部淋巴结";
//		pattern = "颈部淋巴结(.*)[\t]*[\r\n]*颈椎";
//		pattern = "颈椎(.*)[\t]*[\r\n]*四肢";
//		pattern = "四肢(.*)[\t]*[\r\n]*脊柱";
//		pattern = "脊柱(.*)[\t]*[\r\n]*四肢关节";
//		pattern = "四肢关节(.*)[\t]*[\r\n]*医生";
		//妇科检查 没有文档 占位
//		pattern = "本科既往史和现患疾病(.*)[\t]*[\r\n]*外阴";
//		pattern = "外阴(.*)[\t]*[\r\n]*阴道";
//		pattern = "阴道(.*)[\t]*[\r\n]*阴道分泌物";
//		pattern = "阴道分泌物(.*)[\t]*[\r\n]*宫颈";
//		pattern = "宫颈(.*)[\t]*[\r\n]*子宫体";
//		pattern = "子宫体(.*)[\t]*[\r\n]*附件";
//		pattern = "附件(.*)[\t]*[\r\n]*附件";
		//血型 没有文档 占位
//		pattern = "ABO血型(.*)[\t]*[\r\n]*Rh(D)血型";
//		pattern = "Rh(D)血型(.*)[\t]*[\r\n]*Rh(D)血型";
		//风湿检验四项 没有文档 占位
//		pattern = "C-反应蛋白(.*)[\t]*[\r\n]*抗环瓜氨酸肽抗体";
//		pattern = "抗环瓜氨酸肽抗体(.*)[\t]*[\r\n]*抗O";
//		pattern = "抗O(.*)[\t]*[\r\n]*类风湿因子";
//		pattern = "类风湿因子(.*)[\t]*[\r\n]*类风湿因子";
		//血沉 没有文档 占位
//		pattern = "血沉(.*)[\t]*[\r\n]*红细胞沉降率测定";
//		pattern = "红细胞沉降率测定(.*)[\t]*[\r\n]*红细胞沉降率测定";
		//胃功能 没有文档 占位
//		pattern = "胃蛋白酶原I(.*)[\t]*[\r\n]*胃蛋白酶原II";
//		pattern = "胃蛋白酶原II(.*)[\t]*[\r\n]*胃蛋白酶原II";
		//甲状腺功能检测
//		pattern = "FT4[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}";
//		pattern = "FT3[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}";
//		pattern = "TSH[\t]{1,}([\\d.\\t]*uIU/mL)[\t]{1,}";
//		pattern = "ATPO[\t]{1,}([\\d.\\t]*IU/mL)[\t]{1,}";
//		pattern = "TG-Ab[\t]{1,}([\\d.\\t]*IU/mL)[\t]{1,}";
		//肝炎全套 没有文档 占位
//		pattern = "甲肝抗体IgM(.*)[\t]*[\r\n]*甲肝抗体IgM";
//		pattern = "抗丙肝IgG(.*)[\t]*[\r\n]*抗丙肝IgG";
//		pattern = "乙肝表面抗体(.*)[\t]*[\r\n]*乙肝表面抗体";
//		pattern = "乙肝e抗原(.*)[\t]*[\r\n]*乙肝e抗原";
//		pattern = "乙肝e抗体(.*)[\t]*[\r\n]*乙肝e抗体";
//		pattern = "戊肝抗体IgM(.*)[\t]*[\r\n]*戊肝抗体IgM";
//		pattern = "乙肝核心抗体(.*)[\t]*[\r\n]*乙肝核心抗体";
//		pattern = "(乙肝表面抗原.*)[\t]*[\r\n]*乙肝表面抗原";
		//生化全套 部分没有 占位
//		pattern = "二氧化碳[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "α-L-岩藻糖苷酶[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "亮氨酸转肽酶[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "ALB[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "α1球蛋白[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "α2球蛋白[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "β球蛋白[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "尿素氮/肌酐[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "γ球蛋白[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "阴离子间隙[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "渗透压[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "ALT[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}";
//		pattern = "AST[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}";
//		pattern = "肌酸激酶[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}";
//		pattern = "乳酸脱氢酶[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}";
//		pattern = "TP[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "ALB[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "GGT[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}";
//		pattern = "碱性磷酸酶[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}";
//		pattern = "总胆红素[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}";
//		pattern = "直接胆红素[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}";
//		pattern = "BUN[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "CREA[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}";
//		pattern = "GLU[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "磷[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "T-CHO[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "Trig[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "钾[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "钠[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "氯[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "HDL-C[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "URIC[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}";
//		pattern = "脂蛋白a[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}";
//		pattern = "LDL-C[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "Ca[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "ALT/AST[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "球蛋白[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "白/球比[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "间接胆红素[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "腺苷脱氨酶[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "镁[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "前白蛋白[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "肌酸激酶同工酶[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "α-羟丁酸脱氢酶[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "胆碱酯酶[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
//		pattern = "果糖胺[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}";
		//未分类
//		pattern = "AFP[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}";
//		pattern = "CEA[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}";
//		pattern = "CA50[\t]{1,}([\\d.\\t]*IU/mL)[\t]{1,}";
//		pattern = "CA72-4[\t]{1,}([\\d.\\t]*U/mL)[\t]{1,}";
//		pattern = "CA19-9[\t]{1,}([\\d.\\t]*U/mL)[\t]{1,}";
//		pattern = "CA242[\t]{1,}([\\d.\\t]*IU/mL)[\t]{1,}";
//		pattern = "CYFRA21-1[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}";
//		pattern = "NSE[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}";
//		pattern = "I[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
		//宫颈癌早期筛查 没有文档 占位
//		pattern = "HPV6[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV11[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV42[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV43[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV81[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV16[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV18[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV31[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV33[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV35[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV39[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV45[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV51[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV52[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV53[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV56[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV58[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV59[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV66[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV68[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV73[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV82[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HPV83[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
		//未分类  部分没有文档 占位
//		pattern = "糖类抗原125[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "糖类抗原15-3[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "特异β人绒毛膜促性腺激素[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}";
//		pattern = "HCY[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}";
//		pattern = "胸苷激酶1[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}";
		//酒精基因检测 没有文档 占位
//		pattern = "ALDH2基因型检测[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}";
		//前列腺特异性抗原检查 没有文档 占位
//		pattern = "游离前列腺特异性抗原[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}";
//		pattern = "总前列腺特异性抗原[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}";
		//血常规
//		pattern = "WBC[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}";
//		pattern = "NEUT#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}";
//		pattern = "MONO#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}";
//		pattern = "LYMPH#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}";
//		pattern = "RBC[\t]{1,}([\\d.\\t]*1012/L)[\t]{1,}";
//		pattern = "HGB[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "MCV[\t]{1,}([\\d.\\t]*fL)[\t]{1,}";
//		pattern = "EO#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}";
//		pattern = "BASO#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}";
//		pattern = "P-LCR[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "NEUT%[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "LYMPH%[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "MONO%[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "EO%[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "BASO%[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "红细胞比积[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "MCH[\t]{1,}([\\d.\\t]*pg)[\t]{1,}";
//		pattern = "MCHC[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}";
//		pattern = "RDW-C[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "PLT[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}";
//		pattern = "PDW[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "MPV[\t]{1,}([\\d.\\t]*fL)[\t]{1,}";
//		pattern = "PCT[\t]{1,}([\\d.\\t]*%)[\t]{1,}";
//		pattern = "RDW-S[\t]{1,}([\\d.\\t]*fL)[\t]{1,}";
		//尿常规
//		pattern = "VC[\t]{1,}([\\d.\\t]*fL)[\t]{1,}";
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
		//未分类
//		pattern = "HbA1c\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "乳腺彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "腋窝淋巴结彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "乳腺钼钯\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "心脏彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "甲状腺彩色多普勒超声检查[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "泌尿系彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "经腹部妇科彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "十二通道常规心电图检查（体检中心\\)[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "骨密度测定[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "妇科液基薄层细胞学检查与诊断[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "胸部正侧位[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "胸部CT平扫[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "颅脑CT平扫[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "颅脑MR平扫成像+DWI成像[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "经颅彩色多普勒超声检查[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "幽门螺杆菌IgG抗体[\r\n]{1,}(.*)[\r\n]{1}";
//		pattern = "C13呼气试验[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)";
//		pattern = "C14呼气试验[\r\n]{1,}(.*)[\r\n]{1}";
		return pattern;
	}
}