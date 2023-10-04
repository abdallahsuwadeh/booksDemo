package com.sumeria.booksDemo.booksDemo.services;

import com.sumeria.booksDemo.booksDemo.models.Book;
import com.sumeria.booksDemo.booksDemo.repositories.BooksRepository;
import com.sumeria.booksDemo.booksDemo.webClients.TitleToAuthorMapperWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {
    private final BooksRepository repository;
    private final boolean enableAutoAuthorMapping;
    private final TitleToAuthorMapperWebClient authorMapperWebClient;

    @Autowired
    BooksServiceImpl(
            BooksRepository repository,
            TitleToAuthorMapperWebClient authorMapperWebClient,
            @Value("${booksDemo.enableAutoAuthorMapping}")
            boolean enableAutoAuthorMapping) {
        this.enableAutoAuthorMapping = enableAutoAuthorMapping;
        this.repository = repository;
        this.authorMapperWebClient = authorMapperWebClient;
    }

    @Override
    public List<Book> findAll(){
        return repository.findAll();
    }

    @Override
    public Book createBook(Book book){
        if(book.getAuthor() == null || book.getAuthor().isEmpty() || book.getAuthor().isBlank()) {
            if(enableAutoAuthorMapping) {
                String author = authorMapperWebClient.getAuthorOfTitle(book.getTitle());
                book.setAuthor(author);
            }
        }
        return repository.createOrUpdateBook(book);
    }

    @Override
    public Book getBookByTitle(String title){
        return repository.getBookByTitle(title);
    }

    @Override
    public Book getBookByAuthor(String author){
        return repository.getBookByAuthor(author);
    }

    @Override
    public boolean deleteBookByTitle(String title){
        return repository.deleteBookByTitle(title);
    }

}
