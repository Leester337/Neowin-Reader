package com.neowinreader.app;

public class Article {
	private String title;
	private String description;
	private String guid;
	
	public Article(Article xmlFeedItem) {
		// TODO Auto-generated constructor stub
		title = xmlFeedItem.title;
		description = xmlFeedItem.description;
		guid = xmlFeedItem.guid;
	}

	public Article() {
		title = description = guid = null;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean titleIsSet(){
		return title != null;
	}
	
	public boolean descriptionIsSet(){
		return description != null;
	}
	
	public boolean guidIsSet(){
		return guid != null;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}


	public String getLink() {
		return guid;
	}

	public void setLink(String string) {
		this.guid = string;
	}
}
