package model;

import java.sql.*;

public class Services {

    public int id;
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public String transport;

    public String menu;

    public String getTransport() {
        return transport;
    }

    public String getMenu() {
        return menu;
    }

    public void findByIdDurationProject(int id) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.projects where project_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        //return resultSet.getString("project_name");
    }

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
    //services
    public void add(int id, int project, int project_duration, String hotel, String transport, String menu) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.services");

            preparedStatement = connect.prepareStatement("insert into travel.services values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, project);
            preparedStatement.setInt(3, project_duration);
            preparedStatement.setString(4, hotel);
            preparedStatement.setString(5, transport);
            preparedStatement.setString(6, menu);
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

    public void addServicesAux(int id, int project, int project_duration, String hotel, String transport, String menu) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.services_aux");

            preparedStatement = connect.prepareStatement("insert into travel.services_aux values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, project);
            preparedStatement.setInt(3, project_duration);
            preparedStatement.setString(4, hotel);
            preparedStatement.setString(5, transport);
            preparedStatement.setString(6, menu);
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from travel.services_aux");
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
    //projects_services
    public void add(int id, int project) {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.project_services");

            preparedStatement = connect.prepareStatement("insert into travel.project_services values (?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, project);
            preparedStatement.executeUpdate();
            //resultSet = statement.executeQuery("select * from travel.project_services");
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
