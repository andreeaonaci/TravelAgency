package controller;

import org.jetbrains.annotations.NotNull;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterController {
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private view.Register view;
    private model.Client modelClient;
    private model.Agent modelAgent;
    private model.Login model_login;
    private model.Person model;
    private view.MainView mainview;

    public RegisterController(view.@NotNull Register view_register){
        this.view = view_register;
        modelClient = new model.Client();
        modelAgent = new model.Agent();
        model_login = new model.Login();
        model = new model.Person();
        view_register.AddRegisterListener(new register());
        view_register.AddBackListener(new back());
    }

    class back implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent arg0) {
            mainview = new MainView() {

            };
            controller.MainController mainController = new MainController(mainview);
            mainview.setVisible(true);
            view.setVisible(false);
        }
    }

    class register implements ActionListener {
        int id;
        public void actionPerformed (ActionEvent arg0) {
            try {
                int ok = 1; //for the tricky cases
                if (view.getFunction().isEmpty() || view.getUsername().isEmpty() || view.getPassword().isEmpty() || view.getNme().isEmpty() || view.getPhone().length() != 10) {
                    view.showMessage("Invalid data!");
                }
                else {
                    if (view.getPhone().length() != 10) {
                        ok = 0;
                        view.showMessage("Check for the phone number!");
                    }
                    if (!view.getUsername().contains("@")) {
                        ok = 0;
                        view.showMessage("The username/mail is not correct!");
                    }
                    if (view.getPassword().length() < 8) {
                        ok = 0;
                        view.showMessage("Set a longer password!");
                    }
                    if (!view.getFunction().equals("Client") && !view.getFunction().equals("Agent")) {
                        ok = 0;
                        view.showMessage("The function is not proper!");
                    }
                    if (ok == 1) {
                        Class.forName("org.postgresql.Driver");
                        connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                        statement = connect.createStatement();
                        PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.person");
                        resultSet = statement.executeQuery("select count(*) from travel.person");
                        resultSet.next();
                        id = resultSet.getInt(1);
                        id++;
                        model.add(view.getNme(), id, view.getFunction(), view.getPassword(), view.getUsername(), view.getPhone());
                        //model_login.add(id, view.getUsername(), view.getPassword());
                        if (view.getFunction().equals("Client"))
                            modelClient.add(view.getNme(), id, view.getFunction(), view.getPassword(), view.getUsername(), view.getPhone());
                        else
                            modelAgent.add(view.getNme(), id, view.getFunction(), view.getPassword(), view.getUsername(), view.getPhone());
                        view.showMessage("You registered successfully!");
                    }
               }
            } catch (Exception e) {
                view.showMessage("Something went wrong!");
                if (view.getPhone().length() != 10)
                    view.showMessage("Please introduce a valid number!");
                if (!view.getUsername().contains("@"))
                    view.showMessage("The mail is not good!");
                e.printStackTrace();
            }
        }
    }
}
