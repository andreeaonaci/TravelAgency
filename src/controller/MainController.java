package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.io.Serializable;

public class MainController implements Serializable {
    @Serial
    private static final long serialVersionUID = 9177103522343277451L;
    private view.Main view_main;
    private static view.MainView main_view;
    private controller.RegisterController register_controller;
    private controller.LoginController loginControllerClient;
    private controller.LoginController loginControllerAgent;
    private view.Register register;
    private view.Login login;
    private boolean agent;

    public boolean isAgent() {
        return agent;
    }

    public void setAgent(boolean agent) {
        this.agent = agent;
    }

    public MainController(view.Main view_main) {
        this.view_main = view_main;
        view_main.setVisible(true);
        view_main.addLoginAgentListener(new login_agent());
        view_main.addLoginClientListener(new login_client());
        view_main.addRegisterListener(new Register());
    }

    class Register implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            register = new view.Register();
            register_controller = new RegisterController(register);
            register.setVisible(true);
            view_main.setVisible(false);
        }
    }

    class login_agent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            login = new view.Login(true);
            loginControllerAgent = new LoginController(login);
            setAgent(true);
            login.setVisible(true);
            view_main.setVisible(false);
        }
    }

    class login_client implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            login = new view.Login(false);
            loginControllerClient = new LoginController(login);
            setAgent(false);
            login.setVisible(true);
            view_main.setVisible(false);
        }
    }
}
