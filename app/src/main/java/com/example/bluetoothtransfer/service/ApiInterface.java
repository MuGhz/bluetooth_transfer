package com.example.bluetoothtransfer.service;

import com.example.bluetoothtransfer.model.Status;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Muhammad Ghozi on 22/05/19
 * ghozi.mghozi@gmail.com
 * BluetoothTransfer
 */
public interface ApiInterface {
    @POST("test")
    Call<Status> getStatus();
}
