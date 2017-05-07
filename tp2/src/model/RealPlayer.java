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
    public void playCard() {
        System.out.println("Choose");

    }

    @Override
    public Option getOption(){
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
