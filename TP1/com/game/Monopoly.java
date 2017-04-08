package com.game;

import com.data.*;
import com.utils.FileUtils;

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
        System.out.println("Number of plays:" + playLine);
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

        int playerId = 0;
        int dice = 0;

        initializePlayers(nplayers, initialStatement);

        Play play;
        while((play = readPlay(handler,nplays)) != null){
            play.run(this.players, this.board);
        }
//        play = readPlay(handler, nplays);

    }

    private Play readPlay(FileUtils handler, int nplays) {
        int dice;
        int playerId;

        String playLine;
        String[] fields;

        Play play;
//        for(int i = 0; i < nplays; i++){
            dice = 0;
            playLine = handler.getPlayLine();

            if(playLine.equals("DUMP")) return null;

            fields = playLine.split(";");

            if (fields.length!=3){ System.out.println("Wrong number of arguments in play line");}

            playerId = Integer.parseInt(fields[1]);
            dice = Integer.parseInt(fields[2]);
            play = new Play(playerId,dice);


//        }
        return play;
    }

    private void initializePlayers(int nplayers, int initialStatement) {
        for (int k = 0; k < nplayers; k++) {
            this.players[k] = new Player(k);
            this.players[k].deposit(initialStatement);
        }
    }


}
