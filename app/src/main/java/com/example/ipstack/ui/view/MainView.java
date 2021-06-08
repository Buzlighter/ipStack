package com.example.ipstack.ui.view;

import androidx.annotation.NonNull;

import com.example.ipstack.data.model.IpDataResponse;

public interface MainView {

    void showProgress();

    void showData(IpDataResponse response);

    void showError(int message);
}
