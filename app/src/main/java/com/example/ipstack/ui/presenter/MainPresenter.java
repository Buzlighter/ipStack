package com.example.ipstack.ui.presenter;

import com.example.ipstack.R;
import com.example.ipstack.data.util.ApiUtils;
import com.example.ipstack.ui.view.MainView;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainPresenter {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MainView view;
    private String ip;

    public MainPresenter(MainView view, String ip) {
        this.view = view;
        this.ip = ip;
    }

    public void getIpInfo() {
        compositeDisposable.add(
                ApiUtils.getApiService()
                .getIpInfo(ip)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> view.showProgress())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            view.showData(response);
                            compositeDisposable.dispose();
                        },
                        throwable -> view.showError(R.string.request_error)
                )
        );
    }

    public void getOwnIp() {
        compositeDisposable.add(
                ApiUtils.getApiService()
                        .checkOwnIp()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> view.showProgress())
                        .subscribe(
                                response -> {
                                    view.showData(response);
                                    compositeDisposable.dispose();
                                    },
                                throwable -> view.showError(R.string.request_error)

                        ));
    }
}
