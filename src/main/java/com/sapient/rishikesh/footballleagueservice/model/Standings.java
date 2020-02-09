package com.sapient.rishikesh.footballleagueservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Standings {

//    "country_name": "England",
//            "league_id": "148",
//            "league_name": "Premier League",
//            "team_id": "2621",
//            "team_name": "Liverpool",

    private String team_id;
    private String team_name;
    private String overall_league_position;

    public String getOverall_league_position() {
        return overall_league_position;
    }

    public void setOverall_league_position(String overall_league_position) {
        this.overall_league_position = overall_league_position;
    }

    public String getTeam_id() {
        return team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}
