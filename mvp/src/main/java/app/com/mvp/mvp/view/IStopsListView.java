package app.com.mvp.mvp.view;

import java.util.ArrayList;

import app.com.mvp.mvp.pojo.RoutesInfo;

/**
 * Created by PrP.
 */

public interface IStopsListView extends View {
    void onGotStops(ArrayList<RoutesInfo> routesInfos);
    void onNoDataFound();
}
