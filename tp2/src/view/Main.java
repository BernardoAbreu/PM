package view;

import controller.Match;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Display disp = new TerminalDisplay();
        disp.displayInitialScreen();
        Match m = new Match(disp);
        m.run();
    }
}
