package com.yupi.springbootinit.controller;

import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.constant.BSEConstant;
import com.yupi.springbootinit.model.dto.bse.BSEAlgorithmParameterRequest;
import com.yupi.springbootinit.model.enums.CallbackState;
import com.yupi.springbootinit.service.BSEService;
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
