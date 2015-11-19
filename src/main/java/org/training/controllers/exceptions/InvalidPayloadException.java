package org.training.controllers.exceptions;

public class InvalidPayloadException extends RuntimeException {

    private final Object payload;

    public InvalidPayloadException(Object payload) {
        super("The payload is invalid: " + payload);
        this.payload = payload;
    }

    public Object getPayload() {
        return payload;
    }
}
