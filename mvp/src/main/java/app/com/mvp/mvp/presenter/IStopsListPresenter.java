package app.com.mvp.mvp.presenter;

import android.content.Context;

import app.com.mvp.mvp.view.View;

/**
 * Created by PrP
 */

public interface IStopsListPresenter<T extends View> extends Presenter<T> {
    void callServiceForStops(Context context);
}
