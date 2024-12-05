package vs.core.users;

import vs.core.Database;

import java.sql.*;

public class Users {
    public static boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT COUNT(`id`) FROM `User` WHERE `username` = ? AND `password` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return resultSet.getInt(1) == 1;
        return false;
    }

    public static int getUserId(String username) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT `id` FROM `User` WHERE `username` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }
}
