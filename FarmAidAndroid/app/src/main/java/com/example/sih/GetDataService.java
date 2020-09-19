package com.example.sih;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetDataService {

    @POST("/up/wheat/")
    Call<String> getUpWheat(@Body APIObject object);

    @POST("/up/rice/")
    Call<String> getUpRice(@Body APIObject object);

    @POST("/up/sugarcane/")
    Call<String> getUpSugarcane(@Body APIObject object);

    @POST("/mh/arhar/")
    Call<String> getMhArhar(@Body APIObject object);

    @POST("/mh/cotton/")
    Call<String> getMhCotton(@Body APIObject object);

    @POST("/mh/rice/")
    Call<String> getMhRice(@Body APIObject object);

    @POST("/mh/soyabean/")
    Call<String> getMhSoyabean(@Body APIObject object);

    @POST("/hr/wheat/")
    Call<String> getHrWheat(@Body APIObject object);

    @POST("/hr/rice/")
    Call<String> getHrRice(@Body APIObject object);

    @POST("/br/maize/")
    Call<String> getBrMaize(@Body APIObject object);

    @POST("/pb/rice/")
    Call<String> getPbRice(@Body APIObject object);
}
