package com.example.quoteapi.integration.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.quoteapi.domain.Quote;

@Configuration
@AutoConfigureDataMongo
public class MongoDBInit {
    

	public MongoDBInit(@Autowired MongoTemplate mongoTemplate)	{
        mongoTemplate.insert(new Quote("1", "quote1", "author1", "genre1"));
        mongoTemplate.insert(new Quote("2", "quote2", "author1", "genre2"));
        mongoTemplate.insert(new Quote("3", "quote3", "author2", "genre3"));
        mongoTemplate.insert(new Quote("4", "quote4", "author3", "genre4"));

        List<Quote> quotes = mongoTemplate.findAll(Quote.class);
    }   
}
