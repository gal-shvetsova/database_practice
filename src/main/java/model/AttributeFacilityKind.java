package model;

import java.util.UUID;

public class AttributeFacilityKind {
    private final String name;
    private final FacilityKind facilityKind;

    public AttributeFacilityKind(String name, FacilityKind facilityKind) {
        this.name = name;
        this.facilityKind = facilityKind;
    }

    public String getName() {
        return name;
    }

    public FacilityKind getFacilityKind() {
        return facilityKind;
    }

    @Override
    public String toString() {
        return name;
    }
}
