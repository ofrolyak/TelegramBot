package com.tatko.telegram.bot.service.internal;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.dao.UserDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.when;

class UserService4findUserByUser4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserDaoService userDaoService;
    @InjectMocks
    UserService userService;

    @Test
    void success4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        when(userDaoService.findById(userJpaEntity.getId()))
                .thenReturn(Optional.of(userJpaEntity));

        // Action
        UserJpaEntity userByUserJpaEntity = userService.findUserByUser(userJpaEntity);

        // Then
        assertThat(userByUserJpaEntity)
                .isEqualTo(userJpaEntity);
    }

    @Test
    void failure4UserNotFoundException4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        when(userDaoService.findById(userJpaEntity.getId()))
                .thenThrow(UserNotFoundException.class);

        // Then
        assertThatCode(() -> userService.findUserByUser(userJpaEntity))
                .isInstanceOf(UserNotFoundException.class);
    }

}