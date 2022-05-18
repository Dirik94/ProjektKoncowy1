package com.example.projektkoncowy1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
public class Comment extends Auditable{
    @Id
    @GeneratedValue
    @NonNull
    private Long id;
    private String body;

    @ManyToOne
    private Link link;

}
