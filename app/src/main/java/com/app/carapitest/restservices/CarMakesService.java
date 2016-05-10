package com.app.carapitest.restservices;

import com.app.carapitest.models.CarMakesResponse;
import com.app.carapitest.models.CarModelResponse;
import com.app.carapitest.models.CarMediaResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface CarMakesService {
    @GET("/api/vehicle/v2/makes?width=500&height=854")
    Observable<CarMakesResponse> getCarMakes(@Query("state") String state,
                                              @Query("year") int year,
                                              @Query("view") String view,
                                              @Query("api_key") String api_key,
                                              @Query("fmt") String fmt);


    @GET("/api/vehicle/v2/{makeNiceName}/models")
    Observable<CarModelResponse>  getCarModels(@Path("makeNiceName") String makeNiceName,
                                               @Query("view") String view,
                                               @Query("api_key") String api_key,
                                               @Query("fmt") String fmt);

    @GET("/v1/api/vehiclephoto/service/findphotosbystyleid")
    Observable<List<CarMediaResponse>> getMedia(@Query("styleId") int styleId,
                                           @Query("api_key") String api_key,
                                           @Query("fmt") String fmt);

}
