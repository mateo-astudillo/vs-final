package vs.core.votes;

import vs.core.Database;
import vs.core.candidates.Candidate;

import java.sql.*;
import java.time.LocalTime;

public class Votes {
    public static void voteCandidate(Candidate candidate) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO `Vote` (`candidate_id`, `createdAt`) VALUES (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, candidate.id());
        preparedStatement.setTime(2, Time.valueOf(LocalTime.now()));
        preparedStatement.execute();
        connection.close();
    }

    public static void voteBlank() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO `BlankVote` (`createdAt`) VALUES (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setTime(1, Time.valueOf(LocalTime.now()));
        preparedStatement.execute();
        connection.close();
    }

    public static int getVotesByCandidate(Candidate candidate) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT COUNT(`id`) FROM `Vote` WHERE `candidate_id` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, candidate.id());
        ResultSet resultSet = preparedStatement.executeQuery();
        int result = (resultSet.next()) ? resultSet.getInt(1) : 0;
        connection.close();
        return result;
    }

    public static int getBlankVotes() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT COUNT(`id`) FROM `BlankVote`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int result = (resultSet.next()) ? resultSet.getInt(1) : 0;
        connection.close();
        return result;
    }
}
