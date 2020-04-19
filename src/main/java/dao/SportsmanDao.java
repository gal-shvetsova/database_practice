package dao;

import model.Club;
import model.Sport;
import model.Sportsman;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SportsmanDao extends AbstractDao {

    public static List<Sportsman> getAll() {
        String sql = "SELECT SPORTSMAN.ID       sportsman_id,\n" +
                "       SPORTSMAN.ROLE     sportsman_role,\n" +
                "       SPORTSMAN.LOGIN    sportsman_login,\n" +
                "       SPORTSMAN.PASSWORD sportsman_password,\n" +
                "       SPORTSMAN.SURNAME  sportsman_surname,\n" +
                "       SPORTSMAN.NAME     sportsman_name,\n" +
                "       TRAINER.ID         trainer_id,\n" +
                "       TRAINER.ROLE       trainer_role,\n" +
                "       TRAINER.LOGIN      trainer_login,\n" +
                "       TRAINER.PASSWORD   trainer_password,\n" +
                "       TRAINER.SURNAME    trainer_surname,\n" +
                "       TRAINER.NAME       trainer_name,\n" +
                "       CLUB.NAME          club_name,\n" +
                "       SPORT.NAME         sport_name,\n" +
                "       CATEGORY           category\n" +
                "FROM PERSON SPORTSMAN\n" +
                "         JOIN SPORTSMAN_CHARACTERISTIC CHARACTERISTIC ON SPORTSMAN.ID = CHARACTERISTIC.ID_SPORTSMAN\n" +
                "         JOIN CLUB CLUB ON CHARACTERISTIC.CLUB = CLUB.NAME\n" +
                "         JOIN SPORT SPORT ON SPORT.NAME = CHARACTERISTIC.SPORT\n" +
                "         JOIN PERSON TRAINER ON TRAINER.ID = CHARACTERISTIC.ID_TRAINER";
        return query(sql, SportsmanDao::sportsmanRowMapper);
    }

    public static void update(Sportsman newSportsman, Sportsman oldSportsman){
        final String sql = "UPDATE PERSON SET NAME = ?, SURNAME = ? WHERE ID = ?";
        List<Object> params = Arrays.asList(newSportsman.getName(),
                newSportsman.getSurname(), oldSportsman.getId());
        query(sql, params);
        final String sql1 = "UPDATE SPORTSMAN_CHARACTERISTIC SET SPORT = ?, CATEGORY = ?, ID_TRAINER = ?, CLUB = ? " +
                "WHERE ID_SPORTSMAN = ? AND ID_TRAINER = ?";
        List<Object> params1 = Arrays.asList(newSportsman.getSport().getName(), newSportsman.getCategory(),
                newSportsman.getTrainer().getId(), newSportsman.getClub().getName(),
                oldSportsman.getName(), oldSportsman.getTrainer().getId());
        query(sql1, params1);
    }

    private static Sportsman sportsmanRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Sportsman(PersonDao.sportsmanRowMapper(rs, rowNum), new Club(rs.getString("CLUB_NAME")),
                new Sport(rs.getString("SPORT_NAME")), rs.getInt("CATEGORY"), PersonDao.trainerRowMapper(rs, rowNum));
    }
}
