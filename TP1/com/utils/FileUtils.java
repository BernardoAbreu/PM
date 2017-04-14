package com.utils;

/**
 * Created by math on 4/5/17.
 */
import java.io.*;

public class FileUtils {
    private BufferedReader boardFile;

    private BufferedReader playsFile;

    private BufferedWriter statsFile;

    public FileUtils(String boardFile, String playsFile){
        String curDir = System.getProperty("user.dir");
        //Open files
        try {
            this.boardFile = new BufferedReader(new FileReader(curDir+"/"+boardFile));
            this.playsFile = new BufferedReader(new FileReader(curDir+"/"+playsFile));
            this.statsFile = new BufferedWriter(new FileWriter(curDir+"/estatísticas.txt"));
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Error opening out file.");
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

    public void writeLine(String line){
        try {
            this.statsFile.write(line);
        }catch (Exception e){
            System.out.println("ERROR: writing to file.");
            e.printStackTrace();
        }
    }

    public void destroy(){
        try {
            if(this.boardFile != null) this.boardFile.close();
            if(this.playsFile != null) this.playsFile.close();
            if(this.statsFile != null) this.statsFile.close();
        }catch (IOException e){
            System.out.println("ERROR: closing files.");
            e.printStackTrace();
        }
    }
}
