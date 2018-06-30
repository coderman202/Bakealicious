package com.coderman202.bakealicious.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utility class to handle network connections
 */
public class NetworkUtils {


    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final int RESPONSE_CODE = 200;
    private static final int TIMEOUT = 10000;
    private static final int CONNECT_TIMEOUT = 15000;
    private static final String REQUEST_METHOD = "GET";
    private static final String CHARSET_NAME = "UTF-8";
    private static final int SLEEP_MILLIS = 2000;
    /**
     * Method to check network connectivity
     * @param context The context
     * @return TRUE is connected or else FALSE
     */
    public static boolean isConnected(Context context) {
        final ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

}
