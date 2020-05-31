package dao;

import model.Competition;
import model.Facility;
import model.Person;
import model.Sport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CompetitionDao extends AbstractDao {
    public static List<Competition> getAll() throws SQLException {
        final String sql = "" +
                "select c.id          competition_id,\n" +
                "       c.name        competition_name,\n" +
                "       c.sport       competition_sport,\n" +
                "       c.facility    competition_facility,\n" +
                "       c.start_date  competition_start_date,\n" +
                "       c.finish_date competition_finish_date,\n" +
                "       p.id          sportsman_id,\n" +
                "       p.role        sportsman_role,\n" +
                "       p.login       sportsman_login,\n" +
                "       p.password    sportsman_password,\n" +
                "       p.surname     sportsman_surname,\n" +
                "       p.name        sportsman_name\n" +
                "from competition c\n" +
                "         join organizer_competition o_c on c.id = o_c.ID_COMPETITION\n" +
                "         join person p on o_c.id_organizer = p.id\n" +
                "where p.role = 'ORGANIZER'";
        return query(sql, CompetitionDao::competitionRowMapper);
    }

    public static void create(final Competition competition) throws SQLException {
        final String sql = "" +
                "insert into competition " +
                "values (?,?,?,?,?,?)";

        final String sql1 = "insert into organizer_competition values (?,?)";

        UUID uuid = UUID.randomUUID();
        List<Object> params = Arrays.asList(new Object[]{uuid.toString(), competition.getName(),
                competition.getSport().getName(),
                competition.getFacility().getName(),
                competition.getStartDate(), competition.getFinishDate()});

        List<Object> params1 = Arrays.asList(competition.getOrganizer().getId().toString(),
                uuid.toString());

        query(sql, params);
        query(sql1, params1);
    }

    public static void update(final Competition competition) throws SQLException {
        final String sql = "" +
                "update shvetsova.competition set sport = ?, " +
                "facility = ?, start_date = ?, finish_date = ? " +
                "where id = ?";
        List<Object> params = Arrays.asList(new Object[]{competition.getName(),
                competition.getSport().getName(),
                competition.getFacility().getName(),
                competition.getStartDate(),
                competition.getFinishDate()});
        query(sql, params);
    }

    protected static Competition competitionRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Competition(
                UUID.fromString(rs.getString("competition_id")),
                rs.getString("competition_name"),
                new Sport(rs.getString("competition_sport")),
                FacilityDao.getByName(rs.getString("competition_facility")),
                rs.getTimestamp("competition_start_date").toInstant(),
                rs.getTimestamp("competition_finish_date").toInstant(),
                new Person(PersonDao.sportsmanRowMapper(rs, rowNum)));
    }


    public static boolean delete(Competition competition) throws SQLException {
        String sql = "select count(*) count from organizer_competition where id_competition = ?";
        List<Object> params = Collections.singletonList(competition.getId());
        String sql1 = "select count(*) count from participant_competition where id_competition = ?";
        if (queryCount(sql, params) > 0 || queryCount(sql1, params) > 0) {
            return false;
        } else {
            String sql2 = "delete from competition where id = ?";
            query(sql2, params);
            return true;
        }
    }

    public static List<Competition> getByDate(Instant from, Instant to) throws SQLException {
        final String sql = "" +
                "select c.id          competition_id,\n" +
                "       c.name        competition_name,\n" +
                "       c.sport       competition_sport,\n" +
                "       c.facility    competition_facility,\n" +
                "       c.start_date  competition_start_date,\n" +
                "       c.finish_date competition_finish_date,\n" +
                "       p.id          sportsman_id,\n" +
                "       p.role        sportsman_role,\n" +
                "       p.login       sportsman_login,\n" +
                "       p.password    sportsman_password,\n" +
                "       p.surname     sportsman_surname,\n" +
                "       p.name        sportsman_name\n" +
                "from competition c\n" +
                "         join organizer_competition o_c on c.id = o_c.ID_COMPETITION\n" +
                "         join person p on o_c.id_organizer = p.id\n" +
                "where p.role = 'ORGANIZER' and start_date >= ? and finish_date <= ?";
        List<Object> params = Arrays.asList(from, to);
        return query(sql, params, CompetitionDao::competitionRowMapper);
    }

    public static List<Competition> getBySport(Sport sport) throws SQLException {
        final String sql = "" +
                "select c.id          competition_id,\n" +
                "       c.name        competition_name,\n" +
                "       c.sport       competition_sport,\n" +
                "       c.facility    competition_facility,\n" +
                "       c.start_date  competition_start_date,\n" +
                "       c.finish_date competition_finish_date,\n" +
                "       p.id          sportsman_id,\n" +
                "       p.role        sportsman_role,\n" +
                "       p.login       sportsman_login,\n" +
                "       p.password    sportsman_password,\n" +
                "       p.surname     sportsman_surname,\n" +
                "       p.name        sportsman_name\n" +
                "from competition c\n" +
                "         join organizer_competition o_c on c.id = o_c.ID_COMPETITION\n" +
                "         join person p on o_c.id_organizer = p.id\n" +
                "where p.role = 'ORGANIZER' and sport = ?";
        List<Object> params = Collections.singletonList(sport.getName());
        return query(sql, params, CompetitionDao::competitionRowMapper);
    }

    public static List<Competition> getByOrganizer(Person person) throws SQLException {
        final String sql = "" +
                "select c.id          competition_id,\n" +
                "       c.name        competition_name,\n" +
                "       c.sport       competition_sport,\n" +
                "       c.facility    competition_facility,\n" +
                "       c.start_date  competition_start_date,\n" +
                "       c.finish_date competition_finish_date,\n" +
                "       p.id          sportsman_id,\n" +
                "       p.role        sportsman_role,\n" +
                "       p.login       sportsman_login,\n" +
                "       p.password    sportsman_password,\n" +
                "       p.surname     sportsman_surname,\n" +
                "       p.name        sportsman_name\n" +
                "from competition c\n" +
                "         join organizer_competition o_c on c.id = o_c.ID_COMPETITION\n" +
                "         join person p on o_c.id_organizer = p.id\n" +
                "where p.role = 'ORGANIZER' and o_c.id_organizer = ?";
        List<Object> params = Collections.singletonList(person.getId());
        return query(sql, params, CompetitionDao::competitionRowMapper);
    }

    public static List<Competition> getByFacility(Facility facility) throws SQLException {
        final String sql = "" +
                "select c.id          competition_id,\n" +
                "       c.name        competition_name,\n" +
                "       c.sport       competition_sport,\n" +
                "       c.facility    competition_facility,\n" +
                "       c.start_date  competition_start_date,\n" +
                "       c.finish_date competition_finish_date,\n" +
                "       p.id          sportsman_id,\n" +
                "       p.role        sportsman_role,\n" +
                "       p.login       sportsman_login,\n" +
                "       p.password    sportsman_password,\n" +
                "       p.surname     sportsman_surname,\n" +
                "       p.name        sportsman_name\n" +
                "from competition c\n" +
                "         join organizer_competition o_c on c.id = o_c.ID_COMPETITION\n" +
                "         join person p on o_c.id_organizer = p.id\n" +
                "where p.role = 'ORGANIZER' and  c.facility = ?";
        List<Object> params = Collections.singletonList(facility.getName());
        return query(sql, params, CompetitionDao::competitionRowMapper);
    }
}
