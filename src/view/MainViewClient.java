package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class MainViewClient extends JFrame implements MainClient{
    private JPanel basePanel;
    private JButton make_reservation;
    private JButton give_feedback;
    private JButton log_out;
    private JLabel name_client;

    private view.Give_Feedback feedback;

    private view.Login Log_out;

    private view.Make_Reservation reservation;

    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setName_client(String name_client) {
        this.name_client.setText(name_client);
    }

    public String getName_client() {
        return name_client.getText();
    }

    public MainViewClient() {
        setDimension(600, 300);
    }

    public void addLogOutListener (ActionListener listener) {
        this.log_out.addActionListener(listener);
    }

    public void addGiveFeedbackListener (ActionListener listener) {
        this.give_feedback.addActionListener(listener);
    }

    public void addMakeReservationListener (ActionListener listener) {
        this.make_reservation.addActionListener(listener);
    }

    public void showMessage (String message) {
        JOptionPane.showMessageDialog(view.MainViewClient.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public int showMessage1 (String message) {
        return JOptionPane.showConfirmDialog(view.MainViewClient.this, message, "Message", JOptionPane.YES_NO_OPTION);
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
                            getWidth(), getHeight(), new Color(241, 255, 51, 255), true);

                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}