package com.synergizglobal.pmis.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TableauTrustedTicket {
	
	public static String getTrustedTicket() throws Exception{
		String getResponseString = "";
	    
		String postURL = "https://infoviz.syntrackpro.com/trusted";
		String username = "SynTrack";
		String server = "infoviz.syntrackpro.com";
		String clientIp = "www.syntrackpro.com";
        
		/*String postURL = "http://"+UrlGenerator.getIpAddress()+":8000/trusted";
		String username = "tableau admin";
		String server = "10.203.10.157:8000";
		String clientIp = "10.203.10.157";*/
        
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
	
	
	
	
	
	
}
