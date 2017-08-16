package com.report.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date getNowDate(String format){
		Date date = new Date();
		Date date1 = null;
		try{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(date);
		date1 = sdf.parse(dateStr);
		}catch(Exception e){
			
		}
		return date1;
	}
	
	public static String getStrNowDate(String format){
		Date date = new Date();
		String dateStr = null;
		try{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		dateStr = sdf.format(date);
		//date1 = sdf.parse(dateStr);
		}catch(Exception e){
			
		}
		return dateStr;
	}
	
	public static String dateToString(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
 		String dateStr = null;
			try {
				dateStr = sdf.format(date);
			} catch (Exception e) {
				
			}
		return dateStr;
	}
	
	public static Date stringToDate(String dateStr,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
 		Date date=null;
			try {
				date = sdf.parse(dateStr);
			} catch (Exception e) {
				
			}
		return date;
	}
	
    public boolean isDateBefore(String date1,String date2){
        try{
         DateFormat df = DateFormat.getDateTimeInstance();
         return df.parse(date1).before(df.parse(date2));
        }catch(Exception e){
         return false;
        }
     }
    
	public static String timestampToStr(Timestamp timestamp) {
		return DateUtil.timestampToStr(timestamp, null);
	}

	public static String timestampToStr(Timestamp timestamp, String aMask) {
		String ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask))
			mask = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		ret = sdf.format(timestamp);
		return ret;
	}
}

