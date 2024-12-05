package vs.core.persons;

import vs.core.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Persons {
    public static boolean validate(int document) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT COUNT(`document`) FROM `Person` WHERE `document` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, document);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = resultSet.next() && resultSet.getInt(1) == 1;
        connection.close();
        return result;
    }

    public static Person get(int document) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT `first_name`, `last_name`, `year_of_birth`, `document_type`, `circuit`, `gender`, `address` " +
        "FROM `Person` WHERE `document` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, document);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Person person = new Person(
                document,
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getInt("year_of_birth"),
                resultSet.getString("document_type"),
                resultSet.getString("circuit"),
                resultSet.getString("gender"),
                resultSet.getString("address")
        );
        connection.close();
        return person;
    }

    public static boolean hasBeenCancelled(int document) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT `cancelled` FROM `Person` WHERE `document` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, document);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = resultSet.next() && resultSet.getInt(1) == 1;
        connection.close();
        return result;
    }

    public static boolean hasVoted(int document) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT `voted` FROM `Person` WHERE `document` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, document);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = resultSet.next() && resultSet.getInt(1) == 1;
        connection.close();
        return result;
    }

    public static void cancel(Person currentPerson) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "UPDATE `Person` SET `cancelled` = 1 WHERE `document` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, currentPerson.document());
        preparedStatement.execute();
        connection.close();
    }

    public static void setVoted(Person person) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "UPDATE `Person` SET `voted` = 1 WHERE `document` = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, person.document());
        preparedStatement.execute();
        connection.close();
    }

    public static int getTotalNumber() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT COUNT(`document`) FROM `Person`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int result = (resultSet.next()) ? resultSet.getInt(1) : 0;
        connection.close();
        return result;
    }

    public static int getNoVote() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT COUNT(`document`) FROM `Person` WHERE `voted` = 0 AND `cancelled` = 0;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int result = (resultSet.next()) ? resultSet.getInt(1) : 0;
        connection.close();
        return result;
    }

    public static int getTotalCancelled() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        String sql = "SELECT COUNT(`document`) FROM `Person` WHERE `cancelled` = 1;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int result = (resultSet.next()) ? resultSet.getInt(1) : 0;
        connection.close();
        return result;
    }
}
