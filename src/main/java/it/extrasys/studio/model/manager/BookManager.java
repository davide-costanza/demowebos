package it.extrasys.studio.model.manager;

import it.extrasys.studio.model.entity.BookEntity;

/**
 * Interfaccia del facade book manager.
 *
 * @author davide
 */
public interface BookManager {
    /**
     * Save.
     *
     * @param bookEntity
     * @return
     */
    BookEntity save(BookEntity bookEntity);

    /**
     * Find by id.
     *
     * @param id
     * @return
     */
    BookEntity findOne(long id);

    /**
     * Find all.
     *
     * @return
     */
    Iterable<BookEntity> findAll();
}
