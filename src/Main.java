import controller.MainController;
import view.MainView;

import java.awt.event.ActionListener;

public class Main {
    private static view.MainView main_view;
    public static void main(String[] args) throws Exception {
        view.Main view = new view.Main() {
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
        main_view = new MainView() {

        };
        controller.MainController mainController = new MainController(main_view);
        view.setVisible(true);
    }
}