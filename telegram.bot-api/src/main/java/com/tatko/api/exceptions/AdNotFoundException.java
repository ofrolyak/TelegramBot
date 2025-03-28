package com.tatko.api.exceptions;

public class AdNotFoundException extends TelegramBotApiException {

    /**
     * Some constant.
     */
    private static final int ERROR_CODE = -10000;

    /**
     * Constructor.
     * @param args Params.
     */
    public AdNotFoundException(final String... args) {
        super(ERROR_CODE, "Ad %s not found", args);
    }

}
