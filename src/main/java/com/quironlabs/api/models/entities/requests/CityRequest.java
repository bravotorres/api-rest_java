package com.quironlabs.api.models.entities.requests;


public class CityRequest {
    private String city;
    private String state;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CityRequest [city=" + city + ", state=" + state + "]";
    }
}
