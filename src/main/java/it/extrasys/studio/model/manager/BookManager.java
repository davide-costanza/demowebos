package it.extrasys.studio.model.manager;

import it.extrasys.studio.model.entity.BookEntity;

public interface BookManager {
	void create(BookEntity bookEntity);
	
	BookEntity findOne(long id);
	
	BookEntity update(BookEntity bookEntity);
}
