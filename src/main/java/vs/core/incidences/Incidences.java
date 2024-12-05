package vs.core.incidences;

import vs.core.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

public class Incidences {
    public static void register(int userId, Incidence incidence) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO `Incidence` (`user_id`, `name`, `observation`, `createdAt`) VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        preparedStatement.setString(2, incidence.name());
        preparedStatement.setString(3, incidence.observation());
        preparedStatement.setTime(4, Time.valueOf(LocalTime.now()));
        preparedStatement.execute();
    }
}
