package com.example.demo.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

import app.com.mvp.mvp.pojo.Stops;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class RouteLineAdapter extends RecyclerView.Adapter<RouteLineAdapter.TimeLineViewHolder> {

    private List<Stops> mFeedList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RouteLineAdapter(List<Stops> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_timeline, parent, false);

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        Stops timeLineModel = mFeedList.get(position);

       /* if(timeLineModel.getStatus() == OrderStatus.INACTIVE) {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_inactive, android.R.color.darker_gray));
        } else if(timeLineModel.getStatus() == OrderStatus.ACTIVE) {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_active, R.color.colorPrimary));
        } else {*/
            holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.colorPrimary));
//        }


        holder.title.setText(timeLineModel.getName());
    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }

    class TimeLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title)
        TextView title;
        @BindView(R.id.time_marker)
        TimelineView mTimelineView;

        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            mTimelineView.initLine(viewType);
        }
    }
}
