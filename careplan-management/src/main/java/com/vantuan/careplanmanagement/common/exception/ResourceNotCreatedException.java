package com.vantuan.careplanmanagement.common.exception;

public class ResourceNotCreatedException extends BaseException {

    public ResourceNotCreatedException(Throwable cause) {
        super("Resource creation failed", cause);
    }

    public ResourceNotCreatedException(String message) {
        super(message);
    }

    public ResourceNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    protected boolean isClientReadable() {
        return false;
    }
}
