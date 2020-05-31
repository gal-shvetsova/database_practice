package dao;

import model.Club;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClubDao extends AbstractDao{
    public static List<Club> getAll() throws SQLException {
        String sql = "select * from club";
        return query(sql, ClubDao::clubRowMapper);
    }

    public static void insert(Club club) throws SQLException {
        String sql = "insert into club values (?)";
        List<Object> params = Arrays.asList(new Object[]{club.getName()});
        query(sql, params);
    }

    public static void update(Club oldClub, Club newClub) throws SQLException {
        String sql = "update club set name = ? where name = ?";
        List<Object> params = Arrays.asList(new Object[]{newClub.getName(), oldClub.getName()});
        query(sql, params);
    }

    public static boolean delete(Club club) throws SQLException {
        String sql = "select count(*) count from sportsman_characteristic where club = ?";
        List<Object> params = Collections.singletonList(club.getName());
        if (queryCount(sql, params) > 0) {
            return false;
        } else {
            String sql1 = "delete from club where name = ?";
            query(sql1, params);
            return true;
        }
    }

    private static Club clubRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Club(rs.getString("name"));
    }
}
