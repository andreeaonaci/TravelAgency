package controller;

import org.jetbrains.annotations.NotNull;
import view.MainClient;
import view.MainViewClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FeedbackController {

    private view.Give_Feedback give_feedback;
    private view.MainViewClient mainViewClient;
    private model.Feedback model;
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public FeedbackController(view.@NotNull Give_Feedback view) throws SQLException {
        this.give_feedback = view;
        model = new model.Feedback();
        view.AddBackListener(new back());
        view.AddSubmitListener(new submit());
        view.addProjects(view.findByNameIdClient(view.getClient()));
    }
    private MainClient mainClient;
    class back implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
            if (give_feedback.showMessage1("Are you sure you want to exit?") == JOptionPane.YES_OPTION) {
                mainViewClient = new MainViewClient() {

                };
                mainClient = new MainClient() {
                    @Override
                    public void setDimension(int w, int h) {

                    }

                    @Override
                    public void setName_client(String name_client) {

                    }

                    @Override
                    public String getName_client() {
                        return null;
                    }

                    @Override
                    public void addLogOutListener(ActionListener listener) {

                    }

                    @Override
                    public void addGiveFeedbackListener(ActionListener listener) {

                    }

                    @Override
                    public void addMakeReservationListener(ActionListener listener) {

                    }

                    @Override
                    public void showMessage(String message) {

                    }

                    @Override
                    public void setVisible(boolean b) {

                    }

                    @Override
                    public int showMessage1(String text) {
                        return 0;
                    }
                };
                mainClient.setName_client(give_feedback.getClient());
                mainViewClient.setName_client(give_feedback.getClient());
                controller.ClientController clientController = new ClientController(mainViewClient);
                mainViewClient.setVisible(true);
                give_feedback.setVisible(false);
            }
        }
    }
    int id = 4;
    class submit implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
            try {
                if (give_feedback.getProjects().getItemAt(give_feedback.getProjects().getSelectedIndex()).toString().isEmpty() || give_feedback.getFeedback().isEmpty())
                    give_feedback.showMessage("Invalid Data!");
                else {
                    Class.forName("org.postgresql.Driver");
                    connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                    statement = connect.createStatement();
                    PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.feedback");
                    resultSet = statement.executeQuery("select count(*) from travel.feedback");
                    resultSet.next();
                    id = resultSet.getInt(1);
                    id++;
                    model.add(id, model.findByNameIdProject(give_feedback.getProjects().getItemAt(give_feedback.getProjects().getSelectedIndex()).toString()), give_feedback.getFeedback(), give_feedback.getClient(), give_feedback.getMail());
                    mainViewClient = new MainViewClient() {
                        
                    };
                    controller.ClientController clientController = new ClientController(mainViewClient);
                    mainViewClient.setName_client(give_feedback.getClient());
                    give_feedback.showMessage("Thank you for your feedback!");
                    mainViewClient.setVisible(true);
                    give_feedback.setVisible(false);
                }
            } catch (Exception e) {
                give_feedback.showMessage("Something went wrong! :(");
                e.printStackTrace();
            }
        }
    }
}
