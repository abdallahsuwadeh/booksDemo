package com.sumeria.booksDemo.booksDemo.webClients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TitleToAuthorResponse {
    private String firstName;
    private String lastName;

    public TitleToAuthorResponse(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
