package com.beauty.api.utilities.geocoding.config;

import com.beauty.api.utilities.geocoding.GeocodingAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class GeocodingConfiguration {

    private static final String BASE_URL = "https://dapi.kakao.com";

    @Value("${kakao.rest-api-key}")
    private String kakaoRestApiKey;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder().addHeader("Authorization", "KakaoAK " + kakaoRestApiKey).build();
                    return chain.proceed(request);
                })
                .build();
    }

    @Bean
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Bean
    public GeocodingAPI serverAPIs(Retrofit retrofit) {
        return retrofit.create(GeocodingAPI.class);
    }
}


