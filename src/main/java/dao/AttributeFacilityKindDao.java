package dao;

import model.AttributeFacilityKind;
import model.FacilityKind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AttributeFacilityKindDao extends AbstractDao {
    public static List<AttributeFacilityKind> getAll() throws SQLException {
        String sql = "select * from attribute_facility_kind";
        return query(sql, AttributeFacilityKindDao::attributeFacilityKindRowMapper);
    }

    private static AttributeFacilityKind attributeFacilityKindRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new AttributeFacilityKind(rs.getString("name"),
                new FacilityKind(rs.getString("id_type")));
    }

    public static void update(AttributeFacilityKind oldEntity, AttributeFacilityKind newEntity) throws SQLException {
        String sql = "update attribute_facility_kind set name = ?, id_type = ? where name = ?";
        List<Object> params = Arrays.asList(newEntity.getName(), newEntity.getFacilityKind().getName(),
                oldEntity.getName());
        query(sql, params);
    }

    public static boolean delete(AttributeFacilityKind attributeFacilityKind) throws SQLException {
        String sql = "select * from attribute_facility where name_attribute = ?";
        List<Object> params = Collections.singletonList(attributeFacilityKind.getName());
        List<AttributeFacilityKind> result = query(sql, params,
                AttributeFacilityKindDao::attributeFacilityKindRowMapper);
        if (!result.isEmpty()){
            return false;
        } else {
            String sql1 = "delete from attribute_facility_kind where name = ?";
            query(sql1, params);
            return true;
        }
    }

    public static void create(AttributeFacilityKind attributeFacilityKind) throws SQLException {
        String sql = "insert into attribute_facility_kind values (?,?)";
        List<Object> params = Arrays.asList(attributeFacilityKind.getName(),
                attributeFacilityKind.getFacilityKind().getName());
        query(sql, params);
    }

    public static List<AttributeFacilityKind> getByKind(FacilityKind facilityKind) throws SQLException {
        String sql = "select * from attribute_facility_kind where id_type = ?";
        List<Object> params = Collections.singletonList(facilityKind.getName());
        return query(sql, params, AttributeFacilityKindDao::attributeFacilityKindRowMapper);
    }
}
