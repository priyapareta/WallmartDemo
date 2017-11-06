package com.example.demo.presenter;

import android.content.Context;

import com.example.demo.network.NetworkCall;

import java.util.ArrayList;

import app.com.mvp.mvp.GetRoutesListCallback;
import app.com.mvp.mvp.pojo.RoutesInfo;
import app.com.mvp.mvp.pojo.RoutesResponse;
import app.com.mvp.mvp.presenter.IStopsListPresenter;
import app.com.mvp.mvp.view.IStopsListView;
import app.com.mvp.mvp.view.View;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Priya on 06-11-2017.
 */

public class RoutesInfoPresenter implements IStopsListPresenter, GetRoutesListCallback{
    IStopsListView stopsListView;
    CompositeSubscription subscription;
    Context context;

    @Override
    public void attachView(View view, Context context) {
        stopsListView = (IStopsListView) view;
        this.context = context;
        subscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        subscription.unsubscribe();
    }

    @Override
    public void callServiceForStops(Context context) {
        NetworkCall networkCall = NetworkCall.getRetrofit(context);
        subscription.add(networkCall.callServiceForRotes(this, true));
    }

    @Override
    public void onSuccess(RoutesResponse<ArrayList<RoutesInfo>> routesInfo) {
        stopsListView.onGotStops(routesInfo.getRoutes());
    }

    @Override
    public void onError(String message) {
        stopsListView.onError(message);
    }
}
