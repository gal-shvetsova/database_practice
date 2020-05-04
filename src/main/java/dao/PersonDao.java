package dao;

import model.*;

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

    public static List<Person> getAllByRole(Role role){
        String sql = "select * from person where role = ?";
        List<Object> params = Collections.singletonList(role.toString());
        return query(sql, params, PersonDao::personRowMapper);
    }


    public static Person getByLoginAndPassword(String login, String password) {
        String sql = "select * from person where login = ? and password = ?";
        List<Object> params = Arrays.asList(new Object[]{login, password});
        List<Person> result = query(sql, params, PersonDao::personRowMapper);
        return result.isEmpty() ? null : result.get(0);
    }

    public static void insert(Person person) {
        String sql = "insert into person (id, role, login, password, surname, name) values (?, ?, ?, ?, ?, ?)";
        List<Object> params = Arrays.asList(new Object[]{ UUID.randomUUID(), person.getRole(),
                person.getLogin(), person.getPassword(), person.getSurname(), person.getName()});
        query(sql, params);
    }

    public static void updatePerson(Person person) {
        String sql = "update person set name = ?, surname = ? where id = ?";
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
                Role.SPORTSMAN,
                rs.getString("sportsman_login"),
                rs.getString("sportsman_password"),
                rs.getString("sportsman_surname"),
                rs.getString("sportsman_name"));
    }

    protected static Person trainerRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Person(UUID.fromString(rs.getString("trainer_id")),
                 Role.TRAINER,
                rs.getString("trainer_login"),
                rs.getString("trainer_password"),
                rs.getString("trainer_surname"),
                rs.getString("trainer_name"));
    }

    public static void registerPerson(Person person) {
        String sql = "insert into person values (?,?,?,?,?,?)";
        List<Object> params = Arrays.asList(person.getId().toString(), person.getRole().toString(),
                person.getLogin(), person.getPassword(), person.getSurname(), person.getName());
        query(sql, params);
    }

    public static List<Person> getAllByCompetition(Competition competition) {
        String sql = "" +
                "select p.ID       id,\n" +
                "       p.ROLE     role,\n" +
                "       p.LOGIN    login,\n" +
                "       p.PASSWORD password,\n" +
                "       p.SURNAME  surname,\n" +
                "       p.NAME     name\n" +
                "from PARTICIPANT_COMPETITION pc\n" +
                "         join PERSON P on pc.ID_PARTICIPANT = P.ID\n" +
                "where pc.ID_COMPETITION = ?";
        List<Object> params = Collections.singletonList(competition.getId());
        return query(sql, params, PersonDao::personRowMapper);
    }

    public static List<Person> getNotParticipantsOf(Competition competition) {
        String sql = "" +
                "select *\n" +
                "from PERSON P\n" +
                "where role = 'SPORTSMAN'\n" +
                "  and (select count(*)\n" +
                "       from PARTICIPANT_COMPETITION\n" +
                "       where ID_PARTICIPANT = P.ID\n" +
                "         and ID_COMPETITION = ?) = 0\n" +
                "  and (select count(*)\n" +
                "       from SPORTSMAN_CHARACTERISTIC sc\n" +
                "       where sc.ID_SPORTSMAN = p.ID\n" +
                "         and sc.SPORT = ?) > 0\n";

        List<Object> params = Arrays.asList(competition.getId(), competition.getSport().getName());

        return query(sql, params, PersonDao::personRowMapper);
    }
}
