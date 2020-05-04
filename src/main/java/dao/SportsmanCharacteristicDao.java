package dao;

import model.Club;
import model.Sport;
import model.SportsmanCharacteristic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SportsmanCharacteristicDao extends AbstractDao {
    private static final Integer DEFAULT_RIGHT_BORDER = 0;
    private static final Integer DEFAULT_LEFT_BORDER = 100;

    public static List<SportsmanCharacteristic> getAll() {
        String sql = "" +
                "select s.name      sportsman_name,\n" +
                "       s.id        sportsman_id,\n" +
                "       s.surname   sportsman_surname,\n" +
                "       s.password  sportsman_page,\n" +
                "       s.login     sportsman_login,\n" +
                "       t.name      trainer_name,\n" +
                "       t.id        trainer_id,\n" +
                "       t.surname   trainer_surname,\n" +
                "       t.password  trainer_password,\n" +
                "       t.login     trainer_login,\n" +
                "       sc.club     club_name,\n" +
                "       sc.category category,\n" +
                "       sc.sport    sport_name\n" +
                "from sportsman_characteristic sc\n" +
                "         join person s on sc.id_sportsman = s.id\n" +
                "         join person t on sc.id_trainer = t.id";

        return query(sql, SportsmanCharacteristicDao::sportsmanCharacteristicRowMapper);
    }

    public static boolean delete(SportsmanCharacteristic sportsmanCharacteristic) {
        String sql = "select count(*) from sportsman_characteristic where id_sportsman = ?";
        List<Object> params = Collections.singletonList(sportsmanCharacteristic.getSportsman().getId());
        if (queryCount(sql, params) > 0) {
            return false;
        } else {
            String sql1 = "delete from sportsman_characteristic where id_sportsman = ?";
            query(sql1, params);
            return true;
        }
    }

    public static void create(SportsmanCharacteristic sportsmanCharacteristic) {
        String sql = "insert into sportsman_characteristic values (?,?,?,?,?)";
        List<Object> params = Arrays.asList(sportsmanCharacteristic.getSportsman().getId(),
                sportsmanCharacteristic.getSport().getName(), sportsmanCharacteristic.getCategory(),
                sportsmanCharacteristic.getTrainer().getId(), sportsmanCharacteristic.getClub().getName());
        query(sql, params);
    }

    public static void update(SportsmanCharacteristic oldSC, SportsmanCharacteristic newSc) {
        String sql = "update sportsman_characteristic set sport = ?, category = ?, id_trainer = ?, club = ?";
        PersonDao.updatePerson(newSc.getSportsman());
        List<Object> params = Arrays.asList(newSc.getSport().getName(), newSc.getCategory(),
                newSc.getTrainer().getId(), newSc.getClub().getName(), oldSC.getSportsman().getId());
        query(sql, params);
    }

    private static SportsmanCharacteristic sportsmanCharacteristicRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new SportsmanCharacteristic(PersonDao.trainerRowMapper(rs, rowNum),
                new Sport(rs.getString("sport_name")), PersonDao.sportsmanRowMapper(rs, rowNum),
                new Club(rs.getString("club_name")), rs.getInt("category"));
    }

    public static List<SportsmanCharacteristic> getBySportOrCategory(Sport sport, Integer from, Integer to) {
        if (from == null){
            from = DEFAULT_RIGHT_BORDER;
        }
        if (to == null){
            to = DEFAULT_LEFT_BORDER;
        }

        String sql = "" +
                "select s.name      sportsman_name,\n" +
                "       s.id        sportsman_id,\n" +
                "       s.surname   sportsman_surname,\n" +
                "       s.password  sportsman_page,\n" +
                "       s.login     sportsman_login,\n" +
                "       s.login     sportsman_password,\n" +
                "       t.name      trainer_name,\n" +
                "       t.id        trainer_id,\n" +
                "       t.surname   trainer_surname,\n" +
                "       t.password  trainer_password,\n" +
                "       t.login     trainer_login,\n" +
                "       sc.club     club_name,\n" +
                "       sc.category category,\n" +
                "       sc.sport    sport_name\n" +
                "from sportsman_characteristic sc\n" +
                "         join person s on sc.id_sportsman = s.id\n" +
                "         join person t on sc.id_trainer = t.id " +
                "where sc.sport = ? and sc.category >= ? and sc.category <= ?";

        List<Object> params = Arrays.asList(sport.getName(), from, to);

        return query(sql, params, SportsmanCharacteristicDao::sportsmanCharacteristicRowMapper);
    }
}
