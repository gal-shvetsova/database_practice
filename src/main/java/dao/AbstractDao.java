package dao;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao {
    private static final Connection connection = JdbcConnection.getConnection();

     protected static <E> List<E> query(String sql, RowMapper<E> rowMapper) {
        Connection conn = getConnection();
        try {
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preStatement.executeQuery();
            int row = 0;
            List<E> result = new ArrayList<E>();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet, row));
                row++;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() {
        return AbstractDao.connection; //TODO: connection pool
    }

}
