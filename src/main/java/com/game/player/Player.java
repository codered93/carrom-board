package com.game.player;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Queue;

public class Player {

    private String name;
    private int totalPoints;
    private int foulCount;
    private int missedCount;
    private Queue<String> moves;

    public Player(String name, Queue<String> moves) {
        this.name = name;
        this.totalPoints = 0;
        this.foulCount = 0;
        this.missedCount = 0;
        this.moves=moves;
    }

    public void play(String turnName,int points){
        totalPoints+=points;
        updateFouls(turnName);
        updateMisses(turnName);
    }

    public void updateFouls(String turnName){
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            prop.load(input);

            String[] foulTurns = prop.getProperty("FOUL_TURNS").split(",");
            int foulPenaltyPoints=Integer.parseInt(prop.getProperty("FOUL_PENALTY_POINTS"));
            int maxFoulCount=Integer.parseInt(prop.getProperty("MAX_FOUL_COUNT"));

            for(String foul:foulTurns){
                if(turnName.equals(foul)){
                    foulCount+=1;
                    if(foulCount==maxFoulCount)
                        totalPoints+=foulPenaltyPoints;
                    foulCount=0;
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void updateMisses(String turnName){
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            prop.load(input);

            String[] missedTurns = prop.getProperty("MISSED_TURNS").split(",");
            int missedPenaltyPoints=Integer.parseInt(prop.getProperty("MISSED_PENALTY_POINTS"));
            int maxMissedCount=Integer.parseInt(prop.getProperty("MAX_MISSED_COUNT"));


            for(String miss:missedTurns){
                if(turnName.equals(miss)){
                    missedCount+=1;
                    if(missedCount==maxMissedCount)
                        totalPoints+=missedPenaltyPoints;
                    missedCount=0;
                }
                else
                    missedCount=0;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public Queue<String> getMoves() {
        return moves;
    }
}
