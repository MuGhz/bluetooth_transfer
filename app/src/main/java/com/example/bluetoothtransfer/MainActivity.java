package com.example.bluetoothtransfer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.ParcelUuid;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.bluetoothtransfer.adapter.DeviceAdapter;
import com.example.bluetoothtransfer.model.Device;
import com.example.bluetoothtransfer.model.Status;
import com.example.bluetoothtransfer.service.ApiClient;
import com.example.bluetoothtransfer.service.ApiInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_ENABLE_BT=1;
    public static final int DISCOVER_DURATION = 300;
    BluetoothAdapter mBluetoothAdapter;
    ArrayList<Device> deviceItemList;
    ApiInterface apiInterface;
    Status statusResponse;
    private OutputStream outputStream;
    private InputStream inStream;
    String sendOutput;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Create a new device item
                Device newDevice = new Device(device.getName(), device.getAddress());
                // Add it to our adapter
                deviceItemList.add(newDevice);
                mRecyclerView.setAdapter(mAdapter);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Status> statusCall = apiInterface.getStatus();
        statusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                statusResponse = response.body();
                TextView tvApiResult = findViewById(R.id.api_call);
                tvApiResult.setText(statusResponse.getStatus()+" - "+statusResponse.getResult().getAuthorization());
                sendOutput=statusResponse.getStatus()+" - "+statusResponse.getResult().getAuthorization();
                Log.e("Retrofit Get", response.headers().toString());
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if (!mBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
        deviceItemList = new ArrayList<Device>();
        if (bondedDevices.size() > 0) {
            for (BluetoothDevice device : bondedDevices) {
                Device newDevice= new Device(device.getName(),device.getAddress());
                deviceItemList.add(newDevice);
            }
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.search_result);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DeviceAdapter(deviceItemList);
        mRecyclerView.setAdapter(mAdapter);
        Log.e("size", String.valueOf(mAdapter.getItemCount()));

        Button sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == REQUEST_ENABLE_BT)
        {

            String message;
            if (resultCode == RESULT_OK)
            {

                message = "Bluetooth is on";

            } else {

                message = "Bluetooth is off";

            }
            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.show();


        }

    }

}