package com.report.excel;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class ProjectToExcelTitleMap {
	private static Map<String,List<String>> projectMap = new HashMap<String,List<String>>();
	
	private static List yiBanXinXiList = new ArrayList();
	
	private static List neiKeJianChaList = new ArrayList();
	
	private static List waiKeJianChaList = new ArrayList();
	
	private static List fuKeJianChaList = new ArrayList();
	
	private static List xueXingList = new ArrayList();
	
	private static List fengSiJianYanSiXiangList = new ArrayList();
	
	private static List xueChenList = new ArrayList();
	
	private static List weiGongNengList = new ArrayList();
	
	private static List jiaZhuangXianGongnengJianCheList = new ArrayList();
	
	private static List ganYanQuanTaoList = new ArrayList();
	
	private static List shengHuaQuanTaoList = new ArrayList();
	
	private static List gongJingAiZaoQiShaiChaList = new ArrayList();
	
	private static List qianLieXianTeYiXingKangYuanJianChaList = new ArrayList();
	
	private static List xueChangGuiList = new ArrayList();
	
	private static List niaoChangGuiList = new ArrayList();
	
	static{
		yiBanXinXiList.add("姓名");
		yiBanXinXiList.add("性别");
		yiBanXinXiList.add("年龄");
		yiBanXinXiList.add("登记号");
		yiBanXinXiList.add("日期");
		yiBanXinXiList.add("单位");
		projectMap.put("一般信息", yiBanXinXiList);
		
		neiKeJianChaList.add("身高");
		neiKeJianChaList.add("体重");
		neiKeJianChaList.add("BMI");
		neiKeJianChaList.add("发育");
		neiKeJianChaList.add("营养");
		neiKeJianChaList.add("嗜好");
		neiKeJianChaList.add("药物过敏史");
		neiKeJianChaList.add("主要病史");
		neiKeJianChaList.add("家族史");
		neiKeJianChaList.add("心脏");
		neiKeJianChaList.add("肺部检查");
		neiKeJianChaList.add("腹部");
		neiKeJianChaList.add("肝脏");
		neiKeJianChaList.add("脾脏");
		neiKeJianChaList.add("胆囊");
		neiKeJianChaList.add("双肾");
		neiKeJianChaList.add("收缩压");
		neiKeJianChaList.add("舒张压");
		projectMap.put("内科检查", neiKeJianChaList);
		
		waiKeJianChaList.add("皮肤粘膜");
		waiKeJianChaList.add("甲状腺");
		waiKeJianChaList.add("颈部淋巴结");
		waiKeJianChaList.add("颈椎");
		waiKeJianChaList.add("四肢");
		waiKeJianChaList.add("脊柱");
		waiKeJianChaList.add("四肢关节");
		projectMap.put("外科检查", waiKeJianChaList);
		
		fuKeJianChaList.add("本科既往史和现患疾病");
		fuKeJianChaList.add("外阴");
		fuKeJianChaList.add("阴道");
		fuKeJianChaList.add("阴道分泌物");
		fuKeJianChaList.add("宫颈");
		fuKeJianChaList.add("子宫体");
		fuKeJianChaList.add("附件");
		projectMap.put("妇科检查", fuKeJianChaList);
		
		xueXingList.add("ABO血型");
		xueXingList.add("Rh(D)血型");
		projectMap.put("血型", xueXingList);
		
		fengSiJianYanSiXiangList.add("C-反应蛋白");
		fengSiJianYanSiXiangList.add("抗环瓜氨酸肽抗体");
		fengSiJianYanSiXiangList.add("抗O");
		fengSiJianYanSiXiangList.add("类风湿因子");
		projectMap.put("风湿检验四项", fengSiJianYanSiXiangList);
		
		xueChenList.add("血沉");
		xueChenList.add("红细胞沉降率测定");
		projectMap.put("血沉", xueChenList);
		
		weiGongNengList.add("胃蛋白酶原I");
		weiGongNengList.add("胃蛋白酶原II");
		projectMap.put("胃功能", weiGongNengList);
		
		jiaZhuangXianGongnengJianCheList.add("血清游离甲状腺素测定");
		jiaZhuangXianGongnengJianCheList.add("血清游离三碘甲状腺原氨酸测定");
		jiaZhuangXianGongnengJianCheList.add("血清促甲状腺素测定");
		jiaZhuangXianGongnengJianCheList.add("甲状腺过氧化物酶抗体");
		jiaZhuangXianGongnengJianCheList.add("抗甲状腺球蛋白抗体测定TGA");
		projectMap.put("甲状腺功能检测", jiaZhuangXianGongnengJianCheList);
		
		ganYanQuanTaoList.add("甲肝抗体IgM");
		ganYanQuanTaoList.add("抗丙肝IgG");
		ganYanQuanTaoList.add("乙肝表面抗体");
		ganYanQuanTaoList.add("乙肝e抗原");
		ganYanQuanTaoList.add("乙肝e抗体");
		ganYanQuanTaoList.add("戊肝抗体IgM");
		ganYanQuanTaoList.add("乙肝核心抗体");
		ganYanQuanTaoList.add("乙肝表面抗原");
		projectMap.put("肝炎全套", ganYanQuanTaoList);
		
		shengHuaQuanTaoList.add("二氧化碳");
		shengHuaQuanTaoList.add("α-L-岩藻糖苷酶");
		shengHuaQuanTaoList.add("亮氨酸转肽酶");
		shengHuaQuanTaoList.add("白蛋白");
		shengHuaQuanTaoList.add("α1球蛋白");
		shengHuaQuanTaoList.add("α2球蛋白");
		shengHuaQuanTaoList.add("β球蛋白");
		shengHuaQuanTaoList.add("尿素氮/肌酐");
		shengHuaQuanTaoList.add("γ球蛋白");
		shengHuaQuanTaoList.add("阴离子间隙");
		shengHuaQuanTaoList.add("渗透压");
		shengHuaQuanTaoList.add("谷丙转氨酶");
		shengHuaQuanTaoList.add("谷草转氨酶");
		shengHuaQuanTaoList.add("肌酸激酶");
		shengHuaQuanTaoList.add("乳酸脱氢酶");
		shengHuaQuanTaoList.add("总蛋白");
		shengHuaQuanTaoList.add("白蛋白");
		shengHuaQuanTaoList.add("谷氨酰转肽酶");
		shengHuaQuanTaoList.add("碱性磷酸酶");
		shengHuaQuanTaoList.add("总胆红素");
		shengHuaQuanTaoList.add("直接胆红素");
		shengHuaQuanTaoList.add("尿素氮");
		shengHuaQuanTaoList.add("肌酐");
		shengHuaQuanTaoList.add("葡萄糖");
		shengHuaQuanTaoList.add("磷");
		shengHuaQuanTaoList.add("总胆固醇");
		shengHuaQuanTaoList.add("甘油三酯");
		shengHuaQuanTaoList.add("钾");
		shengHuaQuanTaoList.add("钠");
		shengHuaQuanTaoList.add("氯");
		shengHuaQuanTaoList.add("高密度脂蛋白");
		shengHuaQuanTaoList.add("尿酸");
		shengHuaQuanTaoList.add("脂蛋白a");
		shengHuaQuanTaoList.add("低密度脂蛋白");
		shengHuaQuanTaoList.add("钙");
		shengHuaQuanTaoList.add("ALT/AST");
		shengHuaQuanTaoList.add("球蛋白");
		shengHuaQuanTaoList.add("白/球比");
		shengHuaQuanTaoList.add("间接胆红素");
		shengHuaQuanTaoList.add("腺苷脱氨酶");
		shengHuaQuanTaoList.add("镁");
		shengHuaQuanTaoList.add("前白蛋白");
		shengHuaQuanTaoList.add("肌酸激酶同工酶");
		shengHuaQuanTaoList.add("α-羟丁酸脱氢酶");
		shengHuaQuanTaoList.add("胆碱酯酶");
		shengHuaQuanTaoList.add("果糖胺");
		shengHuaQuanTaoList.add("总胆汁酸");
		shengHuaQuanTaoList.add("唾液酸");
		shengHuaQuanTaoList.add("糖化白蛋白");
		projectMap.put("生化全套", shengHuaQuanTaoList);
		
		gongJingAiZaoQiShaiChaList.add("HPV6(低危型)");
		gongJingAiZaoQiShaiChaList.add("HPV11(低危型)");
		gongJingAiZaoQiShaiChaList.add("HPV42(低危型)");
		gongJingAiZaoQiShaiChaList.add("HPV43(低危型)");
		gongJingAiZaoQiShaiChaList.add("HPV81(低危型)");
		gongJingAiZaoQiShaiChaList.add("HPV16(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV18(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV31(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV33(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV35(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV39(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV45(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV51(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV52(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV53(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV56(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV58(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV59(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV66(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV68(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV73(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV82(高危型)");
		gongJingAiZaoQiShaiChaList.add("HPV83(高危型)");
		projectMap.put("宫颈癌早期筛查", gongJingAiZaoQiShaiChaList);
		
		qianLieXianTeYiXingKangYuanJianChaList.add("游离前列腺特异性抗原");
		qianLieXianTeYiXingKangYuanJianChaList.add("总前列腺特异性抗原");
		projectMap.put("前列腺特异性抗原检查", qianLieXianTeYiXingKangYuanJianChaList);
		
		xueChangGuiList.add("白细胞计数");
		xueChangGuiList.add("中性粒细胞计数");
		xueChangGuiList.add("单核细胞计数");
		xueChangGuiList.add("淋巴细胞计数");
		xueChangGuiList.add("红细胞计数");
		xueChangGuiList.add("血红蛋白");
		xueChangGuiList.add("平均红细胞体积");
		xueChangGuiList.add("嗜酸细胞计数");
		xueChangGuiList.add("嗜碱细胞计数");
		xueChangGuiList.add("大血小板比率");
		xueChangGuiList.add("中性粒细胞百分率");
		xueChangGuiList.add("淋巴细胞百分率");
		xueChangGuiList.add("单核细胞百分率");
		xueChangGuiList.add("嗜酸粒细胞百分率");
		xueChangGuiList.add("嗜碱粒细胞百分率");
		xueChangGuiList.add("红细胞比积");
		xueChangGuiList.add("平均血红蛋白含量");
		xueChangGuiList.add("平均血红蛋白浓度");
		xueChangGuiList.add("RBC体积分布宽度(CV)");
		xueChangGuiList.add("血小板");
		xueChangGuiList.add("血小板体积分布宽度");
		xueChangGuiList.add("平均血小板体积");
		xueChangGuiList.add("血小板压积");
		xueChangGuiList.add("RBC体积分布宽度(SD)");
		projectMap.put("血常规", xueChangGuiList);
		
		niaoChangGuiList.add("VC");
		niaoChangGuiList.add("葡萄糖");
		niaoChangGuiList.add("酮体");
		niaoChangGuiList.add("胆红素");
		niaoChangGuiList.add("尿蛋白");
		niaoChangGuiList.add("亚硝酸盐");
		niaoChangGuiList.add("PH值");
		niaoChangGuiList.add("比重");
		niaoChangGuiList.add("隐血");
		niaoChangGuiList.add("尿胆原");
		niaoChangGuiList.add("白细胞");
		projectMap.put("尿常规", niaoChangGuiList);
		
	}

	public static Map<String, List<String>> getProjectMap() {
		return projectMap;
	}

}

