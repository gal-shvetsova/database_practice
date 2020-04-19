package dao;

import model.FacilityKind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FacilityKindDao extends AbstractDao{
    public static List<FacilityKind> getAll(){
        String sql = "select * from facility_kind";
        return query(sql, FacilityKindDao::facilityKindRowManager);
    }

    public static void insert(FacilityKind facilityKind){
        String sql = "insert into facility_kind values (?)";
        List<Object> params = Collections.singletonList(facilityKind.getName());
        query(sql, params);
    }
    public static void update(FacilityKind oldFacilityKind, FacilityKind newFacilityKind){
        String sql = "update facility_kind set name = ? where name = ?";
        List<Object> params = Arrays.asList(newFacilityKind.getName(),oldFacilityKind.getName());
        query(sql, params);
    }

    private static FacilityKind facilityKindRowManager(ResultSet rs, int rowNum) throws SQLException {
        return new FacilityKind(rs.getString("name"));
    }

    public static boolean delete(FacilityKind facilityKind) {
        String sql = "select count(*) count from attribute_facility_kind where id_type = ?";
        List<Object> params = Collections.singletonList(facilityKind.getName());
        String sql1 = "select count(*) count from facility where kind = ?";
        if (queryCount(sql, params) > 0 || queryCount(sql1, params) > 0){
            return false;
        } else {
            String sql3 = "delete from facility_kind where name = ?";
            query(sql3, params);
            return true;
        }
    }
}
