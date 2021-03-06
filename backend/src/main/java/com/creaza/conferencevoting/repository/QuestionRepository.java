package com.creaza.conferencevoting.repository;

import com.creaza.conferencevoting.model.dao.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}