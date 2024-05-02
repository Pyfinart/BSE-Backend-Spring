package com.yupi.springbootinit.model.enums;

/**
 * Success: if a call from Spring to BSE is succeeded
 * Failure: if a call from Spring to BSE is failed
 */
public enum CallbackState {
    Success("Success"),Failure("Failure");
    private String message;

    CallbackState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
