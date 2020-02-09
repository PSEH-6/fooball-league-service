package com.sapient.rishikesh.footballleagueservice.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.rishikesh.footballleagueservice.constants.AppConstants;
import com.sapient.rishikesh.footballleagueservice.model.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@Service
public class CountryRepository {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Country> getCountry() throws RestClientException {
        ObjectMapper mapper = new ObjectMapper();
        List objects =  restTemplate.getForObject(AppConstants.getCountryUrl(), List.class);
        List<Country> countries =  mapper.convertValue(objects, new TypeReference<List<Country>>(){});
        return countries;
    }



}
