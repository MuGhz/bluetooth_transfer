package com.example.bluetoothtransfer.model;

/**
 * Created by Muhammad Ghozi on 22/05/19
 * ghozi.mghozi@gmail.com
 * BluetoothTransfer
 */
public class Device {
    String name;
    String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Device(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
