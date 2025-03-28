package com.tatko.telegram.bot.service.custom.storage;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.dao.UserRoleDaoService;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.exception.UserRoleNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class KeyButtonMapStorage4init4Test extends MockitoExtensionBaseMockTests {

    @Mock
    private UserRoleDaoService userRoleDaoService;
    @InjectMocks
    private KeyButtonMapStorage keyButtonMapStorage;

    @Test
    void before4Test() {

        // Then
        assertThat(keyButtonMapStorage.getKeyButtonMap())
                .isEmpty();

    }

    @Test
    void postConstruct4UserRoleNotFoundExceptionCase4Test() {

        // When
        doThrow(UserRoleNotFoundException.class)
                .when(userRoleDaoService)
                .findById(anyLong());


        // Then
        assertThatCode(() -> keyButtonMapStorage.init())
                .isInstanceOf(UserRoleNotFoundException.class);

    }

    @Test
    void postConstruct4successCase4Test() {

        // Before
        UserRoleJpaEntity userRoleJpaEntity1 = getGen().nextUserRole();
        UserRoleJpaEntity userRoleJpaEntity2 = getGen().nextUserRole();

        // When
        when(userRoleDaoService.findById(eq(1L)))
                .thenReturn(Optional.of(userRoleJpaEntity1));
        when(userRoleDaoService.findById(eq(2L)))
                .thenReturn(Optional.of(userRoleJpaEntity2));

        // Action
        keyButtonMapStorage.init();

        // Then
        assertThat(keyButtonMapStorage.getKeyButtonMap())
                .hasSize(2);

    }


}