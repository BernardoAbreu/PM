package com.player;

import com.structure.Board;
import com.structure.Position;
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
        //If this player is not on the game anymore, ignore the play
        if (!curPlayer.isPlaying()) return eliminated;

        //Otherwise, walk the player
        nroundTrips = curPlayer.walk(this.diceValue, board.getBoardSize());

        if( nroundTrips != 0) { //Player completed a number of trips around the board.
            stats[curPlayer.getId()].incnCompletedRounds(nroundTrips);
            curPlayer.deposit(nroundTrips*500); //Gets reward
        }

        Position curPosition = board.getPosition(curPlayer.getPosition());

        //Polymorphic call depends on position type.
        if(!curPosition.play(curPlayer, stats)) eliminated++;

        return eliminated;
    }

}
