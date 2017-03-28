package it.extrasys.studio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import it.extrasys.studio.model.dao.BookDAO;
import it.extrasys.studio.model.dao.ShelfRepository;
import it.extrasys.studio.model.entity.BookEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemowebosApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(DemowebosApplicationTests.class);
	
    @Autowired
    private EntityManager entityManager;

	@Autowired
	private BookDAO bookDao;

	@Autowired
	private ShelfRepository shelfRepo;

	@Test
	@Transactional
	@Commit
	public void saveABook() {
		System.out.println("\n\nHere!");
	
		LOG.debug("Debugging here!");
		
		BookEntity book = new BookEntity("The book of wonder", "Wonderful");
		
		bookDao.save(book);

		LOG.info("Info here!");

		entityManager.flush();
		
		System.out.println("\n\n... and here!\n");
	}
	
	@AfterTransaction
    void verifyFinalDatabaseState() {
		LOG.info("verifyFinalDatabaseState()");
		
		Iterable<BookEntity> books = bookDao.findAll();
		
		List<BookEntity> bookList = new ArrayList<>();
		books.iterator().forEachRemaining(bookList::add);
		
		System.out.println("Books: " + bookList.size());		
    }
}
