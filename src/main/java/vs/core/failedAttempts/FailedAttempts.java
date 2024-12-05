package vs.core.failedAttempts;

import vs.core.Database;

import java.sql.*;
import java.time.LocalTime;

public class FailedAttempts {
    public static void register(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO `FailedAttempts` (`username`, `password`, `createdAt`) VALUES (?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setTime(3, Time.valueOf(LocalTime.now()));
        preparedStatement.execute();
    }
}
