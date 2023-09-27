package com.sansa.entretainmentapi.model;

public class ErrorResponse {
    private String error;
    public ErrorResponse(String msg){
        this.error = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
