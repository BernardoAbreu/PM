package com.utils;

public class Stats {

    private int nCompletedRounds;

    private double finalStatement;

    private float rentReceived;

    private double rentPaid;

    private double boughtValue;

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

    public double getRentReceived() {
        return rentReceived;
    }

    public void incRentReceived(double rentReceived) {
        this.rentReceived += rentReceived;
    }

    public double getRentPaid() {
        return rentPaid;
    }

    public void incRentPaid(double rentPaid) {
        this.rentPaid += rentPaid;
    }

    public double getBoughtValue() {
        return boughtValue;
    }

    public void incBoughtValue(double boughtValue) {
        this.boughtValue += boughtValue;
    }

    public int getnSkip() {
        return nSkip;
    }

    public void incnSkip() {
        this.nSkip++;
    }
}
