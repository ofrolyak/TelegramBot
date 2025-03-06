package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class UserDaoService4Delete4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserDaoService userDaoService;

    @Test
    void save4successAndExist4Test() {

        // Before
        UserJpaEntity userJpaEntity = getGen().nextUser();

        // When
        Mockito.doNothing()
                .when(userRepository)
                .delete(Mockito.eq(userJpaEntity));

        // Action
        userDaoService.delete(userJpaEntity);

        // Then
        Mockito.verify(userRepository, Mockito.times(1))
                .delete(Mockito.eq(userJpaEntity));
    }

}