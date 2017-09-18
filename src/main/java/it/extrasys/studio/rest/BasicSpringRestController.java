package it.extrasys.studio.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import it.extrasys.studio.model.entity.BookEntity;
import it.extrasys.studio.model.manager.BookManager;

/**
 * Controller Spring-REST.
 *
 * @author davide
 */
@Controller
@RequestMapping("/basic-rest")
public class BasicSpringRestController {
    private static final Logger LOG = LoggerFactory.getLogger(BasicSpringRestController.class);

    //private static final String TEMPLATE = "Wonder %s!";

    private static final String[] NAMES = { "Le uova fatali", "Cronache della galassia",
    		"L'Ordine della Fenice", "50 sfumature di grigio", "Le nuvole", "Fantozzi" };

    private static final String[] AUTHORS = { "Bulgakov", "Asimov",
    		"Rowling", "Aristotele", "Aristofane", "Villaggio" };

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private BookManager bookManager;

    /**
     * Chiamata REST basic (create + get single book).
     *
     * @param name
     * @return
     */
    // http://localhost:8080/demowebos/basic-rest
    // http://localhost:8080/demowebos/basic-rest?name=article
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public BookEntity sayHello(@RequestParam(value = "name", required = false, defaultValue = "book") String name) {
    	
    	BookEntity resBook = null;
    	
    	try {
        	String bookName = NAMES[new Random().nextInt(NAMES.length)];
        	String bookAuthor = AUTHORS[new Random().nextInt(AUTHORS.length)];

            BookEntity book = new BookEntity(bookName, bookAuthor);

            resBook = this.bookManager.save(book);
            
            LOG.debug("New book: " + resBook);
    	} catch (Exception e) {
    		LOG.error("Your code is a mess!", e);
    	}
    	
        return resBook;
    }

    /**
     * Chiamata REST basic (create book).
     *
     * @param newBook
     * @return
     */
    // POST http://localhost:8080/demowebos/basic-rest/books
    @RequestMapping(method = RequestMethod.POST, value = "/books")
    @ResponseBody
    public ResponseEntity<?> createBook(@RequestBody BookEntity newBook) {
        BookEntity result = this.bookManager.save(new BookEntity(newBook.getName(),
                newBook.getAuthor()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        LOG.debug("Response entity location: " + location);

        return ResponseEntity.created(location).build();
    }

    /**
     * Chiamata REST basic (get all books).
     *
     * @return
     */
    // http://localhost:8080/demowebos/basic-rest/books
    @RequestMapping(method = RequestMethod.GET, value = "/books")
    @ResponseBody
    public Collection<BookEntity> readBooks() {
        LOG.debug("\n\n " + getClass().getSimpleName() + " - readBooks()\n");

        List<BookEntity> target = new ArrayList<>();

        this.bookManager.findAll().forEach(target::add);

        LOG.debug("\n\n " + getClass().getSimpleName() + " - Result size: " + target.size() + "\n");

        return target;
    }

    /**
     * Chiamata REST get book.
     *
     * Se il book non viene trovato restituisce 404 (qual e' il pattern REST
     * piu' adeguato per gestire un caso del genere?)
     *
     * @param bookId
     * @return
     */
    // http://localhost:8080/demowebos/basic-rest/books/1
    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookId}")
    @ResponseBody
    public BookEntity readBook(@PathVariable Long bookId) {
        return validateAndGetBook(bookId);
    }

    private BookEntity validateAndGetBook(Long bookId) {
        BookEntity book = null;

        try {
            if (bookId != null) {
                book = this.bookManager.findOne(bookId);
            }
        } finally {
            if (book == null) {
                throw new BookNotFoundException(bookId);
            }
        }

        return book;
    }
}
