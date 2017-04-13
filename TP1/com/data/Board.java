package com.data;

import com.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Board{
	
	private int boardSize;
	private int startPosition;


	private ArrayList<Position> positions;

	public Board(int npositions){
		this.boardSize = npositions;
		this.positions = new ArrayList<Position>(npositions);

	}

	public void addPosition(int boardPosition, Position position){
		this.positions.add(boardPosition-1, position);
	}

	public Position getPosition(int boardPosition){
		return this.positions.get(boardPosition-1);
	}

	public void setStartPosition(int position){
		this.startPosition = position;
	}

	public int getStartPosition(){
		return this.startPosition;
	}

	public static Board makeBoard(FileUtils fileHandler) throws IOException {
		Board board;
		String line = null;
		String[] splitline = null;
		int lineId;
		int boardPosition;
		int positionType;
		int realEstateType;
		int realEstateValue;
		double rentRate;
		int numberOfPositions = Integer.parseInt(fileHandler.getBoardLine());

		board = new Board(numberOfPositions);

		board.setBoardSize(numberOfPositions);
		for (int i = 0; i < numberOfPositions; i++) {
			line = fileHandler.getBoardLine();

			splitline = line.split(";");

			//Error checking: number of properties for positions
			if(splitline.length != 3 && splitline.length !=6){
				System.out.println("Wrong number of arguments for building position.");
				IOException e = new IOException();
				throw e;
			}
			//Initialize
			lineId = 0; boardPosition = 0; positionType = 0; realEstateType = 0; realEstateValue = 0; rentRate = 0;

			//get fields
			lineId = Integer.parseInt(splitline[0]);
			boardPosition = Integer.parseInt(splitline[1]);
			positionType = Integer.parseInt(splitline[2]);
			if(splitline.length > 3){
				realEstateType = Integer.parseInt(splitline[3]);
				realEstateValue = Integer.parseInt(splitline[4]);
				rentRate = Double.parseDouble(splitline[5]);
			}

			switch (positionType){
				case 1:
					board.addPosition(boardPosition, new Start());
					break;
				case 2:
					board.addPosition(boardPosition, new SkipTurn());
					break;
				case 3:
					switch (realEstateType){
						case 1:
							board.addPosition(boardPosition, new RealEstate(boardPosition,realEstateValue, rentRate/100, RealEstate.RESIDENCE));
							break;
						case 2:
							board.addPosition(boardPosition, new RealEstate(boardPosition,realEstateValue, rentRate/100, RealEstate.COMMERCE));
							break;
						case 3:
							board.addPosition(boardPosition, new RealEstate(boardPosition,realEstateValue, rentRate/100, RealEstate.INDUSTRY));
							break;
						case 4:
							board.addPosition(boardPosition, new RealEstate(boardPosition,realEstateValue, rentRate/100, RealEstate.HOTEL));
							break;
						case 5:
							board.addPosition(boardPosition, new RealEstate(boardPosition,realEstateValue, rentRate/100, RealEstate.HOSPITAL));
							break;
					}
					break;
			}
		}

		return board;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
}