package com.sagar.db.NewsApi.Model;

public class TopHeadlinesRequestBuilder {
    private String country, category, sources, q, pageSize, page;


    public TopHeadlinesRequestBuilder country(String country) {
        this.country = country;
        return this;
    }

    public TopHeadlinesRequestBuilder category(String category) {
        this.category = category;
        return this;
    }

    public TopHeadlinesRequestBuilder sources(String sources) {
        this.sources = sources;
        return this;
    }

    public TopHeadlinesRequestBuilder q(String q) {
        this.q = q;
        return this;
    }

    public TopHeadlinesRequestBuilder pageSize(String pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public TopHeadlinesRequestBuilder page(String  page) {
        this.page = page;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }

    public String getSources() {
        return sources;
    }

    public String getQ() {
        return q;
    }

    public String  getPageSize() {
        return pageSize;
    }

    public String  getPage() {
        return page;
    }
}
