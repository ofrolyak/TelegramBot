package com.tatko.telegram.bot.util;

import com.tatko.telegram.bot.config.TelegramBotConfig;
import com.tatko.telegram.bot.dao.UserRoleDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BusinessUtility {

    /**
     * An injected instance of UserRoleDao, used for accessing and managing
     * user role data within the application. This component is automatically
     * wired by Spring's dependency injection mechanism.
     */
    @Autowired
    private UserRoleDaoService userRoleDaoService;

    /**
     * An instance of {@link TelegramBotConfig} that provides access
     * to the configuration
     * settings for a Telegram Bot, including bot name, token,
     * and owner information.
     * It is automatically injected using Spring's dependency injection
     * mechanism.
     */
    @Autowired
    private TelegramBotConfig telegramBotConfig;

    /**
     * Checks if a given chat ID corresponds to the Telegram bot's owner,
     * thereby determining
     * if the given ID belongs to a bot administrator.
     *
     * @param chatId the chat ID to check
     * @return true if the chat ID belongs to the bot's owner
     * and is a bot admin, false otherwise
     */
    public boolean isTelegramBotAdmin(final Long chatId) {
        log.debug("Verify if chatId={} is a telegram bot admin", chatId);
        boolean isTelegramBotAdmin
                = chatId.equals(telegramBotConfig.getTelegramBotOwner());
        log.debug("chatId={}, isTelegramBotAdmin={}",
                chatId, isTelegramBotAdmin);
        return isTelegramBotAdmin;
    }

    /**
     * Build User instance based on Message instance.
     *
     * @param message
     * @return User instance.
     */
    public UserJpaEntity buildUserByMessage(final Message message) {
        log.debug("Process buildUserByMessage for {}", message);
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                .chatId(message.getChatId())
                .firstName(message.getChat().getFirstName())
                .lastName(message.getChat().getLastName())
                .userName(message.getChat().getUserName())
                .registeredAt(LocalDateTime.now())
                //.userRoleId(isTelegramBotAdmin(message.getChatId()) ? 2L : 1L)
                .build();

        String userRoleName;
        if (isTelegramBotAdmin(message.getChatId())) {
            userRoleName = "ADMIN";
        } else {
            userRoleName = "USER";
        }
        userRoleDaoService.findByName(userRoleName).ifPresentOrElse(
                userJpaEntity::setUserRoleJpaEntity, () -> {
            throw new IllegalStateException("User role not found for name: "
                    + userRoleName);
        });

        log.debug("Process buildUserByMessage for {}, user={}",
                message, userJpaEntity);
        return userJpaEntity;
    }

}
