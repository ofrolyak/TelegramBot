package com.tatko.telegram.bot.service.internal;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.dao.UserRoleDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.exception.UserRoleNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doReturn;

class UserRoleService4getUserRoleByUser4Test extends MockitoExtensionBaseMockTests {

    @Mock
    private UserRoleDaoService userRoleDaoServiceMock;
    @InjectMocks
    private UserRoleService userRoleServiceMock;

    @Test
    void process4Test() {

        // Before
        UserRoleJpaEntity userRoleJpaEntity = getGen().nextUserRole();
        UserJpaEntity userJpaEntity = getGen().nextUser();
        userJpaEntity.setUserRoleJpaEntity(userRoleJpaEntity);

        // When
        doReturn(List.of(userRoleJpaEntity))
                .when(userRoleDaoServiceMock)
                        .findAll();

        // Action
        UserRoleJpaEntity userRoleByUserJpaEntity = userRoleServiceMock.getUserRoleByUser(userJpaEntity);

        // Then
        assertThat(userRoleByUserJpaEntity)
                .isEqualTo(userRoleJpaEntity);

    }

    @Test
    void process4UserRoleNotFoundException4Test() {

        // Before
        UserRoleJpaEntity userRoleJpaEntity = getGen().nextUserRole();
        UserJpaEntity userJpaEntity = getGen().nextUser();
        userJpaEntity.setUserRoleJpaEntity(userRoleJpaEntity);

        // When
        doReturn(List.of())
                .when(userRoleDaoServiceMock)
                .findAll();

        // Then
        assertThatCode(() -> userRoleServiceMock.getUserRoleByUser(userJpaEntity))
                .isInstanceOf(UserRoleNotFoundException.class);

    }

}
