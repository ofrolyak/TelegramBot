package com.tatko.telegram.bot.repository;

import com.tatko.telegram.bot.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository for User entity.
 */

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {

    /**
     * Find User.
     *
     * @param chatId Identifier for Telegram Chat.
     * @return {@code Optional<User>} of {@code Optional.empty()}
     * if the user is not found.
     */
    Optional<UserJpaEntity> findByChatId(Long chatId);

}
