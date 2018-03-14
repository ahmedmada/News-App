package com.basic.nanodegree.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Ahmed AbdElQader on 14-Mar-18.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (url.length() < 1 || url == null) {
            return null;
        }
        List<News> news = QueryUtils.fetchNewsData(url);
        return news;
    }
}