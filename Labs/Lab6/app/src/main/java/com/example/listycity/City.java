package com.example.listycity;
public class City implements Comparable<City> {
    private String city;
    private String province;
    City(String city, String province) {
        this.city = city;
        this.province = province;
    }
    String getCityName() {
        return this.city;
    }
    String getProvinceName() {
        return this.province;
    }
    @Override
    public int compareTo(City o) {
        return this.city.compareTo(o.getCityName());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City otherCity = (City) o;
        return this.city.equals(otherCity.getCityName())
                && this.province.equals(otherCity.getProvinceName());
    }
    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + province.hashCode();
        return result;
    }
}
