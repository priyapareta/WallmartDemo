package app.com.mvp;


import java.util.ArrayList;
import java.util.HashMap;

import app.com.mvp.mvp.GetRoutesListCallback;
import app.com.mvp.mvp.pojo.RoutesInfo;
import app.com.mvp.mvp.pojo.RoutesResponse;
import retrofit2.Callback;
import rx.Subscription;

/**
 * Created by PrP
 */

public interface INetwork {
    Subscription callServiceForRotes(GetRoutesListCallback callback, boolean showLoading);

}
