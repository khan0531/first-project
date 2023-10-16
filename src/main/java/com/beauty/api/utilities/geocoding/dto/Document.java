package com.beauty.api.utilities.geocoding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Document {
    private String address_name;
    private String place_name;
    private String road_address_name;
    private String x;
    private String y;
}
