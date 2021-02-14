package com.sagar.db.NewsApi.Interface;

import com.sagar.db.NewsApi.Model.NewsData;

public interface TopHeadlines {
    void onSuccess(NewsData newsData);

    void onFailure(String error);
}
