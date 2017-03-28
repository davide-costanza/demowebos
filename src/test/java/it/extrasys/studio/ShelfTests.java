package it.extrasys.studio;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import it.extrasys.studio.model.dao.ShelfRepository;
import it.extrasys.studio.model.entity.ShelfEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShelfTests {

	private static final Logger LOG = LoggerFactory.getLogger(ShelfTests.class);
	
	@Autowired
	private ShelfRepository shelfRepo;

	@Test
	@Transactional
	@Rollback
	public void saveAShelf() {
		System.out.println("\n\n saveAShelf()");
	
		LOG.debug("saveAShelf()");
		
		ShelfEntity shelf = new ShelfEntity("shelfcode" + UUID.randomUUID());
		
		shelfRepo.save(shelf);

		LOG.debug("Flushing...");

		shelfRepo.flush();

		System.out.println("\n\n shelf saved!\n");
	}
}
