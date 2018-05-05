package com.raritasolutions.yandex_gallery.model;

/**
 * Created by rarita on 21.04.18.
 */

/* Generic-ответ от сервера
В общем случае инста возвращает ответ вида pagination/meta/data
Первый массив нам не нужен, данных не так много для постраничной прогрузки. */
public class Response<T> {
    public final T data;
    public final Meta meta;

    public Response(T data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }
}
