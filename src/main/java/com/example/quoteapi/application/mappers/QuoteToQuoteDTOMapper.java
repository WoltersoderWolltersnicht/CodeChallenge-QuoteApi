package com.example.quoteapi.application.mappers;

import java.util.ArrayList;
import java.util.List;

import com.example.quoteapi.application.dto.common.QuoteDTO;
import com.example.quoteapi.domain.Quote;

public class QuoteToQuoteDTOMapper {
    public static QuoteDTO Map(Quote quote){
        QuoteDTO quoteDTO = new QuoteDTO();
        quoteDTO.id = quote.id;
        quoteDTO.quoteAuthor = quote.quoteAuthor;
        quoteDTO.quoteGenre = quote.quoteGenre;
        quoteDTO.quoteText = quote.quoteText;

        return quoteDTO;
    }

    public static List<QuoteDTO> Map(List<Quote> quotes){
        List<QuoteDTO> quoteDTOs = new ArrayList<QuoteDTO>();
        for (Quote quote : quotes) {
            QuoteDTO quoteDTO = Map(quote);
            quoteDTOs.add(quoteDTO);
        }
        return quoteDTOs;
    }
}
