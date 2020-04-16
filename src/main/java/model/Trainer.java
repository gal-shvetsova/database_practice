package model;

public class Trainer extends Person{
    private final Sportsman sportsman;

    public Trainer(Person person, Sportsman sportsman) {
        super(person);
        this.sportsman = sportsman;
    }

    public Sportsman getSportsman() {
        return sportsman;
    }
}
