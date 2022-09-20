package com.ironhack.authors.model.authors;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Article extends Publication {
    private Long numberRevisions;
    private Long numberCitations;
    private String specialty;

    public Article(){}
    public Article(String title, LocalDate publishingDate, Set<Author> authors, Long numberRevisions, Long numberCitations, String specialty) {
        super(title, publishingDate, authors);
        this.numberRevisions = numberRevisions;
        this.numberCitations = numberCitations;
        this.specialty = specialty;
    }

    //Gets

    public Long getNumberRevisions() {
        return numberRevisions;
    }

    public Long getNumberCitations() {
        return numberCitations;
    }

    public String getSpecialty() {
        return specialty;
    }

    //Sets

    public void setNumberRevisions(Long numberRevisions) {
        this.numberRevisions = numberRevisions;
    }

    public void setNumberCitations(Long numberCitations) {
        this.numberCitations = numberCitations;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /*@Override
    public String toString() {
        return "Article{" +
                "numberRevisions=" + numberRevisions +
                ", numberCitations=" + numberCitations +
                ", specialty='" + specialty + '\'' +
                '}';
    }*/
}
