package com.game;

import com.player.*;
import com.structure.Board;
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
            System.exit(-1);
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

        //Aray of stats, will contain the statistics for each player
        Stats[] stats = new Stats[nplayers];

        int nValidPlays = 0;
        int eliminatedPlayers = 0;
        initializePlayers(nplayers, initialStatement, stats);

        Play play;
        while((play = makePlay(handler)) != null){ //While there are valid plays
            nValidPlays++;
            eliminatedPlayers += play.run(this.players, this.board, stats); //Execute the play
            if(eliminatedPlayers == nplayers-1) break; //If there's only one player still in the game, quit
        }

        printStats(stats, handler, nValidPlays, nplayers);
    }

    private void printStats( Stats[] stats, FileUtils handler, int nValidPlays, int nplayers) {
        String line = "";

        for (int i = 0; i < this.players.length; i++) {
            stats[i].setFinalStatement(this.players[i].getStatement());
        }

        String[] summary = Stats.summarizeStats(stats, nValidPlays/nplayers);
        for(int j = 0; j < summary.length; j++){
            handler.writeLine(summary[j]);
        }

    }

    private Play makePlay(FileUtils handler) {
        int dice;
        int playerId;

        String playLine;
        String[] fields;

        Play play;
        //Read the play from file
        playLine = handler.getPlayLine();
        //Return null when play is invalid
        if(playLine.equals("DUMP")) return null;

        //Otherwise, process the play
        fields = playLine.split(";");

        if (fields.length!=3){ System.out.println("Wrong number of arguments in play line");}

        playerId = Integer.parseInt(fields[1]);
        dice = Integer.parseInt(fields[2]);
        //Build a Play object with the information gathered.
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
