package com.sumeria.booksDemo.booksDemo.integrationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumeria.booksDemo.booksDemo.models.Book;
import com.sumeria.booksDemo.booksDemo.repositories.BooksRepository;
import com.sumeria.booksDemo.booksDemo.services.BooksService;
import com.sumeria.booksDemo.booksDemo.webClients.TitleToAuthorResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = { "booksDemo.titleToAuthorMapperServiceUrl=http://localhost:3333" })
public class IntegrationTests {
    @Autowired
    private BooksService service;
    @Autowired
    private BooksRepository repository;

    public static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start(3333);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }


    @Test
    void booksServiceReturnsBooks(){
        // prepare
        repository.initTheData();

        // action
        List<Book> result = service.findAll();

        // validation
        assertThat(result.size()).isEqualTo(5);
    }

//    @Test
//    void creatingABookWithoutAuthorAddsTheAuthor() throws JsonProcessingException {
//        // prepare
//        ObjectMapper objectMapper = new ObjectMapper();
//        mockBackEnd.enqueue(new MockResponse()
//                .setBody(objectMapper.writeValueAsString(new TitleToAuthorResponse("Mr", "Baker")))
//                .addHeader("Content-Type", "application/json"));
//
//        // action
//        Book result = service.createBook(new Book("titile", "", 99));
//
//        // validation
//        assertThat(result.getAuthor()).isEqualTo("Mr Baker");
//    }
}
