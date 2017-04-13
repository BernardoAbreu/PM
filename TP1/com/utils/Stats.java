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

    public static String[] summarizeStats(Stats[] stats, int nGameRounds){
        String[] summary = new String[7];

        //First line: number of valid plays
        summary[0] = "1:" + String.valueOf(nGameRounds) + "\n";

        //Second line: how many rounds did each player complete?
        summary[1] = "2:";
        for (int i = 0; i < stats.length; i++) {
            summary[1] += String.valueOf(i+1) + "-" + String.valueOf(stats[i].getnCompletedRounds()) + "; ";
        }
        summary[1] += "\n";

        //Third line: players final statements
        summary[2] = "3:";
        for (int k = 0; k < stats.length; k++) {
            summary[2] += String.valueOf(k+1) + "-" + String.valueOf(stats[k].getFinalStatement()) + "; ";
        }
        summary[2] += "\n";

        //Fourth line: players rent received
        summary[3] = "4:";
        for (int l = 0; l < stats.length; l++) {
            summary[3] += String.valueOf(l+1) + "-" + String.valueOf(stats[l].getRentReceived()) + "; ";
        }
        summary[3] += "\n";

        //Fifth line: players rent paid
        summary[4] = "5:";
        for (int m = 0; m < stats.length; m++) {
            summary[4] += String.valueOf(m+1) + "-" + String.valueOf(stats[m].getRentPaid()) + "; ";
        }
        summary[4] += "\n";

        //Sixth line: players money spent on buying places
        summary[5] = "6:";
        for (int n = 0; n < stats.length; n++) {
            summary[5] += String.valueOf(n+1) + "-" + String.valueOf(stats[n].getBoughtValue()) + "; ";
        }
        summary[5] += "\n";

        //Seventh line: players amount of skipped turns
        summary[6] = "7:";
        for (int o = 0; o < stats.length; o++) {
            summary[6] += String.valueOf(o+1) + "-" + String.valueOf(stats[o].getnSkip()) + "; ";
        }
        summary[6] += "\n";

        //Finished gathering stats
        return summary;
    }

}
