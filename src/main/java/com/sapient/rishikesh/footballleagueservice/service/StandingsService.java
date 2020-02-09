package com.sapient.rishikesh.footballleagueservice.service;

import com.sapient.rishikesh.footballleagueservice.dto.StandingResponse;
import org.springframework.web.client.RestClientException;

public interface StandingsService {

    StandingResponse getStandings(String countryName, String leagueName, String teamName) throws RestClientException;
}
