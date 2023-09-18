package com.example.quoteapi.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Quote")
public class Quote {
    @Id
    public String id;
    public String quoteText;
    public String quoteAuthor;
    public String quoteGenre;

    public Quote(String id, String quoteText, String quoteAuthor, String quoteGenre){
        this.id = id;
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
        this.quoteGenre = quoteGenre;
    }
}

