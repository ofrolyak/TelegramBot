package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoService4FindAll4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserDaoService userDaoService;

    @Test
    void save4success4notExist4Test() {

        // When
        Mockito.when(userRepository.findAll())
                .thenReturn(Collections.emptyList());

        // Action
        Collection<UserJpaEntity> userJpaEntityCollection = userDaoService.findAll();

        // Then
        assertThat(userJpaEntityCollection)
                .isEmpty();
        Mockito.verify(userRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void save4success4exist4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        Mockito.when(userRepository.findAll())
                .thenReturn(List.of(userJpaEntity));

        // Action
        List<UserJpaEntity> userJpaEntityCollection = userDaoService.findAll();

        // Then
        assertThat(userJpaEntityCollection)
                .isNotEmpty();
        assertThat(userJpaEntityCollection)
                .hasSize(1);
        assertThat(userJpaEntityCollection.get(0))
                .isEqualTo(userJpaEntity);
        Mockito.verify(userRepository, Mockito.times(1))
                .findAll();
    }

}