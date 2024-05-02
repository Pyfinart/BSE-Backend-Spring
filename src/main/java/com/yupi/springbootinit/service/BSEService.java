package com.yupi.springbootinit.service;

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
}
