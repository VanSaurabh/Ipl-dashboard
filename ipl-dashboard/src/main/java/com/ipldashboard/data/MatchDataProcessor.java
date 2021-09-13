package com.ipldashboard.data;

import com.ipldashboard.model.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@RequiredArgsConstructor
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(MatchInput matchInput) {
        return MatchDataMapper.mapMatchData(matchInput);
    }
}
