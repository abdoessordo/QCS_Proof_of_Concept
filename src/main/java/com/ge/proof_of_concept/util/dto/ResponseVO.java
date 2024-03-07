package com.ge.proof_of_concept.util.dto;

public class ResponseVO<T> {
    private String status;
    private String result;
    private ResponseErrorVo error;
    private T data;
    private String message;

    //==================================================================================================================
    // Getters and Setters
    //==================================================================================================================


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ResponseErrorVo getError() {
        return error;
    }

    public void setError(ResponseErrorVo error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}