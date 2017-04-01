import packagePosition.Position;

import java.util.ArrayList;

public class Board{
	
	private int boardSize;

	private ArrayList<Position> positions;

	public Board(int boardSize){
		this.boardSize = boardSize;
		this.positions = new ArrayList<>(boardSize);
	}


	public void addPosition(int boardPosition, Position position){
		this.positions.add(boardPosition, position);
	}

	public Position getPosition(int boardPosition){
		return this.positions.get(boardPosition);
	}
}