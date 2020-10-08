package com.synergizglobal.pmis.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class CommonMethods {
	public static Logger logger = Logger.getLogger(CommonMethods.class);
	
	public static String[] replaceEmptyByNullInSringArray(String[] _array) {
		for (int i = 0; i < _array.length; i++){
		    if (StringUtils.isEmpty(_array[i])) {
		        _array[i] = null;
		    }
		}
		return _array;
	}
}
