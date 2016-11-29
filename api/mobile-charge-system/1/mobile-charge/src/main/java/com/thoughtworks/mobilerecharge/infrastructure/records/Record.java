package com.thoughtworks.mobilerecharge.infrastructure.records;

import com.thoughtworks.mobilerecharge.web.jersey.Routes;

import java.util.Map;

public interface Record {
    Map<String, Object> toRefJson(Routes routes);

    Map<String, Object> toJson(Routes routes);
}
