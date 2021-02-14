package com.sagar.db.NewsApi;

import android.util.Log;

import com.sagar.db.NewsApi.Interface.NewsApiRequest;
import com.sagar.db.NewsApi.Interface.NewsEverything;
import com.sagar.db.NewsApi.Interface.NewsSources;
import com.sagar.db.NewsApi.Interface.TopHeadlines;
import com.sagar.db.NewsApi.Model.EverythingRequestBuilder;
import com.sagar.db.NewsApi.Model.NewsData;
import com.sagar.db.NewsApi.Model.NewsSourcesData;
import com.sagar.db.NewsApi.Model.SourcesRequestBuilder;
import com.sagar.db.NewsApi.Model.TopHeadlinesRequestBuilder;
import com.sagar.db.NewsApi.Utils.RetrofitUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private List<String> supported_language = new ArrayList<>(Arrays.asList("ar", "de", "en", "es", "fr", "he", "it", "nl", "no", "pt", "ru", "se", "ud", "zh"));
    private List<String> supported_country = new ArrayList<>(Arrays.asList("be", "bg", "br", "ca", "ch", "cn", "co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"));
    private List<String> supported_sortBy = new ArrayList<>(Arrays.asList("relevancy", "popularity", "publishedAt"));
    private List<String> supported_category = new ArrayList<>(Arrays.asList("business", "entertainment", "general", "health", "science", "sports", "technology"));


    public NewsApi(String apiKey) {
        this.apiKey = apiKey;
    }


    /**
     * getSources
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
                error_message += "language not supported ";
            }
        }

        if (sourcesRequestBuilder.getCountry() != null) {
            if (supported_country.contains(sourcesRequestBuilder.getCountry())) {
                query_map.put("country", sourcesRequestBuilder.getCountry());
            } else {
                error = true;
                error_message += "country not supported ";
            }
        }

        query_map.put("apiKey", apiKey);

        if (!error) {
            apiRequest.getSources(query_map).enqueue(new Callback<NewsSourcesData>() {
                @Override
                public void onResponse(Call<NewsSourcesData> call, Response<NewsSourcesData> response) {

                    if (response.code() == 200) {
                        newsSources.onSuccess(response.body());
                    } else if (response.code() == 426) {
                        newsSources.onFailure("Too Many Requests. You made too many requests within a window of time and have been rate limited. Back off for a while");
                    } else if (response.code() == 400) {
                        newsSources.onFailure("Bad Request. The request was unacceptable, often due to a missing or misconfigured parameter");
                    } else if (response.code() == 401) {
                        newsSources.onFailure("Unauthorized. Your API key was missing from the request, or wasn't correct");
                    } else if (response.code() == 500) {
                        newsSources.onFailure("Server Error. Something went wrong on our side");
                    }
                }

                @Override
                public void onFailure(Call<NewsSourcesData> call, Throwable t) {
                    newsSources.onFailure(t.getMessage());
                }
            });
        } else {
            newsSources.onFailure(error_message);
        }
    }

    /**
     * get Everything
     *
     * @param everythingRequestBuilder
     * @param newsEverything
     */

    public void getEverything(EverythingRequestBuilder everythingRequestBuilder, NewsEverything newsEverything) {
        Map<String, String> map = new HashMap<>();

        if (everythingRequestBuilder.getDomains() != null) {
            map.put("domains", everythingRequestBuilder.getDomains());
        }

        if (everythingRequestBuilder.getQ() != null) {
            map.put("q", everythingRequestBuilder.getQ());
        } else {
            error = true;
            error_message += "parameter q missing ";
        }

        if (everythingRequestBuilder.getSortBy() != null) {
            if (supported_sortBy.contains(everythingRequestBuilder.getSortBy())) {
                map.put("sortBy", everythingRequestBuilder.getSortBy());
            } else {
                error = true;
                error_message += "invalid sortBy value ";
            }
        }

        if (everythingRequestBuilder.getFrom() != null) {
            if (isValidDate(everythingRequestBuilder.getFrom())) {
                map.put("from", everythingRequestBuilder.getFrom());
            } else {
                error = true;
                error_message += "Invalid from date format ";
            }
        }

        if (everythingRequestBuilder.getTo() != null) {
            if (isValidDate(everythingRequestBuilder.getTo())) {
                map.put("to", everythingRequestBuilder.getTo());
            } else {
                error = true;
                error_message += "Invalid to date format ";
            }
        }

        map.put("apiKey", apiKey);

        if (!error) {
            apiRequest.getEverything(map).enqueue(new Callback<NewsData>() {
                @Override
                public void onResponse(Call<NewsData> call, Response<NewsData> response) {

                    if (response.code() == 200) {
                        newsEverything.onSuccess(response.body());
                    } else if (response.code() == 426) {
                        newsEverything.onFailure("Too Many Requests. You made too many requests within a window of time and have been rate limited. Back off for a while");
                    } else if (response.code() == 400) {
                        newsEverything.onFailure("Bad Request. The request was unacceptable, often due to a missing or misconfigured parameter");
                    } else if (response.code() == 401) {
                        newsEverything.onFailure("Unauthorized. Your API key was missing from the request, or wasn't correct");
                    } else if (response.code() == 500) {
                        newsEverything.onFailure("Server Error. Something went wrong on our side");
                    }
                }

                @Override
                public void onFailure(Call<NewsData> call, Throwable t) {
                    newsEverything.onFailure(t.getMessage());
                }
            });
        } else {
            newsEverything.onFailure(error_message);
        }
    }


    /**
     * getTopHeadlines
     * @param topHeadlinesRequestBuilder
     * @param topHeadlines
     */
    public void getTopHeadlines(TopHeadlinesRequestBuilder topHeadlinesRequestBuilder, TopHeadlines topHeadlines) {
        Map<String, String> map = new HashMap<>();

        if (topHeadlinesRequestBuilder.getCountry() != null) {
            if (supported_country.contains(topHeadlinesRequestBuilder.getCountry())) {
                map.put("country", topHeadlinesRequestBuilder.getCountry());
            } else {
                error = true;
                error_message += "country not supported ";
            }
        }

        if (topHeadlinesRequestBuilder.getCategory() != null) {
            if (supported_category.contains(topHeadlinesRequestBuilder.getCategory())) {
                map.put("category", topHeadlinesRequestBuilder.getCategory());
            } else {
                error = true;
                error_message += "category not supported ";
            }
        }

        if (topHeadlinesRequestBuilder.getSources() != null) {
            map.put("sources", topHeadlinesRequestBuilder.getSources());
        }

        if (topHeadlinesRequestBuilder.getQ() != null) {
            map.put("q", topHeadlinesRequestBuilder.getQ());
        }

        if (topHeadlinesRequestBuilder.getPage() != null){
            map.put("page",topHeadlinesRequestBuilder.getPage());
        }

        if (topHeadlinesRequestBuilder.getPageSize() != null){
            map.put("pageSize",topHeadlinesRequestBuilder.getPageSize());
        }

        map.put("apiKey", apiKey);

        if (!error){

            apiRequest.getTopHeadlines(map).enqueue(new Callback<NewsData>() {
                @Override
                public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                    if (response.code() == 200) {
                        topHeadlines.onSuccess(response.body());
                    } else if (response.code() == 426) {
                        topHeadlines.onFailure("Too Many Requests. You made too many requests within a window of time and have been rate limited. Back off for a while");
                    } else if (response.code() == 400) {
                        topHeadlines.onFailure("Bad Request. The request was unacceptable, often due to a missing or misconfigured parameter");
                    } else if (response.code() == 401) {
                        topHeadlines.onFailure("Unauthorized. Your API key was missing from the request, or wasn't correct");
                    } else if (response.code() == 500) {
                        topHeadlines.onFailure("Server Error. Something went wrong on our side");
                    }
                }

                @Override
                public void onFailure(Call<NewsData> call, Throwable t) {
                    topHeadlines.onFailure(t.getMessage());
                }
            });

        }else {
            topHeadlines.onFailure(error_message);
        }



        Log.d(TAG, "getTopHeadlines: " + topHeadlinesRequestBuilder.getPageSize());
    }


    private boolean isValidDate(String dateStr) {
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
