package com.raritasolutions.yandex_gallery.model;

/**
 * Created by rarita on 21.04.18.
 */

/* Generic-ответ от сервера
В общем случае инста возвращает ответ вида pagination/meta/data
Первый массив нам не нужен, данных не так много для постраничной прогрузки. */
public class Response<T> {
    private T data;
    private Meta meta;

    public T getData() {
            return data;
        }

    public void setData(T data) {
            this.data = data;
        }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
