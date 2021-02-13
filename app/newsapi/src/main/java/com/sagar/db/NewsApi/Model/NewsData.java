package com.sagar.db.NewsApi.Model;

import java.util.List;

public class NewsData {
    private String status;
    private List<Sources> sources;

    public String getStatus() {
        return status;
    }

    public List<Sources> getSources() {
        return sources;
    }
}
