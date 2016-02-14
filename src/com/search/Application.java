package com.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Application {

	//static final String append = "%20";

	@SuppressWarnings("null")
	public static void main(String[] args) throws Exception {
		String line = null;
		String searchWords = null;
		GoogleSearch googleSearch = new GoogleSearch();
		WriteExcel excel = new WriteExcel();
		ExtractResults er= new ExtractResults();
		HashMap<String, ArrayList<SearchResult>> results = new HashMap<String, ArrayList<SearchResult>>();
		try {
			// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader(new FileReader(
					"data/input.txt"));
			while ((line = br.readLine()) != null) {
				searchWords = line.replace(" ", "+");
				System.out.println(searchWords);
				ArrayList<SearchResult> searchResults = googleSearch
						.search(searchWords);
				for (SearchResult result : searchResults) {
					ArrayList<String>  mailSet = er.extractEmail(result.url);
					result.addEmails(mailSet);
					System.out.println(result.url);
					System.out.println(result.title);
				}
				results.put(line, searchResults);
			}
			 excel.write(results);
		}

		 catch (Exception e) {
			e.printStackTrace();
		}
	}

}
