package com.example.bluetoothtransfer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Muhammad Ghozi on 22/05/19
 * ghozi.mghozi@gmail.com
 * BluetoothTransfer
 */
public class Result {
    @SerializedName("Authorization")
    private String authorization;

    public Result(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
