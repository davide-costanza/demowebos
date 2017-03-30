package it.extrasys.studio.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.studio.model.entity.BookEntity;
import it.extrasys.studio.model.manager.BookManager;

@Component
public class RestService {
	private static final Logger LOG = LoggerFactory.getLogger(RestService.class);
			
	@Autowired
	private BookManager bookManager;
	
	public void logBook(BookEntity bookEntity) {
		LOG.debug(" *** Received book: " + bookEntity);
	}
	
	public BookEntity saveBook(BookEntity bookEntity) {
		return bookManager.save(bookEntity);
	}
	
	public BookEntity findBook(long id) {
		LOG.debug(" *** findBook(): id = " + id);
		
		return bookManager.findOne(id);
	}
	
	public Iterable<BookEntity> findBooks() {		
		return bookManager.findAll();
	}
}
