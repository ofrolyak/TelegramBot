package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.entity.UserArchJpaEntity;
import com.tatko.telegram.bot.repository.UserArchRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Data Access Object for User entity.
 */
@Service
@Setter
@Slf4j
public class UserArchDaoService {

    /**
     * UserRepository is used to get User based info.
     */
    @Autowired
    private UserArchRepository userArchRepository;

    /**
     * Save User entity into DB.
     *
     * @param userArchJpaEntity entity
     * @return Saved User
     */
    public UserArchJpaEntity save(final UserArchJpaEntity userArchJpaEntity) {
        log.debug("Saving userArch: {}", userArchJpaEntity);
        UserArchJpaEntity entitySaved
                = userArchRepository.save(userArchJpaEntity);
        log.debug("Saved entity entitySaved: {}", entitySaved);
        return entitySaved;
    }


}
