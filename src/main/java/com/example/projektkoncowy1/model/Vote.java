package com.example.projektkoncowy1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Vote {
    @Id
    @GeneratedValue
    @NonNull
    private Long id;
    private int vote;

}
