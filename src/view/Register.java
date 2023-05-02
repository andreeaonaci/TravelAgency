package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    public int id = 9;
    //private JButton registerButton;
    private JTextField function;
    private JTextField phone;
    private JTextField name;
    private JTextField username;
    private JTextField password;
    private JButton back;
    private view.MainView mainview;
    private JButton register;
    private JPanel basePanel;

    public String getFunction() {
        return function.getText();
    }
    public String getPhone() {
        return phone.getText();
    }
    public String getNme() {
        return name.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Register() {
        setDimension(700, 400);
    }

    public void AddRegisterListener (ActionListener listener) {
        this.register.addActionListener(listener);
    }

    public void AddBackListener (ActionListener listener) {
        this.back.addActionListener(listener);
    }

    public void showMessage (String message) {
        JOptionPane.showMessageDialog(view.Register.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        basePanel = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 155;
                    final int G = 100;
                    final int B = 255;

                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                            getWidth(), getHeight(), new Color(R, G, B, 255), true);

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

        basePanel.setLayout(new GridBagLayout());
    }
    public boolean getNaame() {
        if (this.name.equals(""))
            return false;
        else
            return true;
    }
    public boolean getPass() {
        if (this.password.equals(""))
            return false;
        else
            return true;
    }
    public boolean getPhonee() {
        if (this.phone.equals(""))
            return false;
        else
            return true;
    }
    public boolean getUser() {
        if (this.username.equals(""))
            return false;
        else
            return true;
    }
    public boolean getFunct() {
        if (this.function.equals(""))
            return false;
        else
            return true;
    }
}


