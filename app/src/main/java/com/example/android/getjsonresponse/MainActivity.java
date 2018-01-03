package com.example.android.getjsonresponse;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String stringUrl = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=e6119fc0e6963d6ee2300a97c6d1cf22";
    //ImageView imageview = (ImageView)(findViewById(R.id.image));
    private String TAG = MainActivity.class.getSimpleName();
    TextView textView;
    //ArrayList<String> arts=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        try {
            new GetImageData().execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    private class GetImageData extends AsyncTask<URL, Void, String> {

        URL url = new URL(stringUrl);
        private GetImageData() throws MalformedURLException {
        }
        @Override
        protected String doInBackground(URL... urls) {
            String mSearchResult = null;
            try{
                mSearchResult = getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mSearchResult;
        }

        @Override
        protected void onPostExecute(String mSearchResult) {
            textView.append(mSearchResult);
        }
    }
}