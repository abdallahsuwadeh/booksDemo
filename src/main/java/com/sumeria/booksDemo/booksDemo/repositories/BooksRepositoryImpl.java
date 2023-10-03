package com.sumeria.booksDemo.booksDemo.repositories;

import com.sumeria.booksDemo.booksDemo.models.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class BooksRepositoryImpl implements BooksRepository {

    private final HashMap<String, Book> data;

    public BooksRepositoryImpl(){
        this.data = new HashMap<String, Book>();
        this.data.put("My_Lovely_Book", new Book("My_Lovely_Book", "Talal Mahmoud", 88));
        this.data.put("book_2", new Book("book_2", "Abed Mohammad", 23));
        this.data.put("Look_Away", new Book("Look Away", "Mahmoud Blabla", 324));
        this.data.put("My_Lovely_Book2", new Book("My_Lovely_Book2", "Talal Mahmoud", 828));
        this.data.put("My_Lovely_Book3", new Book("My_Lovely_Book3", "Talal Mahmoud", 11));
    }

    public List<Book> findAll(){
        return data.values().stream().toList();
    }

    public Book createBook(Book book){
        data.put(book.getTitle(), book);
        return book;
    }

    public Book getBookByTitle(String title){
        return data.get(title);
    }

    public Book getBookByAuthor(String author){
        return data
                .values()
                .stream()
                .filter(b -> b.getAuthor().equals(author))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteBookByTitle(String title){
        if(data.containsKey(title)) {
            data.remove(title);
            return true;
        }

        return false;
    }
}
