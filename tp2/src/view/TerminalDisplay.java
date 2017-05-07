package view;

import java.util.Scanner;

/**
 * Created by math on 5/5/17.
 */
public class TerminalDisplay implements Display {

    private int numberOfPlayers;
    private int teamSize;
    @Override
    public void displayInitialScreen(){

        System.out.println(" ================ Bem vindo ao Truco Mineiro! =================");

        scanTeamSize();
        // scanNumberOfPlayers();

    }

    @Override
    public void printString(String s){
        System.out.println(s);
    }


    private void scanNumberOfPlayers(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Quantos jogadores participarão da partida? <1 - 4> :");
        numberOfPlayers = sc.nextInt();        
        
        while(numberOfPlayers > 4 || numberOfPlayers < 1){
            System.out.println("Numero de jogadores inválido, tente novamente.");
            System.out.println("Quantos jogadores participarão da partida? <1 - 4> :");
            numberOfPlayers = sc.nextInt();
        }

        System.out.println("Numero de jogadores: " + numberOfPlayers);
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

    private void assignPlayerToTeam(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual o seu time? <1 - 2>:");
        teamSize = sc.nextInt();

        while (teamSize > 2 || teamSize < 1){
                System.out.println("Numero de jogadores inválido, tente novamente.");
                System.out.println("Quantos jogadores por time? <1 - 2>:");
                teamSize = sc.nextInt();
        }
    }

// public void printCards(){
// System.out.println("c: " + String.valueOf(c.getValue()) + c.getSuit().getString());

//         // System.out.println( Character.toChars(0xd83cdc00 +  13*16 + c.getValue()));
//         System.out.println( "\ud83c" + String.valueOf(Character.toChars(0xdc00 + 13*16 + Character.getNumericValue(c.getValue()))));// "\udcdd");

// }
    public int getTeamSize(){
        return teamSize;
    }
}
