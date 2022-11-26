package com.example.elobit.mail.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Mail {
    @Id
    private String username;

    private String code;
}
