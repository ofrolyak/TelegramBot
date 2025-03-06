package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.UserArchJpaEntity;
import com.tatko.telegram.bot.repository.UserArchRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class UserArchDaoService4Save4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserArchRepository userArchRepository;
    @InjectMocks
    UserArchDaoService userArchDaoService;

    @Test
    void save4success4Test() {

        // Before
        UserArchJpaEntity userArchJpaEntity = getGen().nextUserArch();

        // When
        Mockito.when(userArchRepository.save(Mockito.eq(userArchJpaEntity)))
                .thenReturn(userArchJpaEntity);

        // Action
        UserArchJpaEntity userArchJpaEntitySaved = userArchDaoService.save(userArchJpaEntity);

        // Then
        assertThat(userArchJpaEntitySaved)
                .isEqualTo(userArchJpaEntity);
        Mockito.verify(userArchRepository, Mockito.times(1))
                .save(Mockito.eq(userArchJpaEntity));
    }

}