package controller;

import org.jetbrains.annotations.NotNull;
import view.MainView;
import view.MainViewAgent;
import view.MainViewClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController {

    private view.Login view_login;
    private static boolean type_of_login;
    private view.Main mainview;
    private model.Agent model_agent;
    private model.Client model_client;
    private view.MainViewAgent agents;
    private boolean authentification;
    private view.MainViewClient client;

    public LoginController(view.@NotNull Login view_login){
        this.view_login = view_login;
        model_client = new model.Client();
        model_agent = new model.Agent();
        agents = new MainViewAgent() {
        };
        view_login.addLoginListener(new login());
        view_login.addBackButtonListener(new back());
    }

    public void checkCredentials(String username, String password, @NotNull String function) {
        if (function.equals("Agent"))
            type_of_login = true;
        else
            type_of_login = false;

        if (type_of_login == true) {
            model_agent.setUsername(username);
            model_agent.getCredentials(type_of_login);

            if (password.equals(model_agent.getPassword())) {
                    authentification = true;
            } else {
                view_login.showMessage("Login Failed!");
                authentification = false;
            }
        }
        else {
            model_client.setUsername(username);
            model_client.getCredentials(type_of_login);

            if (password.equals(model_client.getPassword())) {
                authentification = true;
            } else {
                view_login.showMessage("Login Failed!");
                model_agent = new model.Agent();
                model_client = new model.Client();
                authentification = false;
            }
        }

    }

    class back implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
            mainview = new MainView() {

            };
            controller.MainController mainController = new MainController(mainview);
            mainview.setVisible(true);
            view_login.setVisible(false);
        }
    }

    class login implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (view_login.getUser() == false || view_login.getPass() == false)
                view_login.showMessage("Invalid data!");
            else {
                checkCredentials(view_login.getUsername(), new String(view_login.getPassword()), view_login.getFunction());
                if (type_of_login == false) {
                    if (authentification == true && view_login.getUsername() != null && view_login.getPassword() != null) {
                        client = new MainViewClient() {

                        };
                        try {
                            client.setName_client(model_client.findByMailClient(view_login.getUsername()));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        controller.ClientController clientController = new ClientController(client);
                        client.setVisible(true);
                        view_login.setVisible(false);
                    }
                } else {
                    if (authentification == true && !view_login.getUsername().isEmpty() && !view_login.getPassword().equals("")) {
                        //agents = new MainAgent();
                        try {
                            agents.setName_of_agent(model_agent.findByMailAgent(view_login.getUsername()));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        controller.AgentController agentController = new AgentController(agents);
                        agents.setVisible(true);
                        view_login.setVisible(false);
                    }
                    else {
                        view_login.showMessage("Something went wrong! :(");
                    }
                }
            }
        }
    }
}
