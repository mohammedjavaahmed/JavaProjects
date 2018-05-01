package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RestURIReader {

	public static final int RESPONSE_CODE =200;
	public static void main(String[] args) {
			if(args.length != 0 ){
				String response = RestURIReader.convertToJSonString(args[0]);
				RestURIReader.parseJsonString(response);
			}
	}

	public static String  convertToJSonString(String uri) {
		String responseText = "";
		
		try {
			// reading the rest end point
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			//Mapping to GET Method
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			
			if (connection.getResponseCode() != RESPONSE_CODE) {
				throw new RuntimeException("ERROR CODE  " + connection.getResponseCode());
			}
			
			// Reading the input stream
			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			while ((responseText = bufferedReader.readLine()) != null) {
				System.out.println(responseText);
			}
			connection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return responseText;
	}
	
	public static void parseJsonString(String json) {
		int sum = 0 ; 
		String key = "numbers";
		JsonParser parser = new JsonParser();
		
		JsonElement jsonElement = parser.parse(key);
		JsonObject obj = jsonElement.getAsJsonObject();
		
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
		
		for (Map.Entry<String, JsonElement> entry : entries) {
			sum = 0;
			for(String s : (List<String>)entry.getValue()){
				sum +=Integer.parseInt(s);
			}
			System.out.println(sum);
		}
		
	}
}
