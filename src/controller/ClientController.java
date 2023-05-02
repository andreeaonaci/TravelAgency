package controller;

import org.jetbrains.annotations.NotNull;
import view.Give_Feedback;
import view.Main;
import view.MainViewClient;
import view.Make_Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.io.Serializable;

public class ClientController implements Serializable {
    @Serial
    private static final long serialVersionUID = 9177103522343277451L;
    private view.MainViewClient mainViewClient;
    private view.MainClient viewClient;
    private view.Make_Reservation reservation;
    private model.Feedback model_feedback;
    private view.Give_Feedback feedback;
    private view.MainView Log_out;
    private view.Main log_out;

    public ClientController(view.@NotNull MainClient mainClient) {
        this.viewClient = mainClient;
        mainViewClient = new MainViewClient() {
            @Override
            public void setDimension(int w, int h) {
                super.setDimension(w, h);
            }

            @Override
            public void setName_client(String name_client) {
                super.setName_client(name_client);
            }

            @Override
            public String getName_client() {
                return super.getName_client();
            }

            @Override
            public void addLogOutListener(ActionListener listener) {
                super.addLogOutListener(listener);
            }

            @Override
            public void addGiveFeedbackListener(ActionListener listener) {
                super.addGiveFeedbackListener(listener);
            }

            @Override
            public void addMakeReservationListener(ActionListener listener) {
                super.addMakeReservationListener(listener);
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
        viewClient.setVisible(true);
        mainClient.addGiveFeedbackListener(new give_feedback());
        mainClient.addLogOutListener(new log_out());
        mainClient.addMakeReservationListener(new make_reservation());
    }

    class make_reservation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                reservation = new Make_Reservation();
                reservation.setClient(viewClient.getName_client());
                controller.ReservationController reservationController = new ReservationController(reservation);
                reservation.setVisible(true);
                //reservation.setClient(viewClient.getName_client());
                viewClient.setVisible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class give_feedback implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                feedback = new Give_Feedback();
                model_feedback = new model.Feedback();
                feedback.setClient(viewClient.getName_client());
                feedback.setMail(model_feedback.findByClientMail(viewClient.getName_client()));
                controller.FeedbackController feedbackController = new FeedbackController(feedback);
                feedback.setVisible(true);
                mainViewClient.setVisible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    class log_out implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (mainViewClient.showMessage1("Are you sure you want to log out") == JOptionPane.YES_OPTION) {
                Log_out = new view.MainView() {

                };
                log_out = new Main() {
                    @Override
                    public void setDimension(int w, int h) {

                    }

                    @Override
                    public void addLoginClientListener(ActionListener listener) {

                    }

                    @Override
                    public void addLoginAgentListener(ActionListener listener) {

                    }

                    @Override
                    public void addRegisterListener(ActionListener listener) {

                    }

                    @Override
                    public void setVisible(boolean b) {

                    }
                };
                controller.MainController mainController = new MainController(Log_out);
                mainViewClient.showMessage("You logged out succesfully!");
                log_out.setVisible(true);
                Log_out.setVisible(true);
                viewClient.setVisible(false);
            }
        }
    }
}
