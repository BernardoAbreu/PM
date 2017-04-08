package com.game;

import com.data.*;
import com.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Monopoly {

    private Board board;
    private Player[] players;

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

        this.players = new Player[nplayers];

        int playerId = 0;
        int dice = 0;
        for (int k = 0; k < nplayers; k++) {
            this.players[k] = new Player(k);
            this.players[k].deposit(initialStatement);
        }

        for(int i = 0; i < nplays; i++){
            dice = 0;
            playLine = handler.getPlayLine();
//            System.out.println(playLine);

            if(playLine.equals("DUMP")) break;

            fields = playLine.split(";");

            if (fields.length!=3){ System.out.println("Wrong number of arguments in runGame number: " + String.valueOf(i));}

            playerId = Integer.parseInt(fields[1]);
            dice = Integer.parseInt(fields[2]);

            runPlay(playerId,dice);

        }

    }

    private void runPlay(int playerId, int dice){
        this.players[playerId-1].walk(dice, this.board.getBoardSize());

        Position current = board.getPosition(this.players[playerId-1].getPosition());

        switch (current.getType()){
            case 1:
                //Player gets 500 reais from the bank
                System.out.println("Player: "+ String.valueOf(playerId-1) +"  Completed a round trip.");
                this.players[playerId-1].deposit(500);
                break;
            case 2:
                System.out.println("Player: " + String.valueOf(playerId-1) + "  Skipped turn");
                //Skip turn!!!
                break;
            case 3:
                //Buy Real Estate or pay rent!
                RealEstate re = (RealEstate) current;
                System.out.println("Player: " + String.valueOf(playerId-1) + "   Wants to buy: " + String.valueOf(re.getRealEstateType()));
                break;
            default:
                    System.out.println("ERROR: UNKNOWN POSITION TYPE");
                    System.exit(-1);
        }
    }
}
