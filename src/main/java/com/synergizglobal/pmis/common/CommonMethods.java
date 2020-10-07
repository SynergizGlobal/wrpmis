package com.synergizglobal.pmis.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CommonMethods {
	public static String convertStringDateToMysqlDate(String stringDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		String mysqlDate = null;
		try {
			if(!StringUtils.isEmpty(stringDate)) {
				Date convertedDate = sdf.parse(stringDate);
				mysqlDate = sqlDate.format(convertedDate);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return mysqlDate;
	}
}
