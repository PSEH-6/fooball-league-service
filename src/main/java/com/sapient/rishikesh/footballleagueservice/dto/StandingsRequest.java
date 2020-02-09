package com.sapient.rishikesh.footballleagueservice.dto;

public class StandingsRequest {

    private String country_name;
    private String league_name;
    private String team_name;

    public String getCountry_name() {
        return country_name;
    }

    public String getLeague_name() {
        return league_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public StandingsRequest(String country_name, String league_name, String team_name) {
        this.country_name = country_name;
        this.league_name = league_name;
        this.team_name = team_name;
    }
}
