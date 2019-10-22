package com.game.carrom;

import com.game.Main;
import com.game.board.Board;
import com.game.board.GameStatus;
import com.game.coin.Coin;
import com.game.coin.CoinType;
import com.game.player.Player;
import com.game.turns.*;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class GameOne implements Carrom {

    @Override
    public void start(){
        Coin black =new Coin(CoinType.Black.toString(),9);
        Coin red=new Coin(CoinType.Red.toString(),1);

        Coin[] coins=new Coin[2];
        coins[0]=black;
        coins[1]=red;

        Turn strike=new Strike();
        Turn multiStrike=new MultiStrike();
        Turn redStrike= new RedStrike();
        Turn strikerStrike= new StrikerStrike();
        Turn defunctCoin=new DefunctCoin();
        Turn none=new None();

        Turn[] turns=new Turn[6];
        turns[0]=strike;
        turns[1]=multiStrike;
        turns[2]=redStrike;
        turns[3]=strikerStrike;
        turns[4]=defunctCoin;
        turns[5]=none;

        Queue<String> turnsPlayerOne =new LinkedList<>();
        Queue<String> turnsPlayerTwo =new LinkedList<>();

        fileReader(Main.class.getClassLoader().getResourceAsStream("input/PlayerOne.txt"),turnsPlayerOne);
        fileReader(Main.class.getClassLoader().getResourceAsStream("input/PlayerTwo.txt"),turnsPlayerTwo);

        Player playerOne=new Player("Player One",turnsPlayerOne);
        Player playerTwo=new Player("Player Two",turnsPlayerTwo);

        Player[] players=new Player[2];
        players[0]=playerOne;
        players[1]=playerTwo;

        Board carromBoard=new Board(coins,players,turns,"Start");

        String result=carromBoard.play();

        //make class for winner result.
        if(result== GameStatus.Won.toString()) {
            Player winner=carromBoard.getWinner();
            int winnerIndex=carromBoard.getWinnerIndex();
            System.out.print(winner.getName() + " won the game. ");
            System.out.print("Final Score: "+players[winnerIndex].getTotalPoints());
            for(int i=0;i<players.length;i++)
                if(i!=winnerIndex)
                    System.out.print("-"+players[i].getTotalPoints());
        }
        else if(result==GameStatus.Draw.toString()){
            System.out.print("Game Draw. Final Score: "+players[0].getTotalPoints());
            for(int i=1;i<players.length;i++)
                System.out.print("-"+players[i].getTotalPoints());
        }
    }
    public static void fileReader(InputStream playeName, Queue<String> turns){
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(playeName))) {
            String line;
            while ((line = br.readLine()) != null) {
                turns.add(line.trim());
            }
        }
        catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
