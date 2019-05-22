package com.example.bluetoothtransfer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Muhammad Ghozi on 22/05/19
 * ghozi.mghozi@gmail.com
 * BluetoothTransfer
 */
public class Status {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private Result result;

    public Status(String status, Result result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
