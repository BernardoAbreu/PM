package view;

import java.util.Scanner;

/**
 * Created by math on 5/5/17.
 */
public class TerminalDisplay implements Display {

    private TerminalDisplay(){ }

    private static class TerminalDisplayHolder{
        private static final TerminalDisplay INSTANCE = new TerminalDisplay();
    }

    public static TerminalDisplay getInstance(){
        return TerminalDisplayHolder.INSTANCE;
    }

    private int teamSize;
    @Override
    public void displayInitialScreen(){

        System.out.println(" ================ Bem vindo ao Truco Mineiro! =================");

        scanTeamSize();
    }

    @Override
    public void printString(String s){
        System.out.println(s);
    }

    private void scanTeamSize(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Quantos jogadores por time? <1 - 2>:");
        teamSize = sc.nextInt();

        while (teamSize > 2 || teamSize < 1){
                System.out.println("Numero de jogadores inválido, tente novamente.");
                System.out.println("Quantos jogadores por time? <1 - 2>:");
                teamSize = sc.nextInt();
        }

        System.out.println("Numero de jogadores por time: " + teamSize);
    }

    public int getTeamSize(){
        return teamSize;
    }
}
