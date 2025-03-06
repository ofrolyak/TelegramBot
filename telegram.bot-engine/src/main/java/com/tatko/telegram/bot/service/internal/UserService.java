package com.tatko.telegram.bot.service.internal;

import com.tatko.telegram.bot.dao.UserArchDaoService;
import com.tatko.telegram.bot.dao.UserDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.entity.UserArchJpaEntity;
import com.tatko.telegram.bot.exception.BaseException;
import com.tatko.telegram.bot.exception.UserNotFoundException;
import com.tatko.telegram.bot.service.custom.operation.SendMessageOperation1Param;
import com.tatko.telegram.bot.service.custom.operation.SendMessageOperation2Params;
import com.tatko.telegram.bot.service.processor.CallbackProcessorService;
import com.tatko.telegram.bot.util.BusinessUtility;
import com.tatko.telegram.bot.util.StaticUtility;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class UserService {

    /**
     * Autowired by Spring UserDao bean.
     */
    @Autowired
    private UserDaoService userDaoService;

    /**
     * Autowired by Spring UserArchDao bean.
     */
    @Autowired
    private UserArchDaoService userArchDaoService;

    /**
     * Autowired by Spring BusinessUtility bean.
     */
    @Autowired
    private BusinessUtility businessUtility;

    /**
     * Deliver text message to specific user.
     *
     * @param sendMessageOperation2Params
     * @param textMessage
     * @param chatId
     */
    public void deliverToUser(
            final SendMessageOperation2Params sendMessageOperation2Params,
            @NotNull final String textMessage, @NotNull final long chatId) {

        log.debug("Delivering textMessage {} to chatId {}", textMessage,
                chatId);

        sendMessageOperation2Params.execute(chatId, textMessage);

        log.debug("textMessage {} has been delivered to chatId {}",
                textMessage, chatId);
    }

    /**
     * Deliver text message to specific user.
     *
     * @param sendMessageOperation2Params
     * @param textMessage
     * @param userJpaEntity
     */
    public void deliverToUser(
            final SendMessageOperation2Params sendMessageOperation2Params,
            @NotNull final String textMessage,
            @NotNull final UserJpaEntity userJpaEntity) {

        log.debug("Delivering textMessage {} to user {}",
                textMessage, userJpaEntity);

        deliverToUser(sendMessageOperation2Params, textMessage,
                userJpaEntity.getChatId());

        log.debug("textMessage {} has been delivered to user {}",
                textMessage, userJpaEntity);
    }

    /**
     * Deliver specific SendMessage structure.
     *
     * @param sendMessageOperation1Param
     * @param sendMessage
     */
    public void deliverToUser(
            final SendMessageOperation1Param sendMessageOperation1Param,
            @NotNull final SendMessage sendMessage) {

        log.debug("Delivering sendMessage {}", sendMessage);

        sendMessageOperation1Param.execute(sendMessage);

        log.debug("sendMessage {} has been delivered", sendMessage);
    }

    /**
     * Deliver text message to all users.
     *
     * @param sendMessageOperation2Params
     * @param textMessage
     */
    public void deliverToUsers(
            final SendMessageOperation2Params sendMessageOperation2Params,
            @NotNull final String textMessage) {

        log.debug("Delivering textMessage {}", textMessage);

        var users = userDaoService.findAll();

        log.debug("Found {} users", users.size());

        users.forEach(user ->
                deliverToUser(sendMessageOperation2Params, textMessage, user));

        log.debug("Delivered textMessage {}", textMessage);
    }

    /**
     * Deliver.
     * @param sendMessageOperation1Param
     * @param textMessage
     * @param userJpaEntity
     */
    public void deliverMessageWithButtonToUser(
            final SendMessageOperation1Param sendMessageOperation1Param,
            final String textMessage, final UserJpaEntity userJpaEntity) {

        log.debug("Process deliverMessageWithButtonToUser for user {}",
                userJpaEntity);

        SendMessage sendMessage
                = StaticUtility.buildSendMessage(textMessage, userJpaEntity);

        CallbackProcessorService.andButtonToSendMessage(sendMessage);

        deliverToUser(sendMessageOperation1Param, sendMessage);

        log.debug("Finished process deliverMessageWithButtonToUser for user {}",
                userJpaEntity);

    }

    /**
     * Deliver text message to all users.
     *
     * @param sendMessageOperation1Param
     * @param textMessage
     */
    public void deliverToUsers(
            final SendMessageOperation1Param sendMessageOperation1Param,
            @NotNull final String textMessage) {

        log.debug("Delivering textMessage {}", textMessage);

        var users = userDaoService.findAll();

        log.debug("Found {} users", users.size());

        users.forEach(user -> deliverMessageWithButtonToUser(
                sendMessageOperation1Param, textMessage, user));

        log.debug("Delivered textMessage {}", textMessage);
    }

    /**
     * Register User. If he's already registered do nothing.
     *
     * @param message Message instance that has gotten from Telegram Bot.
     */
    public void registerUser(final Message message) {

        log.debug("Process register user for message {}", message);

        if (userDaoService.findByChatId(message.getChatId()).isEmpty()) {

            UserJpaEntity userJpaEntity
                    = businessUtility.buildUserByMessage(message);
            log.info("Register user {}", userJpaEntity);
            userDaoService.save(userJpaEntity);
        }

        log.debug("Finished process register user for message {}", message);

    }

    /**
     * Delete user based on User instance.
     *
     * @param userJpaEntity
     */
    @Transactional
    public void deleteUser(final UserJpaEntity userJpaEntity) {

        log.debug("Process delete user {}", userJpaEntity);

        // Verify if user exists
        findUserByUser(userJpaEntity);

        UserArchJpaEntity userArchJpaEntity = new UserArchJpaEntity();

        BeanUtils.copyProperties(userJpaEntity, userArchJpaEntity);

        userArchDaoService.save(userArchJpaEntity);

        userDaoService.delete(userJpaEntity);

        log.debug("Finished process delete user {}", userJpaEntity);

    }

    /**
     * Find User entity by User entity.
     *
     * @param userJpaEntity
     * @return User entity.
     */
    public UserJpaEntity findUserByUser(final UserJpaEntity userJpaEntity) {
        log.debug("Process findUserByUser {}", userJpaEntity);
        UserJpaEntity userJpaEntity1
                = userDaoService.findById(userJpaEntity.getId())
                .orElseThrow(UserNotFoundException::new);
        log.debug("Finished process findUserByUser {}", userJpaEntity);
        return userJpaEntity1;
    }

    /**
     * Get registered user or create if it has not registered yet.
     *
     * @param update
     * @return Registered User instance.
     */
    public UserJpaEntity getRegisteredUser(final Update update) {

        log.debug("Process getRegisteredUser {}", update);

        UserJpaEntity userJpaEntityRegistered;

        final long chatId = update.getMessage().getChatId();

        try {
            userJpaEntityRegistered = userDaoService.findByChatId(chatId)
                    .orElseThrow(UserNotFoundException::new);
            log.debug("User found userRegistered: {}", userJpaEntityRegistered);
        } catch (UserNotFoundException e) {
            try {
                registerUser(update.getMessage());
                userJpaEntityRegistered = userDaoService.findByChatId(chatId)
                        .orElseThrow(UserNotFoundException::new);
                log.debug("User found once more userRegistered: {}",
                        userJpaEntityRegistered);
            } catch (Exception e1) {
                log.error("Error: ", e1);
                throw new BaseException();
            }
        }

        log.debug("Finished process getRegisteredUser {}", update);

        return userJpaEntityRegistered;

    }

}
