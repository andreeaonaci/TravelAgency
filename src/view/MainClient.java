package view;

import java.awt.event.ActionListener;

public interface MainClient {
    public void setDimension(int w, int h);

    public void setName_client(String name_client);

    public String getName_client();

    public void addLogOutListener (ActionListener listener);

    public void addGiveFeedbackListener (ActionListener listener);

    public void addMakeReservationListener (ActionListener listener);
    public void showMessage (String message);
    public void setVisible(boolean b);
    public int showMessage1(String text);
}
