package com.beauty.api.utilities.geocoding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GeocodingResponse {
    private List<Document> documents;
}

