package com.basic.nanodegree.newsapp;

/**
 * Created by Ahmed AbdElQader on 14-Mar-18.
 */


public class News {
    private String mTitle;
    private String mType;
    private String mDate;
    private String mSection;
    private String mUrl;
    private String mAuth;

    public News(String title, String type, String date, String section, String url,String auth) {
        this.mTitle = title;
        this.mType = type;
        this.mDate = date;
        this.mSection = section;
        this.mUrl = url;
        this.mAuth = auth;
    }

    public News() {

    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType;
    }

    public String getDate() {
        return mDate;
    }

    public String getSection() {
        return mSection;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmSection() {
        return mSection;
    }

    public void setmSection(String mSection) {
        this.mSection = mSection;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmAuth() {
        return mAuth;
    }

    public void setmAuth(String mAuth) {
        this.mAuth = mAuth;
    }
}