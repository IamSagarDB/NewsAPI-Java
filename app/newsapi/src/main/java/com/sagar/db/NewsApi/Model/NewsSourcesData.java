package com.sagar.db.NewsApi.Model;

import java.util.List;

public class NewsSourcesData {
    private String status,message, code;
    private List<Sources> sources;

    public String getStatus() {
        return status;
    }

    public List<Sources> getSources() {
        return sources;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
