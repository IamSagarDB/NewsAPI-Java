package com.sagar.db.NewsApi.Model;

import java.util.List;

public class NewsData {
    private String status, message, code;
    private int totalResults;
    private List<Articles> articles;


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }
}
