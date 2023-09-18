package com.example.quoteapi.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository {
    public List<Quote> getQuotesByIdAndAuthor(String id, String author);
    public List<Quote> getAllQuotes();    
}