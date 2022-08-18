package com.example.dapp.enumeration;

public enum PaymentMethod {
    ASSET(Method.ASSET),
    POINT(Method.POINT);

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getMethod(){
        return this.method;
    }

    public static class Method {
        public static final String ASSET = "ASSET";
        public static final String POINT = "POINT";
    }

}
