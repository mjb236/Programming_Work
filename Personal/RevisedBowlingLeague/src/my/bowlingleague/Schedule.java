package my.bowlingleague;

/**
 * Class to represent a bowling league game schedule.
 * JDK 8, NetBeans 8.0
 * @author Michael Bowen
 * @version 1.0
 */
public class Schedule {
    private final int sched[][];
    private final int numTeams;
    private int currSched[][];
    
    /**
     * Constructor to create a schedule. Loads sched with ints to represent the teams and lanes.
     * @param numTeams Number of teams in the league.
     */
    public Schedule(int numTeams) {
        this.numTeams = numTeams;
        sched = new int[3][numTeams/2];
        
        for(int i = 0; i < (numTeams/2); i++) {
            sched[0][i] = i;
            sched[1][i] = (i * 2);
            sched[2][i] = (i * 2) + 1;
        } //ends for loop
    } //ends constructor
    
    /**
     * Get a Matchup object that holds the indices of the two teams playing and what lane
     * on which they are playing.
     * @param week The current week of the season.
     * @param lane The lane on which the two teams will be bowling.
     * @return 
     */
    public Matchup getMatchup(int week, int lane) {
        //copy the sched array for manipulation
        currSched = new int[3][numTeams/2];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < (numTeams/2); j++) {
                currSched[i][j] = sched[i][j];
            }//end inner loop
        } //end outerloop
                
        //generate the schedule depending on the week passed in
        for(int i = 1; i < week; i++) {
            //store the last team of the top row
            int temp = currSched[1][(numTeams/2) - 1];
            //shift top row of teams right
            for(int j = ((numTeams/2) - 1); j > 1; j--) {
                currSched[1][j] = currSched[1][j - 1];
            } //ends for loop
            //move first team of bottom row up to the top row
            currSched[1][1] = currSched[2][0];
            //shift bottom row of teams left
            for(int j = 0; j < ((numTeams/2) -1); j++) {
                currSched[2][j] = currSched[2][j + 1];
            } //ends for loop
            //assign temp to the las on bottom row
            currSched[2][(numTeams/2) - 1] = temp;
        
            //store a copy of the current lane assignments
            int tempLanes[] = new int[(numTeams/2)];
            for(int j = 0; j < (numTeams/2); j++){
                tempLanes[j] = currSched[0][j];
            } //ends for
                   
            //move the lane assignments up
            for(int j = 0; j < (numTeams/2); j++) {
                currSched[0][j] = tempLanes[(j+1) % (numTeams/2)];
            } //ends for loop
        } //ends outer for loop
        
        //find the lane assignment
        int index = 0;
        for(int i = 0; i < (numTeams/2); i++) {
            if(currSched[0][i] == lane)
                index = i;
        } //ends for
        
        //return Matchup. flip the matchup on even weeks to create better balance of teams starting
        //on the left or right lane of the set.
        if((week % 2) != 0) {
            return new Matchup(currSched[0][index], currSched[1][index], currSched[2][index]);
        }else {
            return new Matchup(currSched[0][index], currSched[2][index], currSched[1][index]);
        } //ends else
    } //ends getMatchup
    
    public int[][] getArray() {
        return currSched;
    } //ends getArray
} //ends Schedule