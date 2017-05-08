package controller;

import model.*;
import view.Display;
import java.util.List;


public class Play {

    private List<Team> teams;

    private int teamsScore[];

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
        this.teamsScore = new int[teams.size()];


    }

    public void run(int firstPlayerIndex){
        for(int i = 0; i < teams.size(); i++){
            this.teamsScore[i] = 0;
        }
        int firstWinnerId, winnerTeamId = 0;

        this.playValue = PlayValue.NORMAL;

        this.active = true;

        d.printString("Start of Play\n");

        System.out.println("Round 1");
        // First Round
        winnerTeamId = round(firstPlayerIndex);
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
                    
                    winnerTeamId = table.tie() ? -1 : table.getWinnerTeamId();
                    if(!table.tie()){
                        this.teamsScore[table.getWinnerTeamId()] = 2;
                    }
                }
                else{
                    this.teamsScore[table.getWinnerTeamId()] = 2;
                }

            }
            else{
                winnerTeamId = table.getWinnerTeamId();
                firstPlayerIndex = getIndexOfPlayer(table.getWinnerPlayerId());
                firstWinnerId = winnerTeamId;
                System.out.println("Round 1 Winner - Id: " + table.getWinnerPlayerId() + " Index: " + firstPlayerIndex);
                this.teamsScore[winnerTeamId] = 1;

                for(int i = 0; i < 2; i++){
                    System.out.println("Round " + (i+2));
                    winnerTeamId = round(firstPlayerIndex);
                    
                    if(!this.active){
                        this.teamsScore[winnerTeamId]++;
                        break;
                    }
                    else if(this.table.tie()){
                        System.out.println("tie - round " + (i+2));
                        this.teamsScore[firstWinnerId]++;
                        break;
                    }
                    else{
                        winnerTeamId = table.getWinnerTeamId();
                        firstPlayerIndex = getIndexOfPlayer(table.getWinnerPlayerId());
                        System.out.println("Round "+(i+2)+" Winner - Id: " +table.getWinnerPlayerId() + " Index: " + firstPlayerIndex);

                        this.teamsScore[winnerTeamId]++;
                        if(this.teamsScore[winnerTeamId] == 2){
                            break;
                        }
                    }
                }

            }
        }
        else{
            this.teamsScore[winnerTeamId] = 2;
        }


        System.out.println("playValue " + playValue + ": " + playValue.getValue());

        for(int i = 0; i < teams.size(); i++){
            System.out.print(this.teamsScore[i] + " ");
        }
        System.out.println();

        winnerTeam = null;
        for(int i = 0; i < teams.size(); i++){
            if(this.teamsScore[i] == 2){
                winnerTeam = teams.get(i);
                break;
            }
        }
        // for(Team t : teams){
        //     if(t.getId() == winnerTeamId){
        //         winnerTeam = t;
        //         break;
        //     }
        // }

        if(winnerTeam != null){
            System.out.println("Winner team:  " + String.valueOf(winnerTeam.getId()));
            for(Player player: winnerTeam.getPlayers()){
                System.out.println("Player "+ player.getId());
            }
            winnerTeam.addScore(playValue.getValue());
        }

    }

    private int getIndexOfPlayer(int id){

        for(Team t : teams){
            for (Player p : t.getPlayers()){
                System.out.print(p.getId() + " ");
            }
        }
        System.out.println();
        int teamIndex = 0;
        for(Team t : teams){
            int playerIndex = 0;
            for (Player p : t.getPlayers()){
                if(p.getId() == id){
                    return teamIndex + playerIndex*teams.size();
                }
                playerIndex++;
            }
            teamIndex++;
        }
        return 0;
    }


    //Return Id of winning team if fold, otherwise -1
    private int round(int firstPlayerIndex){
        int winnerTeamId = -1;
        this.table.clearTable();

        // for(Team team: teams){
        //     for(Player player: team.getPlayers()){
        //         table.addCard(player.getHand().getCards().get(0),player.getId(), team.getId());
        //     }
        // }
        this.table.printTable();
        teams.get(0).getPlayer(0).getHand().printHand();
        Player currentPlayer, nextPlayer;
        Team currentTeam, nextTeam;
        int nextIndex;
        Option opt, nextOpt;

        System.out.println("firstPlayerIndex: " + firstPlayerIndex);
        currentTeam = teams.get(firstPlayerIndex%teams.size());
        currentPlayer = currentTeam.getPlayer(firstPlayerIndex/teams.size());
        System.out.println("currentPlayer: " + currentPlayer.getId());
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

            System.out.print("Player " + currentPlayer.getId());
            if(opt == Option.ACCEPT){
                // put card on table
                System.out.println(" Accepting");
                currentPlayer.playCard(table,currentTeam);
            }
            else if(opt == Option.FOLD){
                System.out.println(" Folding");
                winnerTeamId = nextTeam.getId();
                this.active = false;
                break;
            }

            currentTeam = nextTeam;
            currentPlayer = nextPlayer;

            nextIndex = (nextIndex + 1)%(teamSize*teams.size());
            nextTeam = teams.get(nextIndex%teams.size());
            nextPlayer = nextTeam.getPlayer(nextIndex/teams.size());
        }

        return winnerTeamId;
    }

    public Team getWinnerTeam(){
        return winnerTeam;
    }

}
