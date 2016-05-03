/*
Michael Bowen
CS 1501 - 25181
*/
import java.io.*;
import java.util.*;
public class MyBoggle {
    private static DictionaryInterface canonDict, solutions;
    //alphabet to use in the place of wild cards if possible
    private static char[] charSet = {'a','b','c','d','e','f','g','h','i','j','k',
                        'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    //arraylist objects to store the possible words and correct words guessed by user
    //these are only used for printing output once the game is done being played
    ArrayList<String> possible, guessed;
    
    private static void traverse(char[][] boggle, boolean[][] visited, int col, int row, StringBuilder str) {
        //set current tile to visited and retrieve character
        visited[col][row] = true;
        char c = Character.toLowerCase(boggle[col][row]);
        int result = 0;
        
        //check for wildcard
        if(c == '*') {
            //replace wildcard with each letter in turn
            for(int k = 0; k < charSet.length; k++) {
                str.append(charSet[k]);
                result = canonDict.search(str);
                
                if(result == 1) {                                       //prefix, continue searching board
                    for(int i = col-1; i <= col+1 && i < 4; i++) 
                        for(int j = row-1; j <= row+1 && j < 4; j++)
                            if(i >= 0 && j >= 0 && !visited[i][j])
                                traverse(boggle, visited, i, j, str);
                } else if (result == 2) {                               //word, add to solution dictionary
                    solutions.add(str.toString());
                    System.out.println(str);
                } else if (result == 3) {                               //word and prefix, add to solutions and keep searching
                    solutions.add(str.toString());
                    System.out.println(str);            
                    for(int i = col-1; i <= col+1 && i < 4; i++) 
                        for(int j = row-1; j <= row+1 && j < 4; j++)
                            if(i >= 0 && j >= 0 && !visited[i][j])
                                traverse(boggle, visited, i, j, str);
                } //ends if/else
                
                //remove wild card replacement before looping through to try next
                str.deleteCharAt(str.length()-1);                
            } //ends wild card for loop
        } else {
            str.append(c);         
            result = canonDict.search(str);       
        
            //System.out.println(str + ": " + result); 
        
            if(result == 1) {                                       //prefix, continue searching board
                for(int i = col-1; i <= col+1 && i < 4; i++) 
                    for(int j = row-1; j <= row+1 && j < 4; j++)
                        if(i >= 0 && j >= 0 && !visited[i][j])
                            traverse(boggle, visited, i, j, str);
            } else if (result == 2) {                               //word, add to solution dictionary
                solutions.add(str.toString());
                System.out.println(str);
            } else if (result == 3) {                               //word and prefix, add to solutions and keep searching
                solutions.add(str.toString());
                System.out.println(str);            
                for(int i = col-1; i <= col+1 && i < 4; i++) 
                    for(int j = row-1; j <= row+1 && j < 4; j++)
                        if(i >= 0 && j >= 0 && !visited[i][j])
                            traverse(boggle, visited, i, j, str);
            } //ends if/else
            
            //remove the last character from the string
            str.deleteCharAt(str.length()-1);
        } //ends else        
        
        visited[col][row] = false;                    
    }
    
    public static void main(String[] args) throws IOException{
        //inputFile name and cmd switch variables - board1.txt, simple as defaults
        String inputFile = "board1.txt";
        String dict = "simple";
        boolean dlb = false;
        //boggle character grid and true/false grid to determine if a tile was used
        char[][] boggle = new char[4][4];
        boolean[][] visited = new boolean[4][4];
        
        
        
        //parse args for board and dictionary
        for(int i = 0; i < args.length; i++) {
            if(args[i].equalsIgnoreCase("-b"))
                inputFile = args[i+1];
            else if(args[i].equalsIgnoreCase("-d"))
                dict = args[i+1];
        } //ends for
        
        //set de la brandais flag
        if(dict.equalsIgnoreCase("dlb"))
            dlb = true;
        
        //instantiate the correct implementations of the dictionary
        if(dlb) {
            canonDict = new DLBTrie();
            solutions = new DLBTrie();
        }else {
            canonDict = new SimpleDictionary();
            solutions = new SimpleDictionary();
        }
        
        //load the dictionary into cannonDict
        Scanner inFile = new Scanner(new FileInputStream("dictionary.txt"));
        String s;
        int count = 0;
        while(inFile.hasNext()) {
            s = inFile.nextLine();
            //elimate words 1 or 2 letters long
            if(s.length() > 2) {
                canonDict.add(s);
                count++;
            }
        } //ends while
        System.out.println(count + " words entered.");
                
        //input boggle board
        inFile = new Scanner(new FileInputStream(inputFile));
        StringBuilder board = null;
        while(inFile.hasNext()){
            board = new StringBuilder(inFile.next());
        } //end input
        
        //initialize visited to false and load chars into boggle grid
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++) {
                visited[i][j] = false;
                boggle[i][j] = board.charAt((i*4) + j);
            }
                
        //traverse boggle board to find all possible words
        StringBuilder str = new StringBuilder("");
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                traverse(boggle, visited, i, j, str);

        //show boggle board to player
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++)
                System.out.print(boggle[i][j] + "   ");
            System.out.println();
        }
        
        
               
        
        
//        System.out.println(inputFile + "   " + dict);
    }//ends main
} //ends MyBoggle
