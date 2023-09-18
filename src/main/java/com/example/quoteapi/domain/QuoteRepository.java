package com.example.quoteapi.domain;

import java.util.List;

public interface QuoteRepository {
    public List<Quote> GetQuotesByIdAndAuthor(String id, String author);
    public List<Quote> GetAllQuotes();    
}