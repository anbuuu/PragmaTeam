package com.pragma.data.exception;

import com.pragma.domain.exception.ErrorBundle;

/**
 * Created by anbu.ezhilan on 10/03/2017.
 */

public class RepositoryErrorBundle implements ErrorBundle {

    private final Exception exception;

    RepositoryErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String message = "";
        if (this.exception != null) {
            message = this.exception.getMessage();
        }
        return message;
    }

}
