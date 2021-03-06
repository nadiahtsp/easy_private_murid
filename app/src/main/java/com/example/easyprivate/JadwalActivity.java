package com.example.easyprivate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.easyprivate.adapter.JadwalAdapter;
import com.example.easyprivate.adapter.PemesananAdapter;
import com.example.easyprivate.api.ApiInterface;
import com.example.easyprivate.api.RetrofitClientInstance;
import com.example.easyprivate.model.JadwalPemesananPerminggu;
import com.example.easyprivate.model.Pemesanan;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {
    RecyclerView jadwalRV;
    private RetrofitClientInstance rci = new RetrofitClientInstance();
    private ApiInterface apiInterface= rci.getApiInterface();
    private Context mContext;
    UserHelper uh;
    ArrayList<JadwalPemesananPerminggu> jappAL =new ArrayList<>();
    private static final String TAG = "JadwalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        init();
        callJadwalperminggu();
    }

    public void init(){
        jadwalRV=findViewById(R.id.jadwalRV);
        uh = new UserHelper(this);
    }
    public void callJadwalperminggu(){
        Call<ArrayList<JadwalPemesananPerminggu>> cJapp = apiInterface.jadwalPermingguByFilter(null,
                null,
                uh.retrieveUser().getId(),
                1);
        cJapp.enqueue(new Callback<ArrayList<JadwalPemesananPerminggu>>() {
            @Override
            public void onResponse(Call<ArrayList<JadwalPemesananPerminggu>> call, Response<ArrayList<JadwalPemesananPerminggu>> response) {
                Log.d(TAG, "onResponse: "+response.message());
                if (!response.isSuccessful()){
                    return;
                }
                jappAL = response.body();
                JadwalAdapter jadwal = new JadwalAdapter(JadwalActivity.this,jappAL);
                jadwalRV.setAdapter(jadwal);
                jadwalRV.setLayoutManager(new LinearLayoutManager(JadwalActivity.this));

            }

            @Override
            public void onFailure(Call<ArrayList<JadwalPemesananPerminggu>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });


    }
}
