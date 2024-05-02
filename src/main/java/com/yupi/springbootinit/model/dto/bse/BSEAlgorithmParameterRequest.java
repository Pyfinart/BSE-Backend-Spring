package com.yupi.springbootinit.model.dto.bse;

import lombok.Data;

import java.util.Map;

@Data
public class BSEAlgorithmParameterRequest {
    private int startTime;
    private int endTime;
    private int sellerRangeFrom;
    private int sellerRangeTo;
    private int buyerRangeFrom;
    private int buyerRangeTo;
    private String stepMode;
    private String timeMode;
    private int orderInterval;
    private Map<String, Integer> sellersSpec;
    private Map<String, Integer> buyersSpec;

    // experiment id
    private String trialId;
}
