import packagePlayer.Player;
import packagePosition.*;

public class Monopoly{

    private Board board;

    private  int numberOfPlayers;

    private int playerPosition[];

    private Player players[];


    private void buildBoard(){
        this.board = new Board(5);
        this.board.addPosition(0,new Start());
        this.board.addPosition(2,new Residence(90,10));
        this.board.addPosition(1,new Commerce(90,20));
        this.board.addPosition(3,new Industry(90,30));
        this.board.addPosition(4,new SkipTurn());
    }

    public Monopoly(int numberOfPlayers, int initalValue){
        //Build board game
        this.buildBoard();

        this.numberOfPlayers = numberOfPlayers;

        //Positions of player on the board
        this.playerPosition = new int[numberOfPlayers];

        //players of the game
        this.players = new Player[numberOfPlayers];

        //Initialize players position and players initial bank account
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players[i] = new Player(i+1);
            this.players[i].deposit(initalValue);
            this.playerPosition[i] = 0;
        }
    }

    public void play(int player, int dice){

        Player currentPlayer = this.players[player-1];

        if(currentPlayer.isPlaying()){
            int boardSize = board.getBoardSize();
            int playerCurrentPosition = this.playerPosition[player-1];


            //Checks if player is going to pass through the start position without stopping
            int startPosition = this.board.getStartPosition();
            if ((( startPosition <= playerCurrentPosition) && (dice > (startPosition + boardSize - playerCurrentPosition)))
                || ((startPosition > playerCurrentPosition) && (dice > (startPosition - playerCurrentPosition)))){

                board.getPosition(startPosition).play(currentPlayer);
            }

            int nextPosition = (dice + playerCurrentPosition) % boardSize;
            board.getPosition(nextPosition).play(currentPlayer);
            this.playerPosition[player-1] = nextPosition;

        }
    }

    public void printStatements(){
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i+1) + ": " + players[i].getStatement());
        }
    }

    public void printPlayerPositions(){
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i+1) + " at position " + playerPosition[i]);
        }
    }
}