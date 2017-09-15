package com.report.word;

import java.util.ArrayList;
import java.util.List;

public class Constant {
	public final static List<String> Patterns = new ArrayList<String>();
	static {
		//一般信息
		Patterns.add("姓名(.*)[\t]*[\r\n]*性别");
		Patterns.add("性别(.*)[\t]*[\r\n]*年龄");
		Patterns.add("年龄(.*)[\t]*[\r\n]*部门");
		//Patterns.add("部门(.*)[\t]*[\r\n]*登记号");
		Patterns.add("登记号(.*)[\t]*[\r\n]*日期");
		Patterns.add("日期(.*)[\t]*[\r\n]*单位");
		Patterns.add("单位(.*)[\t]*[\r\n]*总检结论及建议");
		//总检结论及建议
		Patterns.add("总检结论及建议:([\\s\\S]*?总检医生:.*[\r\n])");
		//内科检查
		Patterns.add("身高[\t](.*)[\r\n]");
		Patterns.add("体重[\t](.*)[\r\n]");
		Patterns.add("BMI[\t](.*)[\r\n]");
		Patterns.add("发育[\t](.*)[\r\n]");
		Patterns.add("营养[\t](.*)[\r\n]");
		Patterns.add("嗜好([\\s\\S]+?).*?[\\t]{1}");
		Patterns.add("药物过敏史[\t](.*)[\r\n]");
		Patterns.add("主要病史([\\s\\S]+?).*?[\\t]{1}");
		Patterns.add("家族史([\\s\\S]+?).*?[\\t]{1}");
		Patterns.add("心脏[\t](.*)[\r\n]");
		Patterns.add("肺部检查[\t](.*)[\r\n]");
		Patterns.add("腹部[\t](.*)[\r\n]");
		Patterns.add("肝脏[\t](.*)[\r\n]");
		Patterns.add("脾脏[\t](.*)[\r\n]");
		Patterns.add("胆囊[\t](.*)[\r\n]");
		Patterns.add("双肾[\t](.*)[\r\n]");
		Patterns.add("收缩压[\t](.*)[\r\n]");
		Patterns.add("舒张压[\t](.*)[\r\n]");
		//外科检查
		Patterns.add("皮肤粘膜[\t](.*)[\r\n]");
		Patterns.add("甲状腺[\t](.*)[\r\n]");
		Patterns.add("颈部淋巴结[\t](.*)[\r\n]");
		Patterns.add("颈椎[\t](.*)[\r\n]");
		Patterns.add("四肢[\t](.*)[\r\n]");
		Patterns.add("脊柱[\t](.*)[\r\n]");
		Patterns.add("四肢关节[\t](.*)[\r\n]");
		//妇科检查 没有文档 占位
		Patterns.add("本科既往史和现患疾病([\\s\\S]+?).*?[\\t]{1}");
		Patterns.add("外阴[\t](.*)[\r\n]");
		Patterns.add("阴道[\t](.*)[\r\n]");
		Patterns.add("阴道分泌物[\t](.*)[\r\n]");
		Patterns.add("宫颈[\t](.*)[\r\n]");
		Patterns.add("子宫体[\t](.*)[\r\n]");
		Patterns.add("附件[\t](.*)[\r\n]");
		//血型 没有文档 占位
		Patterns.add("ABO[\t](.*)[\t\r\n]?");
		Patterns.add("Rh\\(D\\)血型(.*)[\r\n]");
		//风湿检验四项 没有文档 占位
		Patterns.add("CRP[\t]{1,}(.*?mg/L)[\t]{1,}");
		Patterns.add("anti-CCP[\t]{1,}(.*?U/mL)[\t]{1,}");
		Patterns.add("ASO[\t]{1,}(.*?IU/mL)[\t]{1,}");
		Patterns.add("RF[\t]{1,}(.*?IU/mL)[\t]{1,}");
		//血沉 没有文档 占位
		Patterns.add("ESR[\t]{1,}(.*?mm/60min)[\t]{1,}");
		Patterns.add("红细胞沉降率测定(.*)[\t\r\n]?");
		//胃功能 没有文档 占位
		Patterns.add("PGI[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}");
		Patterns.add("PGII[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}");
		//甲状腺功能检测
		Patterns.add("FT4[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}");
		Patterns.add("FT3[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}");
		Patterns.add("TSH[\t]{1,}([\\d.\\t<>]*uIU/mL)[\t]{1,}");//TSH[\t]{1}TSH[\t]{1}(.*?uIU/mL)[\t]{1,}
		Patterns.add("ATPO[\t]{1,}([\\d.\\t<>]*IU/mL)[\t]{1,}");
		Patterns.add("TG-Ab[\t]{1,}([\\d.\\t]*IU/mL)[\t]{1,}");
		//肝炎全套 没有文档 占位
		Patterns.add("HAV-IgM[\t]{1,}(.*)[\t]{1}[\\S]*?[\r\n]{1}");
		Patterns.add("(?:Anti-HCV|HCV-IgG)[\t]{1,}(.*)[\t]{1}[\\S]*?[\r\n]{1}");
		Patterns.add("(?:Anti-HBs|HBsAb)[\t]{1,}(.*)[\t]{1}[\\S]*?[\r\n]{1}");
		Patterns.add("HBeAg[\t]{1,}(.*)[\t]{1}[\\S]*?[\r\n]{1}");
		Patterns.add("(?:Anti-HBe|HBeAb)[\t]{1,}(.*)[\t]{1}[\\S]*?[\r\n]{1}");
		Patterns.add("HEV-IgM[\t]{1,}(.*)[\t]{1}[\\S]*?[\r\n]{1}");
		Patterns.add("(?:Anti-HBc|HBcAb)[\t]{1,}(.*)[\t]{1}[\\S]*?[\r\n]{1}");
		Patterns.add("HBsAg[\t]{1,}(.*)[\t]{1}[\\S]*?[\r\n]{1}");//[\t]{1,}([\\d.\\t]*IU/mL|[\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\t]{1,}
		//生化全套 部分没有 占位
		Patterns.add("CO2[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("AFU[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("LAP[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("Albumin[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("ALPHA1[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("ALPHA2[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("BETA[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("BUN/CR(.*)[\t\r\n]?");
		Patterns.add("GAMMA[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("AG[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("OSM[\t]{1,}([\\d.\\t]*mOSM/L)[\t]{1,}");
		Patterns.add("ALT[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("AST[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("CK[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("LDH[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("TP[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}");
		Patterns.add("ALB[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}");
		Patterns.add("GGT[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("ALP[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("BIL-T[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}");
		Patterns.add("BIL-D[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}");
		Patterns.add("BUN[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("CREA[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}");
		Patterns.add("GLU[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("P[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("T-CHO[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("Trig[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("K[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("Na[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("CL[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("HDL-C[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("URIC[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}");
		Patterns.add("LP\\(a\\)[\t]{1,}([\\d.\\t]*mg/L)[\t]{1,}");
		Patterns.add("LDL-C[\t]{1,}(.*?mmol/L)[\t]{1,}");
		Patterns.add("Ca[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("ALT\\/AST[\t]{1,}([\\d.\\t]*)[\t]{1,}");
		Patterns.add("G[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}");
		Patterns.add("A/G(.*)[\t]{1,}");
		Patterns.add("BIL-I[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}");
		Patterns.add("ADA[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("Mg[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("PAB[\t]{1,}([\\d.\\t]*mg/L)[\t]{1,}");
		Patterns.add("CKMB[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("HBDH[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("CHE[\t]{1,}([\\d.\\t]*U/L)[\t]{1,}");
		Patterns.add("FRUC[\t]{1,}([\\d.\\t]*mmol/L)[\t]{1,}");
		Patterns.add("TBA[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}");
		Patterns.add("SA[\t]{1,}([\\d.\\t]*mg/dL)[\t]{1,}");
		Patterns.add("GA[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		//未分类
		Patterns.add("AFP[\t]{1,}([\\d.\\t<>]*ng/mL)[\t]{1,}");
		Patterns.add("CEA[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}");
		Patterns.add("CA50[\t]{1,}([\\d.\\t]*IU/mL)[\t]{1,}");
		Patterns.add("CA72-4[\t]{1,}([\\d.\\t]*U/mL)[\t]{1,}");
		Patterns.add("CA19-9[\t]{1,}([\\d.\\t]*U/mL)[\t]{1,}");
		Patterns.add("CA242[\t]{1,}([\\d.\\t]*IU/mL)[\t]{1,}");
		Patterns.add("CYFRA21-1[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}");
		Patterns.add("NSE[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}");
		Patterns.add("I[\t]{1,}([\\d.\\t]*ug/L)[\t]{1,}");
		//宫颈癌早期筛查 没有文档 占位
		Patterns.add("HPV6(.*)[\t\r\n]?");
		Patterns.add("HPV11(.*)[\t\r\n]?");
		Patterns.add("HPV42(.*)[\t\r\n]?");
		Patterns.add("HPV43(.*)[\t\r\n]?");
		Patterns.add("HPV81(.*)[\t\r\n]?");
		Patterns.add("HPV16(.*)[\t\r\n]?");
		Patterns.add("HPV18(.*)[\t\r\n]?");
		Patterns.add("HPV31(.*)[\t\r\n]?");
		Patterns.add("HPV33(.*)[\t\r\n]?");
		Patterns.add("HPV35(.*)[\t\r\n]?");
		Patterns.add("HPV39(.*)[\t\r\n]?");
		Patterns.add("HPV45(.*)[\t\r\n]?");
		Patterns.add("HPV51(.*)[\t\r\n]?");
		Patterns.add("HPV52(.*)[\t\r\n]?");
		Patterns.add("HPV53(.*)[\t\r\n]?");
		Patterns.add("HPV56(.*)[\t\r\n]?");
		Patterns.add("HPV58(.*)[\t\r\n]?");
		Patterns.add("HPV59(.*)[\t\r\n]?");
		Patterns.add("HPV66(.*)[\t\r\n]?");
		Patterns.add("HPV68(.*)[\t\r\n]?");
		Patterns.add("HPV73(.*)[\t\r\n]?");
		Patterns.add("HPV82(.*)[\t\r\n]?");
		Patterns.add("HPV83(.*)[\t\r\n]?");
		//未分类  部分没有文档 占位
		Patterns.add("CA125[\t]{1,}([\\d.\\t]*U/mL)[\t]{1,}");
		Patterns.add("CA15-3[\t]{1,}([\\d.\\t]*U/mL)[\t]{1,}");
		Patterns.add("HCG\\+β[\t]{1,}([\\d.\\t]*IU/L)[\t]{1,}");
		Patterns.add("HCY[\t]{1,}([\\d.\\t]*umol/L)[\t]{1,}");
		Patterns.add("胸苷激酶1[\t]{1,}([\\d.\\t]*pmol/L)[\t]{1,}");
		//酒精基因检测 没有文档 占位
		Patterns.add("ALDH2基因型检测(.*)[\t\r\n]?");
		//前列腺特异性抗原检查 没有文档 占位
		Patterns.add("FPSA[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}");
		Patterns.add("TPSA[\t]{1,}([\\d.\\t]*ng/mL)[\t]{1,}");
		//血常规
		Patterns.add("WBC[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}");
		Patterns.add("NEUT#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}");
		Patterns.add("MONO#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}");
		Patterns.add("LYMPH#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}");
		Patterns.add("RBC[\t]{1,}([\\d.\\t]*1012/L)[\t]{1,}");
		Patterns.add("HGB[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}");
		Patterns.add("MCV[\t]{1,}([\\d.\\t]*fL)[\t]{1,}");
		Patterns.add("EO#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}");
		Patterns.add("BASO#[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}");
		Patterns.add("P-LCR[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("NEUT%[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("LYMPH%[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("MONO%[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("EO%[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("BASO%[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("HCT[\t]{1,}([\\d.\\t]*(%|L/L))[\t]{1,}");
		Patterns.add("MCH[\t]{1,}([\\d.\\t]*pg)[\t]{1,}");
		Patterns.add("MCHC[\t]{1,}([\\d.\\t]*g/L)[\t]{1,}");
		Patterns.add("RDW-C[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("PLT[\t]{1,}([\\d.\\t]*109/L)[\t]{1,}");
		Patterns.add("PDW[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("MPV[\t]{1,}([\\d.\\t]*fL)[\t]{1,}");
		Patterns.add("PCT[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("RDW-S[\t]{1,}([\\d.\\t]*fL)[\t]{1,}");
		//尿常规
		Patterns.add("VC[\t]{1,}(.*?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("GLU[\t]{1,}(.*?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("KET[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("BIL[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("PRO[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("NIT[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("PH[\t]{1,}([\\d.]*)[\t]{1,3}");
		Patterns.add("SG[\t]{1,}([\\d.]*)[\t]{1,3}");
		Patterns.add("BLD[\t]{1,}(.*?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("URO[\t]{1,}(.*?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("WBC[\t]{1,}(.*?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		//未分类 部分没有 占位
		//Patterns.add("HbA1c\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("HbA1c[\t]{1,}([\\d.\\t]*%)[\t]{1,}");
		Patterns.add("乳腺彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("腋窝淋巴结彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("乳腺钼钯[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("心脏彩色多普勒超声(?:检查\\[体检\\])[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("甲状腺彩色多普勒超声检查[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("泌尿系彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("经腹部妇科彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("肝胆胰脾彩色多普勒超声检查\\[体检\\][\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add(".*心电图.*?[\r\n]{1,}(诊断意见.*)[\r\n]{1}");
		Patterns.add("骨密度测定[\r\n]{1,}([\\s\\S]*?)报告");
		Patterns.add("妇科液基薄层细胞学检查与诊断.*[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("胸部正侧位.*[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("胸部CT平扫.*[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("颅脑CT平扫.*[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("颅脑MR平扫成像+DWI成像[\r\n]{1,}(.*)[\r\n]{1}");
		Patterns.add("经颅彩色多普勒超声检查[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("HP-IgG[\t]{1,}([\\D]{1,}?[\t]{1,}?[\\D]{1,}?)[\r\n]{1}");
		Patterns.add("C13呼气试验[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("C14呼气试验[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("上消化道钡餐[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
		Patterns.add("脑多普勒超声检查.*[\r\n]{1,}([\\s\\S]*?日期:[\\d-]*)");
	}
}
