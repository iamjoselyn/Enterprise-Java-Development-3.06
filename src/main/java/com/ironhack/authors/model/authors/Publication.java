package com.ironhack.authors.model.authors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	private int version;

	private String title;

	private LocalDate publishingDate;
	
	@ManyToMany
	@JoinTable(
		      name="PublicationAuthor",
		      joinColumns={@JoinColumn(name="publicationId", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="authorId", referencedColumnName="id")})
	private Set<Author> authors = new HashSet<Author>();

	//Tabla intermedia que se crea
	@ManyToMany
	@JoinTable(
			name="PublicationReadByReaders",
			joinColumns={@JoinColumn(name="publicationId", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="readerId", referencedColumnName="id")})
	private Set<Reader> readers = new HashSet<Reader>();

	public Publication(){}
	public Publication(String title, LocalDate publishingDate, Set<Author> authors) {
		this.title = title;
		this.publishingDate = publishingDate;
		this.authors = authors;
	}

	public Long getId() {
		return this.id;
	}

	public int getVersion() {
		return this.version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(LocalDate publishingDate) {
		this.publishingDate = publishingDate;
	}
	
	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Set<Reader> getReaders() {
		return readers;
	}
	public void setReaders(Set<Reader> readers) {
		this.readers = readers;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Publication)) {
			return false;
		}
		Publication other = (Publication) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	/*@Override
	public int hashCode() {
		return 31;
	}*/

	@Override
	public int hashCode() {
		return Objects.hash(id, version, title, publishingDate);
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (title != null && !title.trim().isEmpty())
			result += "title: " + title;
		return result;
	}
}
