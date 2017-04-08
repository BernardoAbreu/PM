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

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }
}
