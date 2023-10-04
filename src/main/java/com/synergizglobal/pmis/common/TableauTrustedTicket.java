package com.synergizglobal.pmis.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class TableauTrustedTicket {
	public static Logger logger = Logger.getLogger(TableauTrustedTicket.class);
	public String getTrustedTicket(String server_name) throws Exception{
		UrlGenerator ugObj = new UrlGenerator();
		String getResponseString = "";
		
		/*String postURL = "https://infoviz.syntrackpro.com/trusted"; 
		String username = "SynTrack"; 
		String server = "infoviz.syntrackpro.com"; 
		String clientIp = "www.syntrackpro.com";*/
        
		
		/*String postURL = "http://"+ugObj.getIpAddress()+":8000/trusted"; 
		String username = "SynTrack"; 
		String server = ugObj.getIpAddress()+":8000";
		String clientIp = ugObj.getIpAddress();*/
	
		
		String postURL = "http://pmis.mrvc.gov.in:8000/trusted";
		
		String username = "SynTrack";
		String server = "pmis.mrvc.gov.in:8000";
        String clientIp = "pmis.mrvc.gov.in";
        
        String target_site = "";//Optional
		try {
			HttpPost post = new HttpPost(postURL);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("server", server));
			params.add(new BasicNameValuePair("client_ip", clientIp));
			params.add(new BasicNameValuePair("target_site", target_site));

			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, "UTF-8");
			post.setEntity(ent);

			HttpClient client = new DefaultHttpClient();
			HttpResponse responsePOST = client.execute(post);
			
			
			BufferedReader reader = new BufferedReader(new  InputStreamReader(responsePOST.getEntity().getContent()), 2048);

			if (responsePOST != null) {
			    StringBuilder sb = new StringBuilder();
			    String line;
			    while ((line = reader.readLine()) != null) {
			        //System.out.println(" line : " + line);
			        sb.append(line);
			    }
			    getResponseString = sb.toString();
			    //use server output getResponseString as string value.
			    
			   // System.out.println("getResponseString : "+getResponseString);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return getResponseString;
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
	
}
