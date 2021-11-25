package com.app.systembackend.controller.response;

import lombok.Data;

@Data
public class CommonResponse<T> {

    private T dataBundle;
    private boolean isSuccess;
    private String  successMessage;
    private String  errorMessage;

    public CommonResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CommonResponse(T dataBundle, String errorMessage) {
        this.dataBundle = dataBundle;
        this.errorMessage = errorMessage;
    }

    public CommonResponse(T dataBundle, boolean isSuccess, String successMessage) {
        this.dataBundle = dataBundle;
        this.isSuccess = isSuccess;
        this.successMessage = successMessage;
    }
}
