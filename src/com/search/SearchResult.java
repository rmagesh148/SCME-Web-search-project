package com.search;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class SearchResult {
	public String url;
	public String title;
	public ArrayList<String> emailIds = new ArrayList<String>();
	public SearchResult(String url, String title) {
		this.url = url;
		this.title = title;
	}
	public void addEmails(ArrayList<String> emailIds){
		this.emailIds.addAll(emailIds);
	}
	public String getEmailString(){
		return StringUtils.join(emailIds,",");
	}
	
}