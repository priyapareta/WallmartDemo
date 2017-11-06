package com.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.demo.adapter.RouteListAdapter;
import com.example.demo.presenter.RoutesInfoPresenter;

import java.util.ArrayList;

import app.com.mvp.mvp.pojo.RoutesInfo;
import app.com.mvp.mvp.view.IStopsListView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IStopsListView{

    RoutesInfoPresenter presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private View.OnClickListener itemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, RoutesDetailActivity.class);
            intent.putExtra("routeInfo", (RoutesInfo)v.getTag());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializePresenter();

        LinearLayoutManager ll = new LinearLayoutManager(this);
        ll.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(ll);

        presenter.callServiceForStops(this);
    }

    private void showToast(String message){
        Toast.makeText(this, message , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String message) {
        showToast(message);
    }

    @Override
    public void initializePresenter() {
        presenter = new RoutesInfoPresenter();
        presenter.attachView(this, this);
    }

    @Override
    public void onGotStops(ArrayList<RoutesInfo> routesInfos) {
        if (routesInfos != null && routesInfos.size() > 0){
            RouteListAdapter routeListAdapter = new RouteListAdapter(this, routesInfos, itemClick);
            recyclerView.setAdapter(routeListAdapter);
        }else onError("No data found");
    }

    @Override
    public void onNoDataFound() {
        onError("No data found");
    }
}
