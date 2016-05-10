package com.app.carapitest.network;

import com.app.carapitest.models.CarMakesResponse;
import com.app.carapitest.models.CarMediaResponse;
import com.app.carapitest.models.CarModelResponse;
import com.app.carapitest.restservices.CarMakesService;
import com.google.gson.GsonBuilder;


import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CarRestAPI {
    public static final String MEDIA_URL = "https://media.ed.edmunds-media.com";
    private static final String BASE_URL = "https://api.edmunds.com/";
    private static final String API_KEY = "67fc8n4tm29nsm9gj2n3v6pe";
    private final CarAPIListener listener;
    private CarMakesService service;

    public CarRestAPI(CarAPIListener listener) {
        this.listener = listener;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        service = retrofit.create(CarMakesService.class);
    }

    public void getCarMakes() {
        Observable<CarMakesResponse> response = service.getCarMakes("new", 2015, "basic", API_KEY, "json");
        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CarMakesResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.OnError("An Error Occured! ");
                    }

                    @Override
                    public void onNext(CarMakesResponse carMakesResponse) {
                        carMakesResponse.getMakes();
                        listener.OnResponse(carMakesResponse);
                    }
                });
    }

    public void getCarModels(String name) {
        Observable<CarModelResponse> response = service.getCarModels(name, "basic", API_KEY, "json");
        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CarModelResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.OnError("An Error Occured! ");
                    }

                    @Override
                    public void onNext(CarModelResponse carMakesResponse) {
                        carMakesResponse.getModels().get(0).getYears().get(0).getStyles();
                        listener.OnResponse(carMakesResponse);
                    }
                });
    }
    public void getCarMedia(int StyleId) {
        Observable<List<CarMediaResponse>> response = service.getMedia(StyleId, API_KEY, "json");


        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CarMediaResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.OnError("An Error Occured! ");

                    }

                    @Override
                    public void onNext(List<CarMediaResponse> carMakesResponse) {
                        carMakesResponse.get(0).getPhotoSrcs();
                        listener.OnResponse(carMakesResponse.get(0));
                    }
                });
    }
    public interface CarAPIListener {
        void OnResponse(Object response);
        void OnError(String msg);
    }


}
