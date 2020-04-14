package model;

public enum Role implements HasId<String> {
    ADMIN("ADMIN"),
    SPORTSMAN("SPORTSMAN"),
    TRAINER("TRAINER"),
    ORGANIZER("ORGANIZER");

    String id;

    Role(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
