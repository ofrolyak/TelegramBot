package com.tatko.telegram.bot.repository;

import com.tatko.telegram.bot.entity.AdJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository for Ad entity.
 */

@Repository
public interface AdRepository extends JpaRepository<AdJpaEntity, Long> {

    /**
     * Find Ad entity based on Delivered timestamp.
     * @param localDateTime
     * @return Optional of Ad entity.
     */
    Optional<AdJpaEntity> findFirstByDeliveredTimeIsNullOrDeliveredTimeIsBefore(
            LocalDateTime localDateTime);

}
