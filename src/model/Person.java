package model;

import java.sql.*;

public class Person {

    public String name;

    public int id;

    protected String password;

    public String phone;

    public String function;

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String username;

    public String getName() {
        return name;
    }

    private static Connection connect;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private int column;

    public int getColumn() {
        return column;
    }

    public Person() {
    }

    public Person(String name, int id, String function, String password, String username, String phone) {
        this.name = name;
        this.id = id;
        this.function = function;
        this.phone = phone;
        this.password = password;
        this.username = username;
    }

    public void add(String name, int id, String function, String password, String username, String phone) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.person");

            preparedStatement = connect.prepareStatement("insert into travel.person values (?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id); //aici imi trebe cumva numarate
            preparedStatement.setString(3, function);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, username);
            preparedStatement.setString(6, phone);
            //if (resultSet.getMetaData().getColumnName(id) == null)
            preparedStatement.executeUpdate();
            //column = preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from travel.person");
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
    public void getCredentials(boolean agent){
        //if (agent == true)
            try {
                Class.forName("org.postgresql.Driver");
                connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres", "0000");
                statement = connect.createStatement();
                PreparedStatement select;
                if (agent == true)
                    select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.agents WHERE agent_mail = ?");
                else  {
                    select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.client WHERE client_mail = ?");
                }
                select.setString(1, username);
                ResultSet rs = select.executeQuery();
                if(rs.next()){
                    if (agent == true)
                        password = rs.getString("agent_password");
                    else
                        password = rs.getString("client_password");
                }
                else{
                    password = "";
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            finally{
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
}
