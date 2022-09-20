package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    @Query("select r from Reader r where r.firstName like concat(?1, '%') or r.lastName like concat(?2, '%')")
    List<Reader> findByFirstNameStartsWithOrLastNameStartsWith(String firstName, String lastName);

    @Query("select r from Reader r " +
            "where r.firstName like concat('%', ?1, '%') or r.lastName like concat('%', ?2, '%') " +
            "order by r.firstName, r.lastName")
    List<Reader> findByFirstNameContainsOrLastNameContains(String firstName, String lastName);



}
