package com.example.TheFit.career.domain;

import javax.persistence.*;

@Entity
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String awards;
    private String license;
    private String work;
}
