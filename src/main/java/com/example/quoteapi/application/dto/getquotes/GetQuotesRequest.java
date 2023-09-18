package com.example.quoteapi.application.dto.getquotes;

public class GetQuotesRequest {
    public String id;
    public String author;

    public GetQuotesRequest(){}

    public GetQuotesRequest(String id, String author){
        this.id = id;
        this.author = author;
    }
}
