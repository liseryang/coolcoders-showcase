package net.coolcoders.showcase.client;

import grails.plugins.gwt.shared.Action;

public class LoginAction implements Action<LoginResponse> {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public LoginAction() {
    }

    public LoginAction(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
