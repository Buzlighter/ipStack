 package com.example.ipstack.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ipstack.R;
import com.example.ipstack.data.model.IpDataResponse;
import com.example.ipstack.data.util.CheckNetworkConnection;
import com.example.ipstack.data.util.ConnectionState;
import com.example.ipstack.ui.presenter.MainPresenter;
import com.example.ipstack.ui.view.MainView;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity implements MainView {
    public final static String TRANSFER_TAG = "IpDataResponseKey";
    private Button checkIp;
    private Button openWebView;
    private EditText enterId;
    private String ip;
    private ProgressBar progressBar;
    private IpDataResponse data;

    public MainPresenter getMainPresenter() {
        return new MainPresenter(this, ip);
    }


    private final View.OnClickListener onClickListenerEnter = v -> {
        tryToRequest();
    };

    private void tryToRequest() {
        if (!ConnectionState.isNetworkConnected) {
            showAlert();
        }
        else {
            ip = enterId.getText().toString();
            if(ip.isEmpty()) {
                getMainPresenter().getOwnIp();
            }
            else if(Patterns.IP_ADDRESS.matcher(ip).matches()) {
                getMainPresenter().getIpInfo();
            }
            else {
                Toast.makeText(this,R.string.invalid_input, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private final View.OnClickListener onClickListenerWeb = v -> {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkIp = findViewById(R.id.btn_check_ip);
        enterId = findViewById(R.id.enter_id);
        progressBar = findViewById(R.id.progressBar);
        openWebView = findViewById(R.id.btn_open_web);

        CheckNetworkConnection network = new CheckNetworkConnection(getApplicationContext());
        network.registerNetworkCallback();

        checkIp.setOnClickListener(onClickListenerEnter);
        openWebView.setOnClickListener(onClickListenerWeb);
    }

    @Override
    public void showProgress() {
        checkIp.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData( @NotNull IpDataResponse response) {
        data = response;
        checkIp.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(MainActivity.this, IpInfoActivity.class);
        intent.putExtra(TRANSFER_TAG, data);
        startActivity(intent);
    }

    @Override
    public void showError(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(R.string.dialog_check)
                .setTitle(R.string.dialog_message);
        builder.setPositiveButton(R.string.dialog_answer, (dialog, id) -> {
                tryToRequest();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}