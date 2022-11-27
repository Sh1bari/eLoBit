package com.example.elobit.models.samples;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class SampleUser {
    private String username;

    private String newUsername;

    private String mail;

    private String password;

    private String newPassword;

    private String code;
}
