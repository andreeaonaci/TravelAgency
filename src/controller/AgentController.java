package controller;

import org.jetbrains.annotations.NotNull;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AgentController implements Serializable {
    @Serial
    private static final long serialVersionUID = 9177103522343277451L;
    private view.MainViewAgent mainviewAgent;
    private view.MainAgent mainAgent;
    private model.Agent model_agent;
    private view.Main Log_out;
    private view.MainView mainView;
    private view.See_feedback feedback;
    private view.AddProject project;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public AgentController(view.@NotNull MainAgent view_agent){
        this.mainAgent = view_agent;
        mainviewAgent = new MainViewAgent() {
            @Override
            public void setName_of_agent(String name) {
                super.setName_of_agent(name);
            }

            @Override
            public String getName_of_agent() {
                return super.getName_of_agent();
            }

            @Override
            public void setDimension(int w, int h) {
                super.setDimension(w, h);
            }

            @Override
            public void addAddProjectListener(ActionListener listener) {
                super.addAddProjectListener(listener);
            }

            @Override
            public void addLogOutListener(ActionListener listener) {
                super.addLogOutListener(listener);
            }

            @Override
            public void addSeeFeedbacksListener(ActionListener listener) {
                super.addSeeFeedbacksListener(listener);
            }

            @Override
            public void showMessage(String message) {
                super.showMessage(message);
            }

            @Override
            public void setVisible(boolean b) {
                super.setVisible(b);
            }
        };
        model_agent = new model.Agent();
        view_agent.addAddProjectListener(new addproject());
        view_agent.addLogOutListener(new log_out());
        view_agent.addSeeFeedbacksListener(new see_feedback());
    }

    class addproject implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            project = new AddProject();
            controller.AddProjectController addProjectController = new AddProjectController(project);
            project.setAgent(mainAgent.getName_of_agent());
            project.setVisible(true);
            mainviewAgent.setVisible(false);
            mainAgent.setVisible(false);
        }
    }

    class log_out implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (mainviewAgent.showMessage1("Are you sure you want to log out?") == JOptionPane.YES_OPTION) {
                Log_out = new view.MainView() {

                };
                mainView = new MainView() {
                    @Override
                    public void setDimension(int w, int h) {
                        super.setDimension(w, h);
                    }

                    @Override
                    public void addLoginClientListener(ActionListener listener) {
                        super.addLoginClientListener(listener);
                    }

                    @Override
                    public void addLoginAgentListener(ActionListener listener) {
                        super.addLoginAgentListener(listener);
                    }

                    @Override
                    public void addRegisterListener(ActionListener listener) {
                        super.addRegisterListener(listener);
                    }
                };
                controller.MainController mainController = new MainController(Log_out);
                mainView.setVisible(true);
                Log_out.setVisible(true);
                mainviewAgent.showMessage("You logged out succesfully!");
                mainviewAgent.setVisible(false);
            }
        }
    }

    class see_feedback implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            feedback = new view.See_feedback();
            feedback.setAgent(mainAgent.getName_of_agent());
            controller.SeeFeedbackController seeFeedbackController = new SeeFeedbackController(feedback);
            feedback.setVisible(true);
            mainviewAgent.setVisible(false);
            mainAgent.setVisible(false);
        }
    }
}
