package dao;

import model.HasId;
import model.Role;
import model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDao extends AbstractDao {
    private static final String TABLE_NAME = "PERSON";

    public static Person getById(Integer id) {
        String sql = "select * from " + TABLE_NAME + " where id = " + id; //TODO: make parameters
        return query(sql, PersonDao::userRowMapper).get(0);
    }

    public static Person getByLoginAndPassword(String login, String password) {
        String sql = "select * from " + TABLE_NAME +
                " where login = " + login + " and password = " + password;
        return query(sql, PersonDao::userRowMapper).get(0);
    }

    public static void insert(Role role, String login, String password, String surname, String name) {
        String sql = "insert into " + TABLE_NAME +
                " (role, login, password, surname, name) values ("
                + role.toString() + ", " + login + ", " +
                password + ", " + surname + ", " + name + ")";
        query(sql);
    }

    private static Person userRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Person(rs.getInt("id"),
                HasId.getById(Role.class, rs.getString("role")),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("surname"),
                rs.getString("name"));
    }
}
