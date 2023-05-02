package model;

import java.sql.*;

public class Agent extends Person {
    public Agent(String name, int id, String function, String phone, String password, String username) {super(name, id, function, phone, password, username);}

    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public Agent() {
    }
    @Override
    public void add(String name, int id, String function, String password, String username, String phone) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.agents");

            preparedStatement = connect.prepareStatement("insert into travel.agents values (?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, function);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, username);
            preparedStatement.setString(6, phone);
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from travel.agents");
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

    public String findByMailAgent(String mail) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.agents where agent_mail = ?");
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();
            //resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getString("agent_name");
    }
}
