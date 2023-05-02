package model;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Projects {

    public int id;
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public String name;

    public java.sql.Date start;

    public java.sql.Date stop;

    public int distance;

    public Agent agent;

    public String getName() {
        return name;
    }

    public int findByNameId(String name) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.agents where agent_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            //resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getInt("agent_id");
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

    public int findByCountryId(String name) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.country where country_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            //resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getInt("country_id");
    }

    public int getDuration(@NotNull Date start, @NotNull Date stop) {
        long var = Math.abs(stop.getTime() - start.getTime());
        return (int) TimeUnit.DAYS.convert(var, TimeUnit.MILLISECONDS);
    }

    public Date findByNameStartingDate(String name) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.projects where project_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getDate("project_start");
    }

    public Date findByNameStopDate(String name) throws SQLException {
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
        return resultSet.getDate("project_stop");
    }

    public void add(int id, String name, int country, String hotel, int distance, Date start, Date stop, int agent) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.projects JOIN travel.country ON project_country = country_id");
            preparedStatement = connect.prepareStatement("insert into travel.projects values (?, ?, ?, ?, ?, ?, ?, ?)");
            resultSet = statement.executeQuery("SELECT * FROM travel.projects JOIN travel.country ON project_country = country_id");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name); //aici imi trebe cumva numarate
            preparedStatement.setInt(3, country);
            preparedStatement.setString(4, hotel);
            preparedStatement.setInt(5, distance);
            preparedStatement.setDate(6, start);
            preparedStatement.setDate(7, stop);
            preparedStatement.setInt(8, agent);
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from travel.projects");
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

    public int findByNameDurationProject(String name) throws SQLException {
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
        return getDuration(resultSet.getDate("project_start"), resultSet.getDate("project_stop"));
    }

}
