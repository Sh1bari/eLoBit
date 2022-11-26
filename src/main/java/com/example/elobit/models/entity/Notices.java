package com.example.elobit.models.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Notices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String day;

    private String month;

    private String year;

    private String title;

    private String text;


}
