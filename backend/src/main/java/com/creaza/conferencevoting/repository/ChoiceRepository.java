package com.creaza.conferencevoting.repository;

import com.creaza.conferencevoting.model.dao.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
