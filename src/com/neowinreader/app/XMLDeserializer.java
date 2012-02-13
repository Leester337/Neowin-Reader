package com.neowinreader.app;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLDeserializer {
	 public static ArrayList<Article> loadMany(String url)
	    {
	        int parserEvent = -1;
	        XmlPullParser parser = null;
	        ArrayList<Article> items = new ArrayList<Article>();
	        String tag = "";
	        Article item = new Article();

	        try {
	            // calls service (referenced in url) to request XML serialized data
	            parser = XmlHelper.loadData(url);
	            parserEvent = parser.getEventType();

	            while (parserEvent != XmlPullParser.END_DOCUMENT) {
	                switch(parserEvent) {
	                 case XmlPullParser.START_TAG:
	                      tag = parser.getName();
	                      if (tag.compareTo("item") == 0){
	                          item = new Article(); 
	                      }
	                  break;
	                  case XmlPullParser.END_TAG:
	                      tag = parser.getName();
	                      if (tag.compareTo("item") == 0){
	                          items.add(item); 
	                      }
	                  break;
	                 
	                  case XmlPullParser.TEXT:
	                      String text = parser.getText();
	                      //if (item.descriptionIsSet() && item.titleIsSet() && item.guidIsSet()){
	                    	//  items.add(item);
	                    	 // item = new Article();
	                      //}
	                      if (text.trim().length() == 0) break;

	                      if (tag.compareTo("title") == 0){
	                          item.setTitle(text);
	                      }
	                      else if (tag.compareTo("description") == 0){
	                    	  if (text.indexOf("/></div>") != -1){
	                    		  int startOfDesc = text.indexOf("/></div>") + 8;
	                    		  int endOfDesc = text.indexOf("<a href") - 1;
	                    		  text = text.substring(startOfDesc, endOfDesc);
	                    	  }
	                          item.setDescription(text);
	                      }
	                      else if (tag.compareTo("guid") == 0){
	                          item.setLink(text);
	                      }
	                      

	                  break;
	                }

	                parserEvent = parser.next();
	            }
	        } catch (XmlPullParserException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        // return de-serialized list of employees
	        return items;
	    }
	}

	class XmlHelper {
	    public static XmlPullParser loadData(String xmlData) throws XmlPullParserException, IOException
	    {

	        XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
	        XmlPullParser parser = parserFactory.newPullParser();
	        parser.setInput(new StringReader(xmlData));

	        return parser;
	    }
}
