package com.utils;

public class Stats {

    private int nCompletedRounds;

    private double finalStatement;

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

    public void incnCompletedRounds() {
        this.nCompletedRounds++;
    }

    public double getFinalStatement() {
        return finalStatement;
    }

    public void setFinalStatement(double finalStatement) {
        this.finalStatement = finalStatement;
    }

    public float getRentReceived() {
        return rentReceived;
    }

    public void incRentReceived(float rentReceived) {
        this.rentReceived += rentReceived;
    }

    public float getRentPaid() {
        return rentPaid;
    }

    public void incRentPaid(float rentPaid) {
        this.rentPaid += rentPaid;
    }

    public float getBoughtValue() {
        return boughtValue;
    }

    public void incBoughtValue(float boughtValue) {
        this.boughtValue += boughtValue;
    }

    public int getnSkip() {
        return nSkip;
    }

    public void incnSkip() {
        this.nSkip++;
    }
}
