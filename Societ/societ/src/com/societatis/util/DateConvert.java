package com.societatis.util;

import java.util.Calendar;
import java.util.Date;


public class DateConvert {


	public static Date getDateFromString(String date) {
		
		String[] dateParts=date.split("-");
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR,Integer.parseInt(dateParts[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(dateParts[1])-1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[2]));
		
		return cal.getTime();	
	}
}
