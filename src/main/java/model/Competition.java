package model;

import java.time.Instant;
import java.util.UUID;

public class Competition implements Model{
    private final UUID id;
    private final String name;
    private final Sport sport;
    private final Facility facility;
    private final Instant startDate;
    private final Instant finishDate;
    private final Person organizer;

    public Competition(UUID id, String name, Sport sport, Facility facility, Instant startDate, Instant finishDate, Person organizer) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.facility = facility;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.organizer = organizer;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sport getSport() {
        return sport;
    }

    public Facility getFacility() {
        return facility;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getFinishDate() {
        return finishDate;
    }

    public Person getOrganizer() {
        return organizer;
    }

    @Override
    public String toString() {
        return name;
    }
}
