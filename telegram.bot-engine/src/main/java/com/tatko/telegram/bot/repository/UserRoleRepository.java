package com.tatko.telegram.bot.repository;

import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRoleRepository
        extends JpaRepository<UserRoleJpaEntity, Long> {

    /**
     * Finds a UserRoleJpaEntity by its name.
     *
     * @param userRoleName the name of the user role to search for
     * @return an Optional containing the UserRoleJpaEntity if found,
     * or an empty Optional if not found
     */
    Optional<UserRoleJpaEntity> findByName(String userRoleName);

}
