package controller;

import model.Projects;
import org.jetbrains.annotations.NotNull;
import view.MainClient;
import view.MainViewClient;
import view.Reservation_Services;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReservationController {

    private view.Make_Reservation view;
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private model.Reservation model;
    private model.Client model_client;
    private view.Reservation_Services services;
    private view.MainViewClient mainViewClient;

    private model.Projects model_project;
    public ReservationController(view.@NotNull Make_Reservation view){
        this.view = view;
        mainViewClient = new MainViewClient() {

        };
        model = new model.Reservation();
        model_project = new Projects();
        view.AddBackListener(new back());
        view.AddNextListener(new next());
    }

    int id = 4;
    class next implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            try {
                if (view.getProjects().getItemAt(view.getProjects().getSelectedIndex()).toString().isEmpty())
                    view.showMessage("Invalid Data!");
                else {
                    Class.forName("org.postgresql.Driver");
                    connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                    statement = connect.createStatement();
                    PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.reservation_aux");
                    resultSet = statement.executeQuery("select count(*) from travel.reservation_aux");
                    resultSet.next();
                    id = resultSet.getInt(1);
                    id++;
                    model.add_reservation_aux(id, model.findByNameIdProject(view.getProjects().getItemAt(view.getProjects().getSelectedIndex()).toString()), model.findByNameIdClient(view.getClient()));
                    services = new Reservation_Services();
                    services.setStart(model_project.findByNameStartingDate(view.getProjects().getItemAt(view.getProjects().getSelectedIndex()).toString()).toString());
                    services.setFinish(model_project.findByNameStopDate(view.getProjects().getItemAt(view.getProjects().getSelectedIndex()).toString()).toString());
                    controller.ServicesController servicesController = new ServicesController(services);
                    services.setProject(view.getProjects().getItemAt(view.getProjects().getSelectedIndex()).toString());
                    services.setHotel(model.findByNameHotelProject(view.getProjects().getItemAt(view.getProjects().getSelectedIndex()).toString()));
                    services.setVisible(true);
                    view.setVisible(false);
                    services.setClient(view.getClient());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private MainClient mainClient;
    class back implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
            if (view.showMessage1("Do you want to lose your changes?") == JOptionPane.YES_OPTION) {
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
                mainClient.setName_client(view.getClient());
                mainViewClient.setName_client(view.getClient());
                controller.ClientController clientController = new ClientController(mainViewClient);
                view.showMessage("You lose your reservation!");
                mainViewClient.setVisible(true);
                view.setVisible(false);
            }
        }
    }
}
