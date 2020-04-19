package model;

import java.util.List;
import java.util.Map;

public class CompetitionResults implements Model{
    private final Map<Integer, Integer> results;
    private final List<Person> participants;
    private final Competition competition;

    public CompetitionResults(Map<Integer, Integer> results, List<Person> participants, Competition competition) {
        this.results = results;
        this.participants = participants;
        this.competition = competition;
    }

    public Map<Integer, Integer> getResults() {
        return results;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public Competition getCompetition() {
        return competition;
    }
}
