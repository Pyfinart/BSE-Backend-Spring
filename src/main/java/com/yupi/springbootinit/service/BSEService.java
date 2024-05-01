package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.py4jInterface.AlgorithmParameterTransfer;
import py4j.GatewayServer;

import javax.annotation.Resource;

public class BSEService {

    @Resource
    GatewayServer gatewayServer;

    public void test() {
//        GatewayServer.turnLoggingOff();
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
}
