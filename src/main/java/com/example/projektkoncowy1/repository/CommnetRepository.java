package com.example.projektkoncowy1.repository;

import com.example.projektkoncowy1.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommnetRepository extends JpaRepository<Comment,Long> {
}
