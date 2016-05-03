package my.bowlingleague;

/**
 * Class to represent a bowler
 * @author Mike Bowen
 */
import java.io.*;

public class Bowler implements Serializable{
    private String name;
    private boolean perfectAttendance, newBowler;
    private int gamesPlayed, gamesAbsent, gamesRollOff, totalPins, currentHdcp,
                highGame, highSeries, hdcpGame, hdcpSeries, highRolloffGame, highRolloffSeries,
                highHdcpRolloffGame, highHdcpRolloffSeries;
    private double avg, prevAvg;
    private int teamID;             //used for accessing the bowler via team
    private int teamSpotID;         //used for accessing the bowler via team
    //two dimensional bowling score array - number of weeks the league convenes x 3 games per week in size
    private BowlingScore[][] scores;
    //creates objects to hold the high games and series. Needed for determining if they were rolloffs or not
    //private BowlingScore highGameObject, highHdcpGameObject;
    //private BowlingScore[] highSeriesArray, highHdcpSeriesArray;
    //array to hold the players handicap by week - used as a record of what the players hdcp has been 
    //private int[] hdcps;
    
    /**
     * 1-arg constructor to create a dummy bowler.
     * @param numWeeks The number of weeks the league runs. Used to initialize score array.
     */
    public Bowler(int numWeeks) {
        name = "Dummy";
        avg = 135;
        prevAvg = 135;
        
        //initialize other variables
        perfectAttendance = true;
        gamesPlayed = 0;
        gamesAbsent = 0;
        gamesRollOff = 0;
        currentHdcp = 0;
        highGame = 0;
        highSeries = 0;
        hdcpGame = 0;
        hdcpSeries = 0;
        highRolloffGame = 0;
        highRolloffSeries = 0;
        highHdcpRolloffGame = 0;
        highHdcpRolloffSeries = 0;
        teamID = 0;
        teamSpotID = 0;
        newBowler = false;
        
        //initialze score array
        scores = new BowlingScore[numWeeks][3];
        for(int i = 0; i < numWeeks; i++) {
            for(int j = 0; j < 3; j++) {
                scores[i][j] = null;
            } //end inner loop
        } //end outer loop
    } //ends no arg constructor - used to create dummy bowlers
 
    /**
     * Three-arg constructor to create a bowler with a name and an average.
     * @param name The bowler's name.
     * @param avg The bowler's average.
     * @param numWeeks The number of weeks the league runs. Used to initialize score array.
     */
    public Bowler(String name, int avg, int numWeeks) {
        this.name = name;
        this.prevAvg = avg;
        this.avg = avg;
        
        //initialize other variables
        perfectAttendance = true;
        gamesPlayed = 0;
        gamesAbsent = 0;
        gamesRollOff = 0;
        currentHdcp = 0;
        highGame = 0;
        highSeries = 0;
        hdcpGame = 0;
        hdcpSeries = 0;
        highRolloffGame = 0;
        highRolloffSeries = 0;
        highHdcpRolloffGame = 0;
        highHdcpRolloffSeries = 0;
        teamID = 0;
        teamSpotID = 0;
        newBowler = false;
        
        //initialze score array
        scores = new BowlingScore[numWeeks][3];
        for(int i = 0; i < numWeeks; i++) {
            for(int j = 0; j < 3; j++) {
                scores[i][j] = null;
            } //end inner loop
        } //end outer loop
    } //ends 2 arg constructor
    
    /**
     * Copy constructor
     * @param oldBowler The bowler to copy.
     */
    public Bowler(Bowler oldBowler) {
        name = oldBowler.getName();
        prevAvg = oldBowler.getPrevAvg();
        avg = oldBowler.getAvg();
        
        //initialize other variables
        perfectAttendance = oldBowler.getPerfectAttendance();
        gamesPlayed = oldBowler.getGamesPlayed();
        gamesAbsent = oldBowler.getGamesAbsent();
        gamesRollOff = oldBowler.getGamesRollOff();
        currentHdcp = oldBowler.getHdcp();
        highGame = oldBowler.getHighGame();
        highSeries = oldBowler.getHighSeries();
        hdcpGame = oldBowler.getHighHdcpGame();
        hdcpSeries = oldBowler.getHighHdcpSeries();
        highRolloffGame = oldBowler.getHighRolloffGame();
        highRolloffSeries = oldBowler.getHighRolloffSeries();
        highHdcpRolloffGame = oldBowler.getHighHdcpRolloffGame();
        highHdcpRolloffSeries = oldBowler.getHighHdcpRolloffSeries();
        teamID = 0;
        teamSpotID = 0;
        newBowler = oldBowler.isNewBowler();
        
        //initialze score array
        scores = new BowlingScore[oldBowler.getNumWeeks()][3];
        for(int i = 0; i < oldBowler.getNumWeeks(); i++) {
            for(int j = 0; j < 3; j++) {
                scores[i][j] = oldBowler.getScore(i, j);
            } //end inner loop
        } //end outer loop
    }
    
    /**
     * Function to set the bowler's name.
     * @param name The name of the bowler.
     */
    public void setName(String name) {
        this.name = name;
    } //ends setName
    
    /**
     * Method to get the bowler's name
     * @return The bowlers name.
     */
    public String getName() {
        return name;
    } //ends getName
    
    /**
     * Function to set the bowler's average.
     * @param avg The bowler's average.
     */
    public void setAverage(double avg) {
        if(totalPins == 0) {
            this.avg = avg;
        } else {
            this.avg = (totalPins * 1.0) / (gamesPlayed + gamesRollOff);
        } //ends if/else            
    } //ends getAvg
    
    /**
     * Method to get the bowler's average.
     * @return The bowler's average.
     */
    public double getAvg() {
        return avg;
    } //ends getAvg    
    
    /**
     * Function to set the previous year's average of the bowler to the current average.
     * Function is only be called when calculating the stats for the first week of bowling.
     */
    public void setPrevAvg() {
        if(newBowler) {
            prevAvg = 300;
        } else {
            prevAvg = avg;
        }        
    } //ends setPrevAvg
    
    /**
     * Gets the bowlers average from the previous year.
     * @return The previous year's average.
     */
    public double getPrevAvg() {
        return prevAvg;
    } //ends getPrevAvg
    
    /**
     * Function used to manually change the bowler's previous average.
     * @param prevAvg The bowler's average from the previous year.
     */
    public void changePrevAvg(double prevAvg) {
        this.prevAvg = prevAvg;
    } //ends changePrevAvg
                
    /**
     * Function to determine the bowlers current handicap.
     * @param highAverage The highest average in the league.
     */
    public void setHdcp(int highAverage) {
        if(avg != 0)
            currentHdcp = highAverage - (int)Math.round(avg);
        else
            //this is in case bowler is new and does not yet have an average.
            //will give the bowler a handicap based on dummy score.
            currentHdcp = highAverage - 135;
    } //ends setHdcp
    
    /**
     * Method to get the bowler's handicap.
     * @return The bowlers current handicap.
     */
    public int getHdcp() {
        return currentHdcp;
    } //end sgetHdcp
    
    /**
     * Function to set the bowler's games played.
     * @param gamesPlayed The number of games the bowler has bowled.
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    } //ends setGamesPlayed
    
    /**
     * Method to get the bowler's games played.
     * @return The number of games the bowler has bowled.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    } //ends getGamesPlayed
    
    /**
     * Function to set the bowler's games rolled off.
     * @param gamesRollOff The number of games the bowler has rolled off.
     */
    public void setGamesRollOff(int gamesRollOff) {
        this.gamesRollOff = gamesRollOff;
    } //ends setGamesRollOff
    
    /**
     * Method to get the bowler's games rolled off.
     * @return The number of games the bowler has rolled off.
     */
    public int getGamesRollOff() {
        return gamesRollOff;
    } //ends getGamesRollOff
    
    /**
     * Function to set the bowler's games absent.
     * @param gamesAbsent The number of games the bowler has been absent.
     */
    public void setGamesAbsent(int gamesAbsent) {
        this.gamesAbsent = gamesAbsent;
    } //ends setGamesAbsent
    
    /**
     * Method to get the bowler's games absent.
     * @return The number of games the bowler has been absent.
     */
    public int getGamesAbsent() {
        return gamesAbsent;
    } //ends getGamesAbsent
    
    /**
     * Method to determine whether or not the bowler has perfect attendance. 
     * Bowler has perfect attendance if he/she has not rolled off or been absent.
     */
    public void setPerfectAttendance() {
        if(gamesRollOff == 0 && gamesAbsent == 0) {
            perfectAttendance = true;
        } else {
            perfectAttendance = false;
        } //ends if-else           
    } //ends setPerfectAttendance
    
    /**
     * Method to get whether or not the bowler has prefect attendance.
     * @return True of bowler has perfect attendance, false otherwise.
     */
    public boolean getPerfectAttendance() {
        return perfectAttendance;
    } //ends getPerfectAttendance
    
    /**
     * Function to determine the bowler's change in average over the season. Used to determine
     * end of year honors for most improved bowler.
     * @return Positive number if the bowler's average went up, negative if the average went down.
     */
    public int averageChange() {
        return ((int) (Math.round(avg) - Math.round(prevAvg)));
    } //ends averageChange
    
    /**
     * Function to get the bowler's total number of pins earned. Total pins earned does not include absent scores.
     * @return The bowler's total number of pins earned.
     */
    public int getTotalPins() {
        return totalPins;
    } //ends getHighHdcpGame
    
    /**
     * Function to get the bowler's high game.
     * @return The bowler's highest game.
     */
    public int getHighGame() {
        return highGame;
    } //ends getHighGame
    
    /**
     * Function to get the bowler's high series.
     * @return The bowler's highest series.
     */
    public int getHighSeries() {
        return highSeries;
    } //ends getHighSeries
    
    /**
     * Function to get the bowler's high game with handicap.
     * @return The bowler's highest game with handicap.
     */
    public int getHighHdcpGame() {
        return hdcpGame;
    } //ends getHighHdcpGame
    
    /**
     * Function to get the bowler's high series with handicap.
     * @return The bowler's highest series with handicap.
     */
    public int getHighHdcpSeries() {
        return hdcpSeries;
    } //ends getHighHdcpGame
    
    /**
     * Sets the bowler's high rolloff game score.
     * @param score The score of the bowler's highest rolloff game.
     */
    public void setHighRolloffGame(int score) {
        highRolloffGame = score;
    } //ends setHighRolloffGame
    
    /**
     * Function to get the bowler's highest rolloff game
     * @return The score of the bowler's highest rolloff.
     */
    public int getHighRolloffGame() {
        return highRolloffGame;
    } //ends getHighRolloffGame
    
    /**
     * Sets the bowler's high rolloff series score.
     * @param score The score of the bowler's highest rolloff series.
     */
    public void setHighRolloffSeries(int score) {
        highRolloffSeries = score;
    } //ends setHighRolloffSeries
    
    /**
     * Function to get the bowler's highest rolloff series.
     * @return The score of the bowler's highest rolloff series.
     */
    public int getHighRolloffSeries() {
        return highRolloffSeries;
    } //ends getHighRolloffSeries
    
    /**
     * Sets the high handicap game for a game that may have been a rolloff.
     * @param score Score of the game.
     */
    public void setHighHdcpRolloffGame(int score) {
        highHdcpRolloffGame = score;
    } //ends setHighHdcpRolloffGame
    
    /**
     * Get the high rolloff game including handicap.
     * @return The bowler's highest rolloff score including handicap.
     */
    public int getHighHdcpRolloffGame() {
        return highHdcpRolloffGame;
    } //ends getHighHdcpRolloffGame
    
    /**
     * Sets the high handicap series for a series that may have been a rolloff.
     * @param score Score of the series.
     */
    public void setHighHdcpRolloffSeries(int score) {
        highHdcpRolloffSeries = score;
    } //ends setHighHdcpRolloffSeries
    
    /**
     * Get the high rolloff series including handicap.
     * @return The bowler's highest rolloff series including handicap.
     */
    public int getHighHdcpRolloffSeries() {
        return highHdcpRolloffSeries;
    } //ends getHighHdcpRolloffSeries
    
    /**
     * Function to get a particular bowling score from the score array.
     * @param week The week the game took place.
     * @param game The game number.
     * @return 
     */
    public BowlingScore getScore(int week, int game) {
        if(game >= 0 && game <= 2)
            return scores[week][game];
        else
            //return an impossible score if there's a problem with the game number
            return new BowlingScore(999, 0, false, false);
    } //ends getScore
    
    /**
     * Sets the score for game number game, in week number week.
     * @param week The week the game was bowled.
     * @param game The number of the game.
     * @param score The BowlingScore object to store.
     */
    public void setScore(int week, int game, BowlingScore score) {
        if((game >= 0 && game <= 2) && (week >= 0 && week < scores.length)) 
            scores[week][game] = score;        
    } //ends setScore
    
    /**
     * Function to determine whether the bowler is new to the league or not.
     * @return True of bowler is new to league, false otherwise.
     */
    public boolean isNewBowler() {
        return newBowler;
    } //ends isNewBowler
    
    /**
     * Function to set whether or not the bowler is new. Should be called with the argument false
     * after the new bowler has bowled a week to establish handicap.
     * @param newBowler True of false.
     */
    public void setNewBowler(boolean newBowler) {
        this.newBowler = newBowler;
    } //ends setNewBowler
    
    /**
     * Function to get the number of weeks the bowler will bowl in a season.
     * Used in the copy constructor to create the new score array.
     * @return The length of the score array.
     */
    public int getNumWeeks() {
        return scores.length;
    } //ends getNumWeeks
    
    /**
     * Update the stats for the bowler.
     * @param week The week that has just been completed.
     */
    public void updateStats(int week) {
        int seriesScore = 0;
        int hdcpSeriesScore = 0;
        boolean wasRolloff = false, wasAbsent = false;
        //for loop to cycle through the three games of the week
        for(int i = 0; i < 3; i++) {
            int tempScore = scores[week - 1][i].getScore();
            int tempHdcpScore = scores[week - 1][i].getHdcpScore();
            if(scores[week - 1][i].isAbsent()) {
                //set perfect attendance equal to false since the bowler was absent
                perfectAttendance = false;
                //increment gamesAbsent
                gamesAbsent++;
                //set absent flag
                wasAbsent = true;
            } else if(scores[week - 1][i].isRolloff()) {
                //set perfect attendance equal to false because the bowler rolled off
                perfectAttendance = false;
                //increment gamesRolloff
                gamesRollOff++;
                //add game score to total pins and series score
                totalPins += tempScore;
                seriesScore += tempScore;
                hdcpSeriesScore += tempHdcpScore;
                
                //update high games if necessary
                if(tempScore > highRolloffGame)
                    highRolloffGame = tempScore;
                if(tempHdcpScore > highHdcpRolloffGame)
                    highHdcpRolloffGame = tempHdcpScore;
                                
                //set rolloff flag
                wasRolloff = true;
            } else {
                //increment gamesPlayed
                gamesPlayed++;
                //add game score to total pins and series score
                totalPins += tempScore;
                seriesScore += tempScore;
                hdcpSeriesScore += tempHdcpScore;
                
                //update high games if necessary
                if(tempScore > highGame)
                    highGame = tempScore;
                if(tempHdcpScore > hdcpGame)
                    hdcpGame = tempHdcpScore;
            } //ends if-else               
        } //ends for loop
        
        if(wasRolloff || wasAbsent) {
            //update high series if necessary
            if(seriesScore > highRolloffSeries)
                highRolloffSeries = seriesScore;
            if(hdcpSeriesScore > highHdcpRolloffSeries)
                highHdcpRolloffSeries = hdcpSeriesScore;
                        
            //update bowler's average
            if((gamesPlayed + gamesRollOff) > 0)
                avg = totalPins / (gamesPlayed + gamesRollOff / 1.0);
            else if(newBowler)
                //sets average to 0 - should only happen if bowler has not yet bowled any games.
                avg = 0;
        }  else if(!wasRolloff && !wasAbsent) {
            //update high series if necessary
            if(seriesScore > highSeries)
                highSeries = seriesScore;
            if(hdcpSeriesScore > hdcpSeries)
                hdcpSeries = hdcpSeriesScore;
            
            //update bowler's average
            avg = totalPins / (gamesPlayed + gamesRollOff / 1.0);            
        } //ends if-else
                        
        if(newBowler) {
            //reset hdcp game and series scores to 0 since bowler does not have actual handicap yet
            hdcpGame = 0;
            hdcpSeries = 0;
            highHdcpRolloffGame = 0;
            highHdcpRolloffSeries = 0;                 
        } //ends if
    } //ends updateStats
    
    /**
     * String representation of the bowler.
     * @return The bowler's name and stats.
     */
    public String toString() {
        String output = "Name: " + name + "\n\tGames: " + (gamesPlayed + gamesRollOff) + 
                "\n\tTotal: " + totalPins + "\n\tAvg: " + Math.round(avg) + "\n\tHdcp: " + currentHdcp; 
        if(highRolloffGame > highGame)
            output += "\n\tHigh: " + highRolloffGame;
        else
            output += "\n\tHigh: " + highGame;
        if(highRolloffSeries > highSeries)
            output += "\n\tHigh/3: " + highRolloffSeries;
        else 
            output += "\n\tHigh/3: " + highSeries;
        if(highHdcpRolloffGame > hdcpGame) 
            output += "\n\tGame Hdcp: " + highHdcpRolloffGame; 
        else
            output += "\n\tGame Hdcp: " + hdcpGame; 
        if(highHdcpRolloffSeries > hdcpSeries)
            output += "\n\tSeries Hdcp: " + highHdcpRolloffSeries + "\n";
        else
            output += "\n\tSeries Hdcp: " + hdcpSeries + "\n";
        
        return output;        
    } //ends toString
    
    /**
     * Gets the standard deviation of the bowler's scores.
     * @return The standard deviation of the bowler's scores.
     */
    public double getStdDev() {
        int average = (int)Math.round(avg);
        int difference = 0;
        double variance = 0.0;
        int numScores = 0;
        double stdDev = 0.0;
        
        for(int i = 0; i < scores.length; i++) {
            for(int j = 0; j < 3; j++) {
                if(scores[i][j] != null) {
                    difference = scores[i][j].getScore() - average;
                    variance += (difference * difference);
                    numScores++;
                }
            } //ends inner for
        } //ends outer for
        
        if(numScores != 0) {
            variance /= (numScores * 1.0);
        } //ends if
        
        stdDev = Math.sqrt(variance);
        
        return stdDev;
    }
    
}//ends Bowler