package view;

import java.util.Scanner;

/**
 * Created by math on 5/5/17.
 */
public class TerminalDisplay implements Display {
    @Override
    public void displayInitialScreen(){
        System.out.println(" ================ Bem vindo ao Truco Mineiro! =================");
        int jogadoresin = 0;
        while(jogadoresin > 4 || jogadoresin < 1){
            System.out.println("Quantos jogadores participarão da partida? <1 - 4> :");
            Scanner sc = new Scanner(System.in);
            jogadoresin = sc.nextInt();
            if(jogadoresin > 4 || jogadoresin < 1){
                System.out.println("Numero de jogadores inválido, tente novamente.");
            }
        }
        System.out.println("Numero de jogadores: " + jogadoresin);
    }
    @Override
    public void printString(String s){
        System.out.println(s);
    }
}
