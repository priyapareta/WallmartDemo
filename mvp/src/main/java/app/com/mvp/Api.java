package app.com.mvp;




import java.util.ArrayList;

import app.com.mvp.mvp.pojo.RoutesInfo;
import app.com.mvp.mvp.pojo.RoutesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by PrP
 */

public interface Api {

    /*you can pass headers from here also*/
    /*@Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })*/

    @GET("v2/5808f00d10000005074c6340")
    Observable<RoutesResponse<ArrayList<RoutesInfo>>> callServiceForRoutes();





}
