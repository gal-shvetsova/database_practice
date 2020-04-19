package model;

public class AttributeFacility implements Model {
    private final AttributeFacilityKind attributeFacilityKind;
    private final Facility facility;
    private final Integer value;

    public AttributeFacility(AttributeFacilityKind attributeFacilityKind, Facility facility, Integer value) {
        this.attributeFacilityKind = attributeFacilityKind;
        this.facility = facility;
        this.value = value;
    }

    public AttributeFacilityKind getAttributeFacilityKind() {
        return attributeFacilityKind;
    }

    public Facility getFacility() {
        return facility;
    }

    public Integer getValue() {
        return value;
    }


}
