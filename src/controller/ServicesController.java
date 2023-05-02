package controller;

import model.Reservation;
import org.jetbrains.annotations.NotNull;
import view.MainClient;
import view.MainViewClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ServicesController {

    private view.Reservation_Services view;
    private view.Make_Reservation reservation;
    private model.Price model_price;
    private view.MainViewClient mainViewClient;
    private model.Projects model_project;
    private model.Services model;
    private model.Reservation model_reservation;
    private MainClient viewClient;

    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public ServicesController(view.@NotNull Reservation_Services view) throws SQLException {
        this.view = view;
        model = new model.Services();
        model_reservation = new model.Reservation();
        model_price = new model.Price();
        model_project = new model.Projects();
        view.AddReserveListener(new reserve());
        view.AddBackListener(new back());
        view.AddSeePriceListener(new see_price());
    }

    class back implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
            mainViewClient = new MainViewClient() {

            };
            viewClient = new MainClient() {
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
            mainViewClient.setName_client(view.getClient());
            mainViewClient.setName_client(view.getClient());
            controller.ClientController clientController = new ClientController(mainViewClient);
            mainViewClient.setVisible(true);
            view.setVisible(false);
        }
    }
    int id = 5;
    float price;
    boolean see = false;
    class reserve implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            try {
                if (view.getTransport().getItemAt(view.getTransport().getSelectedIndex()).toString().isEmpty() || view.getMenu().getItemAt(view.getMenu().getSelectedIndex()).toString().isEmpty())
                    view.showMessage("Invalid Data!");
                else {
                    if (see == false) {
                        if (view.showMessage1("You are going to make a reservation without verifying the price?") == JOptionPane.YES_OPTION) {
                            see = true;
                            Class.forName("org.postgresql.Driver");
                            connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                            statement = connect.createStatement();
                            PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.services");
                            resultSet = statement.executeQuery("select count(*) from travel.services");
                            resultSet.next();
                            id = resultSet.getInt(1);
                            id++;
                            Date start_date = new Date(model_project.findByNameStartingDate(view.getProject()).getTime());
                            Date stop_date = new Date(model_project.findByNameStopDate(view.getProject()).getTime());
                            model_reservation = new Reservation();
                            model.addServicesAux(id, id, model_project.getDuration(start_date, stop_date), model_reservation.findByNameHotelProject(view.getProject()).toString(), view.getTransport().getItemAt(view.getTransport().getSelectedIndex()).toString(), view.getMenu().getItemAt(view.getMenu().getSelectedIndex()).toString());
                            model.add(id, model_project.findByNameIdProject(view.getProject()));
                            model.add(id, id, model_project.getDuration(start_date, stop_date), model_reservation.findByNameHotelProject(view.getProject()).toString(), view.getTransport().getItemAt(view.getTransport().getSelectedIndex()).toString(), view.getMenu().getItemAt(view.getMenu().getSelectedIndex()).toString());
                            model_reservation.add_reservation(id, model.findByNameIdProject(view.getProject()), model.findByNameIdClient(view.getClient()));
                            if (see == true)
                                model_price.add(id, model_price.calculatePrice(id));
                            else
                                view.showMessage("You did the reservation without checking the price!");
                            view.showMessage("Thank you for your reservation!");
                            mainViewClient = new MainViewClient() {

                            };
                            viewClient = new MainClient() {
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
                            ClientController clientController = new ClientController(mainViewClient);
                            mainViewClient.setName_client(view.getClient());
                            mainViewClient.setVisible(true);
                            view.setVisible(false);
                        }
                    }
                    else {
                        Class.forName("org.postgresql.Driver");
                        connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                        statement = connect.createStatement();
                        PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.services");
                        resultSet = statement.executeQuery("select count(*) from travel.services");
                        resultSet.next();
                        id = resultSet.getInt(1);
                        id++;
                        Date start_date = new Date(model_project.findByNameStartingDate(view.getProject()).getTime());
                        Date stop_date = new Date(model_project.findByNameStopDate(view.getProject()).getTime());
                        model_reservation = new Reservation();
                        //model.addServicesAux(id, id, model_project.getDuration(start_date, stop_date), model_reservation.findByNameHotelProject(view.getProject()).toString(), view.getTransport().getItemAt(view.getTransport().getSelectedIndex()).toString(), view.getMenu().getItemAt(view.getMenu().getSelectedIndex()).toString());
                        model.add(id, model_project.findByNameIdProject(view.getProject()));
                        model.add(id, id, model_project.getDuration(start_date, stop_date), model_reservation.findByNameHotelProject(view.getProject()).toString(), view.getTransport().getItemAt(view.getTransport().getSelectedIndex()).toString(), view.getMenu().getItemAt(view.getMenu().getSelectedIndex()).toString());
                        model_reservation.add_reservation(id, model.findByNameIdProject(view.getProject()), model.findByNameIdClient(view.getClient()));
                        if (see == true)
                            model_price.add(id, model_price.calculatePrice(id));
                        else
                            view.showMessage("You did the reservation without checking the price!");
                        view.showMessage("Thank you for your reservation!");
                        mainViewClient = new MainViewClient() {

                        };
                        viewClient = new MainClient() {
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
                        ClientController clientController = new ClientController(mainViewClient);
                        mainViewClient.setName_client(view.getClient());
                        mainViewClient.setVisible(true);
                        view.setVisible(false);
                    }
                }
            } catch (Exception e) {
                view.showMessage("Something went wrong! :(");
                e.printStackTrace();
            }
        }
    }
    class see_price implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            try {
                if (see == false) {
                    Class.forName("org.postgresql.Driver");
                    connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                    statement = connect.createStatement();
                    PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.services_aux");
                    resultSet = statement.executeQuery("select count(*) from travel.services_aux");
                    resultSet.next();
                    id = resultSet.getInt(1);
                    id++;
                    java.sql.Date start_date = new java.sql.Date(model_project.findByNameStartingDate(view.getProject()).getTime());
                    java.sql.Date stop_date = new java.sql.Date(model_project.findByNameStopDate(view.getProject()).getTime());
                    model.addServicesAux(id, id, model_project.getDuration(start_date, stop_date), model_reservation.findByNameHotelProject(view.getProject()).toString(), view.getTransport().getItemAt(view.getTransport().getSelectedIndex()).toString(), view.getMenu().getItemAt(view.getMenu().getSelectedIndex()).toString());
                }
                see = true;
                view.showMessage("Thank you for verifying the price!");
                    Class.forName("org.postgresql.Driver");
                    connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                    statement = connect.createStatement();
                    preparedStatement = connect.prepareStatement("SELECT * FROM travel.services_aux join travel.price on services_aux_id = price_id where price_value = ?");
                    preparedStatement.setFloat(1, model_price.calculatePrice(id));
                    view.setHotel_label(new String(String.valueOf(model_price.getPrice_hotel())));
                    view.setTransport_label(new String(String.valueOf(model_price.getPrice_transport())));
                    view.setMenu_label(new String(String.valueOf(model_price.getPrice_menu())));
                    view.setPrice(new String(String.valueOf(model_price.calculatePrice(id))));
            } catch (Exception e) {
                view.showMessage("Something went wrong!");
                e.printStackTrace();
            }
        }
    }
}
