package com.ipldashboard.service;

import com.ipldashboard.model.Match;
import com.ipldashboard.model.Team;
import com.ipldashboard.repository.TeamRepository;
import com.ipldashboard.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamByName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }

    public List<Match> getMatchesForTeam(String teamName, int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return matchRepository.getMatchByTeamBetweenDates(teamName, startDate, endDate);
    }
}
