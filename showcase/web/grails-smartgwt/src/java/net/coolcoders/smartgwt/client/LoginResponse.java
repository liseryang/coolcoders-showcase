package net.coolcoders.smartgwt.client;

import grails.plugins.gwt.shared.Response;

public class LoginResponse implements Response {
    private static final long serialVersionUID = 1L;
    private boolean successful;
    private Long userId;
    private String message;

    public LoginResponse() {
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
