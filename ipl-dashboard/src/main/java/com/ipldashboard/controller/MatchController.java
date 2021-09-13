package com.ipldashboard.controller;

import com.ipldashboard.model.Match;
import com.ipldashboard.model.Team;
import com.ipldashboard.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/team")
    public ResponseEntity<List<Team>> getAllTeam() {
        return ResponseEntity.ok(matchService.getAllTeams());
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String teamName) {
        return ResponseEntity.ok(matchService.getTeamByName(teamName));
    }

    @GetMapping("/team/{teamName}/matches")
    public ResponseEntity<List<Match>> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        return ResponseEntity.ok(matchService.getMatchesForTeam(teamName, year));
    }
}
