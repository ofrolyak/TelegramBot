package com.tatko.telegram.bot.service.internal;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.dao.UserArchDaoService;
import com.tatko.telegram.bot.dao.UserDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.entity.UserArchJpaEntity;
import com.tatko.telegram.bot.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.BeanUtils;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserService4deleteUser4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserDaoService userDaoService;
    @Mock
    UserArchDaoService userArchDaoService;
    @Spy
    @InjectMocks
    private UserService userService;


    @Test
    void success4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();
        UserArchJpaEntity userArchJpaEntity = new UserArchJpaEntity();
        BeanUtils.copyProperties(userJpaEntity, userArchJpaEntity);

        // When
        doReturn(userJpaEntity)
                .when(userService)
                .findUserByUser(eq(userJpaEntity));
        doReturn(userArchJpaEntity)
                .when(userArchDaoService)
                .save(any(UserArchJpaEntity.class));
        doNothing()
                .when(userDaoService)
                .delete(eq(userJpaEntity));

        // Action
        userService.deleteUser(userJpaEntity);

        // Verify
        verify(userService, times(1))
                .findUserByUser(eq(userJpaEntity));
        verify(userArchDaoService, times(1))
                .save(any(UserArchJpaEntity.class));
        verify(userDaoService, times(1))
                .delete(eq(userJpaEntity));

    }

    @Test
    void userNotFoundException4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();
        UserArchJpaEntity userArchJpaEntity = new UserArchJpaEntity();
        BeanUtils.copyProperties(userJpaEntity, userArchJpaEntity);

        // When
        doThrow(UserNotFoundException.class)
                .when(userService)
                .findUserByUser(eq(userJpaEntity));

        // Action
        assertThatCode(() -> userService.deleteUser(userJpaEntity))
                .isInstanceOf(UserNotFoundException.class);

        // Verify
        verify(userService, times(1))
                .findUserByUser(eq(userJpaEntity));
        verify(userArchDaoService, times(0))
                .save(eq(userArchJpaEntity));
        verify(userDaoService, times(0))
                .delete(eq(userJpaEntity));

    }

}