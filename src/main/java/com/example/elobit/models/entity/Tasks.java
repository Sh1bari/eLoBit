package com.example.elobit.models.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Vladimir Krasnov
 * БД для за задач, зависимость от Users
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String day; //формат: (0 <= число <= 31)

    private String month; //формат: (Ноябрь, Декабрь...)

    private String year; //формат: (2022, 2021...)

    private String title;

    private String text;

    private String status; //формат (done/in progress)

    private String importance; //формат:

    private String alert; //формат:
}
