package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;

class UserRole4findById4Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserRoleRepository userRoleRepository;
    @InjectMocks
    UserRoleDaoService userRoleDaoService;

    @Test
    void save4success4notExist4Test() {

        // Before
        long userRoleId = getGen().nextLong();

        // When
        Mockito.when(userRoleRepository.findById(eq(userRoleId)))
                .thenReturn(Optional.empty());

        // Action
        Optional<UserRoleJpaEntity> userRoleOptional = userRoleDaoService.findById(userRoleId);

        // Then
        assertThat(userRoleOptional)
                .isEmpty();
        Mockito.verify(userRoleRepository, Mockito.times(1))
                .findById(anyLong());
    }

    @Test
    void save4success4exist4Test() {

        // Before
        long userRoleId = getGen().nextLong();
        UserRoleJpaEntity userRoleJpaEntity = getGen().nextUserRole();

        // When
        Mockito.when(userRoleRepository.findById(eq(userRoleId)))
                .thenReturn(Optional.of(userRoleJpaEntity));

        // Action
        Optional<UserRoleJpaEntity> userRoleOptional = userRoleDaoService.findById(userRoleId);

        // Then
        assertThat(userRoleOptional)
                .isNotEmpty();
        assertThat(userRoleOptional.get())
                .isEqualTo(userRoleJpaEntity);
        Mockito.verify(userRoleRepository, Mockito.times(1))
                .findById(anyLong());
    }

}