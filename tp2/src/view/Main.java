package view;

import controller.Match;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Display disp = new TerminalDisplay();
        disp.showInitialScreen();
        Match m = new Match();
        m.run(disp);
    }
}
