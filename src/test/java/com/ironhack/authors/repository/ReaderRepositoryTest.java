package com.ironhack.authors.repository;

import com.ironhack.authors.enums.UserProfile;
import com.ironhack.authors.model.authors.*;
import com.ironhack.authors.repository.authors.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ReaderRepositoryTest {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        //Authors instances
        Author paz = new Author();
        paz.setFirstName("Paz");
        paz.setLastName("Alegría");
        authorRepository.save(paz);

        Author esperanza = new Author();
        esperanza.setFirstName("Esperanza");
        esperanza.setLastName("Amor");
        authorRepository.save(esperanza);

        Author felicidad = new Author();
        felicidad.setFirstName("Felicidad");
        felicidad.setLastName("Abundante");
        authorRepository.save(felicidad);

        Author consuelo = new Author();
        consuelo.setFirstName("Consuelo");
        consuelo.setLastName("Sabio");
        authorRepository.save(consuelo);

        //Book: JPA is awesome
        Book jpaBook = new Book();
        jpaBook.setTitle("JPA is awesome!");
        jpaBook.setNumPages(300);
        jpaBook.setPublishingDate(LocalDate.of(2022, 4, 4));
        //La asociación es bidireccional
        jpaBook.getAuthors().add(esperanza);
        jpaBook.getAuthors().add(paz);
        esperanza.getPublications().add(jpaBook);
        paz.getPublications().add(jpaBook);
        bookRepository.save(jpaBook);

        //BlogPost: Inheritance...
        BlogPost inheritancePost = new BlogPost();
        inheritancePost.setTitle("Inheritance Strategies with JPA and Hibernate – The Complete Guide");
        inheritancePost.setPublishingDate(LocalDate.of(2020, 1, 23));
        inheritancePost.setUrl("https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/#Joined");

        inheritancePost.getAuthors().add(paz);
        paz.getPublications().add(inheritancePost);
        blogPostRepository.save(inheritancePost);

        //Article 5
        Set<Author> authors = new HashSet<>();
        authors.add(felicidad);
        authors.add(paz);
        Article article5 = new Article("Java medium level", LocalDate.of(2021, 5, 28), authors,424L, 30L, "Java Development");
        //Asociación bidireccional
        felicidad.addPublication(article5);
        paz.addPublication(article5);
        articleRepository.save(article5);

        //Reader Lucas solo lee 1 artículo
        ArrayList<Publication> readPublications1 = new ArrayList<>();
        readPublications1.add(article5);
        Reader lucas = new Reader("Lucas", "Pelucas", UserProfile.Advanced, readPublications1);
        readerRepository.save(lucas);

        //Reader Mateo lee Book y BLogpost
        ArrayList<Publication> readPublications2 = new ArrayList<>();
        readPublications2.add(inheritancePost);
        readPublications2.add(jpaBook);
        Reader mateo = new Reader("Mateo", "Pelucon", UserProfile.Beginner, readPublications2 );
        readerRepository.save(mateo);
    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
        blogPostRepository.deleteAll();
        articleRepository.deleteAll(); //este es el orden para que no se borre antes los autores
        authorRepository.deleteAll();
        readerRepository.deleteAll();
    }

    @Test
    void findByFirstNameOrLastNameThatStartsWith() {
        List<Reader> readerList = readerRepository.findByFirstNameContainsOrLastNameContains("luc", "luc");
        assertEquals(2, readerList.size());
    }
}
