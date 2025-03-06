package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.repository.UserRoleRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Setter
@Slf4j
public class UserRoleDaoService {

    /**
     * Autowired by Spring UserRoleRepository bean.
     */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * todo have to be cashed
     * Get list of all UserRole entities.
     * @return List of all UserRole entities.
     */
    public List<UserRoleJpaEntity> findAll() {
        log.debug("Finding all roles");
        List<UserRoleJpaEntity> userRoleJpaEntityList
                = userRoleRepository.findAll();
        log.debug("Found {} roles", userRoleJpaEntityList.size());
        return userRoleJpaEntityList;
    }

    /**
     * Get UserRole entity by its Id.
     * @param id Id.
     * @return UserRole entity.
     */
    @Cacheable("findUserRoleById")
    public Optional<UserRoleJpaEntity> findById(final Long id) {
        log.debug("Finding role with id {}", id);
        Optional<UserRoleJpaEntity> userRoleOptional
                = userRoleRepository.findById(id);
        log.debug("Found role: {}", userRoleOptional);
        return userRoleOptional;
    }

    /**
     * Retrieves a UserRoleJpaEntity based on the provided user role name.
     *
     * @param userRoleName the name of the user role to search for.
     * @return an Optional containing the found UserRoleJpaEntity if it exists,
     *         otherwise an empty Optional.
     */
    public Optional<UserRoleJpaEntity> findByName(final String userRoleName) {
        log.debug("Finding role with name {}", userRoleName);
        Optional<UserRoleJpaEntity> userRoleOptional
                = userRoleRepository.findByName(userRoleName);
        log.debug("Found role: {}", userRoleOptional);
        return userRoleOptional;
    }

    /**
     * Saves a UserRole entity to the database.
     *
     * @param userRoleJpaEntity the UserRoleJpaEntity object to be saved.
     * @return the saved UserRoleJpaEntity object.
     */
    public UserRoleJpaEntity save(final UserRoleJpaEntity userRoleJpaEntity) {
        log.debug("Saving role: {}", userRoleJpaEntity);
        UserRoleJpaEntity userRoleJpaEntitySaved
                = userRoleRepository.save(userRoleJpaEntity);
        log.debug("Saved role: {}", userRoleJpaEntitySaved);
        return userRoleJpaEntitySaved;
    }

}
