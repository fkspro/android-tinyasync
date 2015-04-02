// Gets a single URL synchronously

package com.example.tinyasync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

import android.os.AsyncTask;
import android.util.Log;


public class GetUrl {

    private static final String TAG = "GetUrl";

    public static String hitthepage(String url_name) {
	
	try {

	    String line;
	    String result = "";
	    
	    URL url = new URL(url_name);
	    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

	    BufferedReader result_reader = 
		new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
										
	    while ((line = result_reader.readLine()) != null) {
		result += line;
	    }

	    return result;
	
	} catch (MalformedURLException e) {
	    Log.e(TAG,Log.getStackTraceString(e));
	} catch (IOException e) {
	    Log.e(TAG,Log.getStackTraceString(e));
	}
	
	return null;
    }

}
