package com.tatko.telegram.bot.util;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.config.TelegramBotConfig;
import com.tatko.telegram.bot.dao.UserRoleDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

class BusinessUtility4buildUserByMessage4Test extends MockitoExtensionBaseMockTests {

    @Mock
    private UserRoleDaoService userRoleDaoService;

    @Spy
    @InjectMocks
    private BusinessUtility businessUtility;

    @Test
    void success4isAdminCase4Test() {

        // Before
        Message message = getGen().nextMessage();

       // When
//       doReturn(true)
//               .when(businessUtility)
//                       .isTelegramBotAdmin(eq(chatId));
       doReturn(false)
               .when(businessUtility)
                       .isTelegramBotAdmin(anyLong());
       doReturn(Optional.of(getGen().nextUserRole()))
               .when(userRoleDaoService)
                       .findByName(anyString());

        // Action
        UserJpaEntity userJpaEntity = businessUtility.buildUserByMessage(message);

        // Then
        assertThat(userJpaEntity)
                .isNotNull();
        assertThat(userJpaEntity.getChatId())
                .isEqualTo(message.getChatId());
        assertThat(userJpaEntity.getFirstName())
                .isEqualTo(message.getChat().getFirstName());
        assertThat(userJpaEntity.getLastName())
                .isEqualTo(message.getChat().getLastName());
        assertThat(userJpaEntity.getUserName())
                .isEqualTo(message.getChat().getUserName());
//        assertThat(user.getUserRole().getId())
//                .isEqualTo(2L);

    }

    @Test
    void success4isNotAdminCase4Test() {

        // Before
        Message message = getGen().nextMessage();

        // When
//        doReturn(false)
//                .when(businessUtility)
//                .isTelegramBotAdmin(eq(chatId));
        doReturn(false)
                .when(businessUtility)
                .isTelegramBotAdmin(anyLong());
        doReturn(Optional.of(getGen().nextUserRole()))
                .when(userRoleDaoService)
                .findByName(anyString());

        // Action
        UserJpaEntity userJpaEntity = businessUtility.buildUserByMessage(message);

        // Then
        assertThat(userJpaEntity)
                .isNotNull();
        assertThat(userJpaEntity.getChatId())
                .isEqualTo(message.getChatId());
        assertThat(userJpaEntity.getFirstName())
                .isEqualTo(message.getChat().getFirstName());
        assertThat(userJpaEntity.getLastName())
                .isEqualTo(message.getChat().getLastName());
        assertThat(userJpaEntity.getUserName())
                .isEqualTo(message.getChat().getUserName());
//        assertThat(user.getUserRole().getId())
//                .isEqualTo(1L);

    }

}
