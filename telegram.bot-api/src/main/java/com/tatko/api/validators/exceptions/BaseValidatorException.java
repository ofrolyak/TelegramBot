package com.tatko.api.validators.exceptions;

import com.tatko.api.exceptions.TelegramBotApiException;

public class BaseValidatorException extends TelegramBotApiException {

    /**
     * Constructor.
     *
     * @param params Params.
     */
    public BaseValidatorException(final String... params) {
        super(params);
    }

}
