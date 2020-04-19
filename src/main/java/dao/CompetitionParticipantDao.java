package dao;

import model.Competition;
import model.CompetitionParticipant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompetitionParticipantDao extends AbstractDao {
    public static List<CompetitionParticipant> getAllByCompetition(Competition competition){
        String sql = "" +
                "select p.ID       sportsman_id,\n" +
                "       p.ROLE     sportsman_role,\n" +
                "       p.LOGIN    sportsman_login,\n" +
                "       p.PASSWORD sportsman_password,\n" +
                "       p.SURNAME  sportsman_surname,\n" +
                "       p.NAME     sportsman_name,\n" +
                "       f.NAME     name,\n" +
                "       f.ADDRESS  address,\n" +
                "       f.KIND     kind,\n" +
                "       C2.ID    competition_id,\n" +
                "       c2.name competition_name,\n" +
                "       C2.SPORT competition_sport,\n" +
                "       c2.facility  competition_facility,\n" +
                "       C2.START_DATE competition_start_date,\n" +
                "       C2.FINISH_DATE competition_finish_date,\n" +
                "       pc.result result\n" +
                "from PARTICIPANT_COMPETITION pc\n" +
                "         join PERSON P on pc.ID_PARTICIPANT = P.ID\n" +
                "         join COMPETITION C2 on pc.ID_COMPETITION = C2.ID\n" +
                "         join FACILITY F on C2.FACILITY = F.NAME\n" +
                "where pc.ID_COMPETITION = ?";
        List<Object> params = Collections.singletonList(competition.getId().toString());
        return query(sql, params, CompetitionParticipantDao::competitionParticipantRowMapper);
    }

    private static CompetitionParticipant competitionParticipantRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new CompetitionParticipant(PersonDao.sportsmanRowMapper(rs, rowNum),
                CompetitionDao.competitionRowMapper(rs, rowNum), rs.getInt("result"));
    }

    public static void addParticipant(CompetitionParticipant competitionParticipant) {
        String sql = "insert into participant_competition values (?, ?, ?)";
        List<Object> params = Arrays.asList(competitionParticipant.getParticipant().getId().toString(),
                competitionParticipant.getCompetition().getId().toString(), competitionParticipant.getResult());
        query(sql, params);
    }

    public static void delete(CompetitionParticipant competitionParticipant) {
        String sql = "delete from participant_competition where id_participant = ? and  id_competition = ?";
        List<Object> params = Arrays.asList(competitionParticipant.getParticipant().getId(),
                competitionParticipant.getCompetition().getId());
        query(sql, params);
    }
}
