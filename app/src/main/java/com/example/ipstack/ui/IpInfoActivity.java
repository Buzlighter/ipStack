package com.example.ipstack.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipstack.R;
import com.example.ipstack.data.model.IpDataResponse;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class IpInfoActivity extends AppCompatActivity implements Serializable {
    private IpDataResponse dataResponse;
    private Toolbar toolbar;
    private TextView ip;
    private TextView city;
    private TextView region;
    private TextView country;
//    private ImageView flag;
    private TextView latitude;
    private TextView longitude;
    private TextView isp;
    private TextView gmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_info);

        ip = findViewById(R.id.ip);
        city = findViewById(R.id.city);
        region = findViewById(R.id.region);
        country = findViewById(R.id.country);
//        flag =findViewById(R.id.flag);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        isp = findViewById(R.id.isp);
        gmt = findViewById(R.id.gmt);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        dataResponse = (IpDataResponse)getIntent().getSerializableExtra(MainActivity.TRANSFER_TAG);

        setData();
    }

    public void setData() {
        int gmtBias = dataResponse.getTimezone().getGMT()/3600;
        String gmtPattern = gmtBias >= 0 ? " (GMT+" + gmtBias + ")": " (GMT" + gmtBias + ")";

        //Ссылка на url флага приходит null
        String flagUrl = dataResponse.getCountryFlag();
        if (flagUrl == null) {
            flagUrl = "null";
            Log.d("checkFlagUrl", flagUrl);
        }

        ip.setText(dataResponse.getIpAddress());
        city.append(" " + dataResponse.getCity());
        region.append(" " + dataResponse.getRegion());
        country.append(" " + dataResponse.getCountry());
//        Picasso.get().load(dataResponse.getCountryFlag()).into(flag);
        latitude.append(" " + dataResponse.getLatitude());
        longitude.append(" " + dataResponse.getLongitude());
        isp.append(" " + dataResponse.getConnection().getISP());
        gmt.append(dataResponse.getTimezone().getId() + gmtPattern);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}