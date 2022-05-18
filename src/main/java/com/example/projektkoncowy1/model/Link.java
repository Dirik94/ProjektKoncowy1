package com.example.projektkoncowy1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
public class Link {
    @Id
    @GeneratedValue
    @NonNull
    private Long id;
    private String title;
    private String url;

}
