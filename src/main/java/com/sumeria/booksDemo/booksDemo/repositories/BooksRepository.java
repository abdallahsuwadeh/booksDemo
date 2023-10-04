package com.sumeria.booksDemo.booksDemo.repositories;

import com.sumeria.booksDemo.booksDemo.models.Book;

import java.util.List;

public interface BooksRepository {
    List<Book> findAll();

    Book createOrUpdateBook(Book book);

    Book getBookByTitle(String title);

    Book getBookByAuthor(String author);

    boolean deleteBookByTitle(String title);
}
