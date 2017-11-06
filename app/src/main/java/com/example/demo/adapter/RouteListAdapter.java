package com.example.demo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.com.mvp.mvp.pojo.RoutesInfo;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Prp on 25-07-2017.
 */

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.ItemViewHolder> {
    private Activity mContext;
    private ArrayList<RoutesInfo> listing = new ArrayList<>();
    View.OnClickListener itemClick;

    public RouteListAdapter(Activity mContext, ArrayList<RoutesInfo> listing, View.OnClickListener itemClick){
        this.mContext = mContext;
        this.listing = listing;
        this.itemClick = itemClick;
    }

    @Override
    public RouteListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_route,parent,false);
        return new ItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RouteListAdapter.ItemViewHolder holder, int position) {
        RoutesInfo routesInfo = listing.get(position);
        holder.routeNameTxt.setText(routesInfo.getName());
        if (routesInfo.getImage() != null)
            Picasso.with(mContext).load(routesInfo.getImage()).into(holder.image);

        holder.rootLL.setTag(routesInfo);
        holder.rootLL.setOnClickListener(itemClick);

    }


    @Override
    public int getItemCount() {
        return listing.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.route_name_text)
        TextView routeNameTxt;
        @BindView(R.id.route_image)
        ImageView image;
        @BindView(R.id.rootLL)
        LinearLayout rootLL;

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
