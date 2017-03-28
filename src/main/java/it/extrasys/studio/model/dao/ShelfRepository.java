package it.extrasys.studio.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.studio.model.entity.ShelfEntity;

public interface ShelfRepository extends JpaRepository<ShelfEntity, Long> {
	@SuppressWarnings("unchecked")
	ShelfEntity save(ShelfEntity entity);
	
	Optional<ShelfEntity> findByCode(Optional<String> code);
}
