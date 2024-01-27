package com.quironlabs.api.models.entities;


public class City {
    private Integer id;
    private String name;
    private String state;

    public City() {
        this(0, "", "");
    }

    public City(Integer id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + ", state=" + state + "]";
    }
}
