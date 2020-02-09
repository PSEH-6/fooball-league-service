package com.sapient.rishikesh.footballleagueservice.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.rishikesh.footballleagueservice.constants.AppConstants;
import com.sapient.rishikesh.footballleagueservice.model.League;
import com.sapient.rishikesh.footballleagueservice.model.Standings;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StandingsRepository {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Standings> getLeagues(String leagueId) throws RestClientException {
        ObjectMapper mapper = new ObjectMapper();
        List objects = restTemplate.getForObject(AppConstants.getStandingsUrl(leagueId), List.class);
        List<Standings> standings =  mapper.convertValue(objects, new TypeReference<List<Standings>>(){});
        return standings;
    }
}
