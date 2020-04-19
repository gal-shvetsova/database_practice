package model;

public class Facility implements Model{
    private final String name;
    private final String address;
    private final FacilityKind kind;

    public Facility(String name, String address, FacilityKind kind) {
        this.name = name;
        this.address = address;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public FacilityKind getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return name;
    }
}
