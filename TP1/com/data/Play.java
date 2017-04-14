package com.data;

import com.utils.Stats;

/**
 * Created by math on 4/6/17.
 */
public class Play {
    private int playerId;
    private int diceValue;

    public Play(int playerId, int diceValue) { //Initialize play
        this.playerId = playerId;
        this.diceValue = diceValue;
    }

    public int run(Player[] players, Board board, Stats[] stats){
        Player curPlayer = players[this.playerId-1];
        int eliminated = 0;
        int nroundTrips = 0;
        if (!curPlayer.isPlaying()) return eliminated;

        nroundTrips = curPlayer.walk(this.diceValue, board.getBoardSize());

        if( nroundTrips != 0) { //Player completed a trip around the board.
            stats[curPlayer.getId()].incnCompletedRounds();
            curPlayer.deposit(500);
        }

        Position curPosition = board.getPosition(curPlayer.getPosition());

//        if(curPosition.play(curPlayer, stats)) return 1;
//        else return 0;
        switch (curPosition.getType()){
            case 1: //Start position
                break;
            case 2: //Skip turn
                stats[this.playerId-1].incnSkip();
                break;
            case 3: //Real Estate
                //Buy Real Estate or pay rent!
                RealEstate re = (RealEstate) curPosition;
                if(re.getOwner() == null) {
                    //BUY FROM BANK
                    if(curPlayer.acquireProperty(re)) {//Nothing happens if player can't afford the property, they just stay there
                        stats[curPlayer.getId()].incBoughtValue(re.getValue());
                    }
                }
                else {
                    //PAY RENT
                    if(re.getOwner().getId() == curPlayer.getId()) break;
                    Player owner = re.getOwner();

                    if(!curPlayer.payRent(owner,re)) { curPlayer.leaveGame(); eliminated++; }
                    else{
                        stats[curPlayer.getId()].incRentPaid(re.getValue()*re.getRent());
                        stats[owner.getId()].incRentReceived(re.getValue()*re.getRent());
                    }
                }
                break;
            default: //Something bad happened. Exit!
                System.exit(-1);
        }
//        Player was eliminated, return this information
        return eliminated;
    }

}
