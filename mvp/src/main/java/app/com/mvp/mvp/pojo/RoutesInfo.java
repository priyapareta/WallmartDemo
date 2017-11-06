package app.com.mvp.mvp.pojo;

import java.util.ArrayList;

import app.com.mvp.mvp.Model;

/**
 * Created by Priya on 03-11-2017.
 */

public class RoutesInfo implements Model {
    private String id;
    private String name;
    private String description;
    private String accessible;
    private String image;
    private ArrayList<Stops> stops;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessible() {
        return accessible;
    }

    public void setAccessible(String accessible) {
        this.accessible = accessible;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Stops> getStops() {
        return stops;
    }

    public void setStops(ArrayList<Stops> stops) {
        this.stops = stops;
    }
}

