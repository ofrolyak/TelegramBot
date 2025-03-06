package com.tatko.telegram.bot.service.internal;

import com.tatko.telegram.bot.dao.UserRoleDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.exception.UserRoleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRoleService {

    /**
     * Autowired by Spring UserRoleDao instance.
     */
    @Autowired
    private UserRoleDaoService userRoleDaoService;

    /**
     * Get UserRole instance by User.
     *
     * @param userJpaEntity
     * @return UserRole instance.
     */
    public UserRoleJpaEntity getUserRoleByUser(
            final UserJpaEntity userJpaEntity) {

        log.debug("Process getUserRoleByUser for {}", userJpaEntity);

        UserRoleJpaEntity userRoleJpaEntity1
                = userRoleDaoService.findAll().stream()
                .filter(userRole
                        -> userRole.getId().equals(
                                userJpaEntity.getUserRoleJpaEntity().getId()))
                .findFirst()
                .orElseThrow(UserRoleNotFoundException::new);

        log.debug("Finished process getUserRoleByUser for {}, userRole1: {}",
                userJpaEntity, userRoleJpaEntity1);

        return userRoleJpaEntity1;

    }


}
