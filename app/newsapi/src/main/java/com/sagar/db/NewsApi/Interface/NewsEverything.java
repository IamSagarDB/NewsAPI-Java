package com.sagar.db.NewsApi.Interface;

import com.sagar.db.NewsApi.Model.NewsData;

public interface NewsEverything {
    void onSuccess(NewsData everythingData);

    void onFailure(String error);
}
