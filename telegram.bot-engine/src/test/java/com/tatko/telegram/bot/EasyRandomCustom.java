package com.tatko.telegram.bot;

import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.entity.UserArchJpaEntity;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.service.custom.storage.ServiceDataUserStorage;
import org.jeasy.random.EasyRandom;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

/**
 * Expansion functionality for EasyRandom functionality.
 */

public class EasyRandomCustom extends EasyRandom {

    /**
     * Generate String object.
     *
     * @return Get generated random String object.
     */
    public String nextString() {
        return this.nextObject(String.class);
    }

    public UserRoleJpaEntity nextUserRole() {
        UserRoleJpaEntity userRoleJpaEntity = new UserRoleJpaEntity();
        userRoleJpaEntity.setId(this.nextLong());
        userRoleJpaEntity.setName(this.nextString());
        return userRoleJpaEntity;
    }

    public ServiceDataUserStorage nextServiceDataUserStorage() {
        ServiceDataUserStorage serviceDataUserStorage = new ServiceDataUserStorage();
        serviceDataUserStorage.setUserJpaEntity(this.nextUser());
        serviceDataUserStorage.setUserRoleJpaEntity(this.nextUserRole());
        serviceDataUserStorage.setBroken(this.nextBoolean());
        serviceDataUserStorage.setAdmin(this.nextBoolean());
        serviceDataUserStorage.setChatId(this.nextLong());
        return serviceDataUserStorage;
    }

    public UserJpaEntity nextUser() {
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setId(this.nextLong());
        userJpaEntity.setFirstName(this.nextString());
        userJpaEntity.setLastName(this.nextString());
        userJpaEntity.setUserName(this.nextString());
        userJpaEntity.setChatId(this.nextLong());
        userJpaEntity.setRegisteredAt(this.nextObject(LocalDateTime.class));
        userJpaEntity.setUserRoleJpaEntity(this.nextUserRole());
        return userJpaEntity;
    }

    public UserArchJpaEntity nextUserArch() {
        UserArchJpaEntity userArchJpaEntity = new UserArchJpaEntity();
        userArchJpaEntity.setId(this.nextLong());
        userArchJpaEntity.setFirstName(this.nextString());
        userArchJpaEntity.setLastName(this.nextString());
        userArchJpaEntity.setUserName(this.nextString());
        userArchJpaEntity.setChatId(this.nextLong());
        userArchJpaEntity.setRegisteredAt(this.nextObject(LocalDateTime.class));
        userArchJpaEntity.setUserRoleJpaEntity(this.nextUserRole());
        return userArchJpaEntity;
    }

    public Update nextUpdate() {
        Update update = new Update();
        update.setMessage(this.nextMessage());
        return update;
    }

    public Message nextMessage() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(this.nextLong());
        chat.setFirstName(this.nextString());
        chat.setLastName(this.nextString());
        chat.setUserName(this.nextString());
        message.setChat(chat);
        message.setText(nextString());
        return message;
    }



}
