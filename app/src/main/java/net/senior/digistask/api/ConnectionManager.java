package net.senior.digistask.api;


import net.senior.digistask.model.Model;

import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionManager {
    private static  ConnectionManager ourInstance ;
    private ApiManager apiManager;
    public static ConnectionManager getInstance() {
        if (ourInstance == null)
            ourInstance = new ConnectionManager();

        return ourInstance;
    }

    private ConnectionManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://51.195.89.92:6000")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         apiManager=retrofit.create(ApiManager.class);
    }
    public Flowable<Model> getAll() {
        return apiManager.getData();
    }

}
