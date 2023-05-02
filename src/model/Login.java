package model;

import java.sql.*;
//import javax.swing.*;

public class Login {

    public int id;

    private Connection connect = null;
    private Statement statement = null;

    public String username;
    public String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login_mail) {
        this.username = login_mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String login_password) {
        this.password = login_password;
    }

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /*public void add(int id, String username, String password) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.login");

            preparedStatement = connect.prepareStatement("insert into travel.login values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            //if (resultSet.getMetaData().getColumnName(id) == null)
            preparedStatement.executeUpdate();
            //column = preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from travel.login");
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
*/
}
