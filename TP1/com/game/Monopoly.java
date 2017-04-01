package com.game;

import com.data.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Monopoly {

    private Board board;

    public Monopoly(String filename){
        this.board = null;
        try {
            this.makeBoard(this.board, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeBoard(Board board, String filename) throws IOException{

        BufferedReader br = null;
        FileReader fr = null;

        br = new BufferedReader(new FileReader(filename));

        String line = null;
        String[] splitline = null;
        int[] splNumbers = null;
        int numberOfPositions = Integer.parseInt(br.readLine());

        board = new Board(numberOfPositions);
        for (int i = 0; i < numberOfPositions; i++) {
            try {
                line = br.readLine();
            }catch (IOException e){ //Error checking: insufficient number of lines.
                System.out.println("Fewer lines than expected.");
                e.printStackTrace();
            }
            splitline = line.split(";");

            //Error checking: number of properties for positions
            if(splitline.length != 3 && splitline.length !=6){
                System.out.println("Wrong number of arguments for building position.");
                IOException e = new IOException();
                throw e;
            }
            splNumbers = new int[splitline.length];

            for(int k = 0; k < splitline.length; k++){ //STRING -> INT
                splNumbers[k] = Integer.parseInt(splitline[k]);
            }
//			[0]: id da linha; [1]: posicao no tabuleiro; [2]: tipo da posicao
//			[3]: tipo do imovel; [4]: valor do imovel;[5]: taxa do aluguel
            switch (splNumbers[2]){
                case 1:
                    board.addPosition(splNumbers[1], new Start());
                    break;
                case 2:
                    board.addPosition(splNumbers[1], new SkipTurn());
                    break;
                case 3:
                    switch (splNumbers[3]){
                        case 1:
                            board.addPosition(splNumbers[1], new Residence(splNumbers[4], splNumbers[5]));
                            break;
                        case 2:
                            board.addPosition(splNumbers[1], new Commerce(splNumbers[4], splNumbers[5]));
                            break;
                        case 3:
                            board.addPosition(splNumbers[1], new Industry(splNumbers[4], splNumbers[5]));
                            break;
                        case 4:
                            board.addPosition(splNumbers[1], new Hotel(splNumbers[4], splNumbers[5]));
                            break;
                        case 5:
                            board.addPosition(splNumbers[1], new Hospital(splNumbers[4], splNumbers[5]));
                            break;
                    }
                    break;
            }
        }

        for(int m = 1; m <= numberOfPositions; m ++){
            System.out.println(board.getPosition(m).getType());
        }
    }
}
