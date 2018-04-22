package com.raritasolutions.yandex_gallery;

import com.raritasolutions.yandex_gallery.model.InstaResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rarita on 21.04.18.
 */

public interface RetrofitService {
    @GET("v1/tags/{tag_name}/media/recent")
    Observable<InstaResponse> getTagPhotos(@Path("tag_name") String tag_name,
                                           @Query("access_token") String access_token);
}