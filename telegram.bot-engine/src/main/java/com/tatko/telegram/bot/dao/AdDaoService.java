package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.entity.AdJpaEntity;
import com.tatko.telegram.bot.repository.AdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class AdDaoService {

    /**
     * Autowired by Spring AdRepository bean.
     */
    @Autowired
    private AdRepository adRepository;

    /**
     * Save Ad entity.
     * @param adJpaEntity Ad entity to save.
     * @return Saved Ad entity.
     */
    public AdJpaEntity save(final AdJpaEntity adJpaEntity) {
        log.debug("Saving ad: {}", adJpaEntity);
        AdJpaEntity entitySaved = adRepository.save(adJpaEntity);
        log.debug("Saved entity entitySaved: {}", entitySaved);
        return entitySaved;
    }

    /**
     * Find Ad to deliver.
     * @param localDateTime
     * @return Optional of Ad entity.
     */
    public Optional<AdJpaEntity> findAdToDeliver(
            final LocalDateTime localDateTime) {
        log.debug("Start to process findAdToDeliver for localDateTime: {}",
                localDateTime);
        Optional<AdJpaEntity> adOptional = adRepository
                .findFirstByDeliveredTimeIsNullOrDeliveredTimeIsBefore(
                        localDateTime);
        log.debug("Finish to process findAdToDeliver, adOptional: {}",
                adOptional);
        return adOptional;
    }

}
