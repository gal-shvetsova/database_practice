package dao;

import model.Club;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ClubDao extends AbstractDao{
    public static List<Club> getAll(){
        String sql = "select * from club";
        return query(sql, ClubDao::clubRowMapper);
    }

    public static void insert(Club club) {
        String sql = "insert into club values (?)";
        List<Object> params = Arrays.asList(new Object[]{club.getName()});
        query(sql, params);
    }

    public static void update(Club oldClub, Club newClub) {
        String sql = "update club set name = ? where name = ?";
        List<Object> params = Arrays.asList(new Object[]{newClub.getName(), oldClub.getName()});
        query(sql, params);
    }

    private static Club clubRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Club(rs.getString("name"));
    }
}
