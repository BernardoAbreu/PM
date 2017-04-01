package com.company;
import com.packagePosition.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	static void makeBoard(Board b){
		Scanner scan = new Scanner(System.in);
		String line = null;
		String[] splitline = null;
		int[] splNumbers = null;
		int numberOfPositions = Integer.parseInt(scan.nextLine());

		b = new Board(numberOfPositions);
		for (int i = 0; i < numberOfPositions; i++) {
			try {
				line = scan.nextLine();
			}catch (NoSuchElementException e){
				System.out.println("Number of elements supplied in the file is bigger than actual amount of lines.");
				e.printStackTrace();
			}
			splitline = line.split(";");
			splNumbers = new int[splitline.length];
			for(int k = 0; k < splitline.length; k++){
				splNumbers[k] = Integer.parseInt(splitline[k]);
			}
//			splitline[0] == id da linha;  splitline[1] == posicao no tabuleiro; splitline[2] == tipo da posicao
//			splitline[3] == tipo do imovel (FAZER O SWITCH AQUI); splitline[4] == valor do imovel; splitline[5] == taxa do aluguel
			switch (splNumbers[2]){
				case 1:
					b.addPosition(splNumbers[1], new Start());
					break;
				case 2:
					b.addPosition(splNumbers[1], new SkipTurn());
					break;
				case 3:
					switch (splNumbers[3]){
						case 1:
							b.addPosition(splNumbers[1], new Residence(splNumbers[4], splNumbers[5]));
							break;
						case 2:
							b.addPosition(splNumbers[1], new Commerce(splNumbers[4], splNumbers[5]));
							break;
						case 3:
							b.addPosition(splNumbers[1], new Industry(splNumbers[4], splNumbers[5]));
							break;
						case 4:
							b.addPosition(splNumbers[1], new Hotel(splNumbers[4], splNumbers[5]));
							break;
						case 5:
							b.addPosition(splNumbers[1], new Hospital(splNumbers[4], splNumbers[5]));
							break;
					}
					break;
			}
		}

		for(int m = 1; m <= numberOfPositions; m++){
			System.out.println("Position: " + String.valueOf(b.getPosition(m).getType()));
		}
	}

	public static void main(String[] args) {

		Board board = null;

		makeBoard(board);
		System.out.println("PROJECT!!!");

	}
}