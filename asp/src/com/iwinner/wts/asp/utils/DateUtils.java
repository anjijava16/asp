package com.iwinner.wts.asp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date getExpireDateFormat(String expireDate){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate=null;
		try {
			currentDate = sdf.parse(expireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currentDate;
	}
}
