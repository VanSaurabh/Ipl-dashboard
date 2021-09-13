package com.ipldashboard.data;

import com.ipldashboard.model.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
        }

        Map<String, Team> teamMap = new HashMap<>();
        entityManager.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                .getResultList()
                .stream()
                .map(e -> new Team((String) e[0], (long) e[1]))
                .forEach(team -> teamMap.put(team.getTeamName(), team));

        entityManager.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                .getResultList()
                .forEach(e -> {
                    Team team = teamMap.get( (String) e[0]);
                    team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                });
        entityManager.createQuery("select m.winner, count(*) from Match m group by m.winner", Object[].class)
                .getResultList()
                .forEach(e -> {
                    Team team = teamMap.get( (String) e[0]);
                    if (Objects.nonNull(team)) {
                        team.setTotalWins( (long) e[1]);
                    }
                });

        teamMap.values().forEach(entityManager::persist);
        teamMap.values().forEach(team -> log.info(team.toString()));

    }
}
