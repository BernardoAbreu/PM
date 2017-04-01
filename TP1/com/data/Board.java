package com.data;

import java.util.ArrayList;

public class Board{
	
	private int boardSize;

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
}