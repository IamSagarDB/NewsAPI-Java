package com.sagar.db.NewsApi;

import com.sagar.db.NewsApi.Interface.NewsApiRequest;
import com.sagar.db.NewsApi.Interface.NewsSources;
import com.sagar.db.NewsApi.Model.NewsData;
import com.sagar.db.NewsApi.Model.SourcesRequestBuilder;
import com.sagar.db.NewsApi.Utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApi {
    private String apiKey;
    private NewsApiRequest apiRequest = RetrofitUtil.call_NewsAPI();
    private String TAG = "NewsApiClass";
    private boolean error = false;
    private String error_message = "";
    private List<String> supported_language = new ArrayList<>(Arrays.asList("ar", "de", "en", "es", "fr", "he", "it", "nl", "no", "pt", "ru", "se", "ud", "zh"));
    private List<String> supported_country = new ArrayList<>(Arrays.asList("be", "bg", "br", "ca", "ch", "cn", "co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"));


    public NewsApi(String apiKey) {
        this.apiKey = apiKey;
    }


    /**
     * @param sourcesRequestBuilder
     * @param newsSources
     */
    public void getSources(SourcesRequestBuilder sourcesRequestBuilder, NewsSources newsSources) {
        Map<String, String> query_map = new HashMap<>();
        if (sourcesRequestBuilder.getLanguage() != null) {
            if (supported_language.contains(sourcesRequestBuilder.getLanguage())) {
                query_map.put("language", sourcesRequestBuilder.getLanguage());
            } else {
                error = true;
                error_message += "language not supported\n";
                newsSources.onFailure(error_message);
            }
        }

        if (sourcesRequestBuilder.getCountry() != null) {
            if (supported_country.contains(sourcesRequestBuilder.getCountry())) {
                query_map.put("country", sourcesRequestBuilder.getCountry());
            } else {
                error = true;
                error_message += "country not supported";
                newsSources.onFailure(error_message);
            }
        }

        query_map.put("apiKey", apiKey);

        if (!error) {
            apiRequest.getSources(query_map).enqueue(new Callback<NewsData>() {
                @Override
                public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                    newsSources.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<NewsData> call, Throwable t) {
                    newsSources.onFailure(t.getMessage());
                }
            });
        }
    }



}
