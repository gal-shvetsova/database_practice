package dao;

import model.AttributeFacilityKind;
import model.Facility;
import model.FacilityKind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FacilityDao extends AbstractDao {
    private static final int DEFAULT_ATTR_RIGHT_BORDER = 0;
    private static final Integer DEFAULT_ATTR_LEFT_BORDER = 1000000;

    public static List<Facility> getAll() throws SQLException {
        final String sql = "select * from facility";
        return query(sql, FacilityDao::facilityRowManager);
    }

    public static Facility getByName(String name) throws SQLException {
        final String sql = "select * from facility where name = ?";
        List<Object> params = Arrays.asList(new Object[]{name});
        List<Facility> result = query(sql, params, FacilityDao::facilityRowManager);
        return result.isEmpty() ? null : result.get(0);
    }

    public static void create(Facility facility) throws SQLException {
        final String sql = "insert into facility values (?, ?, ?)";
        List<Object> params = Arrays.asList(facility.getName(),
                facility.getAddress(), facility.getKind().getName());
        query(sql, params);
    }

    public static void update(Facility oldFacility, Facility newFacility) throws SQLException {
        final String sql = "update facility set name = ?, address = ?, kind = ? where name = ?";
        List<Object> params = Arrays.asList(newFacility.getName(), newFacility.getAddress(),
                newFacility.getKind().getName(),
                oldFacility.getName());
        query(sql, params);
    }

    private static Facility facilityRowManager(ResultSet rs, int rowNum) throws SQLException {
        return new Facility(rs.getString("name"), rs.getString("address"),
                new FacilityKind(rs.getString("kind")));
    }

    public static boolean delete(Facility facility) throws SQLException {
        String sql = "select count(*) count from competition where facility = ?";
        List<Object> params = Collections.singletonList(facility.getName());
        if (queryCount(sql, params) > 0) {
            return false;
        } else {
            String sql1 = "delete from facility where name = ?";
            query(sql1, params);
            return true;
        }
    }

    public static List<Facility> getAllByKind(FacilityKind facilityKind) throws SQLException {
        String sql = "select * from facility where kind = ?";
        List<Object> params = Collections.singletonList(facilityKind.getName());
        return query(sql, params, FacilityDao::facilityRowManager);
    }

    public static List<Facility> getByParams(FacilityKind facilityKind, AttributeFacilityKind attributeFacilityKind,
                                             Integer from, Integer to, boolean useAttr) throws SQLException {
        if (from == null || to < 0) {
            from = DEFAULT_ATTR_RIGHT_BORDER;
        }

        if (to == null || to < 0) {
            to = DEFAULT_ATTR_LEFT_BORDER;
        }

        String sql;
        List<Object> params;
        if (useAttr) {
            sql = "" +
                    "select f.name name,\n" +
                    "       f.ADDRESS address,\n" +
                    "       f.KIND kind\n" +
                    "       from FACILITY f join ATTRIBUTE_FACILITY af on " +
                    "f.NAME = af.ID_FACILITY where f.KIND = ? and af.name_attribute = ? and af.VALUE >= ? and af.VALUE <= ?";
            params = Arrays.asList(facilityKind.getName(), attributeFacilityKind.getName(), from, to);
        } else {
            sql = "" +
                    "select * from facility where kind = ?";
            params = Collections.singletonList(facilityKind.getName());
        }
        return query(sql, params, FacilityDao::facilityRowManager);
    }
}
