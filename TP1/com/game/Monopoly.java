package com.game;

import com.data.*;
import com.utils.FileUtils;
import com.utils.Stats;

import java.io.IOException;

public class Monopoly {

    private Board board;
    private Player[] players;
    private Play[] plays;

    public Monopoly(FileUtils handler){
        this.board = null;

        try {
            this.board = Board.makeBoard(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runGame(FileUtils handler){
        String playLine = handler.getPlayLine();
        String[] fields = playLine.split("%");

        if(fields.length != 3){
            System.out.println("Wrong number of arguments present in the first line of jogadas.txt");
            System.exit(-1);
        }

        int nplays = Integer.parseInt(fields[0]);
        int nplayers = Integer.parseInt(fields[1]);
        int initialStatement = Integer.parseInt(fields[2]);

        this.plays = new Play[nplays];
        this.players = new Player[nplayers];

        Stats[] stats = new Stats[nplayers];

        int nValidPlays = 0;
        int eliminatedPlayers = 0;
        initializePlayers(nplayers, initialStatement, stats);

        Play play;
        while((play = readPlay(handler)) != null){
            nValidPlays++;
            eliminatedPlayers += play.run(this.players, this.board, stats);
            if(eliminatedPlayers == nplayers) break;
        }

        printStats(nplayers, stats, nValidPlays);
//        System.out.println("TOTAL Eliminated Players:  " + String.valueOf(eliminatedPlayers));
//        System.out.println("Number of rounds: " + String.valueOf(nValidPlays / nplayers));
    }

    private void printStats(int nplayers, Stats[] stats, int nValidPlays) {
        for (int j = 0; j < this.players.length; j++) {
            stats[j].setFinalStatement(this.players[j].getStatement());
        }
        System.out.println("========================== STATS!! =====================");

        System.out.println("1: "+String.valueOf(nValidPlays/nplayers));
        System.out.print("2: ");
        for (int i = 0; i < stats.length; i++) {
            System.out.print(String.valueOf(i+1) + "-" + String.valueOf(stats[i].getnCompletedRounds()) + "; ");
        }
        System.out.print("\n3: ");
        for (int k = 0; k < stats.length; k++) {
            System.out.print(String.valueOf(k+1) + "-" + String.valueOf(stats[k].getFinalStatement()) + "; ");
        }
        System.out.print("\n4: ");
        for (int l = 0; l < stats.length; l++) {
            System.out.print(String.valueOf(l+1) + "-" + String.valueOf(stats[l].getRentReceived()) + "; ");
        }
        System.out.print("\n5: ");
        for (int m = 0; m < stats.length; m++) {
            System.out.print(String.valueOf(m+1) + "-" + String.valueOf(stats[m].getRentPaid()) + "; ");
        }
        System.out.print("\n6: ");
        for (int n = 0; n < stats.length; n++) {
            System.out.print(String.valueOf(n+1) + "-" + String.valueOf(stats[n].getBoughtValue()) + "; ");
        }
        System.out.print("\n7: ");
        for (int o = 0; o < stats.length; o++) {
            System.out.print(String.valueOf(o+1) + "-" + String.valueOf(stats[o].getnSkip()) + "; ");
        }
        System.out.print("\n");
    }

    private Play readPlay(FileUtils handler) {
        int dice;
        int playerId;

        String playLine;
        String[] fields;

        Play play;

        playLine = handler.getPlayLine();

        if(playLine.equals("DUMP")) return null;

        fields = playLine.split(";");

        if (fields.length!=3){ System.out.println("Wrong number of arguments in play line");}

        playerId = Integer.parseInt(fields[1]);
        dice = Integer.parseInt(fields[2]);
        play = new Play(playerId,dice);


        return play;
    }

    private void initializePlayers(int nplayers, int initialStatement, Stats[] stats) {
        for (int k = 0; k < nplayers; k++) {
            this.players[k] = new Player(k);
            stats[k] = new Stats();
            this.players[k].deposit(initialStatement);
        }
    }


}
