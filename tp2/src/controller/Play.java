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

    public Play(Display d, List<Team> teams, int teamSize){
        this.d = d;
        this.teams = teams;
        this.teamSize = teamSize;
        this.table = new Table(teams.size(), teamSize);

    }

    public void run(int firstPlayerIndex){
        this.table.clearTable();

        PlayValue playValue = PlayValue.NORMAL;
        Player currentPlayer, nextPlayer;
        Team currentTeam, nextTeam;
        int nextIndex;

        Option opt, nextOpt;

        d.printString("Start of Play\n");

        currentTeam = teams.get(firstPlayerIndex%teams.size());
        currentPlayer = currentTeam.getPlayer(firstPlayerIndex/teams.size());

        nextIndex = (firstPlayerIndex+1)%(teamSize*teams.size());
        nextTeam = teams.get(nextIndex%teams.size());
        nextPlayer =nextTeam.getPlayer(nextIndex/teams.size());


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
                break;
            }

            System.out.println("Player: " + currentTeam.getPlayer(nextIndex/teams.size()).getId());
            
            currentTeam = nextTeam;
            currentPlayer = nextPlayer;

            nextIndex = (nextIndex + 1)%(teamSize*teams.size());
            nextTeam = teams.get(nextIndex%teams.size());
            nextPlayer = nextTeam.getPlayer(nextIndex/teams.size());
        }


        System.out.println("playValue " + playValue + ": " + playValue.getValue());

        winnerTeam.addScore(playValue.getValue());
    }


    public Team getWinnerTeam(){
        return winnerTeam;
    }

}
