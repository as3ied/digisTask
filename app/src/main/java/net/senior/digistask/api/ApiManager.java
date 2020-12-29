package net.senior.digistask.api;

import net.senior.digistask.model.Model;


import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiManager {

    @GET("/random")
    Flowable<Model> getData();
}
