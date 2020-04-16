package model;

public class Sportsman extends Person {
    private final Club club;
    private final Sport sport;
    private final Integer category;
    private final Person trainer;

    public Sportsman(Person person, Club club, Sport sport, Integer category, Person trainer) {
        super(person);
        this.club = club;
        this.sport = sport;
        this.category = category;
        this.trainer = trainer;
    }

    public Club getClub() {
        return club;
    }

    public Sport getSport() {
        return sport;
    }

    public Person getTrainer() {
        return trainer;
    }

    public Integer getCategory() {
        return category;
    }
}