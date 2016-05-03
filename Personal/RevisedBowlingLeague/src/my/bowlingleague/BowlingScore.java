/*
 * Class to represent a bowling score.
 * @author Mike Bowen
 * Created on 4/29/14, updated 6/25/14
 */
package my.bowlingleague;
import java.io.*;               //for serializable

public class BowlingScore implements Serializable {
    private int score, hdcp;
    private boolean rolloff, absent;
    
    /**
     * Two-arg constructor to create a bowling score that is not a rolloff or an absentee score.     * 
     * @param score The score for the game.
     * @param hdcp The handicap the bowler had at the time of the game.
     */
    public BowlingScore(int score, int hdcp) {
        this.score = score;
        this.hdcp = hdcp;
        rolloff = false;
        absent = false;
    } //ends 2 arg constructor - assumes score was neither a rolloff or absent
    
    /**
     * Four-arg contructor to create a bowling score that may or may not be a rolloff
     * or absentee score. Roll off and absent are mutually exclusive. Use absent for
     * bowlers who did not roll off, whether on injured reserve or just absent.
     * @param score The score of the game.
     * @param hdcp The handicap of the bowler at the time the game was bowled.
     * @param rolloff Whether or not the game was a roll off.
     * @param absent Whether or not the bowler was absent.
     */
    public BowlingScore(int score, int hdcp, boolean rolloff, boolean absent) {
        this.score = score;
        this.hdcp = hdcp;
        this.rolloff = rolloff;
        this.absent = absent;
    } //ends 4 arg constructor
    
    /**
     * Copy constructor to create a new object containing the same data as the passed in BowlingScore.
     * @param oldScore The BowlingScore object to copy.
     */
    public BowlingScore(BowlingScore oldScore) {
        score = oldScore.getScore();
        hdcp = oldScore.getHdcp();
        rolloff = oldScore.isRolloff();
        absent = oldScore.isAbsent();
    } //ends copy constructor
    
    /**
     * Function to set the score of the game.
     * @param score The score of the bowling game.
     */
    public void setScore(int score) {
        this.score = score;
    } //ends setScore
    
    /**
     * Function to get the score of the game.
     * @return The score of the bowling game.
     */
    public int getScore() {
        return score;
    } //ends getScore
    
    /**
     * Function to set the handicap for the bowling score.
     * @param hdcp The handicap the bowler had at the time the game was bowled.
     */
    public void setHdcp(int hdcp) {
        this.hdcp = hdcp;
    } //ends setHdcp
    
    /**
     * Function to get the handicap the bowler had at the time of the game.
     * @return The handicap the bowler had when the game was bowled.
     */
    public int getHdcp() {
        return hdcp;
    } //ends getHdcp
    
    /**
     * Function to get the score of the game, adjusted with handicap.
     * @return The score with the handicap added
     */
    public int getHdcpScore() {
        return (score + hdcp);
    } //ends getHdcpScore
    
    /**
     * Function to set whether or not the game was a roll off.
     * @param rolloff True if game was a roll off, false otherwise.
     */
    public void setRolloff(boolean rolloff) {
        this.rolloff = rolloff;
    } //ends setRolloff
    
    /**
     * Function to get whether or not the game was a roll off.
     * @return True if game was a roll off, false otherwise.
     */
    public boolean isRolloff() {
        return rolloff;
    } //ends isRolloff
    
    /**
     * Function to set whether or not the bowler was absent for this game.
     * @param absent True if bowler was absent, false otherwise.
     */
    public void setAbsent(boolean absent) {
        this.absent = absent;
    } //ends setAbsent
    
    /**
     * Function to get whether or not the bowler was absent for this game.
     * @return True if bowler was absent, false otherwise.
     */
    public boolean isAbsent() {
        return absent;
    } //ends setScore        
    
    /**
     * To string method to return string representation of the bowling score.
     * @return The bowling score as a string, with "A" for absent and "R" for rolloff.
     */
    public String toString() {
        if(rolloff)
            return (score + "R");
        else if(absent) 
            return (score + "A");
        else
            return (score + "");
    } //ends toString
} //ends BowlingScore