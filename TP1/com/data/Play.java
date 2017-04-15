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

        //Polymorphic call depends on position type.
        if(!curPosition.play(curPlayer, stats)) eliminated++;

        return eliminated;
    }

}
