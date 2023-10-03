package com.sumeria.booksDemo.booksDemo.Dtos;

public class DeleteBookResposnse {
    public DeleteBookResposnse(String title) {
        this.title = title;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
