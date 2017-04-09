package com.data;

import com.utils.Stats;

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

    public int run(Player[] players, Board board, Stats[] stats){
        Player curPlayer = players[this.playerId-1];
        int eliminated = 0;
        if (!curPlayer.isPlaying()) return eliminated;

        if(curPlayer.walk(this.diceValue, board.getBoardSize())) {
            stats[curPlayer.getId()].incnCompletedRounds();
            System.out.println("Player: "+ String.valueOf(curPlayer.getId()) +"  Completed a round trip.");
        }

        Position curPosition = board.getPosition(curPlayer.getPosition());

        switch (curPosition.getType()){
            case 1:
                //Player gets 500 reais from the bank
                System.out.println("Player: "+ String.valueOf(this.playerId) +"  Stopped at start.");
                stats[this.playerId-1].incnCompletedRounds();
                curPlayer.deposit(500);
                break;
            case 2:
                System.out.println("Player: " + String.valueOf(this.playerId) + "  Skipped turn");
                stats[this.playerId-1].incnSkip();
                //Skip turn!!!
                break;
            case 3:
                //Buy Real Estate or pay rent!
                RealEstate re = (RealEstate) curPosition;
                System.out.println("Player: " + String.valueOf(this.playerId) + "   Wants to buy: " + String.valueOf(re.getId()));
                if(re.getOwner() == null) {
                    System.out.println("Position belongs to the bank");
                    System.out.println("Position costs: " + String.valueOf(re.getValue()));
                    System.out.println("Player has:  " + String.valueOf(curPlayer.getStatement()));
                    //BUY FROM BANK
                    if(!curPlayer.acquireProperty(re)) { curPlayer.leaveGame(); eliminated++; } //If player can't afford the property, they lose
                    else { stats[curPlayer.getId()].incBoughtValue(re.getValue()); }
                }
                else {
                    //PAY RENT
                    Player owner = re.getOwner();

                    if(!curPlayer.payRent(owner,re)) { curPlayer.leaveGame(); }
                    else{
                        stats[curPlayer.getId()].incRentPaid(re.getValue()*re.getRent());
                        stats[owner.getId()].incRentReceived(re.getValue()*re.getRent());
                    }
                    System.out.println("Position has an owner");
                }
                break;
            default:
                System.out.println("ERROR: UNKNOWN POSITION TYPE");
                System.exit(-1);
        }
        return eliminated;
    }

}
