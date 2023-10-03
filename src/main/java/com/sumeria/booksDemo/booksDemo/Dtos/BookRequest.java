package com.sumeria.booksDemo.booksDemo.Dtos;

import com.sumeria.booksDemo.booksDemo.models.Book;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BookRequest {
    @NotBlank(message = "Title is mandatory")
    private String title;
    private String author;

    @Min(15)
    private int numberOfPages;

    private String someThingWeDontNeedInModel;

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

    public String getSomeThingWeDontNeedInModel() {
        return someThingWeDontNeedInModel;
    }

    public void setSomeThingWeDontNeedInModel(String someThingWeDontNeedInModel) {
        this.someThingWeDontNeedInModel = someThingWeDontNeedInModel;
    }

    public static Book toModel(BookRequest request) {
        return new Book(request.getTitle(), request.getAuthor(), request.getNumberOfPages());
    }
}
