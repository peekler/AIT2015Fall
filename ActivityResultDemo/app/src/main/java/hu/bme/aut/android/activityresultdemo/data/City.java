package hu.bme.aut.android.activityresultdemo.data;

import java.io.Serializable;

/**
 * Created by peter on 2015. 11. 05..
 */
public class City implements Serializable{
    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
