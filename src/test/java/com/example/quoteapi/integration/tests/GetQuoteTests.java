package com.example.quoteapi.integration.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.example.quoteapi.application.dto.common.QuoteDTO;
import com.example.quoteapi.application.dto.getquotes.GetQuotesRequest;
import com.example.quoteapi.application.dto.getquotes.GetQuotesResponse;
import com.example.quoteapi.integration.mongo.MongoDBInit;
import com.example.quoteapi.integration.mongo.MongoDBTestContainerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Import({ MongoDBTestContainerConfig.class, MongoDBInit.class })
@AutoConfigureMockMvc
class GetQuoteTests {

	@Autowired
	MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	static Stream<Arguments> ok_requests() {
		return Stream.of(
				Arguments.of(new GetQuotesRequest("1", "author1")),
				Arguments.of(new GetQuotesRequest("1", null)),
				Arguments.of(new GetQuotesRequest(null, "author1")));
	}

	@ParameterizedTest
	@MethodSource("ok_requests") // Use the method as the data source.
	void result_ok(GetQuotesRequest request) throws Exception {

		ResultActions resultActions = mockMvc
				.perform(get("/QuoteGarden/get-quotes").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		GetQuotesResponse response = objectMapper.readValue(contentAsString, GetQuotesResponse.class);

		Assertions.assertNotNull(response);
		for (QuoteDTO quoteResponse : response.quotes) {
			if (request.id != null) {
				assertEquals(request.id, quoteResponse.id);
			}

			if (request.author != null) {
				assertEquals(request.author, quoteResponse.quoteAuthor);
			}
		}

	}

	static Stream<Arguments> not_found_requests() {
		return Stream.of(
				Arguments.of(new GetQuotesRequest("notfound", "notfound")),
				Arguments.of(new GetQuotesRequest("notfound", null)),
				Arguments.of(new GetQuotesRequest(null, "notfound")));
	}

	@ParameterizedTest
	@MethodSource("not_found_requests") // Use the method as the data source.
	void result_not_found(GetQuotesRequest request) throws Exception {

		ResultActions resultActions = mockMvc
				.perform(get("/QuoteGarden/get-quotes").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		GetQuotesResponse response = objectMapper.readValue(contentAsString, GetQuotesResponse.class);

		Assertions.assertNotNull(response);
		Assertions.assertNull(response.quotes);
		Assertions.assertNotNull(response.adviseMessages);
		assertEquals(404, response.adviseMessages.code);
		assertEquals("Error no Quote found for input parameters", response.adviseMessages.message);
	}

	@Test
	void ok_all() throws Exception {

		GetQuotesRequest request = new GetQuotesRequest(null, null);
		ResultActions resultActions = mockMvc
				.perform(get("/QuoteGarden/get-quotes").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		GetQuotesResponse response = objectMapper.readValue(contentAsString, GetQuotesResponse.class);

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.quotes);
		Assertions.assertNull(response.adviseMessages);
		Assertions.assertEquals(4, response.quotes.size());
	}
}
