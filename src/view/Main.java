package view;

import java.awt.event.ActionListener;

public interface Main {
    public void setDimension(int w, int h);
    public void addLoginClientListener (ActionListener listener);
    public void addLoginAgentListener (ActionListener listener);
    public void addRegisterListener (ActionListener listener);
    public void setVisible(boolean b);
}
