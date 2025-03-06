package com.tatko.telegram.bot.service.internal;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.dao.UserDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.util.BusinessUtility;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserService4registerUser4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserDaoService userDaoService;
    @Mock
    BusinessUtility businessUtility;
    @Spy
    @InjectMocks
    UserService userService;

    @Test
    void notExistUser4Test() {

        // Before
        Message message = getGen().nextMessage();
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        when(userDaoService.findByChatId(anyLong()))
                .thenReturn(Optional.empty());
        doReturn(userJpaEntity)
                .when(businessUtility)
                .buildUserByMessage(message);

        // Action
        userService.registerUser(message);

        // Then
        verify(userDaoService, times(1))
                .save(eq(userJpaEntity));

    }

    @Test
    void existUser4Test() {

        // Before
        Message message = getGen().nextMessage();
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        when(userDaoService.findByChatId(anyLong()))
                .thenReturn(Optional.of(userJpaEntity));

        // Action
        userService.registerUser(message);

        // Then
        verify(userDaoService, never())
                .save(eq(userJpaEntity));

    }


}