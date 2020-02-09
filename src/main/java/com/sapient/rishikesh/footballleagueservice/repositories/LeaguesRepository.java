package com.sapient.rishikesh.footballleagueservice.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.rishikesh.footballleagueservice.constants.AppConstants;
import com.sapient.rishikesh.footballleagueservice.model.Country;
import com.sapient.rishikesh.footballleagueservice.model.League;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LeaguesRepository {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<League> getLeagues(String countryId) throws RestClientException {
        ObjectMapper mapper = new ObjectMapper();
        List objects = restTemplate.getForObject(AppConstants.getLeagueUrl(countryId), List.class);
        List<League> leagues =  mapper.convertValue(objects, new TypeReference<List<League>>(){});

        return leagues;
    }
}
