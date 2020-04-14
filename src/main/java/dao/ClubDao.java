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

    private static Club clubRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Club(rs.getString("name"));
    }

    public static void createClub(String name) {
        String sql = "insert into club values (?)";
        List<Object> params = Arrays.asList(new Object[]{name});
        query(sql, params);
    }

    public static void updateClub(String oldName, String newName) {
        String sql = "update club set name = ? where name = ?";
        List<Object> params = Arrays.asList(new Object[]{newName, oldName});
        query(sql, params);
    }
}
