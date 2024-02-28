public abstract class Card{


  //Card Class 
  /**
 * Represents one Card 
 *
 * Class Invariant:
 *  
 * Abstract Class, acts as guide for sub classes, each subclass has a question
 Creates useful methods that each of the sub-classes need
 Abtract- method setFromCSV
 */
  /* UML CLASS DIAGRAM:
-----------------------------------------
Abstract Card
-----------------------------------------
+ question : String

-----------------------------------------
+ Card()
+ Card(question : String)
+ Card(original : Card)
+ getQuestion() : String
+ setQuestion(question : String) boolean
+ toString() : String
+ equals(Object o) : boolean

# setFromCSV( String info) abstract
-----------------------------------------
*/
public String question; 

public static final String DEFAULT_QUESTION = "REMINDER: Seek discomfort- Be Open"; 



  //Full Constructor 
  /* Full constructor builds object with all data for instance variables provided.
	 * If arguments are not valid, program, throws an illegal argument exception
	 *
	 * @param question A string question that describes the question   shown on the card 
	 */
  public Card(String question) throws IllegalArgumentException
    {
      if(!this.setQuestion(question)){
        throw new IllegalArgumentException("Invalid data for question: " + question);
      }
    }

  // Default constructor, builds default WildCard object as: REMINDER: Seek discomfort- Be Open
  public Card (){
    this(DEFAULT_QUESTION); 
  }

  /**
	 * Copy constructor builds object with all data from WildCard object provided. No
	 * changes made to original object, no shallow copying
	 *
	 *Card Object which describes a complete Card with a question
	 */
  public Card(Card original) throws IllegalArgumentException
    {
        if (original != null)
        {
            this.setQuestion(original.question);
        }
        else 
        { throw new IllegalArgumentException("Invalid data pass through copy constructor");
            

          //throw exception
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
    if (question!= null && question.length()>0){
      this.question = question;
      return true;
    }else{
      return false; 
    }
  }
/* Access String question representing the question asked on each Card
	 *
	 * @return String question of the Card
	 */
  public String getQuestion(){
    return this.question;
  }

   /**
	 * String of all instance variables from Card
	 * Uses a string to designate the question that is asked alongside the level of the card
	 * 
	 * @return String containing (print) question guide and actual question
	 */
    public String toString(){
      return "This Card property is Question: " + this.question; 
    }

   /**
	 * Checking for equality of WildCard objects, all instance variables exactly equal
	 * to each other (case-sensitive). Argument object not changed
	 * 
	 * @param other WildCard object to compare for equality
	 * 
	 * @return boolean representing equality between both objects, all data is
	 *         exactly equal to each other
	 */
    @Override
    public boolean equals(Object o)
    {
    if(o == null || !(o instanceof Card))
    {
        return false;
    }
    Card other = (Card) o;
    
    return this.question.equals(other.question); 
            
    }

  






  ///SetFromCSV( String Info) This method gets a string that is a representation of each line in the csv file. Each child will choose how to implement/ switch this. 
 // @param String info represents line of each csv file
 public abstract void setFromCSV(String csvInfo);


  
}