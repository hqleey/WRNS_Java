import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class CardDeck{

    /* 
    CardDeck Invarient: This class is meant to serve as a helper class between the two Concrete class and the Front-End. The Main purpose of this class is to get the data from the levelCard.csv and wildCard.csv and make their respective objects from this data. 
    System should close in the event that either csv file cannot be reached/opened
    If csv files can be reached, ArrayList are made of WildCards and LevelCards.
     Each are converted into Arrays once complete
     LevelCard Array is split into 3 ArrayList based on respective Levels
     
    UML CLASS DIAGRAM:
-----------------------------------------
CardDeck
-----------------------------------------
  + levelCards ArrayList<LevelCard>
  + wildCards ArrayList<LevelCard>
  + wcArray[] WildCard
  + lcArray[] LevelCard
  + level1Cards ArrayList<LevelCard>
  + level2Cards ArrayList<LevelCard>
  + level3Cards ArrayList<LevelCard>
-----------------------------------------
+ readLevelCardFile(levelCards: ArrayList<LevelCard> )
+ readWildCardFile( wildCards: ArrayList<WildCard> )
+ printlcArrayList()
+ printwcArrayList()
+ getlcArray()
+ getwcArray()
+ wcConvertToArray(wildCards : ArrayList<WildCard>)
+ lcConvertToArray(levelCards : ArrayList<LevelCard>)
+ sortArray(cards ArrayList<LevelCard>)
+ getLevel1Cards()
+ getLevel2Cards()
+ getLevel3Cards()
-----------------------------------------
*/
  
public ArrayList<LevelCard> levelCards;
public ArrayList<WildCard> wildCards;
public WildCard[] wcArray; 
public LevelCard[] lcArray; 
public ArrayList<LevelCard> level1Cards;
public ArrayList<LevelCard> level2Cards;
public ArrayList<LevelCard> level3Cards;
  
  
  
//Testing to see if Abstract Methods are working

    /*
    WildCard card = new WildCard();

    card.setFromCSV("this is the question throught SetCSV"); 
    System.out.println(card);

    LevelCard cardie = new LevelCard();
    cardie.setFromCSV("1,This is my level Card SETCSV");
    System.out.println(cardie); 
    */
///CONSTRUCTORS
    //Full Constructor 
  /* Full constructor builds data that is able to be used by the front end. This constructor calls methods later in the class that overall converts csvFile information into ArrayLists/ Array that are able to be used in the program
	 */
      public CardDeck(){
        levelCards= new ArrayList<LevelCard>();
        wildCards= new ArrayList<WildCard>();
        level1Cards = new ArrayList<LevelCard>();
        level2Cards = new ArrayList<LevelCard>();
        level3Cards = new ArrayList<LevelCard>();
        
        readWildCardFile( wildCards);
        readLevelCardFile( levelCards);
        //printLCArrayList(levelCards);
        wcArray = wcconvertToArray(wildCards); 
        lcArray = lcconvertToArray(levelCards); 
        sortArray(lcArray);
        
     
    }
  /* Creates an ArrayList of LevelCards from the levelCard csv file.
  If the file cannot be reached, System.exit() is deployed. Reasoning is the user should not have any ability to continue if the csvfile is unreachable
Other wise, data is made from each line
@param ArrayList<LevelCard> levelCards - ArrayList that is going to get LevelCard Objects added from each line in levelCard.csv
  */

  public static void readLevelCardFile(ArrayList<LevelCard> levelCards){
    Scanner input = null;
    String info=""; 

    try{
      input = new Scanner(new FileInputStream("levelCard.csv"));
    }catch (FileNotFoundException fne){
      System.out.println("File not found, please recheck files");
      System.exit(0);
    }
    while(input.hasNextLine()){
      info = input.nextLine();
      LevelCard levelCard = new LevelCard(info);
      levelCards.add(levelCard);
    }
    input.close();
}

  /* Creates an ArrayList of WildCards from the wildCard csv file.
  If the file cannot be reached, System.exit() is deployed. Reasoning is the user should not have any ability to continue if the csvfile is unreachable
Other wise, data is made from each line
@param ArrayList<WildCard> wildCards - ArrayList that is going to get WildCard Objects added from each line in levelCard.csv
  */

  public static void readWildCardFile( ArrayList<WildCard> wildCards){
    Scanner wcInput = null;
    String info=""; 
    try{
      wcInput = new Scanner(new FileInputStream("wildCard.csv"));
    }catch (FileNotFoundException fne){
      System.out.println("File not found, please recheck files");
      System.exit(0);

      
    }

    while(wcInput.hasNextLine()){
      info = wcInput.nextLine();
      WildCard wildCard = new WildCard(info);
      wildCards.add(wildCard);
    }

    wcInput.close();
  }

  /* Prints each LevelCard object from ArrayList given in parameter
        -used primariliy for debugging purposes
  @param ArrayList<LevelCard> levelCards- ArrayList of LevelCard Objects made from information in csv file. 
    */
  public static void printLCArrayList(ArrayList<LevelCard>levelCards) {
    for(LevelCard l : levelCards){
      System.out.println(l); 
    }
  }

   /* Prints each WildCard object from ArrayList given in parameter
        -used primariliy for debugging purposes
  @param ArrayList<WildCard> wildCards- ArrayList of WildCard Objects made from information in csv file. 
    */
  public static void printWCArrayList(ArrayList<WildCard> wildCards) {
    for(WildCard w : wildCards){
      System.out.println(w); 
    }
  }

  /**
	 * Access WildCard[] wcArray representing all WildCard objects made from information in the csv file
	 *
	 * @return WildCard[] 
	 */
  public WildCard[] getwcArray(){
    return wcArray; 
  }

  /**
	 * Access LevelCard[] lcArray representing all LevelCard objects made from information in the csv file
	 *
	 * @return LevelCard[]  
	 */
  public LevelCard[] getlcArray(){
    return lcArray;
  }
  /*Converts ArrayList<WildCard> to WildCard[] representing same data from csvFile but in ArrayFormat

  @param ArrayList<WildCard> wildCards- WildCard ArrayList that represents all WildCard objects from csv file 
  */
  public WildCard[] wcconvertToArray(ArrayList<WildCard> wildcards){
    WildCard[] wcArray = wildcards.toArray(new WildCard[wildcards.size()]);
    return wcArray;
  }

  /*Converts ArrayList<LevelCard> to LevelCard[] representing same data from csvFile but in ArrayFormat

  @param ArrayList<LevelCard> levelCards- LevelCard ArrayList that represents all LevelCard objects from csv file 
  */

  // make it so that user can add more levels/ questions
  public LevelCard[] lcconvertToArray(ArrayList<LevelCard> levelcards){
    LevelCard[] lcArray = levelcards.toArray(new LevelCard[levelcards.size()]);
    return lcArray; 
  }

  /* SortArray() Sorts thorugh LevelCard Array and makes array list based on the Level of the LevelCard element

  @param LevelCard[] cards- represents lcArray that hold LevelCard Objects made from csvfile
  */
   public void sortArray(LevelCard[] cards){
    for (LevelCard card : cards) {
            int level = card.getLevel();
            if (level == 1) {
                level1Cards.add(card);
            } else if (level == 2) {
                level2Cards.add(card);
            } else if (level == 3) {
                level3Cards.add(card);
            }
        }
    
  }
  
/**
	 * Access ArrayList<LevelCard> level1Array representing all LevelCard objects with Level 1 that are made from information in the csv file
	 *
	 * @return  ArrayList<LevelCard> 
	 */
  public ArrayList<LevelCard> getLevel1ArrayList(){
    return level1Cards;
  }

  /**
	 * Access ArrayList<LevelCard> level2Array representing all LevelCard objects with Level 2 that are made from information in the csv file
	 *
	 * @return  ArrayList<LevelCard> 
	 */
  public ArrayList<LevelCard> getLevel2ArrayList(){
    return level2Cards;
  }
  
  /**
	 * Access ArrayList<LevelCard> level3Array representing all LevelCard objects with Level 3 that are made from information in the csv file
	 *
	 * @return  ArrayList<LevelCard> 
	 */
  public ArrayList<LevelCard> getLevel3ArrayList(){
    return level3Cards;
  }

  public static void printLevel1ArrayList(ArrayList<LevelCard> level1Cards) {
    for(LevelCard l: level1Cards){
      System.out.println(l); 
    }
  }

  
  
 }