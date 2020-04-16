package model;

public enum Role implements HasId<String> {
    ADMIN("ADMIN"),  //todo: add constraint for it
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
