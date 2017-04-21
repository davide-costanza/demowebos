package it.extrasys.studio.model.dao;

import org.springframework.data.repository.CrudRepository;

import it.extrasys.studio.model.entity.BookEntity;

/**
 * CRUD repository Spring Data JPA.
 *
 * @author davide
 */
public interface BookDAO extends CrudRepository<BookEntity, Long> {

    /**
     * Save book.
     */
    @Override
    @SuppressWarnings("unchecked")
    BookEntity save(BookEntity bookEntity);

    /**
     * Find book by id.
     *
     * @param id
     * @return
     */
    BookEntity findOne(long id);

    /**
     * Find all books.
     */
    @Override
    Iterable<BookEntity> findAll();
}
