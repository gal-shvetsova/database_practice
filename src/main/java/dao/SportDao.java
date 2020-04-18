package dao;

import model.Sport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
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

    public static boolean delete(Sport sport) {
        String sql = "select count(*) count from competition where sport = ?";
        String sql1 = "select count(*) count from sportsman_characteristic where sport = ?";
        List<Object> params = Collections.singletonList(sport.getName());
        if (queryCount(sql, params) > 0 || queryCount(sql1, params) > 0){
            return false;
        } else {
            String sql2 = "delete from sport where name = ?";
            query(sql2, params);
            return true;
        }
    }

    private static Sport sportRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Sport(rs.getString("name"));
    }

}
