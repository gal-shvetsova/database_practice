package dao;

import model.Facility;
import model.FacilityKind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FacilityDao extends AbstractDao {
    public static List<Facility> getAll() {
        final String sql = "select * from facility";
        return query(sql, FacilityDao::facilityRowManager);
    }

    public static Facility getByName(String name) {
        final String sql = "select * from facility where name = ?";
        List<Object> params = Arrays.asList(new Object[]{name});
        List<Facility> result = query(sql, params, FacilityDao::facilityRowManager);
        return result.isEmpty() ? null : result.get(0);
    }

    public static void create(Facility facility) {
        final String sql = "insert into facility values (?, ?, ?)";
        List<Object> params = Arrays.asList(facility.getName(),
                facility.getAddress(), facility.getKind().getName());
        query(sql, params);
    }

    public static void update(Facility oldFacility, Facility newFacility) {
        final String sql = "update facility set name ?, address = ?, kind = ? where name = ?";
        List<Object> params = Arrays.asList(newFacility.getName(), newFacility.getAddress(), newFacility.getKind(),
                oldFacility.getName());
        query(sql, params);
    }

    private static Facility facilityRowManager(ResultSet rs, int rowNum) throws SQLException {
        return new Facility(rs.getString("name"), rs.getString("address"),
                new FacilityKind(rs.getString("kind")));
    }

    public static boolean delete(Facility facility) {
        String sql = "select count(*) count from competition where facility = ?";
        List<Object> params = Collections.singletonList(facility.getName());
        if (queryCount(sql, params) > 0){
            return false;
        } else {
            String sql1 = "delete from facility where name = ?";
            query(sql1, params);
            return true;
        }
    }
}
