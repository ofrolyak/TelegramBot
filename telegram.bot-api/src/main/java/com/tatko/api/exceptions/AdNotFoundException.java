package com.tatko.api.exceptions;

public class AdNotFoundException extends TelegramBotApiException {

    @Override
    public String getMessage() {
        return "Ad not found";
    }


}
