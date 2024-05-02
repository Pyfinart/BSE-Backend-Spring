package com.yupi.springbootinit.service;

import com.yupi.springbootinit.constant.BSEConstant;
import com.yupi.springbootinit.model.dto.bse.BSEAlgorithmParameterRequest;
import com.yupi.springbootinit.model.py4jInterface.AlgorithmParameterTransfer;
import org.springframework.stereotype.Service;
import py4j.GatewayServer;

import javax.annotation.Resource;

@Service
public class BSEService {

//    @Resource
//    GatewayServer gatewayServer;

    public void test() {
        GatewayServer gatewayServer = new GatewayServer();
        gatewayServer.start();
        AlgorithmParameterTransfer entryPoint = (AlgorithmParameterTransfer) gatewayServer.
                getPythonServerEntryPoint(new Class[]{AlgorithmParameterTransfer.class});
        try {
            entryPoint.helloBSE();
        } catch (Exception e) {
            e.printStackTrace();
        }
        gatewayServer.shutdown();
    }

    public String transferAlgorithmParameter(BSEAlgorithmParameterRequest bseAlgorithmParameterRequest) {
        // validate the parameter
        validateParameter(bseAlgorithmParameterRequest);

        // interact with python BSE
        GatewayServer gatewayServer = new GatewayServer();
        gatewayServer.start();
        AlgorithmParameterTransfer entryPoint = (AlgorithmParameterTransfer) gatewayServer.
                getPythonServerEntryPoint(new Class[]{AlgorithmParameterTransfer.class});
        String callStateString = "";
        try {
            callStateString = entryPoint.transferAlgorithmParameter(bseAlgorithmParameterRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gatewayServer.shutdown();
        return callStateString;
    }

    public void validateParameter(BSEAlgorithmParameterRequest bseAlgorithmParameterRequest) {
        // default value
        // start_time 0
        // end_time 600
        // seller_range (80, 310)
        // buyer_range (250, 490)
        // time_mode periodic
        // step_mode fixed
        // order_interval 30
        if (bseAlgorithmParameterRequest.getStartTime() < 0)
            bseAlgorithmParameterRequest.setStartTime(BSEConstant.ALGORITHM_START_TIME);

        if (bseAlgorithmParameterRequest.getEndTime() < 0)
            bseAlgorithmParameterRequest.setEndTime(BSEConstant.ALGORITHM_END_TIME);

        if (bseAlgorithmParameterRequest.getSellerRangeFrom() < 0)
            bseAlgorithmParameterRequest.setSellerRangeFrom(BSEConstant.SELLER_RANGE_FROM);

        if (bseAlgorithmParameterRequest.getSellerRangeTo() < 0) {
            int sellerRangeFrom = bseAlgorithmParameterRequest.getSellerRangeFrom();
            bseAlgorithmParameterRequest.setSellerRangeTo(sellerRangeFrom < BSEConstant.SELLER_RANGE_TO ?
                    BSEConstant.SELLER_RANGE_TO : sellerRangeFrom + 100);
        }

        if (bseAlgorithmParameterRequest.getBuyerRangeFrom() < 0)
            bseAlgorithmParameterRequest.setBuyerRangeFrom(BSEConstant.BUYER_RANGE_FROM);

        if (bseAlgorithmParameterRequest.getBuyerRangeTo() < 0) {
            int buyerRangeFrom = bseAlgorithmParameterRequest.getBuyerRangeFrom();
            bseAlgorithmParameterRequest.setSellerRangeTo(buyerRangeFrom < BSEConstant.BUYER_RANGE_TO ?
                    BSEConstant.BUYER_RANGE_TO : buyerRangeFrom + 100);
        }

        if (bseAlgorithmParameterRequest.getOrderInterval() < 0)
            bseAlgorithmParameterRequest.setOrderInterval(BSEConstant.ORDER_INTERVAL);

        if (bseAlgorithmParameterRequest.getStepMode() == null || "".equals(bseAlgorithmParameterRequest.getStepMode()))
            bseAlgorithmParameterRequest.setStepMode(BSEConstant.STEP_MODE);
        if (bseAlgorithmParameterRequest.getTimeMode() == null || "".equals(bseAlgorithmParameterRequest.getTimeMode()))
            bseAlgorithmParameterRequest.setTimeMode(BSEConstant.TIME_MODE);

        // checking the sellers_spec and buyers_spec may conduct at front-end
    }
}
