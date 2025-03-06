package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoService4Save4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserDaoService userDaoService;

    @Test
    void save4success4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        Mockito.when(userRepository.save(Mockito.eq(userJpaEntity)))
                .thenReturn(userJpaEntity);

        // Action
        UserJpaEntity userJpaEntitySaved = userDaoService.save(userJpaEntity);

        // Then
        assertThat(userJpaEntitySaved)
                .isEqualTo(userJpaEntity);
        Mockito.verify(userRepository, Mockito.times(1))
                .save(Mockito.eq(userJpaEntity));
    }

}