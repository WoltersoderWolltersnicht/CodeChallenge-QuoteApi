package com.example.quoteapi.domain;

public class Quote {
    public String id;
    public String quoteText;
    public String quoteAuthor;
    public String quoteGenre;

    public Quote(){}
    public Quote(String id, String quoteText, String quoteAuthor, String quoteGenre){
        this.id = id;
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
        this.quoteGenre = quoteGenre;
    }
}

