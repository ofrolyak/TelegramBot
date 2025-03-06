package com.tatko.telegram.bot.service.internal;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.dao.UserDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.exception.BaseException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class UserService4getRegisteredUser4Test
        extends MockitoExtensionBaseMockTests {

    @Mock
    Message messageMock;
    @Mock
    Update updateMock;
    @Mock
    UserDaoService userDaoServiceMock;
    @Spy
    @InjectMocks
    UserService userServiceMock;

    @Test
    void givenUserExist4Test() {

        // Before
        long chatId = getGen().nextLong();;
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        doReturn(messageMock)
                .when(updateMock)
                .getMessage();
        doReturn(chatId)
                .when(messageMock)
                .getChatId();
        doReturn(Optional.of(userJpaEntity))
                .when(userDaoServiceMock)
                .findByChatId(eq(chatId));

        // Action
        UserJpaEntity registeredUserJpaEntity = userServiceMock.getRegisteredUser(updateMock);

        // Then
        assertThat(registeredUserJpaEntity)
                .isEqualTo(userJpaEntity);

    }

    @Test
    void givenUserNotExist4Test() {

        // Before
        long chatId = getGen().nextLong();;
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        doReturn(messageMock)
                .when(updateMock)
                        .getMessage();
        doReturn(chatId)
                .when(messageMock)
                .getChatId();
        doReturn(Optional.empty(), Optional.of(userJpaEntity))
                .when(userDaoServiceMock)
                .findByChatId(eq(chatId));
        doNothing()
                .when(userServiceMock)
                .registerUser(any(Message.class));

        // Action
        UserJpaEntity registeredUserJpaEntity = userServiceMock.getRegisteredUser(updateMock);

        // Then
        assertThat(registeredUserJpaEntity)
                .isEqualTo(userJpaEntity);

    }

    @Test
    void givenUserNotExist4BaseException4Test() {

        // Before
        long chatId = getGen().nextLong();;
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        doReturn(messageMock)
                .when(updateMock)
                .getMessage();
        doReturn(chatId)
                .when(messageMock)
                .getChatId();
        doReturn(Optional.empty(), Optional.of(userJpaEntity))
                .when(userDaoServiceMock)
                .findByChatId(eq(chatId));
        doThrow(RuntimeException.class)
                .when(userServiceMock)
                .registerUser(any(Message.class));

        // Action
        assertThatCode(() -> userServiceMock.getRegisteredUser(updateMock))
                .isInstanceOf(BaseException.class);

    }

}
