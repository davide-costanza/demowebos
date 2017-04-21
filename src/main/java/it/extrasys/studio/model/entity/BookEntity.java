package it.extrasys.studio.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity JPA di prova.
 *
 * @author davide
 */
@Entity
@Table(name = "book")
public class BookEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String author;

    /**
     * Costruttore vuoto.
     */
    public BookEntity() {
    }

    /**
     * Costruttore per nome e autore.
     *
     * @param name
     * @param author
     */
    public BookEntity(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return this.id + "-" + this.name + "-" + this.author;
    }
}
