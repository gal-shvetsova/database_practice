package dao;

import model.AttributeFacilityKind;
import model.FacilityKind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AttributeFacilityKindDao extends AbstractDao {
    public static List<AttributeFacilityKind> getAll() {
        String sql = "select * from attribute_facility_kind";
        return query(sql, AttributeFacilityKindDao::attributeFacilityKindRowMapper);
    }

    private static AttributeFacilityKind attributeFacilityKindRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new AttributeFacilityKind(rs.getString("name"),
                new FacilityKind(rs.getString("kind")));
    }

    public static void update(AttributeFacilityKind oldEntity, AttributeFacilityKind newEntity) {
        String sql = "update attribute_facility_kind set name = ?, facility_kind = ? where name = ?";
        List<Object> params = Arrays.asList(newEntity.getName(), newEntity.getFacilityKind().getName(),
                oldEntity.getName());
        query(sql, params);
    }

    public static void create(AttributeFacilityKind attributeFacilityKind) {
        String sql = "insert into attribute_facility_kind values (?,?)";
        List<Object> params = Arrays.asList(attributeFacilityKind.getName(),
                attributeFacilityKind.getFacilityKind());
    }
}
