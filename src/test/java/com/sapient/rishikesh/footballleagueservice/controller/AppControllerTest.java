package com.sapient.rishikesh.footballleagueservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.rishikesh.footballleagueservice.dto.StandingsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


class AppControllerTest {

    private StandingsRequest standingsRequest;
    @Autowired
    private MockMvc mockMvc;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        standingsRequest = new StandingsRequest("England","Championship","West Brom");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getStandings() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/getStanding")
                .content((new ObjectMapper()).writeValueAsString(standingsRequest))
                .contentType("Application/Json")
                .accept("Application/Json")
        );
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.overall_league_position").exists());

    }
}