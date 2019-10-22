package com.game.board;

import com.game.coin.Coin;
import com.game.coin.CoinsConsumed;
import com.game.player.Player;
import com.game.turns.Turn;


public class Board {
    private Coin[] coins;
    private Player[] players;
    private Turn[] turns;
    private String gameState;
    private Player winner;
    private int winnerIndex;

    public Board(Coin[] coins, Player[] players,Turn[] turns, String gameState) {
        this.coins = coins;
        this.players = players;
        this.turns=turns;
        this.gameState = gameState;
        this.winner = null;
        this.winnerIndex=-1;
    }

    public Player getWinner() {
        return winner;
    }

    public int getWinnerIndex(){
        return winnerIndex;
    }

    public String play(){
        gameState=GameStatus.InProgress.toString();
        while(coinExists()){
            for(Player player:players){
                if(coinExists()) {
                    String outcome = player.getMoves().poll();
                    if(outcome==null){
                        throw new NullPointerException(player.getName()+" should have appropriate number of moves.");
                    }
                    Turn findTurn = null;
                    for (Turn turn : turns) {
                        if (outcome.equals(turn.getName()))
                            findTurn = turn;
                    }
                    if (findTurn == null) {
                        throw new RuntimeException("Invalid Move. Please check spelling or enter a valid move.");
                    }
                    if (!isTurnValid(findTurn)) {
                        throw new RuntimeException("Check input validity in " + player.getName() + " file");
                    }
                    player.play(outcome, points(outcome));

                    updateBoard(findTurn);

                    if (hasWinner()) {
                        gameState = GameStatus.Won.toString();
                        return gameState;
                    }
                }
            }
        }
        gameState=GameStatus.Draw.toString();
        return gameState;
    }



    public boolean hasWinner(){
        Player first=players[0];
        int playerIndex=0;
        int maxPoint=players[0].getTotalPoints();
        int secondMaxPoint=players[1].getTotalPoints();
        for(int i=1;i<players.length;i++){
            if(players[i].getTotalPoints()>maxPoint){
                secondMaxPoint=maxPoint;
                maxPoint=players[i].getTotalPoints();
                first=players[i];
                playerIndex=i;
            }
        }
        if(maxPoint<5)
            return false;
        if(maxPoint-3<secondMaxPoint)
            return false;
        winner=first;
        winnerIndex=playerIndex;
        return true;
    }

    public int points(String outcome){
        int pointsVal=0;
        for(Turn turn:turns){
            if(turn.getName().equals(outcome))
                pointsVal=turn.getPoints();
        }
        return pointsVal;
    }

    public void updateBoard(Turn validTurn){
        for(CoinsConsumed coinsConsumed:validTurn.getCoinsConsumed()){
            String name=coinsConsumed.getCoin();
            int quantity=coinsConsumed.getQuantity();
            for(Coin coin:coins){
                if(name.equals(coin.getName())) {
                    coin.setCount(coin.getCount() - quantity);

                }
            }
        }
    }

    public boolean isTurnValid(Turn findTurn){
        for(CoinsConsumed coinsConsumed:findTurn.getCoinsConsumed()){
            String name=coinsConsumed.getCoin();
            int quantity=coinsConsumed.getQuantity();
            for(Coin coin:coins){
                if(name.equals(coin.getName()) && coin.getCount()<quantity)
                    return false;
            }
        }
        return true;
    }

    public boolean coinExists(){
        int coinsOnBoard=0;
        for(int i=0;i<coins.length;i++){
            coinsOnBoard+=coins[i].getCount();
        }
        return coinsOnBoard>0;
    }
}
