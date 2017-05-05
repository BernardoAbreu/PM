package view;

/**
 * Created by math on 5/5/17.
 */
public class TerminalDisplay implements Display {
    @Override
    public void showInitialScreen(){
        System.out.println("Saidaa");
    }
    @Override
    public void printString(String s){
        System.out.println(s);
    }
}
