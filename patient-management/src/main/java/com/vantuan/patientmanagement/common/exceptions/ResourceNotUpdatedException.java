package com.vantuan.patientmanagement.common.exceptions;

public class ResourceNotUpdatedException extends BaseException {

    public ResourceNotUpdatedException(Throwable cause) {
        super("Resource update failed", cause);
    }

    public ResourceNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    protected boolean isClientReadable() {
        return false;
    }
}
