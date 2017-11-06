package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.adapter.RouteLineAdapter;
import com.squareup.picasso.Picasso;

import app.com.mvp.mvp.pojo.RoutesInfo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RoutesDetailActivity extends AppCompatActivity {

    RoutesInfo routesInfo;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.route_image)
    ImageView routeImage;
    @BindView(R.id.route_name_text)
    TextView routeNameTxt;
    @BindView(R.id.acceccible_image)
    ImageView accessibleImage;
    @BindView(R.id.route_detail_text)
    TextView routeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_detail);
        ButterKnife.bind(this);
        routesInfo = (RoutesInfo) getIntent().getSerializableExtra("routeInfo");

        if (routesInfo == null)
            finish();

        if (routesInfo.getImage() != null)
            Picasso.with(this).load(routesInfo.getImage()).into(routeImage);
        routeNameTxt.setText(routesInfo.getName());
        if (routesInfo.getAccessible() != null && routesInfo.getAccessible().equalsIgnoreCase("true")){
            accessibleImage.setVisibility(View.VISIBLE);
        }else accessibleImage.setVisibility(View.GONE);

        routeDetails.setText(routesInfo.getDescription());

        LinearLayoutManager ll = new LinearLayoutManager(this);
        ll.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(ll);

        if (routesInfo.getStops() != null && routesInfo.getStops().size() > 0)
            recyclerView.setAdapter(new RouteLineAdapter(routesInfo.getStops()));
    }
}
