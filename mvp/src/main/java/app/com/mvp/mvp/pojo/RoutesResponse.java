package app.com.mvp.mvp.pojo;


import app.com.mvp.mvp.Model;

/**
 * Created by PrP
 */

public class RoutesResponse<T> implements Model {

    T routes;

    public T getRoutes() {
        return routes;
    }

    public void setRoutes(T routes) {
        this.routes = routes;
    }
}
