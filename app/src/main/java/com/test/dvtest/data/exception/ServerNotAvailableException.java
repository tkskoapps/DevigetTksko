package com.test.dvtest.data.exception;

public class ServerNotAvailableException extends Exception {

    public ServerNotAvailableException() {

        super("Server not Available. Please Retry later");

    }

}
