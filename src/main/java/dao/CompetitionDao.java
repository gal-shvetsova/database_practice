package dao;

import model.Competition;
import model.CompetitionResults;
import model.Facility;
import model.Sport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionDao extends AbstractDao {
    public static List<Competition> getAll() {
        final String sql = "" +
                "select * " +
                "from shvetsova.competition";
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
        return new Competition(rs.getInt("id"), rs.getString("name"), new Sport(rs.getString("sport")),
                FacilityDao.getByName(rs.getString("facility")),
                rs.getTimestamp("start_date").toInstant(), rs.getTimestamp("finish_date").toInstant());
    }

}
