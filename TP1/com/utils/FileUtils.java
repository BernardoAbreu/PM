package com.utils;

/**
 * Created by math on 4/5/17.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    private BufferedReader boardFile;

    private BufferedReader playsFile;

    public FileUtils(String boardFile, String playsFile){
        //Open files
        try {
            this.boardFile = new BufferedReader(new FileReader(boardFile));
            this.playsFile = new BufferedReader(new FileReader(playsFile));
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }

    }

    public String getBoardLine(){
        String line = null;
        try {
            line = this.boardFile.readLine();
        }catch (IOException e){ //Error checking: insufficient number of lines.
            System.out.println("File has fewer lines than expected.");
            e.printStackTrace();
        }
        return line;
    }

    public String getPlayLine(){
        String line = null;
        try {
            line = this.playsFile.readLine();
        }catch (IOException e){ //Error checking: insufficient number of lines.
            System.out.println("File has fewer lines than expected.");
            e.printStackTrace();
        }
        return line;
    }

}
