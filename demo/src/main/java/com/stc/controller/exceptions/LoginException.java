package com.stc.controller.exceptions;

/**
 *
 * @author mahmoud
 */
public class LoginException extends Exception {

    public LoginException() {
        super("Login failed! Unkown username/password");
    }
}