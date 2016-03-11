package com.nikhil.intelimentapp.Parser;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.nikhil.intelimentapp.Utility.UIUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nikhil on 09-03-2016.
 */


public class HttpRequester {

    private int serviceCode;
    private String url;
    private boolean isGetMethod;
    private AsyncTaskCompleteListener asyncTaskCompleteListener;

    public HttpRequester(String url, AsyncTaskCompleteListener asyncTaskCompleteListener) {
        this.url = url;
        this.asyncTaskCompleteListener = asyncTaskCompleteListener;
        new AsyncHttpRequest().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }


    class AsyncHttpRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.

            try {
                URL url1 = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                UIUtil.AppLog("The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is);
                return contentAsString;

            } catch (Exception e) {
                e.printStackTrace();
            } catch (OutOfMemoryError oume) {
                System.gc();
            } finally {
                if (is != null)
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            return null;
        }

        public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
            BufferedReader r = new BufferedReader(new InputStreamReader(stream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();
        }

        @Override
        protected void onPostExecute(String response) {
            asyncTaskCompleteListener.onTaskCompleted(response);
        }
    }
}
