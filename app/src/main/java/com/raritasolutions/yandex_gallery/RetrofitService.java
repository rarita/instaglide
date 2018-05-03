package com.raritasolutions.yandex_gallery;

import com.raritasolutions.yandex_gallery.model.InstaData;
import com.raritasolutions.yandex_gallery.model.LoginData;
import com.raritasolutions.yandex_gallery.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rarita on 21.04.18.
 */

public interface RetrofitService {
    @GET("v1/users/self")
    Observable<Response<LoginData>> getUserLoginData(@Query("access_token") String access_token);

    @GET("v1/tags/{tag_name}/media/recent")
    Observable<Response<InstaData[]>> getTagPhotos(@Path("tag_name") String tag_name,
                                                 @Query("access_token") String access_token);
    @GET("v1/users/self/media/recent")
    Observable<Response<InstaData[]>> getUserPhotos(@Query("access_token") String access_token);
}