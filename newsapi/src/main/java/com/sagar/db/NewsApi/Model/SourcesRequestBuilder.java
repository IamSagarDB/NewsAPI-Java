package com.sagar.db.NewsApi.Model;

public class SourcesRequestBuilder {
    private String language , country ;

    public SourcesRequestBuilder language(String language){
        this.language = language;
        return this;
    }

    public SourcesRequestBuilder country(String country){
        this.country = country;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }
}
