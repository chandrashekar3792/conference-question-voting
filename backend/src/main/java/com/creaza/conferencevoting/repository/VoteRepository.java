package com.creaza.conferencevoting.repository;

import com.creaza.conferencevoting.model.dao.Vote;
import com.creaza.conferencevoting.model.dto.VoteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select new com.creaza.conferencevoting.model.dto.VoteDto(vote.choice.question.id, vote.choice.id, COUNT(vote.choice.question.id)) from Vote as vote group by vote.choice.question.id")
    List<VoteDto> fetchVotes();
}
