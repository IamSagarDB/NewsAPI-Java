package com.sagar.db.newsapi_java;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sagar.db.NewsApi.Interface.NewsEverything;
import com.sagar.db.NewsApi.Interface.TopHeadlines;
import com.sagar.db.NewsApi.Model.EverythingRequestBuilder;
import com.sagar.db.NewsApi.Model.NewsData;
import com.sagar.db.NewsApi.Model.TopHeadlinesRequestBuilder;
import com.sagar.db.NewsApi.NewsApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsApi newsApi = new NewsApi("36be1026e9664e90a6cd86a83cdf1cae");
        newsApi.getTopHeadlines(new TopHeadlinesRequestBuilder().country("in"), new TopHeadlines() {
            @Override
            public void onSuccess(NewsData newsData) {
                Toast.makeText(MainActivity.this,""+newsData.getStatus(),Toast.LENGTH_LONG).show();
                }

            @Override
            public void onFailure(String error) {
                Toast.makeText(MainActivity.this,""+error,Toast.LENGTH_LONG).show();
            }
        });

    }
}