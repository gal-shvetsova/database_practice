package dao;

import model.HasId;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDao extends AbstractDao {


    public static User getUserById(UUID id) {
        String sql = "select * from sportsman where id = " + id; //TODO: make parameters
        return query(sql, UserDao::userRowMapper).get(0);
    }

    private static User userRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new User(HasId.getById(Role.class, rs.getString("role")),
                UUID.fromString(rs.getString("id")));
    }
}
