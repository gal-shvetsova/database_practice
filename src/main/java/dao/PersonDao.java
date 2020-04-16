package dao;

import model.HasId;
import model.Person;
import model.Role;
import model.Sportsman;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PersonDao extends AbstractDao {


    public static Person getById(Integer id) {
        String sql = "select * from person  where id = ?"; //TODO: make parameters
        List<Object> params = Arrays.asList(new Object[]{id});
        return query(sql, params, PersonDao::personRowMapper).get(0);
    }

    public static List<Person> getAll(){
        String sql = "select * from person";
        return query(sql, PersonDao::personRowMapper);
    }

    public static List<Person> getAllTrainers() {
        String sql = "select * from person where role = ?";
        List<Object> params = Collections.singletonList("TRAINER");
        return query(sql, params, PersonDao::personRowMapper);
    }

    public static Person getByLoginAndPassword(String login, String password) {
        String sql = "select * from person where login = ? and password = ?";
        List<Object> params = Arrays.asList(new Object[]{login, password});
        List<Person> result = query(sql, params, PersonDao::personRowMapper);
        return result.isEmpty() ? null : result.get(0);
    }

    public static void insert(Person person) {
        String sql = "insert into person (role, login, password, surname, name) values (?, ?, ?, ?, ?)";
        List<Object> params = Arrays.asList(new Object[]{person.getRole(),
                person.getLogin(), person.getPassword(), person.getSurname(), person.getName()});
        query(sql, params);
    }

    public static void updatePerson(Person person) {
        String sql = "update person set name = ? surname = ? where id = ?";
        List<Object> params = Arrays.asList(person.getName(), person.getSurname(), person.getId());
        query(sql, params);
    }

    public static void registerSportsman(Sportsman sportsman){
        String sql = "insert into person values " +
                "(?,?,?,?,?,?)";
        List<Object> params = Arrays.asList(sportsman.getId().toString(), sportsman.getRole().toString(), sportsman.getLogin(),
                sportsman.getPassword(), sportsman.getSurname(), sportsman.getName());

        query(sql, params);
        String sql1 = "insert into sportsman_characteristic values (?,?,?,?,?)";
        List<Object> params1 = Arrays.asList(sportsman.getId().toString(), sportsman.getSport().getName(), sportsman.getCategory(),
                sportsman.getTrainer().getId().toString(), sportsman.getClub().getName());

        query(sql1, params1);
    }

    protected static Person personRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Person(UUID.fromString(rs.getString("id")),
                HasId.getById(Role.class, rs.getString("role")),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("surname"),
                rs.getString("name"));
    }

    protected static Person sportsmanRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Person(UUID.fromString(rs.getString("sportsman_id")),
                HasId.getById(Role.class, rs.getString("sportsman_role")),
                rs.getString("SPORTSMAN_LOGIN"),
                rs.getString("SPORTSMAN_PASSWORD"),
                rs.getString("SPORTSMAN_SURNAME"),
                rs.getString("SPORTSMAN_NAME"));
    }

    protected static Person trainerRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Person(UUID.fromString(rs.getString("TRAINER_ID")),
                HasId.getById(Role.class, rs.getString("TRAINER_ROLE")),
                rs.getString("TRAINER_LOGIN"),
                rs.getString("TRAINER_PASSWORD"),
                rs.getString("TRAINER_SURNAME"),
                rs.getString("TRAINER_NAME"));
    }

    public static void registerPerson(Person person) {
        String sql = "insert into person values (?,?,?,?,?,?)";
        List<Object> params = Arrays.asList(person.getId(), person.getRole().toString(),
                person.getLogin(), person.getPassword(), person.getSurname(), person.getName());
        query(sql, params);
    }
}
