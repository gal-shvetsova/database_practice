package model;

import java.time.Instant;

public class Competition {
    private final Integer id;
    private final String name;
    private final Sport sport;
    private final Facility facility;
    private final Instant startDate;
    private final Instant finishDate;

    public Competition(Integer id, String name, Sport sport, Facility facility, Instant startDate, Instant finishDate) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.facility = facility;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Integer getId() {
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

    @Override
    public String toString() {
        return name;
    }
}
