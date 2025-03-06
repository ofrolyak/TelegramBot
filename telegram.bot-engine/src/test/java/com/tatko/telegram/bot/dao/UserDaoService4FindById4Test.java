package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class UserDaoService4FindById4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserDaoService userDaoService;

    @Test
    void userExists4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        when(userRepository.findById(eq(userJpaEntity.getId())))
                .thenReturn(Optional.of(userJpaEntity));

        // Action
        Optional<UserJpaEntity> userOptional = userDaoService.findById(userJpaEntity.getId());

        // Then
        assertThat(userOptional.isPresent())
                .isTrue();
        assertThat(userOptional.get())
                .isEqualTo(userJpaEntity);

    }

    @Test
    void userNotExists4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        when(userRepository.findById(eq(userJpaEntity.getId())))
                .thenReturn(Optional.empty());

        // Action
        Optional<UserJpaEntity> userOptional = userDaoService.findById(userJpaEntity.getId());

        // Then
        assertThat(userOptional.isPresent())
                .isFalse();

    }


}