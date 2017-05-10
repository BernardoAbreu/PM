package model;

import java.util.Scanner;

/**
 * Created by math on 5/5/17.
 */
public class RealPlayer extends Player{

    public RealPlayer(int id){
        super(id);
    }

    @Override
    public void playCard(Table table, Team team) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose");

        this.getHand().printHand();

        for(int i = 0; i < this.getHand().getCards().size(); i++){
            System.out.print((i + 1) +  " ");
        }
        System.out.println();
        int opt = sc.nextInt();
        Card c = this.getHand().removeCard(opt-1);
        table.addCard(c, this.getId(), team.getId());
    }

    @Override
    public Option getOption(){
        System.out.println("Your hand:");
        this.getHand().printHand();
        Scanner sc = new Scanner(System.in);

        System.out.println("1- Raise 2- Accept 3- Fold:");
        int opt = sc.nextInt();

        switch (opt){
            case 1:
                return Option.RAISE;
            case 2:
                return Option.ACCEPT;
            case 3:
                return Option.FOLD;
            default:
                return Option.FOLD;
        }
    }
}
