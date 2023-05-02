package model;

import java.sql.*;

public class Reservation {

    public int id;

    public Projects project;

    public Reservation() {
    }

    public Reservation(int id, Projects project, Client client) {
        this.id = id;
        this.project = project;
        this.client = client;
    }

    public Client client;
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public int findByNameIdProject(String name) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.projects where project_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            //resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getInt("project_id");
    }

    public String findByNameHotelProject(String name) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.projects where project_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            //resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getString("project_hotel");
    }

    public int findByNameIdClient(String name) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.client where client_name = ?");
            preparedStatement.setString(1, name);
            resultSet = statement.executeQuery("select * from travel.client");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getInt("client_id");
    }

    public void add_reservation(int id, int project, int client) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.reservation");

            preparedStatement = connect.prepareStatement("insert into travel.reservation values (?, ?, ?)");

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, project);
            preparedStatement.setInt(3, client);
            preparedStatement.executeUpdate();
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

    public void add_reservation_aux(int id, int project, int client) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.reservation_aux");

            preparedStatement = connect.prepareStatement("insert into travel.reservation_aux values (?, ?, ?)");

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, project);
            preparedStatement.setInt(3, client);
            preparedStatement.executeUpdate();
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

    public void delete_reservation(int id, int project, int client) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.reservation join travel.services on services_reservation = reservation_id");

            preparedStatement = connect.prepareStatement("delete from travel.services row where services_reservation = ?");

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from travel.reservation where reservation_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

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
}
