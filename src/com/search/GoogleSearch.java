package com.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.search.SearchResult;

public class GoogleSearch {

	static final String version = "1.0";
	//static final String apiUrl = "https://ajax.googleapis.com/ajax/services/search/web";
	static final String apiUrl = "https://www.googleapis.com/customsearch/v1?";
	//static final int resultCount = 8;
	static final String key="AIzaSyAxhChKtT6AFUPf4P5jRFrg4Uduc6GMgGk1";
	static final String cx = "006214109175033927711%3Atjtvr4wp2rc1";
	static final String country = "countryUSA";
	public ArrayList<SearchResult> search(String query) {
		ArrayList<SearchResult> searchResults =  new ArrayList<SearchResult>();
		for(int start=1;start<=100;start+=10) {
		String searchUrl = apiUrl + "q=" + query + "&key=" + key + "&cx=" + cx + "&cr=" + country + "&start=" + start;	
		//String searchUrl = "https://www.googleapis.com/customsearch/v1?q=matt+pliel&key=AIzaSyAxhChKtT6AFUPf4P5jRFrg4Uduc6GMgGk&cx=006214109175033927711%3Atjtvr4wp2rc&start=1";
		try {
			URL url = new URL(searchUrl);

			URLConnection connection = url.openConnection();
			// connection.addRequestProperty("Referer", /* Enter the URL of your
			// site here */);

			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			Object obj = new JSONParser().parse(builder.toString());
			JSONObject json = (JSONObject) obj;
			JSONArray responseData = (JSONArray) json.get("items");
			//System.out.println(searchUrl);
			//JSONArray results = (JSONArray) responseData.get("results");
			
			@SuppressWarnings("rawtypes")
			Iterator itr= responseData.iterator();
	        //JSONArray jsonObjectCopy = new JSONArray();

	        
	        while(itr.hasNext()){
	            JSONObject featureJsonObj = (JSONObject)itr.next();
	            String resultUrl = (String)featureJsonObj.get("link");
	            String resultTitle = (String)featureJsonObj.get("title");
	            searchResults.add(new SearchResult(resultUrl, resultTitle));      
	        }
	       
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		return searchResults;
		}
	
	

}
