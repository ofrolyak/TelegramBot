package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserRole4findAll4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserRoleRepository userRoleRepository;
    @InjectMocks
    UserRoleDaoService userRoleDaoService;

    @Test
    void save4success4notExist4Test() {

        // When
        Mockito.when(userRoleRepository.findAll())
                .thenReturn(Collections.emptyList());

        // Action
        Collection<UserRoleJpaEntity> userRoleJpaEntityCollection = userRoleDaoService.findAll();

        // Then
        assertThat(userRoleJpaEntityCollection)
                .isEmpty();
        Mockito.verify(userRoleRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void save4success4exist4Test() {

        // Before
        UserRoleJpaEntity userRoleJpaEntity = getGen().nextUserRole();

        // When
        Mockito.when(userRoleRepository.findAll())
                .thenReturn(List.of(userRoleJpaEntity));

        // Action
        List<UserRoleJpaEntity> userRoleJpaEntityCollection = userRoleDaoService.findAll();

        // Then
        assertThat(userRoleJpaEntityCollection)
                .isNotEmpty();
        assertThat(userRoleJpaEntityCollection)
                .hasSize(1);
        assertThat(userRoleJpaEntityCollection.get(0))
                .isEqualTo(userRoleJpaEntity);
        Mockito.verify(userRoleRepository, Mockito.times(1))
                .findAll();
    }

}