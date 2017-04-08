package com.data;

/**
 * Created by math on 4/6/17.
 */
public class Play {
    private int playerId;
    private int diceValue;

    public Play(int playerId, int diceValue) {
        this.playerId = playerId;
        this.diceValue = diceValue;
    }

    public void run(Player[] players, Board board){
        players[this.playerId-1].walk(this.diceValue, board.getBoardSize());

        Position current = board.getPosition(players[this.playerId-1].getPosition());

        switch (current.getType()){
            case 1:
                //Player gets 500 reais from the bank
                System.out.println("Player: "+ String.valueOf(this.playerId-1) +"  Completed a round trip.");
                players[this.playerId-1].deposit(500);
                break;
            case 2:
                System.out.println("Player: " + String.valueOf(this.playerId-1) + "  Skipped turn");
                //Skip turn!!!
                break;
            case 3:
                //Buy Real Estate or pay rent!
                RealEstate re = (RealEstate) current;
                System.out.println("Player: " + String.valueOf(this.playerId-1) + "   Wants to buy: " + String.valueOf(re.getRealEstateType()));
                if(re.getOwner() == null) {
                    System.out.println("Position belongs to the bank");
                }
                else {
                    System.out.println("Position has an owner");
                }
                break;
            default:
                System.out.println("ERROR: UNKNOWN POSITION TYPE");
                System.exit(-1);
        }
    }
}
