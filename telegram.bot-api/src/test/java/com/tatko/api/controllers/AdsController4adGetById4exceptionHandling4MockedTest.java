package com.tatko.api.controllers;

import com.google.gson.Gson;
import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.GeneralErrorResultApiResponse;
import com.tatko.api.exceptions.AdNotFoundException;
import com.tatko.api.exceptions.TelegramBotApiException;
import com.tatko.api.services.RestExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdsController4adGetById4exceptionHandling4MockedTest extends MockitoExtensionBaseMockTests {

    private MockMvc mockMvc;
    @Mock
    private AdsController adsController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(adsController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void error4AdNotFoundException4Test() throws Exception {

        // When
        when(adsController.adGetById(anyLong()))
                .thenThrow(new AdNotFoundException("0"));

        // Action
        MvcResult mvcResult = mockMvc.perform(get("/ads/{adId}", 0L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertInstanceOf(TelegramBotApiException.class, result.getResolvedException()))
                .andReturn();

        // After
        String contentAsString = mvcResult.getResponse().getContentAsString();

        GeneralErrorResultApiResponse generalErrorResultApiResponse
                = new Gson().fromJson(contentAsString, GeneralErrorResultApiResponse.class);

        // Then
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg())
                .hasSize(1);
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg().get(0).getCode())
                .isEqualTo("-10000");
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg().get(0).getText())
                .contains("not found");

    }

    @Test
    void error4AdAuthenticationException4Test() throws Exception {

        // When
        when(adsController.adGetById(anyLong()))
                .thenThrow(new AccountExpiredException("Token expired"));

        // Action
        MvcResult mvcResult = mockMvc.perform(get("/ads/{adId}", 0L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> Assertions.assertInstanceOf(AuthenticationException.class, result.getResolvedException()))
                .andReturn();

        // After
        String contentAsString = mvcResult.getResponse().getContentAsString();

        GeneralErrorResultApiResponse generalErrorResultApiResponse
                = new Gson().fromJson(contentAsString, GeneralErrorResultApiResponse.class);

        // Then
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg())
                .hasSize(1);
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg().get(0).getCode())
                .isEqualTo("-1");

    }

    @Test
    void error4AdAccessDeniedException4Test() throws Exception {

        // When
        when(adsController.adGetById(anyLong()))
                .thenThrow(new AuthorizationDeniedException("Denied"));

        // Action
        MvcResult mvcResult = mockMvc.perform(get("/ads/{adId}", 0L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> Assertions.assertInstanceOf(AccessDeniedException.class, result.getResolvedException()))
                .andReturn();

        // After
        String contentAsString = mvcResult.getResponse().getContentAsString();

        GeneralErrorResultApiResponse generalErrorResultApiResponse
                = new Gson().fromJson(contentAsString, GeneralErrorResultApiResponse.class);

        // Then
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg())
                .hasSize(1);
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg().get(0).getCode())
                .isEqualTo("-1");

    }

    @Test
    void error4AdRuntimeException4Test() throws Exception {

        // When
        when(adsController.adGetById(anyLong()))
                .thenThrow(new RuntimeException());

        // Action
        MvcResult mvcResult = mockMvc.perform(get("/ads/{adId}", 0L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> Assertions.assertInstanceOf(RuntimeException.class, result.getResolvedException()))
                .andReturn();

        // After
        String contentAsString = mvcResult.getResponse().getContentAsString();

        GeneralErrorResultApiResponse generalErrorResultApiResponse
                = new Gson().fromJson(contentAsString, GeneralErrorResultApiResponse.class);

        // Then
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg())
                .hasSize(1);
        assertThat(generalErrorResultApiResponse.getMsgs().getMsg().get(0).getCode())
                .isEqualTo("-1");

    }

}