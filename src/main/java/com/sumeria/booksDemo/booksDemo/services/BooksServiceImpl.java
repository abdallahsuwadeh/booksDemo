package com.sumeria.booksDemo.booksDemo.services;

import com.sumeria.booksDemo.booksDemo.models.Book;
import com.sumeria.booksDemo.booksDemo.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {
    private final BooksRepository repository;

    @Autowired
    BooksServiceImpl(BooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAll(){
        return repository.findAll();
    }

    @Override
    public Book createBook(Book book){
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
