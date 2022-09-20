package com.ironhack.authors.model.authors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author extends User{
	//@Version indica al motor de bbdd una estrategia
	//private int version;
	//optimistic locking: tema interno de ingenieria
	//pesimistic locking
	// lock: bloqueo que hace mysql para orquestrar las transacciones de manera recurrente

	private String specialty;

	@ManyToMany(mappedBy="authors")
	private List<Publication> publications = new ArrayList<Publication>();

	public Author() {}

	public Author(String firstName, String lastName, String specialty) {
		super(firstName, lastName);
		this.specialty = specialty;
	}

	//Gets
	//public int getVersion() {
		//return this.version;
	//}
	public List<Publication> getPublications() {
		return publications;
	}
	public String getSpecialty() {
		return specialty;
	}

	public void addPublication(Publication publication) {
		//añade el autor a la lista de autores de la publicación
		publication.getAuthors().add(this);
		//añade la publicación a la lista de publicaciones del autor
		this.publications.add(publication);

		//añade un fila con los ids a la tabla intermedia
	}


	@Override
	public int hashCode() {
		return 31;
	}

}