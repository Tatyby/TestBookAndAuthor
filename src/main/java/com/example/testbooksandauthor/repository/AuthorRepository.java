package com.example.testbooksandauthor.repository;

import com.example.testbooksandauthor.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByAuthorFullName(String authorFullName);

}
