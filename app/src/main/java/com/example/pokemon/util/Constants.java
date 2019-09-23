package com.example.pokemon.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {

    public static final String BASE_URL = "https://pokeapi.co/api/v2/";
    public static final String API_KEY = "90e44121";
    public final static String LOADING = "Loading";
    public final static String LOADED = "Loaded";
    public final static String CHECK_NETWORK_ERROR = "Check your network connection.";

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
