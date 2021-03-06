package com.example.easyprivate.api;

import android.app.ProgressDialog;
import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private Retrofit retrofit;
//    public static final String BASE_URL = "http://192.168.1.13:81/easyprivate/public/";
    public static final String BASE_URL = "http://easyprivate.tech/";
    private String defaultTitle = "Tunggu sebentar ya!";
    private String defaultMessage = "Menyambungkan dengan server";
    public ApiInterface getApiInterface(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL+"api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        return  apiInterface;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public ProgressDialog getProgressDialog(Context mContext){
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle(defaultTitle);
        progressDialog.setMessage(defaultMessage);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        return progressDialog;
    }

    public ProgressDialog getProgressDialog(Context mContext, String title, String message){
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        return progressDialog;
    }

    public ProgressDialog getProgressDialog(Context mContext, String message){
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle(defaultTitle);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        return progressDialog;
    }

}
