package com.neowinreader.app;

import java.util.concurrent.ExecutionException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewArticleActivity extends Activity{
	 public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState); 
	       setContentView(R.layout.article);
	       
	       String title = getIntent().getExtras().getString("title");
	       this.setTitle(title);
	       String guid = getIntent().getExtras().getString("guid");
	       try {
			Object result = new DownloadFile().execute(guid).get();
			String html = result.toString();
			int concatFront = 0;
			int concatBack = html.length();
			if (html.contains("<p>"))
				concatFront = html.indexOf("<p>") + 3;
			if (html.contains("<!-- newsitem.pager -->"))
				concatBack = html.indexOf("<!-- newsitem.pager -->");
			html = html.substring(concatFront, concatBack);
			html = html.replace("<p>", "NEWLINE");
			Document doc = Jsoup.parse(html);
			String paragraph = doc.body().text();
			paragraph = paragraph.replace("NEWLINE", "\n\n");
			TextView fullArticle = (TextView)findViewById(R.id.fullArticle);
			fullArticle.setText(paragraph);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       //Toast.makeText(this, title, Toast.LENGTH_LONG).show();
}
}
