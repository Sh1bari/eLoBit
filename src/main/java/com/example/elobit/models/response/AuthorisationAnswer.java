package com.example.elobit.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthorisationAnswer {
    private String usernameAnswer;

    private String passwordAnswer;
}
