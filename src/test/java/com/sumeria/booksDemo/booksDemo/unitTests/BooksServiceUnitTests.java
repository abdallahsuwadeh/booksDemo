package com.sumeria.booksDemo.booksDemo.unitTests;

import com.sumeria.booksDemo.booksDemo.models.Book;
import com.sumeria.booksDemo.booksDemo.repositories.BooksRepository;
import com.sumeria.booksDemo.booksDemo.services.BooksServiceImpl;
import com.sumeria.booksDemo.booksDemo.webClients.TitleToAuthorMapperWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BooksServiceUnitTests {
    private BooksServiceImpl booksService;

    @Mock
    private BooksRepository repository;

    private TitleToAuthorMapperWebClient client = mock(TitleToAuthorMapperWebClient.class);

    @BeforeEach
    void setupBooksService(){
        booksService = new BooksServiceImpl(
                repository,
                client,
                true
        );
    }

    @Test
    void createBookCallsBothMocks(){
        // prepare

        // action
        booksService.createBook(new Book("title", "", 88));

        // validation
        verify(client, times(1)).getAuthorOfTitle("title");
        verify(repository, times(1)).createOrUpdateBook(any(Book.class));
    }

    @Test
    void createBookGetsTheNameFromTheClientIfItDoesntExist(){
        // prepare
        when(client.getAuthorOfTitle("title")).thenReturn("Great Author");
        when(repository.createOrUpdateBook(any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Book.class));

        // action
        var result = booksService.createBook(new Book("title", "", 88));

        // validation
        assertThat(result.getAuthor()).isEqualTo("Great Author");
    }
}
