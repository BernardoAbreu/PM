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
    public void playCard(Table table) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose");

        for(Card c : this.hand){
            System.out.print(c + " ");
        }
        System.out.println();

        int i = 0;
        for(Card c : this.hand){
            System.out.print((++i) +  " ");
        }
        System.out.println();
        int opt = sc.nextInt();
        Card c = this.getHand().removeCard(opt-1);
        table.addCard(c, this.getId(), this.getTeam().getId());
    }

    @Override
    public Option getOption(boolean max){
        for(Card c : this.hand){
            System.out.print(c + " ");
        }
        System.out.println();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Player " + this.getId() + " choosing.");
        System.out.print("1- Accept 2- Fold");
        System.out.println(((max)?":":" 3- Raise:"));

        int opt = sc.nextInt();


        if(opt == 1 ){
            return Option.ACCEPT;
        }
        else if(opt == 2 ){
            return Option.FOLD;
        }
        else if((opt == 3) && !max ){
            return Option.RAISE;
        }
        return Option.ACCEPT;
    }

    @Override
    public Option getOption(){
        return this.getOption(false);
    }
}
