package controller;

import model.*;
import view.Display;
import java.util.List;


public class Play {

    private List<Team> teams;

    private int teamSize;

    private Team winnerTeam;

    private Table table;

    private Display d;

    private boolean active;

    private PlayValue playValue;

    public Play(Display d, List<Team> teams, int teamSize){
        this.d = d;
        this.teams = teams;
        this.teamSize = teamSize;
        this.table = new Table(teams.size(), teamSize);

    }

    public void run(int firstPlayerIndex){
        Player winnerPlayer;
        Team firstWinner;

        this.playValue = PlayValue.NORMAL;

        this.active = true;

        d.printString("Start of Play\n");

        // First Round
        round(firstPlayerIndex);
        if(this.active){
            if(table.tie()){
            /*******************************************************************
            * Quando as cartas jogadas tem o mesmo valor ocorre o empate.
            * Se ocorrer na primeira rodada, quem empatou deve mostrar a maior
            * carta, em seguida o adversário. O maior valor ganha. Caso ocorra
            * empate novamente os jogadores devem mostrar a terceira carta.
            * Quando o empate ocorre na segunda ou na terceira jogada, ganha
            * quem fez a primeira.
            *******************************************************************/
                System.out.println("tie - round 1");
                this.table.clearTable();
                //Put biggest cards on table

                if(table.tie()){
                    System.out.println("tie - round 1.2");
                    this.table.clearTable();
                    //Put all cards on table
                    
                    winnerTeam = table.tie() ? null : table.getWinnerTeam();
                }
                else{
                    winnerTeam = table.getWinnerTeam();
                }

            }
            else{
                winnerTeam = table.getWinnerTeam();
                winnerPlayer = table.getWinnerPlayer();
                firstWinner = winnerTeam;
                //Look for index

                for(int i = 0; i < 2; i++){
                    round(firstPlayerIndex);
                    
                    if(!this.active){
                        break;
                    }
                    else if(this.table.tie()){
                        System.out.println("tie - round " + (i+1));
                        winnerTeam = firstWinner;
                        break;
                    }
                    else{
                        winnerTeam = table.getWinnerTeam();
                        winnerPlayer = table.getWinnerPlayer();
                    }
                }
                
            }
        }
        

        System.out.println("playValue " + playValue + ": " + playValue.getValue());

        if(winnerTeam != null){
            winnerTeam.addScore(playValue.getValue());
        }
    }



    private void round(int firstPlayerIndex){
        this.table.clearTable();

        Player currentPlayer, nextPlayer;
        Team currentTeam, nextTeam;
        int nextIndex;
        Option opt, nextOpt;


        currentTeam = teams.get(firstPlayerIndex%teams.size());
        currentPlayer = currentTeam.getPlayer(firstPlayerIndex/teams.size());

        nextIndex = (firstPlayerIndex+1)%(teamSize*teams.size());
        nextTeam = teams.get(nextIndex%teams.size());
        nextPlayer = nextTeam.getPlayer(nextIndex/teams.size());


        Player thisPlayer, otherPlayer;
        Team thisTeam, otherTeam;

        for(int i = 0; i < teamSize*teams.size(); i++){

            opt = currentPlayer.getOption();


            /****************************************/
            //Raise Option

            thisPlayer = currentPlayer;
            otherPlayer = nextPlayer;
            thisTeam = currentTeam;
            otherTeam = nextTeam;
            while(opt == Option.RAISE){
                System.out.println("Raising");

                // Get other players response
                opt = otherPlayer.getOption();
                System.out.println("Player " + otherPlayer.getId() + " choosing:");
                // Folding from this turn
                if(opt == Option.FOLD){
                    nextTeam = thisTeam;
                }
                else {
                    // accepted the raise
                    if(playValue != PlayValue.DOZE){
                        playValue = playValue.next();
                        System.out.println("playValue at " + playValue);
                    }

                    if(playValue == PlayValue.DOZE){
                        opt = Option.ACCEPT;
                    }
                    
                    // Raising again
                    if(opt == Option.RAISE){
                        //swap players
                        Player auxP = thisPlayer;
                        thisPlayer = otherPlayer;
                        otherPlayer = auxP;

                        //swap Teams
                        //Don't need this if players store their team
                        Team auxT = thisTeam;
                        thisTeam = otherTeam;
                        otherTeam = auxT;
                    }
                }
            }
            /*********************************************/


            if(opt == Option.ACCEPT){
                // put card on table
                System.out.println("Accepting");
                winnerTeam = currentTeam;
            }
            else if(opt == Option.FOLD){
                System.out.println("Folding");
                winnerTeam = nextTeam;
                this.active = false;
                break;
            }

            System.out.println("Player: " + currentTeam.getPlayer(nextIndex/teams.size()).getId());
            
            currentTeam = nextTeam;
            currentPlayer = nextPlayer;

            nextIndex = (nextIndex + 1)%(teamSize*teams.size());
            nextTeam = teams.get(nextIndex%teams.size());
            nextPlayer = nextTeam.getPlayer(nextIndex/teams.size());
        }

    }

    public Team getWinnerTeam(){
        return winnerTeam;
    }

}
