package com.sagar.db.NewsApi.Interface;

import com.sagar.db.NewsApi.Model.NewsData;
import com.sagar.db.NewsApi.Model.NewsSourcesData;
import com.sagar.db.NewsApi.Utils.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsApiRequest {

    @GET(Utils.version + "/sources")
    Call<NewsSourcesData> getSources(@QueryMap Map<String, String> source_query);

    @GET(Utils.version + "/everything")
    Call<NewsData> getEverything(@QueryMap Map<String, String> source_query);
}
