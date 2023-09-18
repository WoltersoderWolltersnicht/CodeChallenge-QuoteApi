package com.example.quoteapi.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quoteapi.application.dto.common.AdviseMessage;
import com.example.quoteapi.application.dto.common.QuoteDTO;
import com.example.quoteapi.application.dto.getquotes.GetQuotesRequest;
import com.example.quoteapi.application.dto.getquotes.GetQuotesResponse;
import com.example.quoteapi.application.mappers.QuoteToQuoteDTOMapper;
import com.example.quoteapi.domain.Quote;
import com.example.quoteapi.domain.QuoteRepository;

@RestController
@RequestMapping("/QuoteGarden")
public class QuoteController {
    
    @Autowired
    private QuoteRepository repository;

    @GetMapping("/get-quotes")
    GetQuotesResponse getQuotes(@RequestBody GetQuotesRequest request) {

        List<Quote> response = null;

        if( request.id != null || request.author != null){
            response = repository.getQuotesByIdAndAuthor(request.id, request.author);
        }else{
            response = repository.getAllQuotes();
        }

        if(response == null) return new GetQuotesResponse(null, new AdviseMessage(500, "Error retrieving quotes"));
        if(response.isEmpty()) return new GetQuotesResponse(null, new AdviseMessage(404, "Error no Quote found for input parameters"));
        List<QuoteDTO> quoteDTOs = QuoteToQuoteDTOMapper.Map(response);
        return new GetQuotesResponse(quoteDTOs, null);
    }
}
