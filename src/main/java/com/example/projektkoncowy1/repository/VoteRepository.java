package com.example.projektkoncowy1.repository;

import com.example.projektkoncowy1.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository  extends JpaRepository<Vote,Long> {
}
