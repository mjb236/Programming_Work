package my.bowlingleague;
/**
 * Class to represent a Bowling team.
 * @author Mike Bowen
 */
import java.io.*;

public class BowlingLeague implements Serializable{
    private int numTeams, numBowlers, highAverage, numWeeks;
    private String name;
    private BowlingTeam[] teams;
    private boolean completedWeeks[];
    private Matchup[][] matchups;
    
    public BowlingLeague(int numTeams, int numWeeks) {
        name = "League";
        this.numTeams = numTeams;
        this.numWeeks = numWeeks;
        numBowlers = 0;
        highAverage = 0;
                
        teams = new BowlingTeam[numTeams];
        for(int i = 0; i < teams.length; i++) {
            teams[i] = new BowlingTeam(numWeeks);
        } //ends for loop
        
        completedWeeks = new boolean[numWeeks];
        for(int i = 0; i < numWeeks; i++) {
            completedWeeks[i] = false;
        } //ends for loop
    } //ends 2 arg constructor constructor
    
    public BowlingLeague(String name, int numTeams, int numBowlers, int numWeeks) {
        //assign passed in data to the league data
        this.name = name;
        this.numTeams = numTeams;
        this.numBowlers = numBowlers;
        this.numWeeks = numWeeks;
        //initialize high average
        highAverage = 0;
        
        //create dummy teams for the league
        teams = new BowlingTeam[numTeams];
        for(int i = 0; i < teams.length; i++) {
            teams[i] = new BowlingTeam("Team", numBowlers, numWeeks);
        } //ends for loop
        
        //initialize completedWeeks array
        completedWeeks = new boolean[numWeeks];
        for(int i = 0; i < numWeeks; i++) {
            completedWeeks[i] = false;
        } //ends for loop
        
        //generate the league schedule to store in matchups array
        Schedule schedule = new Schedule(numTeams);
        matchups = new Matchup[numWeeks][(numTeams/2)];
        for(int i = 1; i <= numWeeks; i++) {
            for(int j = 0; j < (numTeams/2); j++) {
                matchups[i-1][j] = schedule.getMatchup(i, j);
            } //ends inner for
        } //ends for   
        
        //print the matchups for debugging
        for(int i = 0; i < numWeeks; i++) {
            for(int j = 0; j < (numTeams/2); j++) {
                System.out.print(matchups[i][j].getLane() + "  ");
            } //ends lane
            System.out.println();
            for(int j = 0; j < (numTeams/2); j++) {
                System.out.print(matchups[i][j].getTeam1() + "  ");
            } //ends team 1
            System.out.println();
            for(int j = 0; j < (numTeams/2); j++) {
                System.out.print(matchups[i][j].getTeam2() + "  ");
            } //ends team 2
            System.out.println("\n");
        }
        
//****************************************************************************************
// DEBUGGING VERSION OF MATCHUPS USED FOR RUNNING DATA FROM LAST SEASON
//****************************************************************************************

        //week 1
        matchups[0][0] = new Matchup(0,0,1);
        matchups[0][1] = new Matchup(1,2,3);
        matchups[0][2] = new Matchup(2,4,5);
        matchups[0][3] = new Matchup(3,6,7); 
        
        //week 2
        matchups[1][0] = new Matchup(0,6,4);
        matchups[1][1] = new Matchup(1,5,1);
        matchups[1][2] = new Matchup(2,7,2);
        matchups[1][3] = new Matchup(3,3,0);
        
        //week 3
        matchups[2][0] = new Matchup(0,3,7);
        matchups[2][1] = new Matchup(1,0,5);
        matchups[2][2] = new Matchup(2,1,6);
        matchups[2][3] = new Matchup(3,2,4);
        
        //week 4
        matchups[3][0] = new Matchup(0,2,1);
        matchups[3][1] = new Matchup(1,4,3);
        matchups[3][2] = new Matchup(2,7,0);
        matchups[3][3] = new Matchup(3,6,5);
        
        //week 5
        matchups[4][0] = new Matchup(0,5,2);
        matchups[4][1] = new Matchup(1,0,6);
        matchups[4][2] = new Matchup(2,3,1);
        matchups[4][3] = new Matchup(3,7,4);
        
        //week 6
        matchups[5][0] = new Matchup(0,4,0);
        matchups[5][1] = new Matchup(1,1,7);
        matchups[5][2] = new Matchup(2,2,6);
        matchups[5][3] = new Matchup(3,3,5);
        
        //week 7
        matchups[6][0] = new Matchup(0,7,5);
        matchups[6][1] = new Matchup(1,6,3);
        matchups[6][2] = new Matchup(2,0,2);
        matchups[6][3] = new Matchup(3,4,1);
        
        //week 8
        matchups[7][0] = new Matchup(0,3,7);
        matchups[7][1] = new Matchup(1,4,2);
        matchups[7][2] = new Matchup(2,5,1);
        matchups[7][3] = new Matchup(3,6,0);
        
        //week 9 is position round
        
        //week 10
        matchups[9][0] = new Matchup(0,4,6);
        matchups[9][1] = new Matchup(1,2,7);
        matchups[9][2] = new Matchup(2,0,3);
        matchups[9][3] = new Matchup(3,1,5);
        
        //week 11
        matchups[10][0] = new Matchup(0,5,0);
        matchups[10][1] = new Matchup(1,6,1);
        matchups[10][2] = new Matchup(2,4,2);
        matchups[10][3] = new Matchup(3,7,3);
        
        //week 12
        matchups[11][0] = new Matchup(0,5,6);
        matchups[11][1] = new Matchup(1,0,7);
        matchups[11][2] = new Matchup(2,3,4);
        matchups[11][3] = new Matchup(3,1,2);
        
        //week 13
        matchups[12][0] = new Matchup(0,1,3);
        matchups[12][1] = new Matchup(1,4,7);
        matchups[12][2] = new Matchup(2,6,0);
        matchups[12][3] = new Matchup(3,2,5);
        
        //week 14
        matchups[13][0] = new Matchup(0,6,2);
        matchups[13][1] = new Matchup(1,5,3);
        matchups[13][2] = new Matchup(2,7,1);
        matchups[13][3] = new Matchup(3,0,4);
        
        //week 15
        matchups[14][0] = new Matchup(0,1,4);
        matchups[14][1] = new Matchup(1,2,0);
        matchups[14][2] = new Matchup(2,5,7);
        matchups[14][3] = new Matchup(3,3,6);
        
        //week 16
        matchups[15][0] = new Matchup(0,2,3);
        matchups[15][1] = new Matchup(1,6,7);
        matchups[15][2] = new Matchup(2,4,5);
        matchups[15][3] = new Matchup(3,0,1);
        
        //week 17
        matchups[16][0] = new Matchup(0,3,0);
        matchups[16][1] = new Matchup(1,5,1);
        matchups[16][2] = new Matchup(2,6,4);
        matchups[16][3] = new Matchup(3,7,2);
        
        //week 18 is a position round

    } //ends 4 arg constructor constructor
    
    /**
     * Set the name of the league.
     * @param name The name of the bowling league.
     */
    public void setName(String name) {
        this.name = name;
    } //ends setName
    
    /**
     * Function to get the name of the bowling league.
     * @return The name of the bowling league.
     */
    public String getName() {
        return name;
    } //ends getName
    
    /**
     * Set the number of teams the league has.
     * @param numTeams The number of teams in the bowling league.
     */
    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    } //ends setNumTeams
    
    /**
     * Function to get the number of teams in the bowling league.
     * @return The number of teams in the bowling league.
     */
    public int getNumTeams() {
        return numTeams;
    }//ends getNumTeams
    
    /**
     * Set the number of bowlers per team.
     * @param numBowlers The number of bowlers on each team.
     */
    public void setNumBowlers(int numBowlers) {
        this.numBowlers = numBowlers;
    } //ends setNumTeams
    
    /**
     * Function to get the number of bowlers per team.
     * @return The number of bowlers on each time.
     */
    public int getNumBowlers() {
        return numBowlers;
    } //ends getNumBowlers
    
    /**
     * Get the team at index teamNumber
     * @param teamNumber The number of the team.
     * @return The bowling team at index teamNumber.
     */
    public BowlingTeam getTeam(int teamNumber) {
        return teams[teamNumber];
    } //ends getTeam
    
    /**
     * Function to get a string containing information about the league.
     * @return A string with information about the league.
     */
    public String toString() {
        return (name + " league contains " + numTeams + " teams and " + (numTeams * numBowlers) 
                + " total bowlers.");
    }//ends toString    
    
    /**
     * Function to determine the current high average of the league.
     */
    public void setHighAverage() {
        //reset the highAverage to 0
        highAverage = 0;
        int tempHigh = 0;
        for(int i = 0; i < numTeams; i++) {
            for(int j = 0; j < numBowlers; j++) {
                //get the bowlers rounded average
                tempHigh = (int)Math.round(teams[i].getBowler(j).getAvg());
                //if current bowlers rounded average is higher than the previous high, set to highAverage
                if(tempHigh > highAverage) {
                    highAverage = tempHigh;
                } //ends if
            } //ends inner for loop
        } //ends outer for loop
    } //ends setHighAverage
    
    /**
     * Function to get the league's current high average.
     * @return The league's highest average.
     */
    public int getHighAverage() {
        return highAverage;
    } //ends getHighAverage
    
    /**
     * Function used to set each bowlers handicap using the leagues highAverage.
     */
    public void setHandicaps() {
        int hdcpCount;
        for(int i = 0; i < numTeams; i++) {
            hdcpCount = 0;
            for(int j = 0; j < numBowlers; j++) {
                Bowler currentBowler = teams[i].getBowler(j);
                currentBowler.setHdcp(highAverage);
                if(currentBowler.isNewBowler() || currentBowler.getName().equalsIgnoreCase("Dummy"))
                    hdcpCount += highAverage - 135;
                else
                    hdcpCount += currentBowler.getHdcp();
            } //ends inner for loop
            teams[i].setHdcp(hdcpCount);
            System.out.println(teams[i].getName() + " hdcp: " + teams[i].getHdcp() + "\n");
        } //ends outer for loop
    } //ends setHandicaps
    
    /**
     * Function to get 1 weeks worth of matchups.
     * @param week The week for which you want the schedule.
     * @return An array of Matchup objects representing that weeks bowling matchups.
     */
    public Matchup[] getMatchupsForWeek(int week) {
        return matchups[week-1];
    } //ends getMatchupsForWeek
    
    public void setMatchupsForWeek(Matchup[] matchup, int week) {
        matchups[week-1] = matchup;
    }
    
    /**
     * Function to set the number of weeks the bowling league will run.
     * @param weeks The number of weeks the bowling team runs.
     */
    public void setNumWeeks(int weeks) {
        numWeeks = weeks;
    } //ends setNumWeeks
    
    /**
     * Function to get the number of weeks the league runs.
     * @return The number of weeks the league runs.
     */
    public int getNumWeeks() {
        return numWeeks;
    } //ends getNumWeeks
    
    /**
     * Function to determine whether stats for a particular week have been updated.
     * @param week The number of the week in question.
     * @return True if the week has had stats updated, false otherwise.
     */
    public boolean isWeekCompleted(int week) {
        return completedWeeks[week - 1];
    } //ends isWeekCompleted
    
    /**
     * Function to update the stats for a particular week of the bowling season. Only
     * to be called once all scores for the week have been entered.
     * @param week The number of the week for which to update stats.
     */
    public void updateStats(int week) {
        //reset team stats if beginning the season mid point
        if((completedWeeks[numWeeks/2 - 1]) && !completedWeeks[numWeeks/2]) {
            for(int i = 0; i < numTeams; i++) {
                BowlingTeam team = teams[i];
                team.setTotalPins(0);
                team.setWins(0);
                team.setTies(0);
                team.setLosses(0);
                team.setTotalPinsWins(0);
                team.setTotalPinsTies(0);
            } //ends for loop
        } //ends if
        
        for(int i = 0; i < numTeams; i++) {
            for(int j = 0; j < numBowlers; j++) {
                //if first week of the season, set each bowlers prevAvg to their current avg.
                //this is used to determine the bowler with the best change in avg for end of year honors.
                if(!completedWeeks[0]) {
                    teams[i].getBowler(j).setPrevAvg();
                }
                //update bowler's stats
                teams[i].getBowler(j).updateStats(week);                
            } //ends inner for
            
            //update team stats
            teams[i].updateScores(week, highAverage);
            System.out.println(teams[i].getName() + ":\tGame 1: " + teams[i].getScore(week - 1, 0).getHdcpScore() + "  Game 2: " +
                    teams[i].getScore(week - 1, 1).getHdcpScore() + "  Game 3: " + teams[i].getScore(week - 1, 2).getHdcpScore());
        } //ends outer for       
        
        //update new bowlers to not new bowlers as needed
        for(int i = 0; i < numTeams; i++) {
            for(int j = 0; j < numBowlers; j++) {
                Bowler b = teams[i].getBowler(j);
                int games = b.getGamesPlayed() + b.getGamesRollOff();
                if(b.isNewBowler() && games > 0) {
                    b.setNewBowler(false);
                } //ends if
            } //ends inner for
        } //ends outer for
        
        //update teams win/losses
        for(int i = 0; i < (numTeams/2); i++) {
            BowlingTeam team1 = teams[matchups[week-1][i].getTeam1()];
            BowlingTeam team2 = teams[matchups[week-1][i].getTeam2()];
            int team1Total = 0, team2Total = 0;
            
            for(int j = 0; j < 3; j++) {
                if(team1.getScore(week - 1, j).getHdcpScore() > team2.getScore(week - 1, j).getHdcpScore()) {
                    //increment team1's wins and team2's losses
                    team1.setWins(team1.getWins() + 1);
                    team2.setLosses(team2.getLosses() + 1);
                } else if (team1.getScore(week - 1, j).getHdcpScore() < team2.getScore(week - 1, j).getHdcpScore()) {
                    //increment team2's wins and team1's losses
                    team2.setWins(team2.getWins() + 1);
                    team1.setLosses(team1.getLosses() + 1);
                } else {
                    //increment both teams ties
                    team1.setTies(team1.getTies() + 1);
                    team2.setTies(team2.getTies() + 1);                    
                } //ends if/else
                //increment totals
                team1Total += team1.getScore(week - 1, j).getHdcpScore();
                team2Total += team2.getScore(week - 1, j).getHdcpScore();
            } //ends for loop
            
            if(team1Total > team2Total) {
                team1.setTotalPinsWins(team1.getTotalPinsWins() + 1);
            } else if (team1Total < team2Total) {
                team2.setTotalPinsWins(team2.getTotalPinsWins() + 1);                
            } else {
                team1.setTotalPinsTies(team1.getTotalPinsTies() + 1);
                team2.setTotalPinsTies(team2.getTotalPinsTies() + 1);
            } //ends if/else
            
            //update the teams points
            team1.setTotalPoints();
            team2.setTotalPoints();
            //create function in BowlingTeam to calculate highGame/highSeries if no rolloffs
            //add up both team's scores for each game
            //compare and increment wins/losses/ties as necessary
            //figure out high team games and series
            
        } //ends for loop
        
        completedWeeks[week - 1] = true;
    } //ends updateStats
}//ends BowlingLeague