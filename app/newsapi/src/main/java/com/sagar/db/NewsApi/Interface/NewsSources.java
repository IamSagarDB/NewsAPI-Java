package com.sagar.db.NewsApi.Interface;

import com.sagar.db.NewsApi.Model.NewsData;

public interface NewsSources {
    void onSuccess(NewsData newsData);

    void onFailure(String error);
}
