package view;

import java.awt.event.ActionListener;

public interface MainAgent {
    public void setName_of_agent(String name);
    public String getName_of_agent();
    public void setDimension(int w, int h);
    public void addAddProjectListener (ActionListener listener);
    public void addLogOutListener (ActionListener listener);
    public void addSeeFeedbacksListener (ActionListener listener);
    public void showMessage (String message);
    public void setVisible(boolean b);
    public int showMessage1(String text);
}
