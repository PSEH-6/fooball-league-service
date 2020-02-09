package com.sapient.rishikesh.footballleagueservice.controller;

import com.sapient.rishikesh.footballleagueservice.dto.StandingResponse;
import com.sapient.rishikesh.footballleagueservice.dto.StandingsRequest;
import com.sapient.rishikesh.footballleagueservice.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class AppController {

    @Autowired
    private StandingsService standingsService;

    @PostMapping(path = "/getStanding", produces = "Application/Json", consumes = "Application/Json")
    public StandingResponse getStandings(@RequestBody StandingsRequest standingsRequest) {
        return standingsService.getStandings(standingsRequest.getCountry_name(), standingsRequest.getLeague_name(), standingsRequest.getTeam_name());
    }
}
