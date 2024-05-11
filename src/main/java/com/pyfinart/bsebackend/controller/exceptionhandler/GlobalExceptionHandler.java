package com.pyfinart.bsebackend.controller.exceptionhandler;

import com.pyfinart.bsebackend.common.BaseResponse;
import com.pyfinart.bsebackend.common.ErrorCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public BaseResponse<String> handleIllegalStateException(IllegalStateException exception){
        return new BaseResponse<>(ErrorCode.OPERATION_ERROR.getCode(), "BSE is still running, please wait!");
    }

}
