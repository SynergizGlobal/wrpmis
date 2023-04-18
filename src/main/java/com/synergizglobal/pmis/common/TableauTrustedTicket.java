package com.synergizglobal.pmis.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.util.Date;


public class TableauTrustedTicket {
	public static Logger logger = Logger.getLogger(TableauTrustedTicket.class);
	public String getTrustedTicket(String server_name) throws Exception{
		UrlGenerator ugObj = new UrlGenerator();
		
		
		
		String secret = "9PsDNdJzDp2GxN0t4/nr4EY24Vls6k3GbJIrSIWy7Tg=";
		String kid = "8f1717ac-c5bd-4ec8-9edf-f32f2be48182";
		String clientId = "38fac82e-7147-4f2d-8b21-aa0c264ec21d";
		List<String> scopes = new
	ArrayList<>(Arrays.asList("tableau:views:embed"));
		String username = "SynTrack";
		JWSSigner signer = new MACSigner(secret);
		JWSHeader header = new
	JWSHeader.Builder(JWSAlgorithm.HS256).keyID(kid).customParam("iss", clientId).build();
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
			.issuer(clientId)
			.expirationTime(new Date(new Date().getTime() + 60 * 1000)) //expires in 1 minute
			.jwtID(UUID.randomUUID().toString())
			.audience("tableau")
			.subject(username)
			.claim("scp", scopes)
			.build();
		SignedJWT signedJWT = new SignedJWT(header, claimsSet);
		signedJWT.sign(signer);
		//System.out.println("token : "+ signedJWT.serialize());		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		return signedJWT.serialize();
	}
	
	public String getExternalIpAddress() throws Exception {
	    URL whatismyip = new URL("http://checkip.amazonaws.com");
	    BufferedReader in = null;
	    try {
	        in = new BufferedReader(new InputStreamReader(
	                whatismyip.openStream()));
	        String ip = in.readLine();
	        return ip;
	    } finally {
	        if (in != null) {
	            try {
	                in.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public static String myPublicIp() {

	    /*nslookup myip.opendns.com resolver1.opendns.com*/
	    String ipAdressDns  = "";
	    try {
	        String command = "nslookup myip.opendns.com resolver1.opendns.com";
	        Process proc = Runtime.getRuntime().exec(command);

	        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

	        String s;
	        while ((s = stdInput.readLine()) != null) {
	            ipAdressDns  += s + "___";
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return ipAdressDns ;
	}	
	
}
