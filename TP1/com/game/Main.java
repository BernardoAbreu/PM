package com.game;


import com.utils.FileUtils;

public class Main {

	public static void main(String[] args) {

		if(args.length != 2){
			System.out.println("ERROR: unsufficient number of arguments.");
		}

		FileUtils handler = new FileUtils(args[0], args[1]);
		Monopoly monopoly = new Monopoly(handler);

		monopoly.runGame(handler);


	}
}