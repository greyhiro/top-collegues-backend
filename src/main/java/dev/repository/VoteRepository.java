package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

}
