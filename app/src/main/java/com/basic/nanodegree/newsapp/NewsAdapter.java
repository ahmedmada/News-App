package com.basic.nanodegree.newsapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Ahmed AbdElQader on 14-Mar-18.
 */


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> mNewsList;
    private OnItemClickListener mListener;
    private Context mContext;

    public NewsAdapter(List<News> newsList, OnItemClickListener listener){
        mNewsList = newsList;
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(News news);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View newsView = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(newsView);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.mTitle.setText(news.getTitle());
        holder.mType.setText(news.getType());
        holder.mDate.setText(news.getDate());
        holder.mSection.setText(news.getSection());
        holder.bind(mNewsList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mType;
        TextView mDate;
        TextView mSection;

        public NewsViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.news_title);
            mType = (TextView) v.findViewById(R.id.news_type);
            mDate = (TextView) v.findViewById(R.id.news_date);
            mSection = (TextView) v.findViewById(R.id.news_section);
        }

        public void bind(final News news, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(news);
                }
            });
        }
    }

    public void clear(){
        mNewsList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<News> newsList){
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }
}
