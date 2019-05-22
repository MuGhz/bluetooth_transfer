package com.example.bluetoothtransfer.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bluetoothtransfer.R;
import com.example.bluetoothtransfer.model.Device;

import java.util.ArrayList;

/**
 * Created by Muhammad Ghozi on 22/05/19
 * ghozi.mghozi@gmail.com
 * BluetoothTransfer
 */
public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {
    private ArrayList<Device> deviceList;

    public DeviceAdapter(ArrayList<Device> deviceList){
        this.deviceList = deviceList;
    }
    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        holder.txtName.setText(deviceList.get(position).getName());
        holder.txtAddress.setText(deviceList.get(position).getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });;
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtAddress;
        public DeviceViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.device_name);
            txtAddress = (TextView) view.findViewById(R.id.device_address);

        }
    }


}
