package com.sumeria.booksDemo.booksDemo.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Book {
    @NotBlank(message = "Title is mandatory")
    private String title;
    private String author;

    @Min(15)
    private int numberOfPages;

    public Book(){

    }

    public Book(String title, String author, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
