package com.thoughtworks.mobilecharge.support;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    private static int auto_increment_key = 1;
    public static Map<String, Object> cardMap(String number, BigDecimal balance) {
        return new HashMap<String, Object>(){{
            put("number", number);
            put("balance", balance);
        }};
    }
}
