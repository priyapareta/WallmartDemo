package app.com.mvp.mvp;

import java.util.ArrayList;

import app.com.mvp.mvp.pojo.RoutesInfo;
import app.com.mvp.mvp.pojo.RoutesResponse;

public interface GetRoutesListCallback{
        void onSuccess(RoutesResponse<ArrayList<RoutesInfo>> routesInfo);

        void onError(String message);
    }
