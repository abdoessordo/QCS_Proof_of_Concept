package com.ge.proof_of_concept.util.dto;

import org.springframework.util.ObjectUtils;

/**
 * This class is used to build the response object for the API in case of error
 *
 * Author: Abdellah ESSORDO
 * Created on: 06/03/2024
 */
public class ResponseErrorVo {
    private String code;
    private String message;
    private String description;

    public ResponseErrorVo(String code, String message) {
        this.code = code;
        this.message = message;
        this.description = message;
    }

    public ResponseErrorVo(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return ObjectUtils.isEmpty(description) ? message : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
