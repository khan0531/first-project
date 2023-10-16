package com.beauty.api.utilities.geocoding;

import com.beauty.api.utilities.geocoding.dto.GeocodingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingAPI {

    @GET("/v2/local/search/address.json")
    Call<GeocodingResponse> geocode(
            @Query("query") String query
    );
}
