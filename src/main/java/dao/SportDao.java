package dao;

import model.Sport;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SportDao extends AbstractDao {
    public static List<Sport> getAll(){
        String sql = "select * from sport";
        return query(sql,SportDao::sportRowMapper);
    }

    public static void insert (Sport sport){
        String sql = "insert into sport values (?)";
        List<Object> params = Arrays.asList(new Object[]{sport.getName()});
        query(sql, params);
    }

    public static void update (Sport oldSport, Sport newSport){
        String sql = "update sport set name = ? where name = ?";
        List<Object> params = Arrays.asList(new Object[]{oldSport.getName(), newSport.getName()});
        query(sql, params);
    }

    private static Sport sportRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Sport(rs.getString("name"));
    }
}
