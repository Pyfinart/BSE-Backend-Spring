package com.yupi.springbootinit.controller;

import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
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

        // validate the parameter


        String callStateString = bseService.transferAlgorithmParameter(bseAlgorithmParameterRequest);

        if (callStateString.equals(CallbackState.Success.getMessage())) {
            return new BaseResponse<>(ErrorCode.SUCCESS.getCode(), callStateString);
        } else {
            return new BaseResponse<>(ErrorCode.OPERATION_ERROR.getCode(), callStateString);
        }
    }

    public void validateParameter(BSEAlgorithmParameterRequest bseAlgorithmParameterRequest){
        // default value
        // start_time 0
        // end_time 600
        // buyer_range (80, 310)
        // buyer_range (250, 490)
        if(bseAlgorithmParameterRequest.getStartTime() < 0) bseAlgorithmParameterRequest.setStartTime(0);
        if(bseAlgorithmParameterRequest.getEndTime() < 0) bseAlgorithmParameterRequest.setEndTime(600);
        if(bseAlgorithmParameterRequest.getSellerRangeFrom() < 0) bseAlgorithmParameterRequest.setSellerRangeFrom(80);

        if(bseAlgorithmParameterRequest.getSellerRangeTo() < 0) {
            int sellerRangeFrom = bseAlgorithmParameterRequest.getSellerRangeFrom();
            bseAlgorithmParameterRequest.setSellerRangeTo(sellerRangeFrom < 310 ? 310 : sellerRangeFrom + 100);
        }

        if(bseAlgorithmParameterRequest.getBuyerRangeFrom() < 0) bseAlgorithmParameterRequest.setBuyerRangeFrom(250);

        if(bseAlgorithmParameterRequest.getBuyerRangeTo() < 0) {
            int buyerRangeFrom = bseAlgorithmParameterRequest.getBuyerRangeFrom();
            bseAlgorithmParameterRequest.setSellerRangeTo(buyerRangeFrom < 490 ? 490 : buyerRangeFrom + 100);
        }

        if(bseAlgorithmParameterRequest.getOrderInterval() < 0) bseAlgorithmParameterRequest.setOrderInterval(30);

        if(bseAlgorithmParameterRequest.getStepMode() == null || "".equals(bseAlgorithmParameterRequest.getStepMode()))
            bseAlgorithmParameterRequest.setStepMode("fixed");
        if(bseAlgorithmParameterRequest.getTimeMode() == null || "".equals(bseAlgorithmParameterRequest.getTimeMode()))
            bseAlgorithmParameterRequest.setTimeMode("periodic");
    }
}
