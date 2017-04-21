package it.extrasys.studio.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.studio.model.entity.BookEntity;
import it.extrasys.studio.model.manager.BookManager;

/**
 * Bean facade a uso di Camel, che wrappa a sua volta una facade transazionale.
 * Sostanzialmente inutile, dato che e' possibile usare direttamente il manager
 * transazionale.
 *
 * @author davide
 */
@Component
public class RestService {
    private static final Logger LOG = LoggerFactory.getLogger(RestService.class);

    @Autowired
    private BookManager bookManager;

    /**
     * Log entity.
     *
     * @param bookEntity
     */
    public void logBook(BookEntity bookEntity) {
        LOG.debug(" *** Received book: " + bookEntity);
    }

    /**
     * Delega il save entity.
     *
     * @param bookEntity
     * @return
     */
    public BookEntity saveBook(BookEntity bookEntity) {
        return this.bookManager.save(bookEntity);
    }

    /**
     * Delega il find entity by id.
     *
     * @param id
     * @return
     */
    public BookEntity findBook(long id) {
        LOG.debug(" *** findBook(): id = " + id);

        return this.bookManager.findOne(id);
    }

    /**
     * Delega il find all entities.
     *
     * @return
     */
    public Iterable<BookEntity> findBooks() {
        return this.bookManager.findAll();
    }
}
