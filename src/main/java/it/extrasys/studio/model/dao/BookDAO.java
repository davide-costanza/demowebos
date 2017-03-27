package it.extrasys.studio.model.dao;

import org.springframework.data.repository.CrudRepository;

import it.extrasys.studio.model.entity.BookEntity;

public interface BookDAO extends CrudRepository<BookEntity, Long> {
	@SuppressWarnings("unchecked")
	BookEntity save(BookEntity bookEntity);
	
	BookEntity findOne(long id);	
}
