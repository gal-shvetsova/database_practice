package dao;

import model.Club;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClubDao extends AbstractDao{
    public static List<Club> getAll(){
        String sql = "select * from club";
        return query(sql, ClubDao::clubRowMapper);
    }

    private static Club clubRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Club(rs.getString("name"));
    }
}
