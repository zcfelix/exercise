package com.thoughtworks.mobilerecharge.support;

import java.math.BigDecimal;
import java.util.Date;
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

    public static Map<String, Object> packageMap() {
        return new HashMap<String, Object>(){{
            put("package_id", 1);
            put("effective_date", new Date(2016, 12, 1));
        }};
    }
}
