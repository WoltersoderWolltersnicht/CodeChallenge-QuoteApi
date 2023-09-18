package com.example.quoteapi.application.dto.getquotes;

import java.util.List;

import com.example.quoteapi.application.dto.common.AdviseMessage;
import com.example.quoteapi.application.dto.common.QuoteDTO;

public class GetQuotesResponse {
    public List<QuoteDTO> quotes;
    public AdviseMessage adviseMessages;

    public GetQuotesResponse(){}

    public GetQuotesResponse(List<QuoteDTO> quotes,AdviseMessage adviseMessages){
        this.quotes = quotes;
        this.adviseMessages = adviseMessages;

    }
}
