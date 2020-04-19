package dao;

import model.AttributeFacility;
import model.AttributeFacilityKind;
import model.Facility;
import model.FacilityKind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AttributeFacilityDao extends AbstractDao{
    public static List<AttributeFacility> getAll(){
        String sql = "" +
                "select\n" +
                "    af.VALUE value,\n" +
                "    f.KIND kind,\n" +
                "    f.ADDRESS address,\n" +
                "    f.NAME name,\n" +
                "    AFK.NAME attr_name\n" +
                "from ATTRIBUTE_FACILITY af\n" +
                "         join FACILITY F on af.ID_FACILITY = F.NAME\n" +
                "         join ATTRIBUTE_FACILITY_KIND AFK on af.NAME_ATTRIBUTE = AFK.NAME";
        return query(sql, AttributeFacilityDao::attributeFacilityRowMapper);
    }

    public static void create(AttributeFacility attributeFacility){
        String sql = "insert into ATTRIBUTE_FACILITY values (?,?,?)";
        List<Object> params = Arrays.asList(attributeFacility.getAttributeFacilityKind().getName(),
                attributeFacility.getFacility().getName(), attributeFacility.getValue());
        query(sql, params);
    }

    public static void delete(AttributeFacility attributeFacility){
        String sql = "delete from ATTRIBUTE_FACILITY where name_attribute = ? and id_facility = ?";
        List<Object> params = Arrays.asList(attributeFacility.getAttributeFacilityKind().getName(),
                attributeFacility.getFacility().getName());
        query(sql, params);
    }

    private static AttributeFacility attributeFacilityRowMapper(ResultSet rs, int rowNum) throws SQLException {
        FacilityKind kind = new FacilityKind(rs.getString("kind"));
        return new AttributeFacility(new AttributeFacilityKind(rs.getString("attr_name"),
                kind), new Facility(rs.getString("name"),
                rs.getString("address"),kind),
                rs.getInt("value"));
    }
}
