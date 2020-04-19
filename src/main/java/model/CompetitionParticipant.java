package model;

public class CompetitionParticipant implements Model{
    private final Person participant;
    private final Competition competition;
    private final Integer result;

    public CompetitionParticipant(Person participant, Competition competition, Integer result) {
        this.participant = participant;
        this.competition = competition;
        this.result = result;
    }

    public Person getParticipant() {
        return participant;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Integer getResult() {
        return result;
    }

    @Override
    public String toString() {
        return participant.toString();
    }
}
