package com.basic.nanodegree.newsapp;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, LoaderManager.LoaderCallbacks<List<News>> {

    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String mSearchQuery;
    RecyclerView mRecyclerView;
    TextView mEmptyStateTextView;
    View mLoadingIndicator;

    private static final int NEWS_LOADER_ID = 1;

    public static final String LOG_TAG = MainActivity.class.getName();
    private static final String NEWS_REQUEST_URL = "http://content.guardianapis.com/search";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mLoadingIndicator = (View) findViewById(R.id.loading_indicator);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NewsAdapter(new ArrayList<News>(), new NewsAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(News news) {
                String url = news.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
             }

        });

        mRecyclerView.setAdapter(mAdapter);

        initGetData();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initGetData();
    }

    private void initGetData(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("No Internet Connection");
        }
    }



    @Override

    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        Uri baseUri = Uri.parse(NEWS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("api-key", "31348b4c-ec34-4a51-98d4-dfc8471f2a6c");

        if(mSearchQuery!=null)
            uriBuilder.appendQueryParameter("q", mSearchQuery);

        return new NewsLoader(this, uriBuilder.toString());

    }


    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {
        mLoadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setVisibility(View.VISIBLE);
        mEmptyStateTextView.setText("No News");

        mAdapter.clear();

        if (newsList != null && !newsList.isEmpty()) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setVisibility(View.GONE);
            mAdapter.addAll(newsList);
        }

    }


    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

        mAdapter.clear();

    }


    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchQuery = query;
        getLoaderManager().restartLoader(NEWS_LOADER_ID, null, this);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.btn_search));
        searchView.setOnQueryTextListener(this);
        return true;
    }


}
