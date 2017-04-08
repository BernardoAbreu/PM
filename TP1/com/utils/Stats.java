package com.utils;

public class Stats {

    private int nCompletedRounds;

    private float finalStatement;

    private float rentReceived;

    private float rentPaid;

    private float boughtValue;

    private int nSkip;

    public Stats() {
        this.nCompletedRounds = 0;
        this.finalStatement = 0;
        this.rentReceived = 0;
        this.rentPaid = 0;
        this.boughtValue = 0;
        this.nSkip = 0;
    }

    public int getnCompletedRounds() {
        return nCompletedRounds;
    }

    public void setnCompletedRounds(int nCompletedRounds) {
        this.nCompletedRounds = nCompletedRounds;
    }

    public float getFinalStatement() {
        return finalStatement;
    }

    public void setFinalStatement(float finalStatement) {
        this.finalStatement = finalStatement;
    }

    public float getRentReceived() {
        return rentReceived;
    }

    public void setRentReceived(float rentReceived) {
        this.rentReceived = rentReceived;
    }

    public float getRentPaid() {
        return rentPaid;
    }

    public void setRentPaid(float rentPaid) {
        this.rentPaid = rentPaid;
    }

    public float getBoughtValue() {
        return boughtValue;
    }

    public void setBoughtValue(float boughtValue) {
        this.boughtValue = boughtValue;
    }

    public int getnSkip() {
        return nSkip;
    }

    public void setnSkip(int nSkip) {
        this.nSkip = nSkip;
    }
}
