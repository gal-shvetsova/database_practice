package dao;

import model.Competition;
import model.Person;
import model.Sport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CompetitionDao extends AbstractDao {
    public static List<Competition> getAll() {
        final String sql = "" +
                "select " +
                "c.id competition_id " +
                "c.sport competition_sport " +
                "c.facility competition_facility " +
                "c.start_date competition_start_date " +
                "c.finish_date competition_finish_date " +
                "p.id id " +
                "p.role role " +
                "p.login login " +
                "p.password password " +
                "p.surname surname " +
                "p.name name " +
                "from shvetsova.competition c join organizer_competition o_c on c.id_competition = p.id" +
                "join person p on o_c.id_organizer = p.id where p.role = 'ORGANIZER";
        return query(sql, CompetitionDao::competitionRowMapper);
    }

    public static void create(final Competition competition) {
        final String sql = "" +
                "insert into shvetsova.competition " +
                "values (?,?,?,?)";

        List<Object> params = Arrays.asList(new Object[]{competition.getName(),
                competition.getSport().getName(),
                competition.getFacility().getName(),
                competition.getStartDate(), competition.getFinishDate()});
        query(sql, params);
    }

    public static void update(final Competition competition) {
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

    private static Competition competitionRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Competition(rs.getInt("id"), rs.getString("name"),
                new Sport(rs.getString("sport")),
                FacilityDao.getByName(rs.getString("facility")),
                rs.getTimestamp("start_date").toInstant(),
                rs.getTimestamp("finish_date").toInstant(),
                new Person(PersonDao.personRowMapper(rs, rowNum)));
    }

}
