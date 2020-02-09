package com.sapient.rishikesh.footballleagueservice.model;

public class Country {

    private String country_id;
    private String country_name;

    public String getCountry_id() {
        return country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public Country setCountry_id(String country_id) {
        this.country_id = country_id;
        return this;
    }

    public Country setCountry_name(String country_name) {
        this.country_name = country_name;
        return this;
    }
}
