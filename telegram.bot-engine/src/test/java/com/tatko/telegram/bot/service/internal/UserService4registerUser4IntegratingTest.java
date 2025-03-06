package com.tatko.telegram.bot.service.internal;

import com.tatko.telegram.bot.SpringBootTestFullBaseMockTests;
import com.tatko.telegram.bot.dao.UserDaoService;
import com.tatko.telegram.bot.dao.UserRoleDaoService;
import com.tatko.telegram.bot.entity.UserJpaEntity;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.doReturn;

class UserService4registerUser4IntegratingTest extends SpringBootTestFullBaseMockTests {

    @Autowired
    private UserRoleDaoService userRoleDaoService;
    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private UserService userService;

    @Test
    void base4Test() {

        // Prepare
        UserRoleJpaEntity userRoleJpaEntity = new UserRoleJpaEntity();
        userRoleJpaEntity.setName("USER");
        userRoleJpaEntity.setDescription("User");
        userRoleJpaEntity.setCreatingTime(LocalDateTime.now());
        userRoleDaoService.save(userRoleJpaEntity);

        // Before
        Message message = getGen().nextMessage();

        // Action
        userService.registerUser(message);

        // Then
        List<UserJpaEntity> all = userDaoService.findAll();

    }

}