package dao;

import model.Sportsman;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SportsmanDao extends AbstractDao {
    public SportsmanDao() throws Exception {
    }

    List<Sportsman> findAll() {
        String sql = "select * from sportsman";
        return query(sql, SportsmanDao::sportsmanRowMapper);
    }


    private static Sportsman sportsmanRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Sportsman(rs.getInt("id"),
                rs.getString("name"), rs.getString("surname"),
                rs.getString("patronymic"));
    }
}
