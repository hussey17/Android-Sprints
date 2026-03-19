package com.example.lab08;
import java.util.ArrayList;
import java.util.List;
public class CustomList {
    private List<City> cities;
    public CustomList() {
        this.cities = new ArrayList<>();
    }
    public void addCity(City city) {
        cities.add(city);
    }
    public boolean hasCity(City city) { // Uncommented the hasCity method --> should run now //
        return cities.contains(city);
    }
}