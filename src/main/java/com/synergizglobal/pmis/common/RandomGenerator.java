package com.synergizglobal.pmis.common;

import java.util.Random;

public class RandomGenerator {
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static String generateAlphaNumericRandom(int count) {
		StringBuilder builder = new StringBuilder();
		for (int i = 1 ; i <= count ; i++) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public static String generateNumericRandom( int len ){
		   StringBuilder builder = new StringBuilder( len );
	        // Using numeric values 
	        String numbers = "0123456789"; 
	  
	        // Using random method 
	        Random rndm_method = new Random(); 
	  
	        for (int i = 0; i < len; i++){ 
	            builder.append(rndm_method.nextInt(numbers.length())); 
	        } 
	        return builder.toString(); 
	}
}
