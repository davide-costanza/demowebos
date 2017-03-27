package it.extrasys.studio.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.extrasys.studio.model.entity.BookEntity;

//@Repository
public class BookDAOImpl /*implements BookDAO*/ {
	//@PersistenceContext
	EntityManager entityManager;

	public void create(BookEntity bookEntity) {
		entityManager.persist(bookEntity);
	}

	public BookEntity findOne(long id) {
		return entityManager.find(BookEntity.class, id);
	}

	public BookEntity update(BookEntity bookEntity) {
		return entityManager.merge(bookEntity);
	}
}
//*/
