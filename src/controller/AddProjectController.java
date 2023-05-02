package controller;

import org.jetbrains.annotations.NotNull;
import view.MainAgent;
import view.MainViewAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddProjectController {
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private view.AddProject view;
    private model.Projects model;
    private view.MainAgent mainAgent;
    private static view.MainViewAgent mainview;

    public AddProjectController(view.@NotNull AddProject view){
        this.view = view;
        model = new model.Projects();
        view.addAddprojectListener(new addproject());
        view.addBackListener(new back());
    }

    class back implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
            if (view.showMessage1("Do you want to lose your changes?") == JOptionPane.YES_OPTION) {
                mainview = new MainViewAgent() {

                };
                mainAgent = new MainAgent() {
                    @Override
                    public void setName_of_agent(String name) {

                    }

                    @Override
                    public String getName_of_agent() {
                        return null;
                    }

                    @Override
                    public void setDimension(int w, int h) {

                    }

                    @Override
                    public void addAddProjectListener(ActionListener listener) {

                    }

                    @Override
                    public void addLogOutListener(ActionListener listener) {

                    }

                    @Override
                    public void addSeeFeedbacksListener(ActionListener listener) {

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
                controller.AgentController agentController = new AgentController(mainview);
                mainview.setName_of_agent(view.getAgent());
                mainview.setVisible(true);
                mainAgent.setVisible(true);
                view.setVisible(false);
            }
        }
    }
    int id = 4;
    class addproject implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
            try {
                if (view.getAgent().isEmpty() || view.getHotel().isEmpty() || view.getNume().isEmpty() || !view.getStart().isValid() || !view.getStop().isValid() || view.getNume().isEmpty())
                    view.showMessage("Invaild data!");
                else {
                    Class.forName("org.postgresql.Driver");
                    connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                    statement = connect.createStatement();
                    PreparedStatement select = (PreparedStatement) connect.prepareStatement("SELECT * FROM travel.agents");
                    //preparedStatement.setInt(2, );
                    resultSet = statement.executeQuery("select * from travel.agents");
                    java.sql.Date start_date = new java.sql.Date(view.getStart().getDate().getTime());
                    java.sql.Date stop_date = new java.sql.Date(view.getStop().getDate().getTime());
                    model.add(id, view.getNume(), model.findByCountryId(view.getCountry()), view.getHotel(), Integer.parseInt(view.getDistance()), start_date, stop_date, model.findByNameId(view.getAgent()));
                    view.showMessage("Project added successfully!");
                }
                } catch(Exception e){
                    view.showMessage("Something went wrong! :(");
                    e.printStackTrace();
                }
        }
    }
}