package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.py4jInterface.AlgorithmParameterTransfer;
import org.junit.jupiter.api.Test;
import py4j.GatewayServer;

public class BSEServiceTest {
    @Test
    void firstTest() {
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
}
