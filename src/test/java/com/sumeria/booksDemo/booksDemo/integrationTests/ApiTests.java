package com.sumeria.booksDemo.booksDemo.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumeria.booksDemo.booksDemo.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        // prepare

        // action + validation
        MvcResult result = mockMvc
                .perform(get("/api/books/book_2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("book_2")))
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = objectMapper.readValue(json, Book.class);

        assertThat(book.getAuthor()).isEqualTo("Abed Mohammad");
        assertThat(book.getNumberOfPages()).isEqualTo(23);
    }
}
