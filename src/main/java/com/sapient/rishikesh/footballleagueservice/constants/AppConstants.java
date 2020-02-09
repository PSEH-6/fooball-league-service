package com.sapient.rishikesh.footballleagueservice.constants;

public class AppConstants {

    public static String getLeagueUrl(String countryId) {
        return "https://apiv2.apifootball.com/?action=get_leagues&country_id="+countryId+"&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
    }

    public static String getCountryUrl() {
        return "https://apiv2.apifootball.com/?action=get_countries&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
    }

    public static String getStandingsUrl(String leagueId) {
        return "https://apiv2.apifootball.com/?action=get_standings&league_id="+leagueId+"&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
    }
}
