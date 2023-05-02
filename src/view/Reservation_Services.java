package view;

import model.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class Reservation_Services extends JFrame {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private controller.ServicesController contr;

    private JLabel projects;
    private model.Services model;
    private JComboBox transport;
    private JComboBox menu;
    private JButton back;
    private view.Make_Reservation reservation;
    private JButton reserve;
    private JPanel basePanel;

    private JButton see_price;
    private JLabel price;
    private JLabel start;
    private JLabel finish;
    private JLabel hotel;
    private JLabel client;
    private JLabel menu_label;
    private JLabel transport_label;
    private JLabel hotel_label;

    public String getProject() {
        return this.projects.getText();
    }
    public void setHotel(String name) {this.hotel.setText(name);}

    public void setProject(String name) {
        this.projects.setText(name);
    }

    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Reservation_Services() throws SQLException, ClassNotFoundException {
        model = new Services();
        setDimension(800, 400);

        AddInComboTransport("plane");
        AddInComboTransport("train");
        AddInComboTransport("bus");

        AddInComboMenu("Ultra All Inclusive");
        AddInComboMenu("All Inclusive");
        AddInComboMenu("Only Breakfast");
        AddInComboMenu("Breakfast + Dinner");
        AddInComboMenu("Only Dinner");
    }

    public void AddInComboTransport(String name) {
        this.transport.addItem(name);
    }

    public void AddInComboMenu(String name) {
        this.menu.addItem(name);
    }

    public JComboBox getTransport() {
        return transport;
    }

    public JComboBox getMenu() {
        return menu;
    }

    public void AddReserveListener (ActionListener listener) {
        this.reserve.addActionListener(listener);
    }

    public void AddBackListener (ActionListener listener) {
        this.back.addActionListener(listener);
    }

    public void AddSeePriceListener (ActionListener listener) {
        this.see_price.addActionListener(listener);
    }

    public void showMessage (String message) {
        JOptionPane.showMessageDialog(view.Reservation_Services.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public int showMessage1 (String message) {
        return JOptionPane.showConfirmDialog(view.Reservation_Services.this, message, "Message", JOptionPane.YES_NO_OPTION);
    }

    public boolean getTrans() {
        if (this.transport.equals(""))
            return false;
        else
            return true;
    }

    public boolean getMn() {
        if (this.menu.equals(""))
            return false;
        else
            return true;
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public void setStart(String start) {
        this.start.setText(start);
    }

    public void setFinish(String stop) {
        this.finish.setText(stop);
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
                            getWidth(), getHeight(), new Color(255, 0, 0, 255), true);

                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

    }

    public void setClient(String name) {
        this.client.setText(name);
    }

    public String getClient() {
        return this.client.getText();
    }

    public void setMenu_label(String menu_label) {
        this.menu_label.setText(menu_label);
    }

    public void setTransport_label(String transport_label) {
        this.transport_label.setText(transport_label);
    }

    public void setHotel_label(String hotel_label) {
        this.hotel_label.setText(hotel_label);
    }

}

