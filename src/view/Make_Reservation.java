package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class Make_Reservation extends JFrame {

    private JLabel client;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private view.MainViewClient mainview;
    private view.Reservation_Services services;
    private JComboBox projects;
    private JButton next;
    private JButton back;
    private JPanel basePanel;

    public void setClient(String name) {
        this.client.setText(name);
    }

    public String getClient() {
        return this.client.getText();
    }

    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Make_Reservation() {
        setDimension(600, 300);
    try {
        Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
        statement = connect.createStatement();
        resultSet = statement.executeQuery("select * from travel.projects");

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
    }

    public JComboBox getProjects() {
        return projects;
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
    public void showMessage (String message) {
        JOptionPane.showMessageDialog(view.Make_Reservation.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public int showMessage1 (String message) {
        return JOptionPane.showConfirmDialog(view.Make_Reservation.this, message, "Message", JOptionPane.YES_NO_OPTION);
    }

    public void AddNextListener (ActionListener listener) {
        this.next.addActionListener(listener);
    }

    public void AddBackListener (ActionListener listener) {
        this.back.addActionListener(listener);
    }

    public boolean getProj() {
        if (this.projects == null)
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
                            getWidth(), getHeight(), new Color(84, 186, 50, 255), true);

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

