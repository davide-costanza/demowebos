package it.extrasys.studio.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import it.extrasys.studio.model.entity.BookEntity;

/**
 * Test con memory db effettuati con @DataJpaTest.
 *
 * @author davide
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class BookDAOTest {

    private static final Logger LOG = LoggerFactory.getLogger(BookDAOTest.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookDAO bookDao;

    /**
     * Save test, con commit del risultato.
     */
    @Test
    @Commit
    public void saveABook() {
        System.out.println("\n\nHere!");

        LOG.debug("Debugging here!");

        BookEntity book = new BookEntity("The book of wonder", "Wonderful");

        this.bookDao.save(book);

        LOG.info("Info here!");

        // Il metodo flush() e' presente anche nel JpaRepository
        this.entityManager.flush();

        System.out.println("\n\n... and here!\n");
    }

    /**
     * Check after transaction.
     */
    @AfterTransaction
    void verifyFinalDatabaseState() {
        LOG.info("verifyFinalDatabaseState()");

        Iterable<BookEntity> books = this.bookDao.findAll();

        List<BookEntity> bookList = new ArrayList<>();
        books.iterator().forEachRemaining(bookList::add);

        System.out.println("Books: " + bookList.size());
    }
}
