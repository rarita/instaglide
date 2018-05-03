package com.raritasolutions.yandex_gallery.model;

/**
 * Created by rarita on 21.04.18.
 */

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
