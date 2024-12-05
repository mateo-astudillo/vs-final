package vs.core.candidates;

import vs.core.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Candidates {
    public static ArrayList<Candidate> getCandidates() throws SQLException, ClassNotFoundException {
        ArrayList<Candidate> candidates = new ArrayList<>();
        Connection connection = Database.getConnection();
        String sql = "SELECT `id`, `president_first_name`, `president_last_name`, " +
        "`vice_president_first_name`, `vice_president_last_name`, `political_party`, `list` FROM `Candidate`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            candidates.add(new Candidate(
                resultSet.getInt("id"),
                resultSet.getString("president_first_name"),
                resultSet.getString("president_last_name"),
                resultSet.getString("vice_president_first_name"),
                resultSet.getString("vice_president_last_name"),
                resultSet.getString("political_party"),
                resultSet.getInt("list")
            ));
        }
        return candidates;
    }
}

