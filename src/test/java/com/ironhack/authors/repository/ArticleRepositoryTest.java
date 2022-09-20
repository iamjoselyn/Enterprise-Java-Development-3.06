package com.ironhack.authors.repository;

import com.ironhack.authors.model.authors.Article;
import com.ironhack.authors.model.authors.Author;
import com.ironhack.authors.model.authors.BlogPost;
import com.ironhack.authors.model.authors.Book;
import com.ironhack.authors.repository.authors.ArticleRepository;
import com.ironhack.authors.repository.authors.AuthorRepository;
import com.ironhack.authors.repository.authors.BlogPostRepository;
import com.ironhack.authors.repository.authors.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ArticleRepositoryTest {
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

        //Books
        Book javaBook = new Book();
        javaBook.setTitle("Java is Fun");
        javaBook.setNumPages(200);
        javaBook.setPublishingDate(LocalDate.of(2017, 4, 4));

        javaBook.getAuthors().add(esperanza);
        esperanza.getPublications().add(javaBook);

        bookRepository.save(javaBook);

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

        BlogPost inheritancePost = new BlogPost();
        inheritancePost.setTitle("Inheritance Strategies with JPA and Hibernate – The Complete Guide");
        inheritancePost.setPublishingDate(LocalDate.of(2020, 1, 23));
        inheritancePost.setUrl("https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/#Joined");

        inheritancePost.getAuthors().add(paz);
        paz.getPublications().add(inheritancePost);

        blogPostRepository.save(inheritancePost);

        //Article type Objects intances
        Article inheritanceArticle1 = new Article();

        inheritanceArticle1.setTitle("Software history");
        inheritanceArticle1.setPublishingDate(LocalDate.of(2021, 3, 28));

        inheritanceArticle1.setNumberCitations(45L);
        inheritanceArticle1.setNumberRevisions(101L);
        inheritanceArticle1.setSpecialty("Software Development");
        //Asociación bidireccional
        inheritanceArticle1.getAuthors().add(felicidad);
        inheritanceArticle1.getAuthors().add(paz);
        inheritanceArticle1.getAuthors().add(paz);

        felicidad.getPublications().add(inheritanceArticle1);
        paz.getPublications().add(inheritanceArticle1);
        paz.getPublications().add(inheritanceArticle1);

        articleRepository.save(inheritanceArticle1);

        //Article 2
        Article inheritanceArticle2 = new Article();

        inheritanceArticle2.setTitle("Java easy level");
        inheritanceArticle2.setPublishingDate(LocalDate.of(2020, 3, 28));

        inheritanceArticle2.setNumberCitations(10L);
        inheritanceArticle2.setNumberRevisions(218L);
        inheritanceArticle2.setSpecialty("Java Development");
        //Asociación bidireccional
        inheritanceArticle2.getAuthors().add(esperanza);
        inheritanceArticle2.getAuthors().add(felicidad);
        inheritanceArticle2.getAuthors().add(paz);

        felicidad.getPublications().add(inheritanceArticle2);
        paz.getPublications().add(inheritanceArticle2);
        esperanza.getPublications().add(inheritanceArticle2);

        articleRepository.save(inheritanceArticle2);

        //Article 3
        Article inheritanceArticle3 = new Article();

        inheritanceArticle3.setTitle("Java difficult level");
        inheritanceArticle3.setPublishingDate(LocalDate.of(2021, 4, 28));

        inheritanceArticle3.setNumberCitations(20L);
        inheritanceArticle3.setNumberRevisions(324L);
        inheritanceArticle3.setSpecialty("Java Development");
        //Asociación bidireccional
        inheritanceArticle3.getAuthors().add(felicidad);
        inheritanceArticle3.getAuthors().add(paz);

        felicidad.getPublications().add(inheritanceArticle3);
        paz.getPublications().add(inheritanceArticle3);

        articleRepository.save(inheritanceArticle3);

        //Article 4
        Article inheritanceArticle4 = new Article();

        inheritanceArticle4.setTitle("Java medium level");
        inheritanceArticle4.setPublishingDate(LocalDate.of(2021, 5, 28));

        inheritanceArticle4.setNumberCitations(30L);
        inheritanceArticle4.setNumberRevisions(424L);
        inheritanceArticle4.setSpecialty("Java Development");
        //Asociación bidireccional
        inheritanceArticle4.getAuthors().add(felicidad);
        inheritanceArticle4.getAuthors().add(paz);

        felicidad.getPublications().add(inheritanceArticle4);
        paz.getPublications().add(inheritanceArticle4);

        articleRepository.save(inheritanceArticle4);

        //Article 5
        Set<Author> authors = new HashSet<>();
        authors.add(felicidad);
        authors.add(paz);

        Article inheritanceArticle5 = new Article("Java medium level", LocalDate.of(2021, 5, 28), authors,424L, 30L, "Java Development");

        //Asociación bidireccional
        felicidad.addPublication(inheritanceArticle5);
        paz.addPublication(inheritanceArticle5);

        articleRepository.save(inheritanceArticle5);

        //Article 6
        Set<Author> authors2 = new HashSet<>();
        authors2.add(consuelo);
        authors2.add(esperanza);
        Article inheritanceArticle6 = new Article("Java for dummies", LocalDate.of(2021, 5, 28), authors2,424L, 30L, "Java Development");
        //Asociación bidireccional
        felicidad.addPublication(inheritanceArticle6);
        paz.addPublication(inheritanceArticle6);

        articleRepository.save(inheritanceArticle6);

    }

    @Test
    void findArticleBySpecialty() {
        List<Article> articles = articleRepository.findBySpecialtyContains("ftware");
        assertEquals(1, articles.size());
    }

    @Test
    void findTopFiveMostQuotedArticles() {
        List<Article> mostQuotedArticle = articleRepository.findMostQuotedTopFiveArticles("Software Development", 30L, LocalDate.of(2021, 3, 27), PageRequest.of(0, 5));
        assertEquals(1, mostQuotedArticle.size());
    }


}
