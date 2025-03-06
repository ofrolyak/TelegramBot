package com.tatko.telegram.bot.repository;

import com.tatko.telegram.bot.entity.UserArchJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for User entity.
 */

@Repository
public interface UserArchRepository
        extends JpaRepository<UserArchJpaEntity, Long> {

}
