package com.sagar.db.NewsApi.Interface;

import com.sagar.db.NewsApi.Model.NewsData;
import com.sagar.db.NewsApi.Utils.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsApiRequest {

    @GET(Utils.version + "/sources")
    Call<NewsData> getSources(@QueryMap Map<String, String> source_query);
}
