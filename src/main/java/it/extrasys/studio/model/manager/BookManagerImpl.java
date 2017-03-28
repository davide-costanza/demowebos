package it.extrasys.studio.model.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.extrasys.studio.model.dao.BookDAO;
import it.extrasys.studio.model.entity.BookEntity;

@Component("bookService")
@Transactional
public class BookManagerImpl implements BookManager {

	@Autowired
	BookDAO bookDao;
	
	@Override
	public BookEntity save(BookEntity bookEntity) {
		return bookDao.save(bookEntity);
	}

	@Override
	public BookEntity findOne(long id) {
		return bookDao.findOne(id);
	}
}
