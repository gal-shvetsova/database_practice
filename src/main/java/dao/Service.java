package dao;

import connection.JdbcConnection;
import model.*;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

public class Service {
    private static JdbcConnection connection;

    public static Person signIn(String login, String password) throws SQLException {
        return PersonDao.getByLoginAndPassword(login, password);
    }

    public static List<Club> getAllClubs() throws SQLException {
        return ClubDao.getAll();
    }

    public static void createClub(Club club) throws SQLException {
        ClubDao.insert(club);
    }

    public static void updateClub(Club oldClub, Club newClub) throws SQLException {
        ClubDao.update(oldClub, newClub);
    }

    public static void createSport(Sport sport) throws SQLException {
        SportDao.insert(sport);
    }

    public static void updateSport(Sport oldSport, Sport newSport) throws SQLException {
        SportDao.update(oldSport, newSport);
    }

    public static List<Sport> getAllSports() throws SQLException {
        return SportDao.getAll();
    }

    public static List<FacilityKind> getAllFacilityKinds() throws SQLException {
        return FacilityKindDao.getAll();
    }

    public static void createFacilityKind(FacilityKind facilityKind) throws SQLException {
        FacilityKindDao.insert(facilityKind);
    }

    public static void updateFacilityKind(FacilityKind oldFacilityKind, FacilityKind newFacilityKind) throws SQLException {
        FacilityKindDao.update(oldFacilityKind, newFacilityKind);
    }

    public static List<Competition> getCompetitions() throws SQLException {
        return CompetitionDao.getAll();
    }

    public static void createCompetition(Competition competition) throws SQLException {
        CompetitionDao.create(competition);
    }

    public static void updateCompetition(Competition competition) throws SQLException {
        CompetitionDao.update(competition);
    }

    public static List<Facility> getAllFacilities() throws SQLException {
        return FacilityDao.getAll();
    }

    public static void createFacility(Facility facility) throws SQLException {
        FacilityDao.create(facility);
    }

    public static void updateFacility(Facility oldFacility, Facility newFacility) throws SQLException {
        FacilityDao.update(oldFacility, newFacility);
    }

    private static List<Person> getAllPersonsByRole(Role role) throws SQLException {
        return PersonDao.getAllByRole(role);
    }

    public static List<Sportsman> getAllSportsmen() throws SQLException {
        return SportsmanDao.getAll();
    }

    public static List<Person> getAllTrainers() throws SQLException {
        return getAllPersonsByRole(Role.TRAINER);
    }

    public static List<Person> getAllOrganizers() throws SQLException {
        return getAllPersonsByRole(Role.ORGANIZER);
    }

    public static void updateSportsman(Sportsman oldSportsman, Sportsman newSportsman) throws SQLException {
        SportsmanDao.update(oldSportsman, newSportsman);
    }

    public static List<Person> getAllPerson() throws SQLException {
        return PersonDao.getAll();
    }

    public static void updatePerson(Person person) throws SQLException {
        PersonDao.updatePerson(person);
    }

    public static void registerSportsman(Sportsman sportsman) throws SQLException {
        PersonDao.registerSportsman(sportsman);
    }

    public static void registerPerson(Person person) throws SQLException {
        PersonDao.registerPerson(person);
    }

    public static List<AttributeFacilityKind> getAllAttributeFacilityKinds() throws SQLException {
        return AttributeFacilityKindDao.getAll();
    }

    public static void updateAttributeFacilityKind(AttributeFacilityKind oldEntity, AttributeFacilityKind newEntity) throws SQLException {
        AttributeFacilityKindDao.update(oldEntity, newEntity);
    }

    public static void createAttributeFacilityKind(AttributeFacilityKind attributeFacilityKind) throws SQLException {
        AttributeFacilityKindDao.create(attributeFacilityKind);
    }

    public static boolean deleteAttributeFacilityKind(AttributeFacilityKind attributeFacilityKind) throws SQLException {
        return AttributeFacilityKindDao.delete(attributeFacilityKind);
    }

    public static boolean deleteClub(Club club) throws SQLException {
        return ClubDao.delete(club);
    }

    public static boolean deleteCompetition(Competition competition) throws SQLException {
        return CompetitionDao.delete(competition);
    }

    public static boolean deleteFacilityKind(FacilityKind facilityKind) throws SQLException {
        return FacilityKindDao.delete(facilityKind);
    }

    public static boolean deleteFacility(Facility facility) throws SQLException {
        return FacilityDao.delete(facility);
    }

    public static boolean deleteSport(Sport sport) throws SQLException {
        return SportDao.delete(sport);
    }

    public static List<SportsmanCharacteristic> getSportCharacteristic() throws SQLException {
        return SportsmanCharacteristicDao.getAll();
    }

    public static boolean deleteSportCharacteristic(SportsmanCharacteristic sportsmanCharacteristic) throws SQLException {
        return SportsmanCharacteristicDao.delete(sportsmanCharacteristic);
    }

    public static void createSportsmanCharacteristic(SportsmanCharacteristic sportsmanCharacteristic) throws SQLException {
        SportsmanCharacteristicDao.create(sportsmanCharacteristic);
    }


    public static void updateSportsmanCharacteristic(SportsmanCharacteristic oldEntity, SportsmanCharacteristic entity) throws SQLException {
        SportsmanCharacteristicDao.update(oldEntity, entity);
    }

    public static void deleteCompetitionParticipant(CompetitionParticipant competitionParticipant) throws SQLException {
        CompetitionParticipantDao.delete(competitionParticipant);
    }

    public static List<CompetitionParticipant> getPersonsByCompetition(Competition competition) throws SQLException {
        return CompetitionParticipantDao.getAllByCompetition(competition);
    }

    public static List<Person> getNotParticipantsOf(Competition competition) throws SQLException {
        return PersonDao.getNotParticipantsOf(competition);
    }

    public static List<AttributeFacility> getAllAttributes() throws SQLException {
        return AttributeFacilityDao.getAll();
    }

    public static List<Facility> getFacilitiesByKind(FacilityKind facilityKind) throws SQLException {
        return FacilityDao.getAllByKind(facilityKind);
    }

    public static void deleteAttributeForFacility(AttributeFacility attributeFacility) throws SQLException {
        AttributeFacilityDao.delete(attributeFacility);
    }

    public static void createAttributeFacility(AttributeFacility attributeFacility) throws SQLException {
        AttributeFacilityDao.create(attributeFacility);
    }

    public static void setConnection(JdbcConnection connection) {
        Service.connection = connection;
    }

    public static JdbcConnection getConnection() {
        return connection;
    }

    public static List<AttributeFacilityKind> getAttributesByKind(FacilityKind facilityKind) throws SQLException {
        return AttributeFacilityKindDao.getByKind(facilityKind);
    }

    public static List<Facility> getFacilityWithParams(FacilityKind facilityKind,
                                                       AttributeFacilityKind attributeFacilityKind,
                                                       Integer from, Integer to, boolean useAttr) throws SQLException {
        return FacilityDao.getByParams(facilityKind, attributeFacilityKind, from, to, useAttr);
    }

    public static List<SportsmanCharacteristic> getSportsmanBySportOrCategory(Sport sport, Person trainer,
                                                                              Integer from, Integer to) throws SQLException {
       return SportsmanCharacteristicDao.getBySportOrCategory(sport,trainer, from, to);
    }

    public static List<SportsmanCharacteristic> getSportsmanWithMoreThanOneSport() {
        return SportsmanCharacteristicDao.getWithMoreThanOneSport();
    }

    public static List<Person> getTrainersForSportsman(Person sportsman) throws SQLException {
        return SportsmanCharacteristicDao.getTrainersForSportsman(sportsman);
    }

    public static List<Person> getTrainersBySport(Sport sport) throws SQLException {
        return SportsmanCharacteristicDao.getTrainersBySport(sport);
    }

    public static List<Competition> getCompetitionsByDate(Instant from, Instant to) throws SQLException {
        return CompetitionDao.getByDate(from, to);
    }

    public static List<Competition> getCompetitionsBySport(Sport sport) throws SQLException {
        return CompetitionDao.getBySport(sport);
    }

    public static List<Competition> getCompetitionsByOrganizer(Person person) throws SQLException {
        return CompetitionDao.getByOrganizer(person);
    }

    public static List<Competition> getCompetitionsByFacility(Facility facility) throws SQLException {
        return CompetitionDao.getByFacility(facility);
    }

    public static List<CompetitionParticipant> getWinnersOfCompetition(Competition competition) throws SQLException {
        return CompetitionParticipantDao.getWinnersOf(competition);
    }

    public static List<SportsmanCharacteristic> getSportsmenWithoutCompetitionOnPeriod(Instant from, Instant to) {
        return SportsmanCharacteristicDao.getWithoutCompetitionOnPetiod(from, to);
    }
}
