package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class See_feedback extends JFrame {
     private Connection connect = null;
     private Statement statement = null;
     private PreparedStatement preparedStatement = null;
     private ResultSet resultSet = null;
     private JComboBox feedback_count;
     private JPanel basePanel;
    private JButton back;
     private JButton see_feedback;
     private JTable table;
    private JTextArea feedback;
    private JLabel agent;
    private JComboBox idfeed;
    private JButton change;
    private controller.FeedbackController feedbackController;

     public void setDimension(int w, int h) {
          add(basePanel);
          setBounds(300, 200, w, h);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
     }

    public String getAgent() {
        return agent.getText();
    }

    public void setAgent(String agent) {
        this.agent.setText(agent);
    }

    public JComboBox getIdfeed() {
        return idfeed;
    }

    public void setIdfeed(int idfeed) {
        this.idfeed.addItem(idfeed);
    }

    public See_feedback() {
         setDimension(1000, 300);
         try {
             Class.forName("org.postgresql.Driver");
             connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
             statement = connect.createStatement();
             resultSet = statement.executeQuery("select distinct travel.feedback.feedback_project, travel.projects.project_name from travel.feedback inner join travel.projects on feedback_project = project_id");

             while (resultSet.next()) {
                 feedback_count.addItem(resultSet.getString("project_name"));
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }
         finally {
             close();
         }
     }

     public void setFeedback(String text) {

         feedback.setText(text);
     }

     public JComboBox getFeedback_count() {
         return feedback_count;
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

     public void AddBackListener (ActionListener listener) {
          this.back.addActionListener(listener);
     }

     public void AddSeeFeedbackListener (ActionListener listener) {
          this.see_feedback.addActionListener(listener);
     }

     public void showMessage (String message) {
          JOptionPane.showMessageDialog(view.See_feedback.this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
     }

     public void AddChangeListener (ActionListener listener) {
         this.change.addActionListener(listener);
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
                            getWidth(), getHeight(), new Color(12, 154, 231, 255), true);

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

