package com.pyfinart.bsebackend.model.py4jInterface;

import com.pyfinart.bsebackend.model.dto.bse.BSEAlgorithmParameterRequest;

public interface AlgorithmParameterTransfer {
    void helloBSE();

    String transferAlgorithmParameter(BSEAlgorithmParameterRequest bseAlgorithmParameterRequest, String filename);

}
