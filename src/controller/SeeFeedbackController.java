package controller;

import org.jetbrains.annotations.NotNull;
import view.MainAgent;
import view.MainViewAgent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SeeFeedbackController {

    private view.See_feedback see_feedback;
    private view.MainViewAgent mainViewAgent;
    private view.MainAgent mainAgent;
    private model.Feedback model;
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static ResultSet result = null;

    public SeeFeedbackController(view.@NotNull See_feedback view) {
        this.see_feedback = view;
        model = new model.Feedback();
        view.AddBackListener(new back());
        view.AddChangeListener(new change());
        view.AddSeeFeedbackListener(new SeeFeedbackController.see_feedback());
    }

    class back implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            mainViewAgent = new MainViewAgent() {

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
            mainAgent.setName_of_agent(see_feedback.getAgent());
            mainViewAgent.setName_of_agent(see_feedback.getAgent());
            controller.AgentController agentController = new AgentController(mainViewAgent);
            mainViewAgent.setVisible(true);
            see_feedback.setVisible(false);
        }
    }

    class change implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            try {
                see_feedback.getIdfeed().removeAllItems();
            }
            catch (Exception e) {
                see_feedback.showMessage("You don`t have enough projects!");
                e.printStackTrace();
            }
        }
    }

    class see_feedback implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            try {
                Class.forName("org.postgresql.Driver");
                connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                statement = connect.createStatement();
                preparedStatement = connect.prepareStatement("select distinct travel.projects.project_name, travel.feedback.feedback_id from travel.projects inner join travel.feedback on project_id = feedback_project where project_name = ?");
                preparedStatement.setString(1, see_feedback.getFeedback_count().getItemAt(see_feedback.getFeedback_count().getSelectedIndex()).toString());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    if (!see_feedback.getIdfeed().getItemListeners().equals(resultSet.getInt("feedback_id")))
                    see_feedback.setIdfeed(resultSet.getInt("feedback_id"));
                }
                Class.forName("org.postgresql.Driver");
                connect = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0000");
                statement = connect.createStatement();
                preparedStatement = connect.prepareStatement("SELECT * FROM travel.feedback join travel.projects on feedback_project = project_id where feedback_id = ?");
                preparedStatement.setInt(1, (Integer) see_feedback.getIdfeed().getItemAt(see_feedback.getIdfeed().getSelectedIndex()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    see_feedback.setFeedback(resultSet.getString("feedback_text"));
                }
            } catch (Exception e) {
                see_feedback.showMessage("You don`t have another feedbacks!");
                e.printStackTrace();
            }
        }
    }
}

