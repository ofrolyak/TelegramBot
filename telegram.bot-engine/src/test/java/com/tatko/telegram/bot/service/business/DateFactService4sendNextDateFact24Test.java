package com.tatko.telegram.bot.service.business;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.service.custom.operation.SendMessageOperation1Param;
import com.tatko.telegram.bot.service.internal.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DateFactService4sendNextDateFact24Test extends MockitoExtensionBaseMockTests {

    @Mock
    UserService userService;
    @Spy
    @InjectMocks
    DateFactService dateFactService;

    @Test
    void processWithoutSubscribe4Test() {

        // Before
        SendMessageOperation1Param sendMessageOperation1Param
                = mock(SendMessageOperation1Param.class);
        Mono<String> stringMono = mock(Mono.class);

        // When
        doReturn(stringMono)
                .when(dateFactService)
                .getDateFactForThisDay();
        doReturn(null)
                .when(stringMono)
                .subscribe(any(Consumer.class));

        // Action
        dateFactService.sendNextDateFact(sendMessageOperation1Param);

        // Then
        verify(dateFactService, times(1))
                .getDateFactForThisDay();
        verify(stringMono, times(1))
                .subscribe(any(Consumer.class));

    }

    @Test
    void processWithSubscribe4Test() {

        // Before
        SendMessageOperation1Param sendMessageOperation1Param
                = mock(SendMessageOperation1Param.class);
        Mono<String> stringMono = Mono.just(getGen().nextString());

        // When
        doReturn(stringMono)
                .when(dateFactService)
                .getDateFactForThisDay();
        doNothing()
                .when(userService)
                .deliverToUsers(any(SendMessageOperation1Param.class), anyString());

        // Action
        dateFactService.sendNextDateFact(sendMessageOperation1Param);

        // Then
        verify(dateFactService, times(1))
                .getDateFactForThisDay();
        verify(userService, times(1))
                .deliverToUsers(any(SendMessageOperation1Param.class), anyString());

    }

}
