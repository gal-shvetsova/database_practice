package model;

public class FacilityKind implements Model {
    private final String name;

    public FacilityKind(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
