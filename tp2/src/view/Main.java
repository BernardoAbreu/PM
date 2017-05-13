package view;

import controller.MatchControl;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        Display display = GraphicalDisplay.getInstance();
        Display display = TerminalDisplay.getInstance();
        display.displayInitialScreen();
        MatchControl m = new MatchControl(display);
        m.run();
    }
}
