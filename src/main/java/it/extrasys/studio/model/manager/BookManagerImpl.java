package it.extrasys.studio.model.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.extrasys.studio.model.dao.BookDAO;
import it.extrasys.studio.model.entity.BookEntity;

/**
 * Facade book manager per la transazionalita'.
 *
 * @author davide
 */
@Component("bookService")
@Transactional
public class BookManagerImpl implements BookManager {

    // Repository Spring Data JPA
    @Autowired
    private BookDAO bookDao;

    @Override
    public BookEntity save(BookEntity bookEntity) {
        return this.bookDao.save(bookEntity);
    }

    @Override
    public BookEntity findOne(long id) {
        return this.bookDao.findOne(id);
    }

    @Override
    public Iterable<BookEntity> findAll() {
        return this.bookDao.findAll();
    }
}
