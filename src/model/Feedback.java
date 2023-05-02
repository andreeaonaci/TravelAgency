package model;

import java.sql.*;

public class Feedback {

    public Client name;

    private Connection connect = null;
    private Statement statement = null;

    public int id;

    public String mail;

    public String feedback;

    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public String findByClientMail(String name) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.client where client_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            //resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet.next();
        return resultSet.getString("client_mail");
    }



    public int findByNameIdProject(String name) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM travel.projects where project_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resultSet.getInt("project_id");
    }

    public void add(int id, int project, String feedback, String name, String mail) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.feedback join travel.client where feedback_client = client_id");

            preparedStatement = connect.prepareStatement("insert into travel.feedback values (?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, project); //aici imi trebe cumva numarate
            preparedStatement.setString(3, feedback);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, mail);
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from travel.feedback");
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
