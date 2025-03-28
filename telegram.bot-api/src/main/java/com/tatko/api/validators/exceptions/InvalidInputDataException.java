package com.tatko.api.validators.exceptions;

public class InvalidInputDataException extends BaseValidatorException {

    /**
     * Error code.
     */
    public static final int ERROR_CODE = 20001;

    /**
     * Constructor.
     *
     * @param params Params.
     */
    public InvalidInputDataException(final String... params) {
        super(params);
    }

    /**
     * Error code.
     * @return
     */
    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

    /**
     * Exception description.
     * @return
     */
    @Override
    public String getMessage() {
        return "Invalid input data: " + getArgs()[0];
    }
}
