package model;

import java.sql.*;
public class PriceHotel extends Hotel {
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    public float getPriceForHotelPerNight(String hotel) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
        statement = connect.createStatement();
        PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.projects");

        preparedStatement = connect.prepareStatement("select * from travel.projects join travel.country on project_country = country_id where project_hotel = ?");
        preparedStatement.setString(1, hotel);
        resultSet = preparedStatement.executeQuery();
        float price = 0;
        resultSet.next();
        if (resultSet.getString("country_name").equals("Japan"))
            price = 400;
        if (resultSet.getString("country_name").equals("Romania"))
            price = 200;
        if(resultSet.getString("country_name").equals("Germany"))
            price = 300;
        if(resultSet.getString("country_name").equals("China"))
            price = 600;
        if (resultSet.getString("country_name").equals("France"))
            price = 500;
        return price;
    }
}
