package com.ipldashboard.repository;

import com.ipldashboard.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :startDate and :endDate order by date desc")
    List<Match> getMatchByTeamBetweenDates(@Param("teamName") String teamName,
                                           @Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);
}
