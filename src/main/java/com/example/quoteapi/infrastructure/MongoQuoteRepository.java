package com.example.quoteapi.infrastructure;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.quoteapi.domain.Quote;
import com.example.quoteapi.domain.QuoteRepository;

@Repository
public class MongoQuoteRepository implements QuoteRepository{

    private final MongoTemplate mongoTemplate;

    public MongoQuoteRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Quote> getQuotesByIdAndAuthor(String id, String author) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(id != null) criteria.and("_id").is(id);
        if(author != null) criteria.and("quoteAuthor").is(author);
        query.addCriteria(criteria);

        List<Quote> quotes = mongoTemplate.find(query, Quote.class);
        return quotes;
    }

    @Override
    public List<Quote> getAllQuotes() {
        List<Quote> quotes = mongoTemplate.findAll(Quote.class);
        return quotes;
    }

}