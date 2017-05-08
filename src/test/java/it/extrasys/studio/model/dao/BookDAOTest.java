package it.extrasys.studio.model.dao;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import it.extrasys.studio.model.entity.BookEntity;

/**
 * Test con memory db effettuati con @DataJpaTest.
 *
 * @author davide
 */
// E' implicitamente Transactional
@DataJpaTest
@RunWith(SpringRunner.class)
@TestExecutionListeners(value = { SqlScriptsTestExecutionListener.class }, mergeMode = MergeMode.MERGE_WITH_DEFAULTS)
public class BookDAOTest {

    private static final Logger LOG = LoggerFactory.getLogger(BookDAOTest.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookDAO bookDao;

    private String currentTest = null;

    /**
     * Metodo di preparazione invocato prima di ogni test.
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        LOG.info("\n\n *** setup()\n");

        // Sostituito dagli script @Sql
        // this.bookDao.deleteAll();
    }

    /**
     * Save test, con commit del risultato.
     */
    @Test
    @Commit
    @Sql("clean_all_books.sql")
    public void saveABook() {
        System.out.println("\n\n saveABook - START");

        LOG.debug("Debugging here!");

        BookEntity book = new BookEntity("The book of wonder", "Wonderful");

        this.bookDao.save(book);

        LOG.info("Info here!");

        // Il metodo flush() e' presente anche nel JpaRepository
        this.entityManager.flush();

        System.out.println("\n\n saveABook - END\n");

        this.currentTest = "saveABook";
    }

    /**
     * Test con database preparato via Sql.
     */
    // La transazione viene implicitamente rollbackata
    @Test
    // NOTA BENE: viene eseguito in transazione, PRIMA del setup!
    @Sql({ "clean_all_books.sql", "test_read_books.sql" })
    public void readBooks() {
        System.out.println("\n\n readBooks - START");

        List<BookEntity> target = new ArrayList<>();

        this.bookDao.findAll().forEach(target::add);

        LOG.debug("Books: {}", target.size());

        System.out.println("\n\n readBooks - END\n");

        this.currentTest = "readBooks";
    }

    /**
     * Check after transaction.
     */
    @AfterTransaction
    void verifyFinalDatabaseState() {
        LOG.info("\n\n *** verifyFinalDatabaseState() - current test: {}\n", this.currentTest);

        // Essendoci un solo metodo AfterTransaction, e' necessario capire qual
        // e' il controllo post-transazione da effettuare
        if ("saveABook".equals(this.currentTest)) {
            Iterable<BookEntity> books = this.bookDao.findAll();

            List<BookEntity> bookList = new ArrayList<>();
            books.iterator().forEachRemaining(bookList::add);

            LOG.debug("\n\n *** Books: {}", bookList.size());

            assertThat(bookList.size(), is(1));
        } else if ("readBooks".equals(this.currentTest)) {
            // La transazione e' rollbackata, quindi il test non ha lasciato
            // tracce
        }
    }
}
