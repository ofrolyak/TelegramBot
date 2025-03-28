package com.tatko.api.exceptions;

import lombok.Getter;

public class TelegramBotApiException extends RuntimeException {

    /**
     * Exception code.
     */
    @Getter
    private final int errorCode;
    /**
     * Exception description.
     */
    private final String errorDesc;
    /**
     * Arguments.
     */
    @Getter
    private final String[] args;

    /**
     * Constructor.
     * @param params Params.
     */
    public TelegramBotApiException(
            final String... params) {
        super();
        this.errorCode = -1;
        this.errorDesc = "Parent TelegramBotApi Exception";
        this.args = params;
    }

    /**
     * Constructor.
     * @param errorCodeParam
     * @param errorDescParam
     * @param params
     */
    public TelegramBotApiException(
            final int errorCodeParam,
            final String errorDescParam,
            final String... params) {
        super();
        this.errorCode = errorCodeParam;
        this.errorDesc = errorDescParam;
        this.args = params;
    }

    /**
     * Get string presentation for this exception.
     * @return String presentation for this exception.
     */
    @Override
    public String getMessage() {
        return String.format(errorDesc, args);
    }
}
