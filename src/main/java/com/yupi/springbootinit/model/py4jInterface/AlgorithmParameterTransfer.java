package com.yupi.springbootinit.model.py4jInterface;

import com.yupi.springbootinit.model.dto.bse.BSEAlgorithmParameterRequest;

public interface AlgorithmParameterTransfer {
    void helloBSE();

    String transferAlgorithmParameter(BSEAlgorithmParameterRequest bseAlgorithmParameterRequest, String filename);

}
