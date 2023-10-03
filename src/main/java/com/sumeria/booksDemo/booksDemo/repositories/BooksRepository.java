package com.sumeria.booksDemo.booksDemo.repositories;

import com.sumeria.booksDemo.booksDemo.models.Book;

import java.util.List;

public interface BooksRepository {
    List<Book> findAll();

    Book createBook(Book book);

    Book getBookByTitle(String title);

    boolean deleteBookByTitle(String title);
}
