package com.tatko.telegram.bot.service.custom.storage;

import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ServiceDataUserStorage {

    /**
     * Property that describe if this structure has been formed correctly.
     */
    private boolean isBroken;

    /**
     * Is admin property for user that make activity in Telegram bot.
     */
    private boolean isAdmin;

    /**
     * User instance for user that make activity in Telegram bot.
     */
    private UserJpaEntity userJpaEntity;

    /**
     * UserRole instance for user that make activity in Telegram bot.
     */
    private UserRoleJpaEntity userRoleJpaEntity;

    /**
     * ChatId for current user.
     */
    private long chatId;

    /**
     * Constructor itself.
     */
    public ServiceDataUserStorage() {
        this.isBroken = false;
    }

}
