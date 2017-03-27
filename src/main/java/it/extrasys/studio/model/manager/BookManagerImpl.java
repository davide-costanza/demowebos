package it.extrasys.studio.model.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.extrasys.studio.model.dao.BookDAOImpl;
import it.extrasys.studio.model.entity.BookEntity;

@Component("bookService")
@Transactional
public class BookManagerImpl implements BookManager {

	@Autowired
	BookDAOImpl bookDao;
	
	@Override
	public void create(BookEntity bookEntity) {
		bookDao.create(bookEntity);
	}

	@Override
	public BookEntity findOne(long id) {
		return bookDao.findOne(id);
	}

	@Override
	public BookEntity update(BookEntity bookEntity) {
		return bookDao.update(bookEntity);
	}
}
