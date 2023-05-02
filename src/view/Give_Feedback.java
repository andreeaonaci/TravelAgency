package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class Give_Feedback extends JFrame {

    private JLabel client;
    private PreparedStatement preparedStatement1 = null;

    private ResultSet resultSet1 = null;
    private JLabel mail;
    private JTextArea feedback;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    private ResultSet resultSet = null;

    private view.MainViewClient mainview;
    private JComboBox projects;
    private JButton back;
    private JButton submit;
    private JPanel basePanel;

    public String getClient() {
        return this.client.getText();
    }

    public void setClient(String name) {this.client.setText(name);}

    public String getMail() {
        return mail.getText();
    }

    public void setMail (String mail) {this.mail.setText(mail);}

    public String getFeedback() {
        return feedback.getText();
    }

    public JComboBox getProjects() {
        return projects;
    }
    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public int findByNameIdClient(String client) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement1 = connect.prepareStatement("SELECT * FROM travel.client where client_name = ?");
            preparedStatement1.setString(1, client);
            resultSet1 = preparedStatement1.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resultSet1.next();
        return resultSet1.getInt("client_id");
    }

    public int showMessage1 (String message) {
        return JOptionPane.showConfirmDialog(view.Give_Feedback.this, message, "Message", JOptionPane.YES_NO_OPTION);
    }

    public void addProjects(int id) {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("select distinct travel.reservation.reservation_customer, travel.projects.project_name from travel.projects inner join travel.reservation on project_id = reservation_project where reservation_customer = ?"); //not necessary, already unique
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                projects.addItem(resultSet.getString("project_name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            close();
        }
    }

    /*public void addProjects(ResultSet result) {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from travel.projects");
            //System.out.println("aici");
            while (resultSet.next())
                projects.addItem(resultSet.getString(2));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            close();
        }
    }*/

    public Give_Feedback() throws Exception {
        setDimension(600, 300);
        //addProjects(findByNameIdClient(getClient()));

        /*basePanel.addComponentListener(new ComponentAdapter() {
        });*/
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public void AddSubmitListener (ActionListener listener) {
        this.submit.addActionListener(listener);
    }

    public void AddBackListener (ActionListener listener) {
        this.back.addActionListener(listener);
    }

    public void showMessage (String message) {
        JOptionPane.showMessageDialog(view.Give_Feedback.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean getProj() {
        if (this.projects == null)
            return false;
        else
            return true;
    }

    public boolean getFb() {
        if (this.feedback.getText().equals(""))
            return false;
        else
            return true;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        basePanel = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 55;
                    final int G = 100;
                    final int B = 255;

                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                            getWidth(), getHeight(), new Color(51, 249, 255, 255), true);

                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
    }

}


