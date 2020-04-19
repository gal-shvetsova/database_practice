package model;

public class SportsmanCharacteristic implements Model{
    private final Person trainer;
    private final Sport sport;
    private final Person sportsman;
    private final Club club;
    private final Integer characteristic;

    public SportsmanCharacteristic(Person trainer, Sport sport, Person sportsman, Club club, Integer characteristic) {
        this.trainer = trainer;
        this.sport = sport;
        this.sportsman = sportsman;
        this.club = club;
        this.characteristic = characteristic;
    }

    public Person getTrainer() {
        return trainer;
    }

    public Sport getSport() {
        return sport;
    }

    public Person getSportsman() {
        return sportsman;
    }

    public Club getClub() {
        return club;
    }

    public Integer getCategory() {
        return characteristic;
    }
}
