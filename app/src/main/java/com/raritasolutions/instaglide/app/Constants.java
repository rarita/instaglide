package com.raritasolutions.instaglide.app;

/**
 * Created by rarita on 21.04.18.
 */

public class Constants {
    // Отображение списка
    public final int COLUMN_COUNT = 3;
    public final int LIST_SPACING = 2; // В DP
    // Работа с сетью
    public final String API_URL = "https://api.instagram.com/";
    // Токен не стоит хранить в публичных местах, если это не токен Instagram API.
    // Свои фотки и то всего 200 раз в час можно смотреть :/
    public final String ACCESS_TOKEN_PUBLIC_SCOPE = "3913988116.1677ed0.cec06026aa004f9388be68dde58af958";

}
