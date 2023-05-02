package model;

import java.sql.*;

public abstract class Hotel {
    private int hotel_id; //project
    private String name;
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public String getHot(String project) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
        statement = connect.createStatement();
        preparedStatement = connect.prepareStatement("SELECT * FROM travel.projects where project_name = ?");
        preparedStatement.setString(1, name);
        resultSet = preparedStatement.executeQuery();
        return resultSet.getString("project_hotel");
    }
}

