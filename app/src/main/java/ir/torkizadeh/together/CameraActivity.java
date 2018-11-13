package ir.torkizadeh.together;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import android.nfc.Tag;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



import com.bumptech.glide.Glide;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView camera_picture;
//    Button call_camera, call_bluetooth, call_location, call_wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
//        camera_picture = findViewById(R.id.camera_picture);
//        call_bluetooth = findViewById(R.id.call_bluetooth);
//        call_location = findViewById(R.id.call_location);
//        call_wifi = findViewById(R.id.call_wifi);
        findViewById(R.id.call_camera).setOnClickListener(CameraActivity.this);
        findViewById(R.id.call_bluetooth).setOnClickListener(CameraActivity.this);
        findViewById(R.id.call_location).setOnClickListener(CameraActivity.this);
        findViewById(R.id.call_wifi).setOnClickListener(CameraActivity.this);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 150 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            camera_picture.setImageBitmap(image);
            // do whatever you want with the image now}
        } else if (requestCode == 150 && resultCode == RESULT_CANCELED)
            Toast.makeText(this, "picture is cancelled", Toast.LENGTH_SHORT).show();
        if (requestCode == 101 && resultCode == RESULT_OK)
            Toast.makeText(this, "Bluetooth was Enabled", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_camera:
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            100);
                }
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, 150);
                break;
            case R.id.call_bluetooth: //Enabling bluetooth
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter == null) {
                    Toast.makeText(this, "This Device don't have Bluetooth", Toast.LENGTH_SHORT).show();
                } else if (bluetoothAdapter.isEnabled()) {
                    Intent enableBluetoothAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBluetoothAdapter, 101);
                }
                break;

            case R.id.call_wifi://Enabling Wifi
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
                break;

            case R.id.call_location:
                Toast.makeText(this, "Locaiton", Toast.LENGTH_SHORT).show();
                break;
            //location
      //branch 05


        }
    }
}