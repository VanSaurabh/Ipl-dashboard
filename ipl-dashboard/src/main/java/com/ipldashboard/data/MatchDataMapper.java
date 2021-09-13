package com.ipldashboard.data;

import com.ipldashboard.model.Match;

import java.time.LocalDate;

public class MatchDataMapper {

    public static Match mapMatchData(MatchInput matchInput) {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());
        match.setNeutralVenue(matchInput.getNeutral_venue());

        String firstInningsTeam;
        String secondInningsTeam;

        if("bat".equals(matchInput.getToss_decision())) {
            firstInningsTeam = matchInput.getToss_winner();
            secondInningsTeam = matchInput.getToss_winner()
                    .equals(matchInput.getTeam1()) ? matchInput.getTeam1() : matchInput.getTeam2();
        } else {
            secondInningsTeam = matchInput.getToss_winner();
            firstInningsTeam = matchInput.getToss_winner()
                    .equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
        }

        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setEliminator(matchInput.getEliminator());
        match.setMethod(matchInput.getMethod());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }

    private MatchDataMapper() {
        //empty constructor
    }
}
