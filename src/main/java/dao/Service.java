package dao;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import connection.JdbcConnection;
import model.*;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

public class Service {
    private static JdbcConnection connection;

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

    private static List<Person> getAllPersonsByRole(Role role) {
        return PersonDao.getAllByRole(role);
    }

    public static List<Sportsman> getAllSportsmen() {
        return SportsmanDao.getAll();
    }

    public static List<Person> getAllTrainers() {
        return getAllPersonsByRole(Role.TRAINER);
    }

    public static List<Person> getAllOrganizers() {
        return getAllPersonsByRole(Role.ORGANIZER);
    }

    public static void updateSportsman(Sportsman oldSportsman, Sportsman newSportsman) {
        SportsmanDao.update(oldSportsman, newSportsman);
    }

    public static List<Person> getAllPerson() {
        return PersonDao.getAll();
    }

    public static void updatePerson(Person person) {
        PersonDao.updatePerson(person);
    }

    public static void registerSportsman(Sportsman sportsman) {
        PersonDao.registerSportsman(sportsman);
    }

    public static void registerPerson(Person person) {
        PersonDao.registerPerson(person);
    }

    public static List<AttributeFacilityKind> getAllAttributeFacilityKinds() {
        return AttributeFacilityKindDao.getAll();
    }

    public static void updateAttributeFacilityKind(AttributeFacilityKind oldEntity, AttributeFacilityKind newEntity) {
        AttributeFacilityKindDao.update(oldEntity, newEntity);
    }

    public static void createAttributeFacilityKind(AttributeFacilityKind attributeFacilityKind) {
        AttributeFacilityKindDao.create(attributeFacilityKind);
    }

    public static boolean deleteAttributeFacilityKind(AttributeFacilityKind attributeFacilityKind) {
        return AttributeFacilityKindDao.delete(attributeFacilityKind);
    }

    public static boolean deleteClub(Club club) {
        return ClubDao.delete(club);
    }

    public static boolean deleteCompetition(Competition competition) {
        return CompetitionDao.delete(competition);
    }

    public static boolean deleteFacilityKind(FacilityKind facilityKind) {
        return FacilityKindDao.delete(facilityKind);
    }

    public static boolean deleteFacility(Facility facility) {
        return FacilityDao.delete(facility);
    }

    public static boolean deleteSport(Sport sport) {
        return SportDao.delete(sport);
    }

    public static List<SportsmanCharacteristic> getSportCharacteristic() {
        return SportsmanCharacteristicDao.getAll();
    }

    public static boolean deleteSportCharacteristic(SportsmanCharacteristic sportsmanCharacteristic) {
        return SportsmanCharacteristicDao.delete(sportsmanCharacteristic);
    }

    public static void createSportsmanCharacteristic(SportsmanCharacteristic sportsmanCharacteristic) {
        SportsmanCharacteristicDao.create(sportsmanCharacteristic);
    }


    public static void updateSportsmanCharacteristic(SportsmanCharacteristic oldEntity, SportsmanCharacteristic entity) {
        SportsmanCharacteristicDao.update(oldEntity, entity);
    }

    public static void deleteCompetitionParticipant(CompetitionParticipant competitionParticipant) {
        CompetitionParticipantDao.delete(competitionParticipant);
    }

    public static List<CompetitionParticipant> getPersonsByCompetition(Competition competition) {
        return CompetitionParticipantDao.getAllByCompetition(competition);
    }

    public static List<Person> getNotParticipantsOf(Competition competition) {
        return PersonDao.getNotParticipantsOf(competition);
    }

    public static List<AttributeFacility> getAllAttributes() {
        return AttributeFacilityDao.getAll();
    }

    public static List<Facility> getFacilitiesByKind(FacilityKind facilityKind) {
        return FacilityDao.getAllByKind(facilityKind);
    }

    public static void deleteAttributeForFacility(AttributeFacility attributeFacility) {
        AttributeFacilityDao.delete(attributeFacility);
    }

    public static void createAttributeFacility(AttributeFacility attributeFacility) {
        AttributeFacilityDao.create(attributeFacility);
    }

    public static void setConnection(JdbcConnection connection) {
        Service.connection = connection;
    }

    public static JdbcConnection getConnection() {
        return connection;
    }

    public static List<AttributeFacilityKind> getAttributesByKind(FacilityKind facilityKind) {
        return AttributeFacilityKindDao.getByKind(facilityKind);
    }

    public static List<Facility> getFacilityWithParams(FacilityKind facilityKind,
                                                       AttributeFacilityKind attributeFacilityKind,
                                                       Integer from, Integer to, boolean useAttr) {
        return FacilityDao.getByParams(facilityKind, attributeFacilityKind, from, to, useAttr);
    }

    public static List<SportsmanCharacteristic> getSportsmanBySportOrCategory(Sport sport, Person trainer,
                                                                              Integer from, Integer to) {
       return SportsmanCharacteristicDao.getBySportOrCategory(sport,trainer, from, to);
    }

    public static List<SportsmanCharacteristic> getSportsmanWithMoreThanOneSport() {
        return SportsmanCharacteristicDao.getWithMoreThanOneSport();
    }

    public static List<Person> getTrainersForSportsman(Person sportsman) {
        return SportsmanCharacteristicDao.getTrainersForSportsman(sportsman);
    }

    public static List<Person> getTrainersBySport(Sport sport) {
        return SportsmanCharacteristicDao.getTrainersBySport(sport);
    }

    public static List<Competition> getCompetitionsByDate(Instant from, Instant to) {
        return CompetitionDao.getByDate(from, to);
    }

    public static List<Competition> getCompetitionsBySport(Sport sport) {
        return CompetitionDao.getBySport(sport);
    }

    public static List<Competition> getCompetitionsByOrganizer(Person person) {
        return CompetitionDao.getByOrganizer(person);
    }

    public static List<Competition> getCompetitionsByFacility(Facility facility) {
        return CompetitionDao.getByFacility(facility);
    }

    public static List<CompetitionParticipant> getWinnersOfCompetition(Competition competition) {
        return CompetitionParticipantDao.getWinnersOf(competition);
    }

    public static List<SportsmanCharacteristic> getSportsmenWithoutCompetitionOnPeriod(Instant from, Instant to) {
        return SportsmanCharacteristicDao.getWithoutCompetitionOnPetiod(from, to);
    }
}
