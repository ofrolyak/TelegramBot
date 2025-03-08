package com.tatko.api.repositories;

import com.tatko.api.entities.AdJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsRepository
        extends JpaRepository<AdJpaEntity, Long>,
        JpaSpecificationExecutor<AdJpaEntity> {
}
