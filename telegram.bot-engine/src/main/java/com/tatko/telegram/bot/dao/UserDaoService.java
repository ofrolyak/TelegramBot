package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.repository.UserRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object for User entity.
 */
@Service
@Setter
@Slf4j
public class UserDaoService {

    /**
     * UserRepository is used to get User based info.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Find User.
     *
     * @param chatId Identifier for Telegram Chat.
     * @return {@code Optional<User>} of {@code Optional.empty()}
     * if the user is not found.
     */
    public Optional<UserJpaEntity> findByChatId(final Long chatId) {
        log.debug("Finding user by chatId: {}", chatId);
        Optional<UserJpaEntity> userOptional
                = userRepository.findByChatId(chatId);
        log.debug("Found userOptional: {}", userOptional);
        return userOptional;
    }

    /**
     * Save User entity into DB.
     *
     * @param userJpaEntity entity
     * @return Saved User
     */
    public UserJpaEntity save(final UserJpaEntity userJpaEntity) {
        log.debug("Saving user: {}", userJpaEntity);
        UserJpaEntity userJpaEntitySaved = userRepository.save(userJpaEntity);
        log.debug("Saved userSaved: {}", userJpaEntitySaved);
        return userJpaEntitySaved;
    }

    /**
     * Return list of all users.
     *
     * @return List of users.
     */
    public List<UserJpaEntity> findAll() {
        log.debug("Finding all users");
        List<UserJpaEntity> userJpaEntities = userRepository.findAll();
        log.debug("Found users: {}", userJpaEntities);
        return userJpaEntities;
    }

    /**
     * Delete User entity.
     * @param userJpaEntity User entity.
     */
    public void delete(final UserJpaEntity userJpaEntity) {
        log.debug("Deleting user: {}", userJpaEntity);
        userRepository.delete(userJpaEntity);
        log.debug("Deleted user: {}", userJpaEntity);
    }

    /**
     * Find User entity by user id.
     * @param id User id
     * @return Optional of User entity
     */
    public Optional<UserJpaEntity> findById(final long id) {
        log.debug("Finding user by id: {}", id);
        Optional<UserJpaEntity> userOptional = userRepository.findById(id);
        log.debug("Found userOptional: {}", userOptional);
        return userOptional;
    }

}
