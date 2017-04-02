import packagePosition.Position;

import java.util.ArrayList;

public class Board{
	
	private int boardSize;
    private int startPosition;
	private Position positions[];

	public Board(int boardSize){
		this.boardSize = boardSize;
		this.positions = new Position[boardSize];
	}


	public void addPosition(int boardPosition, Position position){
	    if (position.getType() == 1){
	        this.startPosition = boardPosition;
        }
		this.positions[boardPosition] = position;
	}

	public Position getPosition(int boardPosition){
		return this.positions[boardPosition];
	}

	public int getStartPosition(){
	    return this.startPosition;
    }

    public int getBoardSize(){
		return  this.boardSize;
	}
}