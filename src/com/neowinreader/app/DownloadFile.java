package com.neowinreader.app;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadFile extends AsyncTask<String, Void, String>{

	private DefaultHttpClient client = new DefaultHttpClient();
	@Override
	
    protected String doInBackground(String... params) {

	HttpGet getRequest = new HttpGet(params[0]);
	try{
		HttpResponse getResponse = client.execute(getRequest);
		Log.d(neowinReaderActivity.class.getSimpleName(), "Error Occured" + getResponse.toString());
		final int statusCode = getResponse.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			return null;
		}
		
		HttpEntity getResponseEntity = getResponse.getEntity();
		
		if (getResponseEntity != null){
			return EntityUtils.toString(getResponseEntity);
		}
	}
catch (IOException e){
	getRequest.abort();
	Log.w(getClass().getSimpleName(), "Error for URL " , e);
	
}
	return null;
}

}
