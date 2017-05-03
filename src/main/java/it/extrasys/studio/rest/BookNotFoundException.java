package it.extrasys.studio.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione custom: quando la si lancia da un web service Spring genera uno
 * status code 404.
 *
 * @author davide
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6648904846420784876L;

    /**
     * Messaggio informativo: contiene l'id errato.
     *
     * @param bookId
     */
    public BookNotFoundException(Long bookId) {
        super("Could not find book '" + bookId + "'.");
    }
}
