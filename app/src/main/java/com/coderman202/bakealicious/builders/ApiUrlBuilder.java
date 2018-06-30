package com.coderman202.bakealicious.builders;

import android.content.Context;

import com.coderman202.bakealicious.utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Reggie on 16/06/2018.
 * Builds URL with base URL constant and gets network connectivity to get baking data.
 * Uses Retrofit with OkHttp.
 * This was one of my resources:
 * @see <a href="https://code.tutsplus.com/tutorials/getting-started-with-retrofit-2--cms-27792">
 *     Getting Started with Retrofit 2 HTTP Client</a>
 * User: Chike Mgbemena
 * Date: 2016/12/16
 *
 */

public class ApiUrlBuilder {

    private static Retrofit retrofitClient = null;



    // Constant - how long til connection will timeout - in milliseconds.
    private static final int CONNECT_TIMEOUT = 15000;

    // Base URL to get the JSON data from.
    public static final String JSON_DATA_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";



    public static Retrofit getRetrofitClient(Context context) throws IOException {

        if(!NetworkUtils.isConnected(context)){
            throw new IOException();
        }

        if(retrofitClient == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(interceptor).followRedirects(true);
            builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

            retrofitClient = new Retrofit.Builder().baseUrl(JSON_DATA_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build()).build();
        }
        return retrofitClient;
    }
}
