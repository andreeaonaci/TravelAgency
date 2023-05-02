package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class MainViewAgent extends JFrame implements MainAgent {
    private JPanel basePanel;
    private JButton see_feedback;
    private JButton log_out;
    private JLabel name_of_agent;

    public void setName_of_agent(String name) {
        name_of_agent.setText(name);
    }

    private view.See_feedback feedback;
    private view.Login for_name;

    private view.AddProject project;
    private JButton addproject;
    private JButton delete;

    public String getName_of_agent() {
        return this.name_of_agent.getText();
    }

    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MainViewAgent() {
        setDimension(600, 300);
    }

    public void addAddProjectListener (ActionListener listener) {
        this.addproject.addActionListener(listener);
    }

    public void addLogOutListener (ActionListener listener) {
        this.log_out.addActionListener(listener);
    }

    public void addSeeFeedbacksListener (ActionListener listener) {
        this.see_feedback.addActionListener(listener);
    }

    public void showMessage (String message) {
        JOptionPane.showMessageDialog(view.MainViewAgent.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public int showMessage1 (String message) {
        return JOptionPane.showConfirmDialog(view.MainViewAgent.this, message, "Message", JOptionPane.YES_NO_OPTION);
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
                            getWidth(), getHeight(), new Color(51, 116, 255, 255), true);

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

