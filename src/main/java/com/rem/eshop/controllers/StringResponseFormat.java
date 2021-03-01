package com.rem.eshop.controllers;

public class StringResponseFormat {
    private String message;

    public StringResponseFormat(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
