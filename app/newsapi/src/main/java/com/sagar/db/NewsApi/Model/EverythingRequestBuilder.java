package com.sagar.db.NewsApi.Model;

public class EverythingRequestBuilder {
    private String domains, q, from, to, sortBy;

    public EverythingRequestBuilder domains(String domains) {
        this.domains = domains;
        return this;
    }

    public EverythingRequestBuilder q(String q) {
        this.q = q;
        return this;
    }

    public EverythingRequestBuilder from(String from) {
        this.from = from;
        return this;
    }

    public EverythingRequestBuilder to(String to) {
        this.to = to;
        return this;
    }

    public EverythingRequestBuilder sortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public String getDomains() {
        return domains;
    }

    public String getQ() {
        return q;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSortBy() {
        return sortBy;
    }
}
