package com.tatko.telegram.bot.service;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.service.custom.storage.ServiceDataUserStorage;
import com.tatko.telegram.bot.service.internal.UserRoleService;
import com.tatko.telegram.bot.service.internal.UserService;
import com.tatko.telegram.bot.util.BusinessUtility;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class TelegramBotConfiguratorService4configureServiceData4Test
        extends MockitoExtensionBaseMockTests {

    @Mock
    private UserRoleService userRoleServiceMock;
    @Mock
    private BusinessUtility businessUtilityMock;
    @Mock
    private UserService userServiceMock;
    @Mock
    private Message messageMock;
    @Mock
    private Update updateMock;
    @InjectMocks
    private TelegramBotConfiguratorService telegramBotConfiguratorServiceMock;

    @Test
    void process4Test() {

        // Before
        long chatId = getGen().nextLong();;
        boolean isTelegramBotAdmin = getGen().nextBoolean();;
        UserJpaEntity userJpaEntity = getGen().nextUser();
        UserRoleJpaEntity userRoleJpaEntity = getGen().nextUserRole();

        // When
        doReturn(true)
                .when(updateMock)
                .hasMessage();
        doReturn(messageMock)
                .when(updateMock)
                .getMessage();
        doReturn(true)
                .when(messageMock)
                .hasText();
        doReturn(chatId)
                .when(messageMock)
                .getChatId();
        doReturn(userJpaEntity)
                .when(userServiceMock)
                .getRegisteredUser(eq(updateMock));
        doReturn(isTelegramBotAdmin)
                .when(businessUtilityMock)
                .isTelegramBotAdmin(eq(chatId));
        doReturn(userRoleJpaEntity)
                .when(userRoleServiceMock)
                .getUserRoleByUser(eq(userJpaEntity));

        // Action
        telegramBotConfiguratorServiceMock.configureServiceData(updateMock);

        // Then
        ServiceDataUserStorage serviceDataUserStorage
                = telegramBotConfiguratorServiceMock
                .getServiceDataUserThreadLocal().get();
        assertThat(serviceDataUserStorage.getChatId())
                .isEqualTo(chatId);
        assertThat(serviceDataUserStorage.getUserJpaEntity())
                .isEqualTo(userJpaEntity);
        assertThat(serviceDataUserStorage.isAdmin())
                .isEqualTo(isTelegramBotAdmin);
        assertThat(serviceDataUserStorage.getUserRoleJpaEntity())
                .isEqualTo(userRoleJpaEntity);
        assertThat(serviceDataUserStorage.isBroken())
                .isFalse();

    }

    @Test
    void process4isBrokenByChatId4Test() {

        // When
        doReturn(false)
                .when(updateMock)
                .hasMessage();

        // Action
        telegramBotConfiguratorServiceMock.configureServiceData(updateMock);

        // Then
        ServiceDataUserStorage serviceDataUserStorage
                = telegramBotConfiguratorServiceMock
                .getServiceDataUserThreadLocal().get();
        assertThat(serviceDataUserStorage.isBroken())
                .isTrue();

    }

    @Test
    void process4isBrokenByUser4Test() {

        // Before
        long chatId = getGen().nextLong();;

        // When
        doReturn(true)
                .when(updateMock)
                .hasMessage();
        doReturn(messageMock)
                .when(updateMock)
                .getMessage();
        doReturn(true)
                .when(messageMock)
                .hasText();
        doReturn(chatId)
                .when(messageMock)
                .getChatId();
        doThrow(RuntimeException.class)
                .when(userServiceMock)
                .getRegisteredUser(eq(updateMock));

        // Action
        telegramBotConfiguratorServiceMock.configureServiceData(updateMock);

        // Then
        ServiceDataUserStorage serviceDataUserStorage
                = telegramBotConfiguratorServiceMock
                .getServiceDataUserThreadLocal().get();
        assertThat(serviceDataUserStorage.isBroken())
                .isTrue();

    }

    @Test
    void process4isBrokenByAdmin4Test() {

        // Before
        long chatId = getGen().nextLong();;
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        doReturn(true)
                .when(updateMock)
                .hasMessage();
        doReturn(messageMock)
                .when(updateMock)
                .getMessage();
        doReturn(true)
                .when(messageMock)
                .hasText();
        doReturn(chatId)
                .when(messageMock)
                .getChatId();
        doReturn(userJpaEntity)
                .when(userServiceMock)
                .getRegisteredUser(eq(updateMock));
        doThrow(RuntimeException.class)
                .when(businessUtilityMock)
                .isTelegramBotAdmin(eq(chatId));

        // Action
        telegramBotConfiguratorServiceMock.configureServiceData(updateMock);

        // Then
        ServiceDataUserStorage serviceDataUserStorage
                = telegramBotConfiguratorServiceMock
                .getServiceDataUserThreadLocal().get();
        assertThat(serviceDataUserStorage.isBroken())
                .isTrue();

    }

    @Test
    void process4isBrokenByUserRole4Test() {

        // Before
        long chatId = getGen().nextLong();;
        boolean isTelegramBotAdmin = getGen().nextBoolean();;
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        doReturn(true)
                .when(updateMock)
                .hasMessage();
        doReturn(messageMock)
                .when(updateMock)
                .getMessage();
        doReturn(true)
                .when(messageMock)
                .hasText();
        doReturn(chatId)
                .when(messageMock)
                .getChatId();
        doReturn(userJpaEntity)
                .when(userServiceMock)
                .getRegisteredUser(eq(updateMock));
        doReturn(isTelegramBotAdmin)
                .when(businessUtilityMock)
                .isTelegramBotAdmin(eq(chatId));
        doThrow(RuntimeException.class)
                .when(userRoleServiceMock)
                .getUserRoleByUser(eq(userJpaEntity));

        // Action
        telegramBotConfiguratorServiceMock.configureServiceData(updateMock);

        // Then
        ServiceDataUserStorage serviceDataUserStorage
                = telegramBotConfiguratorServiceMock
                .getServiceDataUserThreadLocal().get();
        assertThat(serviceDataUserStorage.isBroken())
                .isTrue();

    }

}
