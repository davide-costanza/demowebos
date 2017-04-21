package it.extrasys.studio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.extrasys.studio.model.entity.BookEntity;
import it.extrasys.studio.model.manager.BookManager;

/**
 * Command line runner usato per l'inizializzazione del db.
 *
 * @author davide
 */
@Component
public class DemowebosCommandLineRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DemowebosCommandLineRunner.class);

    @Autowired
    private BookManager service;

    @Override
    public void run(String... arg0) throws Exception {
        LOG.info("\n\n Initializing database...");

        this.service.save(new BookEntity("Fatal eggs", "Bulgakov"));
        this.service.save(new BookEntity("The mound", "Lovecraft"));

        Iterable<BookEntity> books = this.service.findAll();

        books.iterator().forEachRemaining(System.out::println);

        LOG.info("\n ...Done!\n");
    }
}
