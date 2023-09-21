package com.example.testbooksandauthor.repository;

import com.example.testbooksandauthor.model.BookAuthorRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRelationRepository extends JpaRepository<BookAuthorRelation, Integer> {
}
