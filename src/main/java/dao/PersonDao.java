package dao;

import model.HasId;
import model.Role;
import model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PersonDao extends AbstractDao {
    public static Person getById(Integer id) {
        String sql = "select * from person  where id = ?"; //TODO: make parameters
        List<Object> params = Arrays.asList(new Object[]{id});
        return query(sql, PersonDao::personRowMapper).get(0);
    }

    public static Person getByLoginAndPassword(String login, String password) {
        String sql = "select * from person where login = ? and password = ?";
        List<Object> params = Arrays.asList(new Object[]{login, password});
        List<Person> result = query(sql, params, PersonDao::personRowMapper);
        return result.isEmpty() ? null : result.get(0);
    }

    public static void insert(Role role, String login, String password, String surname, String name) {
        String sql = "insert into person (role, login, password, surname, name) values (?, ?, ?, ?, ?)";
        List<Object> params = Arrays.asList(new Object[]{role, login, password, surname, name});
        query(sql, params);
    }

    private static Person personRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Person(rs.getInt("id"),
                HasId.getById(Role.class, rs.getString("role")),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("surname"),
                rs.getString("name"));
    }
}
