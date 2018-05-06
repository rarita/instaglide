package com.raritasolutions.yandex_gallery;

import com.raritasolutions.yandex_gallery.model.InstaData;
import com.raritasolutions.yandex_gallery.model.LoginData;
import com.raritasolutions.yandex_gallery.model.Response;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rarita on 21.04.18.
 */

public interface RetrofitService {
    // API никогда не пришлет пустого значения, поэтому резонно использовать Single
    @GET("v1/users/self")
    Single<Response<LoginData>> getUserLoginData(@Query("access_token") String access_token);

    @GET("v1/tags/{tag_name}/media/recent")
    Single<Response<InstaData[]>> getTagPhotos(@Path("tag_name") String tag_name,
                                               @Query("access_token") String access_token);
    @GET("v1/users/self/media/recent")
    Single<Response<InstaData[]>> getUserPhotos(@Query("access_token") String access_token);
}