package view;

import model.Card;
import model.Player;
import model.RealPlayer;
import model.Team;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by math on 5/12/17.
 */
public class RealPlayerObserver implements Observer{
    private RealPlayerObserver(){ }

    private static class RealPlayerObserverHolder{
        private static final RealPlayerObserver INSTANCE = new RealPlayerObserver();
    }

    public static RealPlayerObserver getInstance(){
        return RealPlayerObserverHolder.INSTANCE;
    }

    @Override
    public void update(Observable observable, Object o) {
        Display disp = TerminalDisplay.getInstance();
        Integer source = (Integer) o;
        RealPlayer player = (RealPlayer) observable;
        Scanner sc = new Scanner(System.in);
        int opt;
        String message = "";
        if(player.getTeam().getId() == Team.HUMAN_PLAYER_TEAM){
            if(o == null){
                opt = -1;
                while(opt < 1 || opt > 3){
                    message = "";
                    disp.printString("Início da jogada");
                    disp.printString("1- Aceitar o valor da jogada? 2- Desistir da jogada? Ou 3- Aumentar o valor da jogada?:");
                    disp.printString("O que deseja fazer? <1 - 3>");
                    opt = sc.nextInt();
                    if(opt < 1 || opt > 3) disp.printString("O valor escolhido não está entre as opções fornecidas. Por favor, tente novamente.");
                }
                player.setPlayValueOption(opt);

            }else{
                opt = -1;
                while(opt < 1 || opt > 3){
                    message = "";
                    disp.printString("As cartas que estão na sua mão são: ");

                    for(Card c : player.getHand()){
                        message += c + " ";
                    }
                    disp.printString(message);
                    message = "";
                    int i = 0;
                    for(Card c : player.getHand()){
                        message += String.valueOf(++i) +  "  ";

                    }
                    disp.printString(message);
                    disp.printString("");
                    disp.printString("Qual carta deseja jogar? <1 - 3>");
                    opt = sc.nextInt();
                    if(opt < 1 || opt > 3) disp.printString("O valor escolhido não está entre as opções fornecidas. Por favor, tente novamente.");
                }
                player.setCardOption(opt-1);
            }
        }
    }
}
