package de.goeuro.dev.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnector {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HttpConnector.class);

	public StringBuffer getJson(String resource){
		StringBuffer sb  = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getJson(String) - start"); 
		}

		HttpURLConnection con=null;
		try {
			
			con = setConnection(resource);
			sb = readResponse(con);
			
		} catch (IOException e) {
			logger.error("getJson(String) "+ e.getMessage()); 		}
		
		
		return sb;
	}

	private HttpURLConnection setConnection(String resource) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getJson() - start"); 
		}

		String url = "http://pre.dev.goeuro.de:12345/api/v1/suggest/position/en/name/"+resource;
		URL obj = null;

		HttpURLConnection con = null;
			
			logger.info("getJson(String) URL resource: "+ url); 		
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent",
					"Mozilla//5.0 (Windows NT 6.1; WOW64) ");
			int responseCode = con.getResponseCode();
			
			
		
		if (logger.isDebugEnabled()) {
			logger.debug("setConnection(String) - responseCode: "+responseCode); 
			logger.debug("setConnection(String) - end"); 
		}
		return con;

		

	}
	
	private StringBuffer readResponse(HttpURLConnection con){
		
		BufferedReader in = null;
		String inputLine = null;
		StringBuffer response = null;
		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			logger.error("readResponse(HttpURLConnection) " + e.getMessage()); 
		}

		response = new StringBuffer();

		try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			logger.error("readResponse(HttpURLConnection) " + e.getMessage()); 
		}
		try {
			in.close();
		} catch (IOException e) {

			logger.error("readResponse(HttpURLConnection)" + e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("readResponse(HttpURLConnection) - end"); 
		}
		return response;
	}

}
