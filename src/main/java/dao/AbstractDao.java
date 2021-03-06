package dao;

import connection.JdbcConnection;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

abstract public class AbstractDao {
    private static final JdbcConnection connection = Service.getConnection();

    protected static <E> List<E> query(String sql, List<Object> params, RowMapper<E> rowMapper) throws SQLException {
        Connection conn = getConnection().getConnection();
        PreparedStatement preStatement = conn.prepareStatement(sql);
        ResultSet resultSet = prepareStatement(preStatement, params).executeQuery();
        int row = 0;
        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(rowMapper.mapRow(resultSet, row));
            row++;
        }
        return result;
    }


    protected static <E> List<E> query(String sql, RowMapper<E> rowMapper) throws SQLException {
        return query(sql, Collections.emptyList(), rowMapper);
    }

    protected static void query(String sql) throws SQLException {
        query(sql, Collections.emptyList());
    }

    protected static int queryCount(String sql, List<Object> params) throws SQLException {
        Connection conn = getConnection().getConnection();
        PreparedStatement preStatement = conn.prepareStatement(sql);
        ResultSet resultSet = prepareStatement(preStatement, params).executeQuery();
        int result = -1;
        while (resultSet.next()) {
            result = entityCountRowMapper(resultSet, 0);
        }
        return result;
    }

    protected static void query(String sql, List<Object> params) throws SQLException {
        Connection conn = getConnection().getConnection();
        PreparedStatement preStatement = conn.prepareStatement(sql);
        prepareStatement(preStatement, params);
        preStatement.executeQuery();
    }

    private static PreparedStatement prepareStatement(PreparedStatement statement, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            Object param = params.get(i);
            if (param instanceof UUID) {
                statement.setString(i + 1, ((UUID) param).toString());
            } else if (param instanceof String) {
                statement.setString(i + 1, (String) param);
            } else if (param instanceof Integer) {
                statement.setInt(i + 1, (Integer) param);
            } else if (param instanceof Instant) {
                statement.setTimestamp(i + 1, Timestamp.from((Instant) param));
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return statement;
    }

    private static JdbcConnection getConnection() {
        return AbstractDao.connection; //TODO: connection pool
    }

    protected static int entityCountRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("count");
    }


}
