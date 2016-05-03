//uses a method to print out 3 verses of "If You're Happy and You Know It"
//created by Michael Bowen
//created on 2/20/13
//JDK 7

public class HappySong {
    
    public static void main(String[] args) {
        //calls the method with different parameters
        happyVerse("clap your hands", "clap");
        happyVerse("stomp your feet", "stomp");
        happyVerse("shout Hurray!", "hoo-ray");        
    }//ends main
    
    //method to print out a verse of the song using given actions and sounds
    //arguments: string to hold the action, string to hold the sound
    public static void happyVerse(String action, String sound){
        //prints the verse
        String happyPart = "If you're happy and you know it, ";
        System.out.println(happyPart + action + "(" + sound + " " + sound + ")\n"
                + happyPart + action + "(" + sound + " " + sound + ")\n"
                + happyPart + "then your face will surely show it\n"
                + happyPart + action + "(" + sound + " " + sound + ")\n");
    }//ends happyVerse
}//ends HappySong
