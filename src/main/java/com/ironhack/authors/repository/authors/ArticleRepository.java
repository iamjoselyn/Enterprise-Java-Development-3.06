package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //Query: devolver las 5 artículos con más citaciones del último mes de una determinada especialidad
    @Query("select a from Article a where a.specialty = ?1 and a.numberCitations > ?2 and a.publishingDate > ?3")
    List<Article> findMostQuotedTopFiveArticles(String specialty, Long numberCitations, LocalDate publishingDate, Pageable pageable);

    @Query("select a from Article a where a.specialty like ?1") //a%n
    List<Article> findArticleBySpecialty(String specialty);

    @Query("select a from Article a where a.specialty like concat('%', ?1, '%')")
    List<Article> findBySpecialtyContains(String specialty);


    //@Query(value = "MYSQL QUERY", nativeQuery = true); el value puedes poner queries mysql
}
