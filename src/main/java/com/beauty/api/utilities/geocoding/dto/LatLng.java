package com.beauty.api.utilities.geocoding.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LatLng {
    private double lat;
    private double lng;
}
