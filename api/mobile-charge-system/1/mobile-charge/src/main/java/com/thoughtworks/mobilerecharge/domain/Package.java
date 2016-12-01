package com.thoughtworks.mobilerecharge.domain;

import com.thoughtworks.mobilerecharge.infrastructure.records.Record;
import com.thoughtworks.mobilerecharge.web.jersey.Routes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Package implements Record {
    private String package_id;
    private String name;
    private String description;
    private BigDecimal price;
    private Date effective_date;
    private Date expiry_date;

    public Package(String package_id, String name, String description, BigDecimal price, Date effective_date, Date expiry_date) {
        this.package_id = package_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.effective_date = effective_date;
        this.expiry_date = expiry_date;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
//            put("uri", routes.packageUri(Package.this));
            put("id", package_id);
            put("name", name);
            put("description", description);
            put("price", price.toString());
            put("effective_date", effective_date);
            put("expiry_date", expiry_date);
        }};
    }

    public String getPackageId() {
        return package_id;
    }
}
