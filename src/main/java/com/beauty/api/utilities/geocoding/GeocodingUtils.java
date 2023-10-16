package com.beauty.api.utilities.geocoding;

import com.beauty.api.utilities.geocoding.dto.GeocodingResponse;
import com.beauty.api.utilities.geocoding.dto.LatLng;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;


@AllArgsConstructor
@Slf4j
@Component
public class GeocodingUtils {

    private final GeocodingAPI geocodingAPI;

    public LatLng getLatLng(String address) {
        Call<GeocodingResponse> call = geocodingAPI.geocode(address);

        try {
            Response<GeocodingResponse> response = call.execute();
            if (response.isSuccessful()) {
                GeocodingResponse responseBody = response.body();

                return LatLng.builder()
                        .lat(Double.parseDouble(responseBody.getDocuments().get(0).getY()))
                        .lng(Double.parseDouble(responseBody.getDocuments().get(0).getX()))
                        .build();
            }
            log.error(response.errorBody().string());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return LatLng.builder()
                .lat(0)
                .lng(0)
                .build();
    }
}
