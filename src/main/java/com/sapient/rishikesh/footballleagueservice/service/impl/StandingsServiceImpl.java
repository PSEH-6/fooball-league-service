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
        List<Country> countryList = null;
        countryList = countryRepository.getCountry();
        Country country = new Country();

        countryList.parallelStream().forEach(c -> {
            if(c.getCountry_name().equalsIgnoreCase(countryName)){
                country.setCountry_id(c.getCountry_id());
                country.setCountry_name(c.getCountry_name());
            }
        });

        if(country.getCountry_id()==null || country.getCountry_id().isEmpty()) {
            StandingResponse standingResponse = new StandingResponse();
            standingResponse.setError("Invalid Country Name");
            return standingResponse;
        }

        List<League> leagueList = leaguesRepository.getLeagues(country.getCountry_id());
        League league = new League();

        leagueList.parallelStream().forEach(l -> {
            if(l.getLeague_name().equalsIgnoreCase(leagueName)){
                league.setCountry_id(l.getCountry_id());
                league.setCountry_name(l.getCountry_name());
                league.setLeague_id(l.getLeague_id());
                league.setLeague_name(l.getLeague_name());
            }
        });

        if(league.getLeague_id()==null || league.getLeague_id().isEmpty()) {
            StandingResponse standingResponse = new StandingResponse();
            standingResponse.setError("Invalid League Name");
            return standingResponse;
        }

        List<Standings> standingsList = standingsRepository.getLeagues(league.getLeague_id());
        Standings standings = new Standings();
        standingsList.parallelStream().forEach(s -> {
            if(s.getTeam_name().equalsIgnoreCase(teamName)){
                standings.setCountry_name(s.getCountry_name());
                standings.setLeague_id(s.getLeague_id());
                standings.setLeague_name(s.getLeague_name());
                standings.setTeam_id(s.getTeam_id());
                standings.setTeam_name(s.getTeam_name());
                standings.setOverall_league_position(s.getOverall_league_position());
            }
        });

        if(standings.getTeam_id()==null || standings.getTeam_id().isEmpty()) {
            StandingResponse standingResponse = new StandingResponse();
            standingResponse.setError("Invalid Team Name");
            return standingResponse;
        }

        StandingResponse standingResponse = new StandingResponse();
        standingResponse.setCountry_id(country.getCountry_id());
        standingResponse.setCountry_name(standings.getCountry_name());
        standingResponse.setLeague_id(standings.getLeague_id());
        standingResponse.setLeague_name(standings.getLeague_name());
        standingResponse.setTeam_id(standings.getTeam_id());
        standingResponse.setTeam_name(standings.getTeam_name());
        standingResponse.setOverall_league_position(standings.getOverall_league_position());
        return standingResponse;


    }
}
