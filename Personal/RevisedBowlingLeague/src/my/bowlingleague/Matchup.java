package my.bowlingleague;

import java.io.*;

/**
 * Class to represent a bowling matchup.
 * @author Michael Bowen
 * JDK 8, NetBeans 8.0
 * @version 1.0
 */
public class Matchup implements Serializable {
    private int lane, team1, team2;
    
    /**
     * Constructor to create a matchup between two bowling teams.
     * @param lane The lane number Team 1 will start on.
     * @param team1 The first team in the matchup.
     * @param team2 The second team in the matchup.
     */
    public Matchup(int lane, int team1, int team2) {
        this.lane = lane;
        this.team1 = team1;
        this.team2 = team2;
    } //ends constructor
    
    /**
     * Gets the number of the left lane of the matchup.
     * @return The lane number.
     */
    public int getLane() {
        return lane;
    } //ends getLane
    
    /**
     * Gets the number representing the first team of the matchup.
     * @return The number of the first team of the matchup.
     */    
    public int getTeam1() {
        return team1;
    } //ends getTeam1
    
    /**
     * Gets the number representing the second team of the matchup.
     * @return The number of the second team of the matchup.
     */
    public int getTeam2() {
        return team2;
    } //ends getTeam2
} //ends Matchup
