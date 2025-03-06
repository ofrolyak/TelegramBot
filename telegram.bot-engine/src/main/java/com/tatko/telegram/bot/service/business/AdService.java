package com.tatko.telegram.bot.service.business;

import com.tatko.telegram.bot.dao.AdDaoService;
import com.tatko.telegram.bot.entity.AdJpaEntity;
import com.tatko.telegram.bot.service.custom.operation.SendMessageOperation2Params;
import com.tatko.telegram.bot.service.internal.UserService;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AdService {

    /**
     * Autowired by Spring UserService bean.
     */
    @Autowired
    private UserService userService;

    /**
     * Autowired by Spring AdDao bean.
     */
    @Autowired
    private AdDaoService adDaoService;

    /**
     * Frequency parameter for Ad scheduler.
     */
    @Value("${telegram.bot.scheduler.ad.frequency.hour}")
    private int telegramBotSchedulerAdFrequencyHour;

    /**
     * refreshDeliveredDateForAd.
     *
     * @param adJpaEntity
     */
    void refreshDeliveredDateForAd(@NotNull final AdJpaEntity adJpaEntity) {

        log.debug("Refreshing delivered dates for ad {}", adJpaEntity);

        adJpaEntity.setDeliveredTime(LocalDateTime.now());
        adDaoService.save(adJpaEntity);

        log.debug("Ad {} has been refreshed", adJpaEntity);
    }

    /**
     * Deliver Ad to all users.
     *
     * @param sendMessageOperation2Params
     * @param adJpaEntity
     */
    void deliverAdToUsers(
            final SendMessageOperation2Params sendMessageOperation2Params,
            @NotNull final AdJpaEntity adJpaEntity) {

        log.debug("Delivering ad {} to users", adJpaEntity);

        userService.deliverToUsers(
                sendMessageOperation2Params, adJpaEntity.getAd());

        refreshDeliveredDateForAd(adJpaEntity);

        log.debug("Ad {} has been delivered", adJpaEntity);
    }

    /**
     * Sent the next Ad to all users.
     * @param sendMessageOperation2Params
     */
    public void sendNextAd(
            final SendMessageOperation2Params sendMessageOperation2Params) {

        log.info("Sending ads to all users");

        var adOptional = adDaoService.findAdToDeliver(LocalDateTime.now()
                .minusHours(telegramBotSchedulerAdFrequencyHour));

        adOptional.ifPresentOrElse(ad ->
                deliverAdToUsers(sendMessageOperation2Params, ad),
                () -> log.info("There are not adOptional to deliver"));

        log.info("Ads sent");

    }

}
