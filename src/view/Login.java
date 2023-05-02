/*package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel basePanel;
    private JTextField username;
    private JTextField password;
    private JButton login;

    *//*public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Login() {
        setDimension(600, 300);
    }*//*

    public void setUsername(String name) {
        username.setText(name);
    }

    public void setPassword(String pass) {
        password.setText(pass);
    }

    public void addloginListener(ActionListener listener) {
        this.login.addActionListener(listener);
    }

    JLabel lblErrorMessage = new JLabel("");

    public void setErrorMessage(String errorMessage) {
        lblErrorMessage.setText(errorMessage);
    }
}*/

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField username;
    private JPasswordField password;
    public String getUsername() {
        return this.username.getText();
    }
    public char[] getPassword() {
        return this.password.getPassword();
    }
    public boolean getUser() {
        if (this.username == null)
            return false;
        else return true;
    }

    public boolean getPass() {
        if (this.password == null)
            return false;
        else return true;
    }
    private JButton back;

    public String getFunction() {
        return function.getText();
    }

    private JButton login;
    public JLabel function;
    private JPanel basePanel;

    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Login(boolean agent) {
        setDimension(600, 300);
        if (agent == true)
            function.setText("Agent");
        else
            function.setText("Client");
    }

    public void showMessage (String message) {
        JOptionPane.showMessageDialog(view.Login.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    public void addLoginListener(ActionListener listener) {
        this.login.addActionListener(listener);
    }
    public void addBackButtonListener(ActionListener listener) {
        this.back.addActionListener(listener);
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
                            getWidth(), getHeight(), new Color(17, 45, 9, 255), true);

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
