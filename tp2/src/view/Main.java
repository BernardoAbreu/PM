package view;

import controller.MatchControl;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Display disp = new TerminalDisplay();
        disp.displayInitialScreen();
        MatchControl m = new MatchControl(disp);
        m.run();
    }
}
