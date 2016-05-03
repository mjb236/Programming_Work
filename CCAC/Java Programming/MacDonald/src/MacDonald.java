//prints Old MacDonald with animal name and sounds
//created by Michael Bowen
//created on 2/20/13
//JDK 7

public class MacDonald {

    public static void main(String[] args) {
        animalVerse("pig", "oink");
        animalVerse("cow", "moo");
        animalVerse("sheep", "baa");
    }//ends main
    
    //this method displays the verse to OldMacdonald had a farm, with the animal and sound provided
    //arguments: name of the animal as a String, sound of animal as a String
    public static void animalVerse(String animal, String sound){
        System.out.println("Old MacDonald had a farm, EE-I-EE-I-O." 
                + "\nAnd on that farm he had a " + animal + ", EE-I-EE-I-O."  
                + "\nWith a " + sound + " " + sound + " here and a " + sound + " " + sound + " there. " 
                + "\nHere a " + sound + ", there a " + sound + ", everywhere a " + sound + " " + sound +"." 
                + "\nOld MacDonald had a farm, EE-I-EE-I-O.\n");
    }//ends animalVerse
}//ends program
