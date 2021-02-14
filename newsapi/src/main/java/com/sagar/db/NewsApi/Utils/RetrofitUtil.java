package com.sagar.db.NewsApi.Utils;

import com.sagar.db.NewsApi.Interface.NewsApiRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    public static NewsApiRequest call_NewsAPI(){
        OkHttpClient.Builder client = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder().client(client.build()).addConverterFactory(GsonConverterFactory.create()).baseUrl(Utils.baseUrl).build();
        return retrofit.create(NewsApiRequest.class);
    }
}
