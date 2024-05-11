package com.pyfinart.bsebackend.controller;

import com.pyfinart.bsebackend.common.BaseResponse;
import com.pyfinart.bsebackend.common.ErrorCode;
import com.pyfinart.bsebackend.model.dto.bse.BSEAlgorithmParameterRequest;
import com.pyfinart.bsebackend.model.enums.CallbackState;
import com.pyfinart.bsebackend.service.BSEService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/bse")
@Slf4j
public class BSEController {

    @Resource
    BSEService bseService;

    @PostMapping("/")
    public BaseResponse<String> receiveAlgorithmParameter(@RequestBody BSEAlgorithmParameterRequest bseAlgorithmParameterRequest) {
        if (bseAlgorithmParameterRequest == null) {
            return new BaseResponse<>(ErrorCode.PARAMS_ERROR);
        }

        String callStateString = bseService.transferAlgorithmParameter(bseAlgorithmParameterRequest);

        if (callStateString.equals(CallbackState.Success.getMessage())) {
            return new BaseResponse<>(ErrorCode.SUCCESS.getCode(), callStateString);
        } else {
            return new BaseResponse<>(ErrorCode.OPERATION_ERROR.getCode(), callStateString);
        }
    }

}
