public class LevelCard extends Card
{
//LevelCard Class 
  /**
 * Represents one Level Card 
 *
 * Class Invariant:
 * - LevelCard level stored as an integer describes the level of depth that is usually shown on the card 1- Perception, 2-Connection, 3- Reflection. It can only be these 3 levels
 * - LevelCard question reflects the question that is on all cards,
 *must not be null or an empty String
 */
  
  /* UML CLASS DIAGRAM:
-----------------------------------------
LevelCard
-----------------------------------------
- question : String
- level : int
-----------------------------------------
+ LevelCard()
+ LevelCard(question : String, level : int)
+ LevelCard(original : LevelCard)
+ setAll(question : String, level : int)
+ setLevel( level: int) : void
+ getLevel() : int

+ toString() : String
+ equals(Object o) : boolean

+ setfromCSV(info : String )
-----------------------------------------
*/

  ///INSTANCE VARIABLES
  private String question; 
  private int level; 

  //Constant Values
  public static final int DEFAULT_LEVEL = 1; 

  ///CONSTRUCTORS
    //Full Constructor 
  /* Full constructor builds object with all data for instance variables provided.
	 * If arguments are not valid, program, throws an illegal argument exception
	 *
	 * @param level, type int which describes the depth of the question
	 * @param question A string question that describes the question   shown on the card 
	 */
  public LevelCard(int level, String question) throws IllegalArgumentException
    {
      //error check // exception handling iae
        if(!this.setLevel(level)){
          throw new IllegalArgumentException ("Invalid data for Level:  " + level);
        }
      if(!this.setQuestion(question)){
        throw new IllegalArgumentException("Invalid data for question: " + question);
      }
    }

    /**
	 * Copy constructor builds object with all data from LevelCard object provided. No
	 * changes made to original object, no shallow copying
	 *
	 *LevelCard Object which describes a compllete Level Card with a level and question
	 */
    public LevelCard(LevelCard original) throws IllegalArgumentException
    {
        if (original != null)
        {
            this.setAll( original.level,original.question);
        }
        else 
        { throw new IllegalArgumentException("Invalid data pass through copy constructor");
            

          //throw exception
        }
}


  //default contructor
// Default constructor, builds default LevelCard object as: 1 Seek Discomfort: Be Open
  public LevelCard(){
    //use static constants
    this(DEFAULT_LEVEL, "Seek Discomfort: Be Open");
  }

  public LevelCard(String info){
   // System.out.println("acessed csv constructor\n"); 
    
    setFromCSV(info); 

    
  }


	/*** MUTATOR METHODS (SETTERS) ***/
	/**
	 * Sets value for card only if valid, otherwise will not change instance
	 * variable. Returns boolean representing whether error occured (false) or
	 * operation completed successfully (true)
	 *
	 * @param level stored as int & describes the level of depth that is usually shown on the card 1- Perception, 2-Connection, 3- Reflection. 
	 */
  public boolean  setLevel(int level){
    if(level >0 && level<=3){
    this.level = level; 
      return true; 
    }else{
      return false;
    }
  }
/**
	 * Sets question for card only if valid, otherwise will not change instance
	 * variable. Returns boolean representing whether error occured (false) or
	 * operation completed successfully (true)
	 *
	 * @param question- A string question that describes the question   shown on the card 
	 *          
	 *
	 * @return false if question is  null or empty
	 *         true otherwise
	 */
  public boolean setQuestion(String question){
    if (question == null || question.length() == 0) {
      //System.out.println("bad data null or empty string");
			return false;
  
		} else {
			this.question = question;
			return true;
		}
    
      }

  /*
	 * Sets level and question for LevelCard only if valid, returns boolean representing
	 * whether error occured (false) or operation completed successfully (true)
	 *
	 ** @param level stored as int & describes the level of depth that is usually shown on the card 1- Perception, 2-Connection, 3- Reflection. 
	 ** @param question- A string question that describes the question   shown on the card 
	 *
	 * @return true if card suit AND value are valid, false otherwise
  */
  public boolean setAll(int level, String question){
		

		if ((this.setLevel(level) &&this.setQuestion(question ) ) == true){
			
			return true;
    }else{
    return false; 
    }
    
    
  }

/*** ACCESSOR METHODS (GETTERS) ***/
	/**
	 * Access int level representing depth of perception of each card
	 *
	 * @return int level of the LevelCard
	 */
  public int getLevel(){
    return this.level; 
  }

  /* Access String question representing the question asked on each Card
	 *
	 * @return String question of the Card
	 */
  public String getQuestion(){
    return this.question;
  }



  /*** OTHER REQUIRED METHODS ***/
	/**
	 * String of all instance variables from Card and LevelCard, no newline character at end of String.
	 * Uses a string to designate the question that is asked alongside the level of the card
	 * 
	 * @return String containing (print) question and level, separated by a sentence distinguishing the two
	 */
@Override
public String toString(){
  return "Level: " +this.level + " Question: " + this.question
    +"\n"; 
}
///other useful methods
  /**
	 * Checking for equality of LevelCard objects, all instance variables exactly equal
	 * to each other (case-sensitive). Argument object not changed
	 * 
	 * @param other LevelCard object to compare for equality
	 * 
	 * @return boolean representing equality between both objects, all data is
	 *         exactly equal to each other
	 */
@Override
    public boolean equals(Object o)
    {
        if(o == null || !(o instanceof LevelCard))
        {
            return false;
        }
        LevelCard other = (LevelCard) o;

        return this.question.equals(other.question) && this.level == other.level; 
                
    }


////Abstract
  ///SetFromCSv( String Info) This method gets a string that is a representation of each line in the csv file and splits it up if the string is not null or empty
 // @param String info represents line of each csv file
@Override
public void setFromCSV(String csvInfo){
  //System.out.println("setFromCSV method placeholder");
  //LevelCard levelCard = new LevelCard(level, question);
  if( csvInfo == null || csvInfo.length()<0){
      throw new IllegalArgumentException("No csv file given to method"); 
      
    }
    String[] values = csvInfo.split(",",2);
    if (values.length != 2){
      throw new IllegalArgumentException("parts length isn't equal to 2");
    }
    

    
    int level; 
    try{
       level = Integer.parseInt(values[0]);
    } catch (NumberFormatException nfe){
      throw new IllegalArgumentException("Csv doesn't have required values for second split needed to run code. need double"); 
    }
  String question = values[1];
    
    if(setAll(level, question) != true){
      throw new IllegalArgumentException("invalid data given make sure name is a String, temp is a double, and uses is a String"); 
    }
  
  
}

  

}

