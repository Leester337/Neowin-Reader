package com.neowinreader.app;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class neowinReaderActivity extends Activity {
    /** Called when the activity is first created. */

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		try {
			deserializeXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(neowinReaderActivity.class.getSimpleName(), "probblem");
		}
		
    }

	public void deserializeXML() throws IOException{
		try {
			Object result = new DownloadFile().execute("http://feeds.feedburner.com/neowin-main.xml").get();
		//TextView t = (TextView)findViewById(R.id.xmlData);
		String xmlData = result.toString();
		ArrayList<Article> feed = XMLDeserializer.loadMany(xmlData);
		final ArrayList<Article> feedClone = (ArrayList<Article>) feed.clone();
		ListView articleListView = (ListView)findViewById(R.id.atricleListView);
		ArticleAdapter arrayAdapter = new ArticleAdapter(neowinReaderActivity.this, R.layout.listitems, feed);
		articleListView.setAdapter(arrayAdapter);
		articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentView, View childView, int position, long id){
				Article currentArticle = feedClone.get(position);
				viewFullArticle(currentArticle.getTitle(), currentArticle.getLink());
			}
		});
		              
			}
		   catch (Exception e) {
			   Toast.makeText(this, "Error Occured: " + e.getMessage(), Toast.LENGTH_LONG).show();
			   Log.d(neowinReaderActivity.class.getSimpleName(), "Error Occured" + e.getMessage());
		     }

		   }
	
	
	private void viewFullArticle(String articleTitle, String guid){
		//Toast.makeText(this, articleTitle, Toast.LENGTH_LONG).show();
		Intent newIntent = new Intent(this, ViewArticleActivity.class);
		newIntent.putExtra("title", articleTitle);
		newIntent.putExtra("guid", guid);
		startActivity(newIntent);
	}
}
