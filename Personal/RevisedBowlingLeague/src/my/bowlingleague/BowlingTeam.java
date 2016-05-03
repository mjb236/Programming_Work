package my.bowlingleague;

/**
 * Class to represent a Bowling team.
 * @author Mike Bowen
 */
import java.io.*;

public class BowlingTeam implements Serializable{
    private String name;
    private int wins, losses, ties, totalPinsWins, totalPinsTies, totalPins, hdcp, hdcpGame,
                hdcpSeries;
    private double totalPoints;
    private Bowler[] bowlers;
    private BowlingScore[][] scores;
    //keep track of the total teams in the league
    private static int numTeams = 0;

    /**
     * One-arg constructor that creates a new bowling team defaulted to 4 bowlers in the team.
     * @param numWeeks The number of weeks the league runs. Used to initialize score array.
    */
    public BowlingTeam(int numWeeks) {
        numTeams++;
        name = "Team " + numTeams;
        //default bowlers to an array of 4 bowlers and initilized with dummy bowlers
        bowlers = new Bowler[4];
        for(int i = 0; i < bowlers.length; i++){
            bowlers[i] = new Bowler(numWeeks);
        }//ends for loop
        
        //initialize variables to 0
        wins = 0;
        losses = 0;
        ties = 0;
        totalPinsWins = 0;
        totalPinsTies = 0;
        totalPins = 0;
        hdcp = 0;
        hdcpGame = 0;
        hdcpSeries = 0;
        totalPoints = 0.0;
        
        //initialze score array
        scores = new BowlingScore[numWeeks][3];
        for(int i = 0; i < numWeeks; i++) {
            for(int j = 0; j < 3; j++) {
                scores[i][j] = null;
            } //end inner loop
        } //end outer loop
    } //ends no arg constructor
    
    /**
     * Two-arg constructor that creates a bowling team with name teamName and defaulted
     * to having 4 bowlers in the team.
     * @param teamName Name of the bowling team.
     * @param numWeeks The number of weeks the league runs. Used to initialize score array.
     */
    public BowlingTeam(String teamName, int numWeeks) {
        numTeams++;
        name = teamName;
        bowlers = new Bowler[4];
        for(int i = 0; i < bowlers.length; i++){
            bowlers[i] = new Bowler(numWeeks);
        }//ends for loop
        
        //initialize variables to 0
        wins = 0;
        losses = 0;
        ties = 0;
        totalPinsWins = 0;
        totalPinsTies = 0;
        totalPins = 0;
        hdcp = 0;
        hdcpGame = 0;
        hdcpSeries = 0;
        totalPoints = 0.0;
        
        //initialze score array
        scores = new BowlingScore[numWeeks][3];
        for(int i = 0; i < numWeeks; i++) {
            for(int j = 0; j < 3; j++) {
                scores[i][j] = null;
            } //end inner loop
        } //end outer loop
    } //ends 1 arg constructor
    
    /**
     * Three-arg constructor that creates a team with name teamName with numBowlers bowlers.
     * @param teamName Name of the bowling team.
     * @param numBowlers Number of bowlers on the team.
     * @param numWeeks The number of weeks the league runs. Used to initialize score array.
     */
    public BowlingTeam(String teamName, int numBowlers, int numWeeks) {
        numTeams++;
        name = teamName + " " + numTeams;
        bowlers = new Bowler[numBowlers];
        for(int i = 0; i < bowlers.length; i++) {
            bowlers[i] = new Bowler(numWeeks);
        } //ends for loop
        
        //initialize variables to 0
        wins = 0;
        losses = 0;
        ties = 0;
        totalPinsWins = 0;
        totalPinsTies = 0;
        totalPins = 0;
        hdcp = 0;
        hdcpGame = 0;
        hdcpSeries = 0;
        totalPoints = 0.0;
        
        //initialze score array
        scores = new BowlingScore[numWeeks][3];
        for(int i = 0; i < numWeeks; i++) {
            for(int j = 0; j < 3; j++) {
                scores[i][j] = null;
            } //end inner loop
        } //end outer loop
    }//ends 2 arg constructor
    
    /**
     * Method that returns the team bowler at position position.
     * @param position Position of the bowler on the team.
     * @return Bowler at the position supplied.
     */
    public Bowler getBowler(int position) {
        return bowlers[position];
    } //ends getBowler
    
    /**
     * Function to set the bowler at position position equal to the passed in bowler.
     * @param bowler The bowler object to replace the old bowler at position.
     * @param position The position of the bowler in the bowler array.
     */
    public void setBowler(Bowler bowler, int position) {
        bowlers[position] = new Bowler(bowler);
    } //ends setBowler
    
    /**
     * Sets the name of the bowling team.
     * @param name The new name of the team.
     */
    public void setName(String name) {
        this.name = name;
    } //ends setName
    
    /**
     * Method to get the name of the bowling team.
     * @return The name of the team.
     */
    public String getName() {
        return name;
    } //ends getName
    
    /**
     * Sets the number of games the team has won.
     * @param wins The number of games the team has won.
     */    
    public void setWins(int wins) {
        this.wins = wins;
    } //ends setWins
    
    /**
     * Method to get the number of games the team has won.
     * @return The number of games the team has won.
     */
    public int getWins() {
        return wins;
    } //ends getWins
    
    /**
     * Sets the number of games the team lost.
     * @param losses The number of games the team has lost.
     */    
    public void setLosses(int losses) {
        this.losses = losses;
    } //ends setLosses
    
    /**
     * Method to get the number of games the team has lost.
     * @return The number of games the team has lost.
     */
    public int getLosses() {
        return losses;
    } //ends getLosses
    
    /**
     * Sets the number of games the team tied.
     * @param ties The number of games the team has tied.
     */    
    public void setTies(int ties) {
        this.ties = ties;
    } //ends setTies
    
    /**
     * Method to get the number of games the team has tied.
     * @return The number of games the team has tied.
     */
    public int getTies() {
        return ties;
    } //ends getTies
    
    /**
     * Sets the number of times the team has won total pins in a match.
     * @param totalPinsWins The number of times the team has won total pins in a match.
     */    
    public void setTotalPinsWins(int totalPinsWins) {
        this.totalPinsWins = totalPinsWins;
    } //ends setTotalPinsWins
    
    /**
     * Method to get the number of times the team has won total pins in a match.
     * @return The number of times the team has won total pins in a match.
     */
    public int getTotalPinsWins() {
        return totalPinsWins;
    } //ends getTotalPinsWins
    
    /**
     * Sets the number of times the team has tied total pins in a match.
     * @param totalPinsTies The number of times the team has tied total pins in a match.
     */    
    public void setTotalPinsTies(int totalPinsTies) {
        this.totalPinsTies = totalPinsTies;
    } //ends setTotalPinsTies
    
    /**
     * Method to get the number of times the team has tied total pins in a match.
     * @return The number of times the team has tied total pins in a match.
     */
    public int getTotalPinsTies() {
        return totalPinsTies;
    } //ends getTotalPinsTies
    
    /**
     * Sets the total number of pins the team has earned.
     * @param totalPins The total number of pins the team has earned.
     */    
    public void setTotalPins(int totalPins) {
        this.totalPins = totalPins;
    } //ends setTotalPins
    
    /**
     * Method to get the total number of pins the team has earned.
     * @return The total number of pins the team has earned.
     */
    public int getTotalPins() {
        return totalPins;
    } //ends getTotalPins
    
    /**
     * Function to set the handicap for the team. 
     * Team handicap is sum of all bowler handicaps.
     */
    private void setHdcp() {
        for(int i = 0; i < bowlers.length; i++) {
            hdcp += bowlers[i].getHdcp();
        } //ends for loop
    } //ends setHdcp
    
    /**
     * Method to get the team's total handicap
     * @return The team's total handicap.
     */
    public int getHdcp() {
        return hdcp;
    } //ends getHdcp
    
    /**
     * Private function to determine and store the teams high handicap game.
     * TO BE IMPLEMENTED *************************************
     */
    private void setHdcpGame() {
        //variable to store the game to compare to current high handicap game.
        int tempGame = 0;        
    } //ends setHdcpGame
    
    /**
     * Method to get the team's highest game with handicap.
     * @return The team's high handicap game.
     */
    public int getHdcpGame() {
        return hdcpGame;
    } //ends getHdcpGame
    
    /**
     * Private function to determine and store the teams high handicap series.
     * TO BE IMPLEMENTED *************************************
     */
    private void setHdcpSeries() {
        //variable to store the game to compare to current high handicap series.
        int tempSeries = 0;        
    } //ends setHdcpSeries
    
    /**
     * Method to get the team's highest series with handicap.
     * @return The teams high handicap series.
     */
    public int getHdcpSeries() {
        return hdcpSeries;
    } //ends getHdcpSeries
    
    /**
     * Get the total number of points earned by the team. 2 points per win, 1 point
     * per totalPinsWin, 1 point per tie and 0.5 points for a totalPinsTie.
     * @return The total number of points earned by the team.
     */
    public double getTotalPoints() {
        return totalPoints;
    } //ends getTotalPoints
    
    /**
     * Function to update the teams total points, based on wins/losses/ties/totalPinWins/totalPinsTies
     */
    public void setTotalPoints() {
        totalPoints = (wins * 2) + (ties) + (totalPinsWins) + (totalPinsTies * 0.5);
    } //ends setTotalPoints
    
    /**
     * Sets the team's total handicap.
     * @param hdcp The sum of the team's bowler's handicaps.
     */
    public void setHdcp(int hdcp) {
        this.hdcp = hdcp;
    } //ends setHdcp
    
    /**
     * Function to update the stats of the team after the current week of bowling has
     * been entered and finalized. This function will add points to total points and
     * update hdcp, hdcpGame and hdcpSeries as necessary.
     * @param week The week of the league just completed.
     * @param highAvg The highest average in the league. Used for dummy and new bowler calculations.
     */
    public void updateScores(int week, int highAvg) {
        //flags to determine whether there was a rolloff or absence. used to negate the hdcp game/series
        //scores.
        boolean game1Valid = true, game2Valid = true, game3Valid = true;
        int gameOne = 0, gameTwo = 0, gameThree = 0;
        
        //calculate game totals
        for(int i = 0; i < bowlers.length; i++) {
            Bowler b = bowlers[i];
            if(b.getName().equalsIgnoreCase("Dummy") || b.isNewBowler()) {
                gameOne += 135;
                gameTwo += 135;
                gameThree += 135;
            } else {
                gameOne += bowlers[i].getScore(week - 1, 0).getScore();
                gameTwo += bowlers[i].getScore(week - 1, 1).getScore();
                gameThree += bowlers[i].getScore(week - 1, 2).getScore();
                //invalidate the game for high game/series
                if(bowlers[i].getScore(week - 1, 0).isRolloff() || bowlers[i].getScore(week - 1, 0).isAbsent())
                    game1Valid = false;
                if(bowlers[i].getScore(week - 1, 1).isRolloff() || bowlers[i].getScore(week - 1, 1).isAbsent())
                    game2Valid = false;
                if(bowlers[i].getScore(week - 1, 2).isRolloff() || bowlers[i].getScore(week - 1, 2).isAbsent())
                    game3Valid = false;
            } //ends if-else           
        } //ends for
        
        //create game objects
        if(game1Valid)
            scores[week - 1][0] = new BowlingScore(gameOne, hdcp);
        else
            scores[week - 1][0] = new BowlingScore(gameOne, hdcp, true, false);
        if(game2Valid)
            scores[week - 1][1] = new BowlingScore(gameTwo, hdcp);
        else
            scores[week - 1][1] = new BowlingScore(gameTwo, hdcp, true, false);
        if(game3Valid)
            scores[week - 1][2] = new BowlingScore(gameThree, hdcp);
        else
            scores[week - 1][2] = new BowlingScore(gameThree, hdcp, true, false);
        
        //update high hdcp game
        if((gameOne + hdcp) > hdcpGame && game1Valid) 
            hdcpGame = (gameOne + hdcp);
        if ((gameTwo + hdcp) > hdcpGame && game2Valid) 
            hdcpGame = (gameTwo + hdcp);
        if ((gameThree + hdcp) > hdcpGame && game3Valid) 
            hdcpGame = (gameThree + hdcp);
                
        //update high hdcp series
        if((gameOne + gameTwo + gameThree + (hdcp * 3)) > hdcpSeries && (game1Valid && game2Valid && game3Valid)) {
            hdcpSeries = (gameOne + gameTwo + gameThree) + (hdcp * 3);
        } //ends if
        
        //update total pins
        totalPins += (gameOne + gameTwo + gameThree) + (hdcp * 3);
        
    } //ends updateStats    
    
    /**
     * Moves a bowler up in the bowler array.
     * @param index The position of the bowler to move up the list.
     */
    public void moveBowlerUp(int index) {
        if(index > 0) {
            Bowler temp = bowlers[index - 1];
            bowlers[index - 1] = bowlers[index];
            bowlers[index] = temp;
        } //ends if
    } //ends moveBowlerUp
    
    /**
     * Moves a bowler down in the bowler array.
     * @param index The position of the bowler to move down the list.
     */
    public void moveBowlerDown(int index) {
        if(index < (bowlers.length - 1)) {
            Bowler temp = bowlers[index + 1];
            bowlers[index + 1] = bowlers[index];
            bowlers[index] = temp;
        } //ends if
    } //ends moveBowlerUp
    
    public BowlingScore getScore(int week, int game) {
        if(game >= 0 && game <= 2)
            return scores[week][game];
        else
            //return an impossible score if there's a problem with the game number
            return new BowlingScore(999, 0, false, false);
    } //ends getScore
    
    /**
     * Function to get a string representation of the team.
     * @return A String containing pertinent information for the team.
     */
    public String toString() {
        String output = name + "\n\tWins: " + wins + "\n\tLosses: " + losses + "\n\tTies: " +
                ties + "\n\tTotal Pins: " + totalPinsWins + "\n\tPoints: " + totalPoints +
                "\n\tTotal: " + totalPins + "\n\tHdcp: " + hdcp + "\n";
        return output;
    } //ends toString
    
} //ends BowlingTeam