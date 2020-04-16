package dao;

import connection.JdbcConnection;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class AbstractDao {
    private static final Connection connection = JdbcConnection.getConnection();

     protected static <E> List<E> query(String sql, List<Object> params, RowMapper<E> rowMapper) {
        Connection conn = getConnection();
        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet resultSet = prepareStatement(preStatement, params).executeQuery();
            int row = 0;
            List<E> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet, row));
                row++;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static <E> List<E> query(String sql, RowMapper<E> rowMapper) {
        return query(sql, Collections.emptyList(), rowMapper);
    }

    protected static void query(String sql) {
         query(sql, Collections.emptyList());
    }


    protected static void query(String sql, List<Object> params) {
        Connection conn = getConnection();
        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            prepareStatement(preStatement, params);
            preStatement.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement prepareStatement(PreparedStatement statement, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            Object param = params.get(i);
            if (param instanceof String){
                statement.setString(i + 1, (String)param);
            } else if (param instanceof Integer){
                statement.setInt(i + 1, (Integer)param);
            } else if (param instanceof Instant){
                statement.setTimestamp(i + 1, Timestamp.from((Instant)param));
            }
        }
        return statement;
    }

    private static Connection getConnection() {
        return AbstractDao.connection; //TODO: connection pool
    }

}
