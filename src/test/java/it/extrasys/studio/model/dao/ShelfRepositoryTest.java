package it.extrasys.studio.model.dao;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import it.extrasys.studio.model.entity.ShelfEntity;

/**
 * Test con memory db mediante @DataJpaTest.
 *
 * @author davide
 */
// Configura un in-memory db (vedi application.properties), rileva entity e
// repository bypassando l'application context applicativo e rende tutto
// @Transactional di default
@DataJpaTest
@RunWith(SpringRunner.class)
public class ShelfRepositoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(ShelfRepositoryTest.class);

    @Autowired
    private ShelfRepository shelfRepo;

    /**
     * Save test con rollback.
     */
    @Test
    @Rollback
    public void saveAShelf() {
        System.out.println("\n\n saveAShelf()");

        LOG.debug("saveAShelf()");

        ShelfEntity shelf = new ShelfEntity("shelfcode" + UUID.randomUUID());

        this.shelfRepo.save(shelf);

        LOG.debug("Flushing...");

        this.shelfRepo.flush();

        System.out.println("\n\n shelf saved!\n");
    }
}
