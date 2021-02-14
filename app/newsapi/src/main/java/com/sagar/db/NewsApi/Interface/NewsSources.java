package com.sagar.db.NewsApi.Interface;

import com.sagar.db.NewsApi.Model.NewsSourcesData;

public interface NewsSources {
    void onSuccess(NewsSourcesData newsData);

    void onFailure(String error);
}
