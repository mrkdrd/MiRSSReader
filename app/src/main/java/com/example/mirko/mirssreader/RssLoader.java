package com.example.mirko.mirssreader;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.example.mirko.mirssreader.Adapter.FeedAdapter;
import com.example.mirko.mirssreader.Model.RssObject;
import com.google.gson.Gson;

public class RssLoader {

    public RssObject rssObject;
    public RecyclerView recyclerView;
    public Context context;

    private final String RSS_link;
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";

    public RssLoader(RecyclerView recyclerView, String RSS_link, Context context) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.RSS_link = RSS_link;
    }

    public void loadRSS() {
        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                String result;
                DataHandler http = new DataHandler();
                result = http.GetData(params[0]);
                return  result;
            }

            @Override
            protected void onPostExecute(String s) {
                rssObject = new Gson().fromJson(s,RssObject.class);

                FeedAdapter adapter = new FeedAdapter(rssObject, context);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
        loadRSSAsync.execute(url_get_data.toString());
    }
}

