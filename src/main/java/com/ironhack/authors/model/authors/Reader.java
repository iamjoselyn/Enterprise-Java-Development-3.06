package com.ironhack.authors.model.authors;

import com.ironhack.authors.enums.UserProfile;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Data da problemas de eficiencia
public class Reader extends User {

    @Column(columnDefinition = "ENUM('Beginner', 'Advanced', 'Professional')", nullable = true)
    @Enumerated(EnumType.STRING)
    private UserProfile profile;

    @ManyToMany(mappedBy = "readers")
    private List<Publication> publications = new ArrayList<>();

    public Reader(){}
    public Reader(String firstName, String lastName, UserProfile profile, List<Publication> publications) {
        super(firstName, lastName);
        this.profile = profile;
        this.publications = publications;
    }

    //Gets
    public UserProfile getProfile() {
        return profile;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    //Method to add publications to the Reader
    public void addPublication(Publication publication) {
        //añade el autor a la lista de autores de la publicación
        publication.getReaders().add(this);
        //añade la publicación a la lista de publicaciones del autor
        this.publications.add(publication);

        //añade un fila con los ids a la tabla intermedia
    }

}
