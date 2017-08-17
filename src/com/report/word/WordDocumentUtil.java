package com.report.word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;

import com.report.util.ChineseUtil;


/**
 *

 * @Description:Word试卷文档模型化解析

 * @author <a href="mailto:thoslbt@163.com">Thos</a> 42  * @ClassName: WordToHtml 44  * @version V1.0
 *
 */
public class WordDocumentUtil {

    /**
     * 回车符ASCII码
     */
    private static final short ENTER_ASCII = 13;

    /**
     * 空格符ASCII码
     */
    private static final short SPACE_ASCII = 32;

    /**
     * 水平制表符ASCII码
     */
    private static final short TABULATION_ASCII = 9;

	public static String htmlText = "";
	public static String htmlTextTbl = "";
	public static int counter = 0;
	public static int beginPosi = 0;
	public static int endPosi = 0;
	public static int beginArray[];
	public static int endArray[];
	public static String htmlTextArray[];
	public static boolean tblExist = false;

	public static final String inputFile = "D:\\temp\\test03.doc";
	//public static final String inputFile = "D:\\temp\\test07.docx";
	public static final String htmlFile = "E:/Medical/abc.html";

	public static final String departmentNoRegEx = "\\d{10}";
	public static final String numberRegEx = "\\d+";
	
	public static void main(String argv[]) {
		try {
			//getWordAndStyle(inputFile);
			IPoiExtractContent wordService = null;
			if(inputFile.substring(inputFile.lastIndexOf(".")+1).equalsIgnoreCase("docx")){
				wordService = new PoiXwpfExtractContentImpl();
				String str = wordService.getContent(inputFile);
				System.out.println(str);
	        }else{ //2003
	        	wordService = new PoiHwpfExtractContentImpl();
	        	/*FileInputStream in = new FileInputStream(new File(inputFile));
		        HWPFDocument doc = new HWPFDocument(in);
				String str = wordService.getContent(doc);*/
	        	String str = wordService.getContent(inputFile);
	        	System.out.println(str);
	        	String personalInfo = "";
	        	if(str.indexOf("总检结论及建议") != -1){
	        		personalInfo = str.substring(0,str.indexOf("总检结论及建议"));
	        	}
	        	String overallInfo = "";
	        	if(str.indexOf("总检结论及建议") != -1 && str.indexOf("内科检查") != -1){
	        		overallInfo = str.substring(str.indexOf("总检结论及建议"), str.indexOf("内科检查"));
	        	}
	        	String projectText = str.substring(str.indexOf("内科检查"));
	        	String bloodRoutineInfo = "";
	        	if(str.indexOf("血常规") != -1){
	        		bloodRoutineInfo = str.substring(str.indexOf("血常规"),str.indexOf("尿常规"));
	        	}
	        	Pattern projectPattern = Pattern.compile(
						"检查日期:(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)",
						Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
				Map<String,Map> projectMap = new HashMap<String,Map>();
				Map projectItemMap = new HashMap();
				List projectList = new ArrayList();
				int lastPosition = 0;
				Matcher projectMatcher = projectPattern.matcher(projectText);
				while (projectMatcher.find()) {
					//System.out.println("t：" + projectMatcher.group());
					int currentPosition = projectMatcher.start();
					projectList.add(projectText.substring(lastPosition == 0 ? lastPosition : lastPosition + projectMatcher.group().length(),currentPosition));
					lastPosition = currentPosition;
				} 
				for(int i = 0; i < projectList.size(); i++){
					System.out.println(projectList.get(i));
				}
	        	//System.out.println(projectText);
	        	Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(personalInfo);
	            personalInfo = m.replaceAll("");
	        	//登记号的处理
				Pattern pattern = Pattern.compile(departmentNoRegEx);
				Matcher matcher = pattern.matcher(personalInfo);
				//boolean rs = matcher.matches();
				ArrayList<String> strs = new ArrayList<String>();
				while (matcher.find()) {
					strs.add(matcher.group(0));
				}
				for (String s : strs) {
					System.out.println(s);
				}
				//日期的处理
				String reportDate = matchDateString(personalInfo);
				System.out.println(reportDate);
				
				//去空白字符
				String notBlankCharacter = "";
				List keywords = new ArrayList();
				keywords.add("姓名");
				keywords.add("性别");
				keywords.add("年龄");
				keywords.add("部门");
				keywords.add("日期");
				keywords.add("单位");
				for(int i = 0; i < personalInfo.length(); i++){
					 char c = personalInfo.charAt(i);
	                if (!ChineseUtil.isMessyCode(c+"")){
	                	notBlankCharacter += c;
	                }
				}
				String words = "";
				//姓名的处理
				String userName = "";
				String userNameCharacter = notBlankCharacter.substring(notBlankCharacter.indexOf("姓名")+2);
				for(int i = 0; i < userNameCharacter.length(); i++){
					char c = userNameCharacter.charAt(i);
					//两位组词
	            	words = words.substring(words.length() - 1 <= 0 ? 0 : words.length() - 1,words.length());
	            	words += c;
	            	if(keywords.contains(words)){
	            		userName = userNameCharacter.substring(0, userNameCharacter.indexOf(words));
	            		break;
	            	}
				}
				System.out.println(userName);
				//姓别的处理
				String sex = "";
				String sexCharacter = notBlankCharacter.substring(notBlankCharacter.indexOf("性别")+2);
				for(int i = 0; i < sexCharacter.length(); i++){
					char c = sexCharacter.charAt(i);
					//两位组词
	            	words = words.substring(words.length() - 1 <= 0 ? 0 : words.length() - 1,words.length());
	            	words += c;
	            	if(keywords.contains(words)){
	            		sex = sexCharacter.substring(0, sexCharacter.indexOf(words));
	            		break;
	            	}
				}
				System.out.println(sex);
				//年龄的处理
				String age = "";
				String ageCharacter = notBlankCharacter.substring(notBlankCharacter.indexOf("年龄")+2);
				for(int i = 0; i < ageCharacter.length(); i++){
					char c = ageCharacter.charAt(i);
					//两位组词
	            	words = words.substring(words.length() - 1 <= 0 ? 0 : words.length() - 1,words.length());
	            	words += c;
	            	if(keywords.contains(words)){
	            		age = ageCharacter.substring(0, ageCharacter.indexOf(words));
	            		Pattern agePattern = Pattern.compile(numberRegEx);
	    				Matcher ageMatcher = agePattern.matcher(age);
	    				while (ageMatcher.find()){
	    					age = ageMatcher.group(0);
	    				}
	            		break;
	            	}
				}
				System.out.println(age);
				//单位的处理
				String company = "";
				String companyCharacter = notBlankCharacter.substring(notBlankCharacter.indexOf("单位")+2);
				for(int i = 0; i < companyCharacter.length(); i++){
					char c = companyCharacter.charAt(i);
					//两位组词
	            	words = words.substring(words.length() - 1 <= 0 ? 0 : words.length() - 1,words.length());
	            	words += c;
	            	if(keywords.contains(words)){
	            		company = companyCharacter.substring(0, companyCharacter.indexOf(words));
	            		break;
	            	}
				}
				System.out.println(company);
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * word文档图片存储路径
     * @return
     */
    public static String wordImageFilePath(){
        return "E:/Medical/WordImage/";
    }

    /**
     *  word文档图片Web访问路径
     * @return
     */
    public static String wordImgeWebPath(){
        return "E:/Medical/WordImage/";
    }

    /**
     * 读取每个文字样式
     * 
     * @param fileName
     * @throws Exception
     */


    public static void getWordAndStyle(String fileName) throws Exception {
        FileInputStream in = new FileInputStream(new File(fileName));
        HWPFDocument doc = new HWPFDocument(in);

        Range rangetbl = doc.getRange();//得到文档的读取范围   
        TableIterator it = new TableIterator(rangetbl); 
        int num=100;         

        beginArray=new int[num];
        endArray=new int[num];
        htmlTextArray=new String[num];

        // 取得文档中字符的总数
        int length = doc.characterLength();
        // 创建图片容器
        PicturesTable pTable = doc.getPicturesTable();

        htmlText = "<html><head><title>" + doc.getSummaryInformation().getTitle() + "</title></head><body>";
        // 创建临时字符串,好加以判断一串字符是否存在相同格式

        if(it.hasNext())
        {
            readTable(it,rangetbl);
        }

        int cur=0;

        String tempString = "";
        for (int i = 0; i < length - 1; i++) {
            // 整篇文章的字符通过一个个字符的来判断,range为得到文档的范围
            Range range = new Range(i, i + 1, doc);

            CharacterRun cr = range.getCharacterRun(0); 
            
            if(tblExist)
            {
                if(i==beginArray[cur])
                {         
                    htmlText+=tempString+htmlTextArray[cur];
                    tempString="";
                    i=endArray[cur]-1;
                    cur++;
                    continue;
                }
            }
            if (pTable.hasPicture(cr)) {
                htmlText +=  tempString ;                
                // 读写图片                
                readPicture(pTable, cr);
                tempString = "";                
            } 
            else {

                Range range2 = new Range(i + 1, i + 2, doc);
                // 第二个字符
                CharacterRun cr2 = range2.getCharacterRun(0);
                char c = cr.text().charAt(0);

                // 判断是否为空格符
                if (c == SPACE_ASCII)
                    tempString += "&nbsp;";
                // 判断是否为水平制表符
                else if (c == TABULATION_ASCII)
                    tempString += "&nbsp;&nbsp;&nbsp;&nbsp;";
                // 比较前后2个字符是否具有相同的格式
                boolean flag = compareCharStyle(cr, cr2);
                if (flag&&c !=ENTER_ASCII)
                    tempString += cr.text();
                else {
                    String fontStyle = "<span style='font-family:" + cr.getFontName() + ";font-size:" + cr.getFontSize() / 2
                    + "pt;color:"+getHexColor(cr.getIco24())+";";

                    if (cr.isBold())
                        fontStyle += "font-weight:bold;";
                    if (cr.isItalic())
                        fontStyle += "font-style:italic;";

                    htmlText += fontStyle + "' >" + tempString + cr.text();
                    htmlText +="</span>";
                    tempString = "";
                }
                // 判断是否为回车符
                if (c == ENTER_ASCII)
                    htmlText += "<br/>";

            }
        }

        htmlText += tempString+"</body></html>";
        //生成html文件
        writeFile(htmlText);
        System.out.println("------------WordToHtml转换成功----------------");
        //word试卷数据模型化
        analysisHtmlString(htmlText);
        System.out.println("------------WordToHtml模型化成功----------------");
    }

    /**
     * 读写文档中的表格
     * 
     * @param pTable
     * @param cr
     * @throws Exception
     */
    public static void readTable(TableIterator it, Range rangetbl) throws Exception {

        htmlTextTbl="";
        //迭代文档中的表格  

        counter=-1;
        while (it.hasNext()) 
        { 
            tblExist=true;
            htmlTextTbl="";
            Table tb = (Table) it.next();    
            beginPosi=tb.getStartOffset() ;
            endPosi=tb.getEndOffset();

            //System.out.println("............"+beginPosi+"...."+endPosi);
            counter=counter+1;
            //迭代行，默认从0开始
            beginArray[counter]=beginPosi;
            endArray[counter]=endPosi;

            htmlTextTbl+="<table border>";
            for (int i = 0; i < tb.numRows(); i++) {      
                TableRow tr = tb.getRow(i);   

                htmlTextTbl+="<tr>";
                //迭代列，默认从0开始   
                for (int j = 0; j < tr.numCells(); j++) {
                    TableCell td = tr.getCell(j);//取得单元格
                    int cellWidth=td.getWidth();

                    //取得单元格的内容   
                    for(int k=0;k<td.numParagraphs();k++){      
                        Paragraph para =td.getParagraph(k);      
                        String s = para.text().toString().trim();   
                        if(s=="")
                        {
                            s=" ";
                        }
                        htmlTextTbl += "<td width="+cellWidth+ ">"+s+"</td>";
                    }       
                }      
            }   
            htmlTextTbl+="</table>" ;    
            htmlTextArray[counter]=htmlTextTbl;

        } //end while 
    }    

    /**
     * 读写文档中的图片
     * 
     * @param pTable
     * @param cr
     * @throws Exception
     */
    public static void readPicture(PicturesTable pTable, CharacterRun cr) throws Exception {
        // 提取图片
        Picture pic = pTable.extractPicture(cr, false);
        // 返回POI建议的图片文件名
        String afileName = pic.suggestFullFileName();

        File file = new File(wordImageFilePath());
        System.out.println(file.mkdirs());
        OutputStream out = new FileOutputStream(new File( wordImageFilePath()+ File.separator + afileName));
        pic.writeImageContent(out);
        htmlText += "<img src='"+wordImgeWebPath()+ afileName
        + "' mce_src='"+wordImgeWebPath()+ afileName + "' />";
    }


    public static boolean compareCharStyle(CharacterRun cr1, CharacterRun cr2) 
    {
        boolean flag = false;
        if (cr1.isBold() == cr2.isBold() && cr1.isItalic() == cr2.isItalic() && cr1.getFontName().equals(cr2.getFontName()) 
                && cr1.getFontSize() == cr2.getFontSize()&& cr1.getColor() == cr2.getColor()) 
        {
            flag = true;
        }
        return flag;
    }

    /*** 字体颜色模块start ********/
    public static int red(int c) {  
        return c & 0XFF;  
    }  

    public static int green(int c) {  
        return (c >> 8) & 0XFF;  
    }  

    public static int blue(int c) {  
        return (c >> 16) & 0XFF;  
    }  

    public static int rgb(int c) {  
        return (red(c) << 16) | (green(c) << 8) | blue(c);  
    }  

    public static String rgbToSix(String rgb) {  
        int length = 6 - rgb.length();  
        String str = "";  
        while (length > 0) {  
            str += "0";  
            length--;  
        }  
        return str + rgb;  
    }  


    public static String getHexColor(int color) {  
        color = color == -1 ? 0 : color;  
        int rgb = rgb(color);  
        return "#" + rgbToSix(Integer.toHexString(rgb));  
    }  
    /** 字体颜色模块end ******/

    /**
     * 写文件
     * 
     * @param s
     */
    public static void writeFile(String s) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        PrintWriter writer = null;
        try {
            File file = new File(htmlFile);
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(s);
            bw.close();
            fos.close();
            //编码转换
            writer = new PrintWriter(file, "GB2312");
            writer.write(s);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * 分析html
     * @param s
     */
    public static void analysisHtmlString(String s){

        String q[] = s.split("<br/>");

        LinkedList<String> list = new LinkedList<String>();

        //清除空字符
        for (int i = 0; i < q.length; i++) {
            if(StringUtils.isNotBlank(q[i].toString().replaceAll("</?[^>]+>","").trim())){

                list.add(q[i].toString().trim());
            }
        }
        String[] result = {};
        String ws[]=list.toArray(result);
        int singleScore = 0;
        int multipleScore = 0;
        int fillingScore = 0;
        int judgeScore = 0;
        int askScore = 0;
        int singleNum = 0;
        int multipleNum = 0;
        int fillingNum = 0;
        int judgeNum = 0;
        int askNum = 0;
        /***********试卷基础数据赋值*********************/
        for (int i = 0; i < ws.length; i++) {
            String delHtml=ws[i].toString().replaceAll("</?[^>]+>","").trim();//去除html
            if(delHtml.contains("、单选题")){
                String numScore=numScore(delHtml);
                singleNum= Integer.parseInt(numScore.split(",")[0]) ;
                singleScore=Integer.parseInt(numScore.split(",")[1]) ;
            }else if(delHtml.contains("、多择题")){
                String numScore=numScore(delHtml);
                multipleNum= Integer.parseInt(numScore.split(",")[0]) ;
                multipleScore=Integer.parseInt(numScore.split(",")[1]) ;
            }else if(delHtml.contains("、填空题")){
                String numScore=numScore(delHtml);
                fillingNum= Integer.parseInt(numScore.split(",")[0]) ;
                fillingScore=Integer.parseInt(numScore.split(",")[1]) ;
            }else if(delHtml.contains("、判断题")){
                String numScore=numScore(delHtml);
                judgeNum= Integer.parseInt(numScore.split(",")[0]) ;
                judgeScore=Integer.parseInt(numScore.split(",")[1]) ;
            }else if(delHtml.contains("、问答题")){
                String numScore=numScore(delHtml);
                askNum= Integer.parseInt(numScore.split(",")[0]) ;
                askScore=Integer.parseInt(numScore.split(",")[1]) ;
            }

        }
        /**************word试卷数据模型化****************/
        List<Map<String, Object>> bigTiMaps = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> smalMaps = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> sleMaps = new ArrayList<Map<String,Object>>();
        String htmlText="";
        int smalScore=0;
        for (int j = ws.length-1; j>=0; j--) {
            String html= ws[j].toString().trim();//html格式
            String delHtml=ws[j].toString().replaceAll("</?[^>]+>","").trim();//去除html
            if(!isSelecteTitele(delHtml)&&!isTitele(delHtml)&&!isBigTilete(delHtml)){//无
                if(isTitele(delHtml)){
                    smalScore=itemNum(delHtml);
                }
                htmlText=html+htmlText;
            }else if(isSelecteTitele(delHtml)){//选择题选择项
                Map<String, Object> sleMap = new HashMap<String, Object>();//选择题选择项
                sleMap.put("seleteItem", delHtml.substring(0, 1));
                sleMap.put("seleteQuest", html+htmlText);
                sleMaps.add(sleMap);
            }else if(isTitele(delHtml)){//小标题
                Map<String, Object> smalMap = new HashMap<String, Object>();//小标题
                smalMap.put("smalTilete", html+htmlText);
                smalMap.put("smalScore", smalScore>0?smalScore+"":itemNum(delHtml)+"");
                smalMap.put("sleMaps", sleMaps);
                smalMaps.add(smalMap);
            }else if(isBigTilete(delHtml)){//大标题
                Map<String, Object> bigTiMap = new HashMap<String, Object>();//大标题
                bigTiMap.put("bigTilete", delHtml.substring(2, 5));
                bigTiMap.put("smalMaps", smalMaps);
                bigTiMaps.add(bigTiMap);
            }    

        }
        //System.out.println(bigTiMaps.toString());
    }

    //获取大题-题目数量以及题目总计分数
    public static String numScore(String delHtml){

        String regEx="[^0-9+，|,+^0-9]";   
        Pattern p = Pattern.compile(regEx);   
        Matcher m = p.matcher(delHtml);
        String s=m.replaceAll("").trim();
        if(StringUtils.isNotBlank(s)){
            if(s.contains(",")){
                return s;
            }else if(s.contains("，")){
                return s.replace("，", ",");
            }else{
                return "0,0";
            }
        }else{
            return "0,0";
        }

    }
    //获取每小题分数
    public static int itemNum(String delHtml){
        Pattern pattern = Pattern.compile("（(.*?)）"); //中文括号 
        Matcher matcher = pattern.matcher(delHtml);
        if (matcher.find()&&isNumeric(matcher.group(1))){
            return Integer.parseInt(matcher.group(1));
        }else {
            return 0;
        }
    }
    //判断Str是否是 数字
    public static boolean isNumeric(String str){ 
        Pattern pattern = Pattern.compile("[0-9]*"); 
        return pattern.matcher(str).matches();    
    } 
    //判断Str是否存在小标题号
    public static boolean isTitele(String str){
        Pattern pattern = Pattern.compile("^([\\d]+[-\\、].*)"); 
        return pattern.matcher(str).matches();
    }
    //判断Str是否是选择题选择项
    public static boolean isSelecteTitele(String str){
        Pattern pattern = Pattern.compile("^([a-zA-Z]+[-\\：].*)"); 
        return pattern.matcher(str).matches();
    }
    //判断Str是否是大标题
    public static boolean isBigTilete(String str){
        boolean iso= false ;
        if(str.contains("一、")){
            iso=true;
        }else if(str.contains("二、")){
            iso=true;
        }else if(str.contains("三、")){
            iso=true;
        }else if(str.contains("四、")){
            iso=true;
        }else if(str.contains("五、")){
            iso=true;
        }else if(str.contains("六、")){
            iso=true;
        }else if(str.contains("七、")){
            iso=true;
        }else if(str.contains("八、")){
            iso=true;
        }
        return iso;
    }
    
    /** 
     * (1)能匹配的年月日类型有： 
     *    2014年4月19日 
     *    2014年4月19号 
     *    2014-4-19 
     *    2014/4/19 
     *    2014.4.19 
     * (2)能匹配的时分秒类型有： 
     *    15:28:21 
     *    15:28 
     *    5:28 pm 
     *    15点28分21秒 
     *    15点28分 
     *    15点 
     * (3)能匹配的年月日时分秒类型有： 
     *    (1)和(2)的任意组合，二者中间可有任意多个空格 
     * 如果dateStr中有多个时间串存在，只会匹配第一个串，其他的串忽略 
     * @param text 
     * @return 
     */  
	private static String matchDateString(String dateStr) {
		try {
			List matches = null;
			Pattern p = Pattern.compile(
					"(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			Matcher matcher = p.matcher(dateStr);
			if (matcher.find() && matcher.groupCount() >= 1) {
				matches = new ArrayList();
				for (int i = 1; i <= matcher.groupCount(); i++) {
					String temp = matcher.group(i);
					matches.add(temp);
				}
			} else {
				matches = Collections.EMPTY_LIST;
			}
			if (matches.size() > 0) {
				return ((String) matches.get(0)).trim();
			} else {
			}
		} catch (Exception e) {
			return "";
		}

		return dateStr;
	}
}