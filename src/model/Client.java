package model;

import java.sql.*;

public class Client extends Person {
    public Client(String name, int id, String function, String phone, String password, String username) {
        super(name, id, function, phone, password, username);
    }

    private static Connection connect = null;

    public Client() {
    }

    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public void add(String name, int id, String function, String password, String username, String phone) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.client");

            preparedStatement = connect
                    .prepareStatement("insert into travel.client values (?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id); //aici imi trebe cumva numarate
            preparedStatement.setString(3, function);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, username);
            preparedStatement.setString(6, phone);
            //if (resultSet.getMetaData().getColumnName(id) == null)
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from travel.client");
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

    public String findByMailClient(String mail) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.client where client_mail = ?");
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getString("client_name");
    }
}

