package com.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
//import org.jsoup.UnsupportedMimeTypeException;
public class ExtractResults {

	
	public ArrayList<String> extractEmail(String url) throws IOException{
		ArrayList<String> emails = new ArrayList<String>();
		try{
			
		Document doc = Jsoup.connect(url).get();

        Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
        Matcher matcher = p.matcher(doc.text());
        
        while (matcher.find()) {
            emails.add(matcher.group());
        }
		}
		
		catch(Exception ex){
		System.out.println("Some Exceptions");
		
	}
		return emails;
		
	}
}

