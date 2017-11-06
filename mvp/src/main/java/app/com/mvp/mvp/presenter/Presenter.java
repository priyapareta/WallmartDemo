package app.com.mvp.mvp.presenter;


import android.content.Context;

import app.com.mvp.mvp.view.View;

/**
 * Created by PrP
 */
public interface Presenter<T extends View> {

    void attachView(T view, Context context);

    void onStop();
}
