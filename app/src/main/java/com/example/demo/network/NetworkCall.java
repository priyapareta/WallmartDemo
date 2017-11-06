package com.example.demo.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.demo.BuildConfig;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import app.com.mvp.Api;
import app.com.mvp.INetwork;
import app.com.mvp.mvp.GetRoutesListCallback;
import app.com.mvp.mvp.pojo.RoutesInfo;
import app.com.mvp.mvp.pojo.RoutesResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by PrP
 */

public class NetworkCall implements INetwork {
    private Context context;
    private ProgressDialog progressDialog;
    private Api api;

    private NetworkCall(Context context) {
        this.context = context;
    }

    public static NetworkCall getRetrofit(Context context) {
        NetworkCall networkCall = new NetworkCall(context);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        httpClient.addInterceptor(interceptor);

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        // prepare call in Retrofit 2.0

        networkCall.api = retrofit.create(Api.class);
        return networkCall;
    }

    private void showLoading() {
        try {
            dismissLoading();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Call this function manually in callback responce
    * Note: If you are calling two or more services one after other then call this method in last service callback
    * */
    public void dismissLoading() {
        try {
            if (progressDialog != null) {
                progressDialog.cancel();
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Subscription getRoutesList(final GetRoutesListCallback callback) {

        return api.callServiceForRoutes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends RoutesResponse<ArrayList<RoutesInfo>>>>() {
                    @Override
                    public Observable<? extends RoutesResponse<ArrayList<RoutesInfo>>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<RoutesResponse<ArrayList<RoutesInfo>>>() {
                    @Override
                    public void onCompleted() {
                        dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                        dismissLoading();

                    }

                    @Override
                    public void onNext(RoutesResponse<ArrayList<RoutesInfo>> cityListResponse) {
                        callback.onSuccess(cityListResponse);
                        dismissLoading();

                    }
                });
    }

    @Override
    public Subscription callServiceForRotes(GetRoutesListCallback callback, boolean showLoading) {
        if (showLoading) {
            showLoading();
        }
        return getRoutesList(callback);
//        Observable<RoutesResponse<ArrayList<RoutesInfo>>> observable = api.callServiceForRoutes();
        //asynchronous call
//        call.enqueue(callback);
    }
}


