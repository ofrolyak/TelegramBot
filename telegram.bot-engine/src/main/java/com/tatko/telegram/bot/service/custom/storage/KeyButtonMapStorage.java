package com.tatko.telegram.bot.service.custom.storage;

import com.tatko.telegram.bot.dao.UserRoleDaoService;
import com.tatko.telegram.bot.entity.UserRoleJpaEntity;
import com.tatko.telegram.bot.exception.UserRoleNotFoundException;
import com.tatko.telegram.bot.service.custom.button.KeyButton;
import com.tatko.telegram.bot.service.custom.command.BotCommandCustom;
import com.tatko.telegram.bot.service.custom.command.BotCommandCustomServiceAction;
import com.tatko.telegram.bot.service.custom.command.BotCommandCustomSettingsAction;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.tatko.telegram.bot.service.custom.button.KeyButton.DELETE_MY_DATA;
import static com.tatko.telegram.bot.service.custom.button.KeyButton.GET_MY_DATA;
import static com.tatko.telegram.bot.service.custom.button.KeyButton.SEND_AD;
import static com.tatko.telegram.bot.service.custom.button.KeyButton.SEND_NEXT_DATE_FACT;

@Getter
@Component
@Slf4j
public class KeyButtonMapStorage {

    /**
     * Autowired by Spring UserRoleDao bean.
     */
    @Autowired
    private UserRoleDaoService userRoleDaoService;

    /**
     * Date structure.
     */
    private final Map<UserRoleJpaEntity, Map<Class<? extends BotCommandCustom>,
            Set<KeyButton>>> keyButtonMap = new HashMap<>();

    /**
     * Building date structure.
     */
    @PostConstruct
    @Transactional
    public void init() {

        log.info("Process init");

        keyButtonMap.putAll(Map.of(userRoleDaoService.findById(2L)
                        .orElseThrow(UserRoleNotFoundException::new),
                Map.of(BotCommandCustomServiceAction.class,
                        Set.of(SEND_NEXT_DATE_FACT, SEND_AD)),
                userRoleDaoService.findById(1L)
                        .orElseThrow(UserRoleNotFoundException::new),
                Map.of(BotCommandCustomServiceAction.class,
                        Set.of(),
                        BotCommandCustomSettingsAction.class,
                        Set.of(GET_MY_DATA, DELETE_MY_DATA))));

        log.info("Finished process init");

    }

}
