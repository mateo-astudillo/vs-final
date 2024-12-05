package vs.core.votingTable;

import vs.core.Database;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VotingTable {
    public static void openTable(int userId, LocalTime now) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO `VotingTable` " +
                "(`user_id`, `opening_time`, `closing_time`, `real_opening_time`, `date`) " +
                "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, userId);
        preparedStatement.setTime(2, Time.valueOf("08:00:00"));
        preparedStatement.setTime(3, Time.valueOf("18:00:00"));
        preparedStatement.setTime(4, Time.valueOf(now));
        preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
        preparedStatement.execute();
    }

    public static void close(int userId, LocalTime realOpeningTime) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "UPDATE `VotingTable` SET `real_closing_time` = ? WHERE `user_id` = ? AND `real_opening_time` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setTime(1, Time.valueOf(LocalTime.now()));
        preparedStatement.setLong(2, userId);
        preparedStatement.setTime(3, Time.valueOf(realOpeningTime));
        preparedStatement.execute();
    }
}
