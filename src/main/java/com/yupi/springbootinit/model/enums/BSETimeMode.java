package com.yupi.springbootinit.model.enums;

public enum BSETimeMode {
    Periodic("periodic"), Fixed("fixed"), DripFixed("drip-fixed"),
    DripJitter("drip-jitter"), DripPoisson("drip-poisson");
    private String value;

    BSETimeMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
