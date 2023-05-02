package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class MainView extends JFrame implements Main {
    private JButton Register;
    private JPanel basePanel;
    private JButton login_agent;
    private JButton login_client;

    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MainView() {
        setDimension(600, 300);
    }

    public void addLoginClientListener (ActionListener listener) {
        this.login_client.addActionListener(listener);
    }

    public void addLoginAgentListener (ActionListener listener) {
        this.login_agent.addActionListener(listener);
    }

    public void addRegisterListener (ActionListener listener) {
        this.Register.addActionListener(listener);
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
                            getWidth(), getHeight(), new Color(80, 11, 217, 255), true);

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
