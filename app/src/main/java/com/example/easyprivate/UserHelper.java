package com.example.easyprivate;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.easyprivate.api.ApiInterface;
import com.example.easyprivate.api.RetrofitClientInstance;
import com.example.easyprivate.model.Absen;
import com.example.easyprivate.model.Pemesanan;
import com.example.easyprivate.model.User;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserHelper {
    private static final String TAG = "UserHelper";
    private Context mContext;
    private SharedPreferences preferences;

    private RetrofitClientInstance rci = new RetrofitClientInstance();
    private ApiInterface apiInterface = rci.getApiInterface();

    private static final String TAG_USER = "currUser";
    private static final String TAG_PREF = "currPref";
    private static final String TAG_PEMESANAN = "currPemesanan";


    public UserHelper(Context mContext) {
        this.mContext = mContext;
        this.preferences = mContext.getSharedPreferences(TAG_PREF, Context.MODE_PRIVATE);
    }

    public void storeUser(User u){
        String jsonStr = new Gson().toJson(u);

        SharedPreferences.Editor editor = preferences.edit();

        removeUser();
        editor.putString(TAG_USER, jsonStr);
        editor.commit();
    }

    public User retrieveUser(){
        Gson gson = new Gson();
        String json = preferences.getString(TAG_USER, "");
        Log.d(TAG, "retrieveUser: json = "+json);
        try {
            User u = gson.fromJson(json, User.class);
            return u;
        }catch (Throwable t){
            Log.d(TAG, "retrieveUser: "+t.getMessage());
            return null;
        }
    }

    public void removeUser(){
        if (retrieveUser() != null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(TAG_USER);
            editor.commit();
        }
    }

    public Absen jsonToAbsen(String absenJsonStr){
        Gson gson = new Gson();
        try {
            Absen absen = gson.fromJson(absenJsonStr, Absen.class);
            return absen;
        }catch (Throwable t){
            Log.d(TAG, "jsonToAbsen: "+t.getMessage());
            return null;
        }
    }

    public void putIntoImage(String avatarStr, CircleImageView civ){
        Picasso.get()
                .load(avatarStr)
                .placeholder(R.drawable.account_default)
                .error(R.drawable.account_default)
                .noFade()
                .into(civ, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                        String avatarStrAlt = modifyAvatarStr(avatarStr);
                        putIntoImageAlt(avatarStrAlt, civ);
                    }
                });
    }

    public void putIntoImageAlt(String avatarStr, CircleImageView civ){
        Picasso.get()
                .load(avatarStr)
                .placeholder(R.drawable.account_default)
                .error(R.drawable.account_default)
                .noFade()
                .into(civ);
    }

    public String modifyAvatarStr(String avatarStr){
        avatarStr = rci.getBaseUrl() + "assets/avatars/" + avatarStr;
        return avatarStr;
    }

    public static String getUserTag() {
        return TAG_USER;
    }

    public static String getPrefTag() {
        return TAG_PREF;
    }

    public void storePemesanan(Pemesanan pesanan){
        String jsonStr = new Gson().toJson(pesanan);

        SharedPreferences.Editor editor = preferences.edit();

        removePemesanan();
        editor.putString(TAG_PEMESANAN, jsonStr);
        editor.commit();
    }

    public Pemesanan retrievePemesanan(){
        Gson gson = new Gson();
        String json = preferences.getString(TAG_PEMESANAN, "");
        try {
            Pemesanan pesanan = gson.fromJson(json, Pemesanan.class);
            return pesanan;
        }catch (Throwable t){
            Log.d(TAG, "retrievePemesanan: "+t.getMessage());
            return null;
        }
    }

    public void removePemesanan(){
        if (retrievePemesanan() != null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(TAG_PEMESANAN);
            editor.commit();
        }
    }
}
