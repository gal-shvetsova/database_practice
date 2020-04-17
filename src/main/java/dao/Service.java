package dao;

import model.*;

import java.util.List;

public class Service {
    public static Person signIn(String login, String password) {
        return PersonDao.getByLoginAndPassword(login, password);
    }

    public static List<Club> getAllClubs() {
        return ClubDao.getAll();
    }

    public static void createClub(Club club) {
        ClubDao.insert(club);
    }

    public static void updateClub(Club oldClub, Club newClub) {
        ClubDao.update(oldClub, newClub);
    }

    public static void createSport(Sport sport) {
        SportDao.insert(sport);
    }

    public static void updateSport(Sport oldSport, Sport newSport) {
        SportDao.update(oldSport, newSport);
    }

    public static List<Sport> getAllSports() {
        return SportDao.getAll();
    }

    public static List<FacilityKind> getAllFacilityKinds() {
        return FacilityKindDao.getAll();
    }

    public static void createFacilityKind(FacilityKind facilityKind) {
        FacilityKindDao.insert(facilityKind);
    }

    public static void updateFacilityKind(FacilityKind oldFacilityKind, FacilityKind newFacilityKind) {
        FacilityKindDao.update(oldFacilityKind, newFacilityKind);
    }

    public static List<Competition> getCompetitions() {
        return CompetitionDao.getAll();
    }

    public static void createCompetition(Competition competition) {
        CompetitionDao.create(competition);
    }

    public static void updateCompetition(Competition competition) {
        CompetitionDao.update(competition);
    }

    public static List<Facility> getAllFacilities() {
        return FacilityDao.getAll();
    }

    public static void createFacility(Facility facility) {
        FacilityDao.create(facility);
    }

    public static void updateFacility(Facility oldFacility, Facility newFacility) {
        FacilityDao.update(oldFacility, newFacility);
    }

    private static List<Person> getAllPersonsByRole(Role role){
        return PersonDao.getAllByRole(role);
    }

    public static List<Sportsman> getAllSportsmen() {
        return SportsmanDao.getAll();
    }

    public static List<Person> getAllTrainers() {
        return getAllPersonsByRole(Role.TRAINER);
    }

    public static List<Person> getAllOrganizers(){
        return getAllPersonsByRole(Role.ORGANIZER);
    }

    public static void updateSportsman(Sportsman oldSportsman, Sportsman newSportsman) {
        SportsmanDao.update(oldSportsman, newSportsman);
    }

    public static List<Person> getAllPerson() {
        return PersonDao.getAll();
    }

    public static void updatePerson(Person person){
        PersonDao.updatePerson(person);
    }

    public static void registerSportsman(Sportsman sportsman){
        PersonDao.registerSportsman(sportsman);
    }

    public static void registerPerson(Person person) {
        PersonDao.registerPerson(person);
    }

    public static List<AttributeFacilityKind> getAllAttributeFacilityKinds(){
        return AttributeFacilityKindDao.getAll();
    }

    public static void updateAttributeFacilityKind(AttributeFacilityKind oldEntity, AttributeFacilityKind newEntity) {
        AttributeFacilityKindDao.update(oldEntity, newEntity);
    }

    public static void createAttributeFacilityKind(AttributeFacilityKind attributeFacilityKind){
        AttributeFacilityKindDao.create(attributeFacilityKind);
    }
}
