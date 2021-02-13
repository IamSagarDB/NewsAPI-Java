package com.sagar.db.newsapi_java;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sagar.db.NewsApi.Interface.NewsSources;
import com.sagar.db.NewsApi.Model.NewsData;
import com.sagar.db.NewsApi.Model.SourcesRequestBuilder;
import com.sagar.db.NewsApi.NewsApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsApi newsApi = new NewsApi("36be1026e9664e90a6cd86a83cdf1cae");
        newsApi.getSources(new SourcesRequestBuilder().country("ts"), new NewsSources() {
            @Override
            public void onSuccess(NewsData newsData) {
                Toast.makeText(MainActivity.this, newsData.getSources().get(0).getLanguage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_LONG).show();

            }
        });
    }
}