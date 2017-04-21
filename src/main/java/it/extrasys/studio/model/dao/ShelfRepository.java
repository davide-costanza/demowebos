package it.extrasys.studio.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.studio.model.entity.ShelfEntity;

/**
 * JPA Repository.
 *
 * @author davide
 */
public interface ShelfRepository extends JpaRepository<ShelfEntity, Long> {

    /**
     * Save new entity.
     */
    @Override
    @SuppressWarnings("unchecked")
    ShelfEntity save(ShelfEntity entity);

    /**
     * Find by code.
     *
     * @param code
     * @return
     */
    Optional<ShelfEntity> findByCode(Optional<String> code);
}
