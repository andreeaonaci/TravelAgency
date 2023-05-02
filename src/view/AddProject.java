package view;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddProject extends JFrame {
    public int id = 9;
    private JPanel basePanel;

    public String getAgent() {
        return this.agent.getText();
    }
    public JXDatePicker getStart() {
        return this.start;
    }
    public JXDatePicker getStop() {
        return this.stop;
    }
    private JLabel agent;
    private JTextField name;
    private JXDatePicker start;
    private JXDatePicker stop;
    private JButton addproject;
    private model.Projects project;
    private JButton back;
    private JTextField country;
    private JLabel distancee;
    private JTextField distance;
    private JTextField hotel;

    public void setAgent(String name) {
        this.agent.setText(name);
    }
    public String getDistance() {
        return this.distance.getText();
    }
    public String getHotel() {return this.hotel.getText(); }

    public String getNume() {
        return this.name.getText();
    }
    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(400, 300, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getCountry() {
        return this.country.getText();
    }

    public AddProject() {
        setDimension(600, 400);
        //this.agent.setText(name);
    }

    public void addAddprojectListener (ActionListener listener) {
        this.addproject.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        this.back.addActionListener(listener);
    }

    public void showMessage (String message) {
        JOptionPane.showMessageDialog(view.AddProject.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    public boolean getNme() {
        if (this.project.equals(""))
            return false;
        else return true;
    }

    public int showMessage1 (String message) {
        return JOptionPane.showConfirmDialog(view.AddProject.this, message, "Message", JOptionPane.YES_NO_OPTION);
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
                            getWidth(), getHeight(), new Color(255, 51, 105, 255), true);

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