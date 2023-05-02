package model;

import java.sql.*;

public class Price {
    private model.PriceHotel priceHotel;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private PreparedStatement preparedStatement1 = null;
    private PreparedStatement preparedStatement2 = null;
    private ResultSet resultSet = null;
    private ResultSet resultSet1 = null;
    private static ResultSet result = null;

    public int reservationId;

    public float price_menu = 0;
    public float price_transport = 0;
    public float price_hotel = 0;

    public void add(int id, float value) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.price");

            preparedStatement = connect.prepareStatement("insert into travel.price values (?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setFloat(2, value);
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from travel.services");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    int duration;

    public int findByIdProjectReservation(int id) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.reservation_aux where reservation_aux_id = ?");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            result.next();
            //System.out.println(result.getInt(2));
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
        //
        //System.out.println(result.getInt(2));
        return result.getInt(2);
    }

    public int findByIdDistanceProject(int id) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            //statement = connect.createStatement();
            preparedStatement1 = connect.prepareStatement("select * from travel.projects where project_id = ?");
            //System.out.println("ceva" + findByIdProjectReservation(id));
            preparedStatement1.setInt(1, findByIdProjectReservation(id));
            resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            //System.out.println(resultSet.getMetaData().getColumnName(1) + resultSet.getMetaData().getColumnName(2) + resultSet.getMetaData().getColumnName(3));
            //resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //resultSet.next();
        return resultSet.getInt(5);
    }

    public float calculatePrice(int service_id) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
        /*preparedStatement = connect.prepareStatement("SELECT * FROM travel.services join travel.price on services_id = price_id where services_id = ?");
        preparedStatement.setInt(1, service_id);*/
            preparedStatement2 = connect.prepareStatement("SELECT * FROM travel.services_aux where services_aux_id = ?");
            preparedStatement2.setInt(1, service_id);
            resultSet1 = preparedStatement2.executeQuery();
            resultSet1.next();
            duration = resultSet1.getInt("services_aux_project_duration");
            priceHotel = new PriceHotel();
            price_hotel = priceHotel.getPriceForHotelPerNight(resultSet1.getString("services_aux_hotel")) * duration;
            //while (resultSet.next()) {
            if (resultSet1.getString("services_aux_menu").equals("Ultra All Inclusive"))
                price_menu = 300;
            if (resultSet1.getString("services_aux_menu").equals("Only Breakfast"))
                price_menu = 50;
            if (resultSet1.getString("services_aux_menu").equals("Breakfast + Dinner"))
                price_menu = 80;
            if (resultSet1.getString("services_aux_menu").equals("Only Dinner"))
                price_menu = 40;
            if (resultSet1.getString("services_aux_menu").equals("All Inclusive"))
                price_menu = 200;
            if (resultSet1.getString("services_aux_transport").equals("plane"))
                price_transport = (float) (findByIdDistanceProject(service_id) * 0.5);
            else if (resultSet1.getString("services_aux_transport").equals("train"))
                price_transport = (float) (findByIdDistanceProject(service_id) * 0.4);
            else if (resultSet1.getString("services_aux_transport").equals("bus"))
                price_transport = (float) (findByIdDistanceProject(service_id) * 0.3);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        price_menu = price_menu * duration;
        return price_menu + price_transport + price_hotel;
    }

    public float getPrice_menu() {
        return price_menu;
    }

    public float getPrice_transport() {
        return price_transport;
    }

    public float getPrice_hotel() {
        return price_hotel;
    }
}
