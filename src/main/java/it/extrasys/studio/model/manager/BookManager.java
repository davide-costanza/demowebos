package it.extrasys.studio.model.manager;

import it.extrasys.studio.model.entity.BookEntity;

public interface BookManager {
	BookEntity save(BookEntity bookEntity);
	
	BookEntity findOne(long id);
}
