package com.sapient.rishikesh.footballleagueservice.service.impl;

import com.sapient.rishikesh.footballleagueservice.dto.StandingResponse;
import com.sapient.rishikesh.footballleagueservice.model.Country;
import com.sapient.rishikesh.footballleagueservice.model.League;
import com.sapient.rishikesh.footballleagueservice.model.Standings;
import com.sapient.rishikesh.footballleagueservice.repositories.CountryRepository;
import com.sapient.rishikesh.footballleagueservice.repositories.LeaguesRepository;
import com.sapient.rishikesh.footballleagueservice.repositories.StandingsRepository;
import com.sapient.rishikesh.footballleagueservice.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Component
public class StandingsServiceImpl implements StandingsService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LeaguesRepository leaguesRepository;

    @Autowired
    private StandingsRepository standingsRepository;

    @Override
    public StandingResponse getStandings(String countryName, String leagueName, String teamName) throws RestClientException {
        Country country = getCountryByName(countryName);

        if(country.getCountry_id()==null || country.getCountry_id().isEmpty()) {
            StandingResponse standingResponse = new StandingResponse();
            standingResponse.setError("Invalid Country Name");
            return standingResponse;
        }

        League league = getLeagueByNameAndCountry(leagueName, country);


        if(league.getLeague_id()==null || league.getLeague_id().isEmpty()) {
            StandingResponse standingResponse = new StandingResponse();
            standingResponse.setError("Invalid League Name");
            return standingResponse;
        }

        Standings standings = getStandingsByLeagueAndTeamName(teamName, league);


        if(standings.getTeam_id()==null || standings.getTeam_id().isEmpty()) {
            StandingResponse standingResponse = new StandingResponse();
            standingResponse.setError("Invalid Team Name");
            return standingResponse;
        }


        return mapToStandingsResponse(country, league, standings);

    }


    private StandingResponse mapToStandingsResponse (Country country, League league, Standings standings) {
        StandingResponse standingResponse = new StandingResponse();
        standingResponse.setCountry_id(country.getCountry_id());
        standingResponse.setCountry_name(country.getCountry_name());
        standingResponse.setLeague_id(league.getLeague_id());
        standingResponse.setLeague_name(league.getLeague_name());
        standingResponse.setTeam_id(standings.getTeam_id());
        standingResponse.setTeam_name(standings.getTeam_name());
        standingResponse.setOverall_league_position(standings.getOverall_league_position());
        return standingResponse;
    }

    private Country getCountryByName (String countryName) throws RestClientException {
        List<Country> countryList = null;
        countryList = countryRepository.getCountry();
        Country country = new Country();

//        countryList.parallelStream().forEach(c -> {
//            if(c.getCountry_name().equalsIgnoreCase(countryName)){
//                country.setCountry_id(c.getCountry_id());
//                country.setCountry_name(c.getCountry_name());
//            }
//        });

        for(Country c : countryList) {
            if(c.getCountry_name().equalsIgnoreCase(countryName)){
                country.setCountry_id(c.getCountry_id());
                country.setCountry_name(c.getCountry_name());
                break;
            }
        }
        return country;
    }

    private League getLeagueByNameAndCountry (String leagueName, Country country) throws RestClientException {
        List<League> leagueList = leaguesRepository.getLeagues(country.getCountry_id());
        League league = new League();

//        leagueList.parallelStream().forEach(l -> {
//            if(l.getLeague_name().equalsIgnoreCase(leagueName)){
//                league.setLeague_id(l.getLeague_id());
//                league.setLeague_name(l.getLeague_name());
//            }
//        });

        for(League l : leagueList) {
            if(l.getLeague_name().equalsIgnoreCase(leagueName)){
                league.setLeague_id(l.getLeague_id());
                league.setLeague_name(l.getLeague_name());
                break;
            }
        }
        return league;
    }

    private Standings getStandingsByLeagueAndTeamName(String teamName, League league) throws RestClientException {
        List<Standings> standingsList = standingsRepository.getLeagues(league.getLeague_id());
        Standings standings = new Standings();

//        standingsList.parallelStream().forEach(s -> {
//            if(s.getTeam_name().equalsIgnoreCase(teamName)){
//                standings.setTeam_id(s.getTeam_id());
//                standings.setTeam_name(s.getTeam_name());
//                standings.setOverall_league_position(s.getOverall_league_position());
//            }
//        });

        for (Standings s : standingsList) {
            if(s.getTeam_name().equalsIgnoreCase(teamName)){
                standings.setTeam_id(s.getTeam_id());
                standings.setTeam_name(s.getTeam_name());
                standings.setOverall_league_position(s.getOverall_league_position());
                break;
            }
        }
        return standings;
    }


}
