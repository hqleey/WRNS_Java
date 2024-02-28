public class WildCard extends Card{

//WildCard Class 
  /**
 * Represents one WildCard 
 *
 * Class Invariant:
 *  
 * - WildCard question reflects the question that is on all cards,
 *must not be null or an empty String
 extension of Card Class
 */
  /* UML CLASS DIAGRAM:
-----------------------------------------
WildCard
-----------------------------------------

-----------------------------------------
+ WildCard()
+ WildCard(question : String)
+ WildCard(original : wildCard)
+ toString() : String
+ setFromCSV( csvInfo : String)
-----------------------------------------
*/

  public static final String DEFAULT_QUESTION = "WildCard Default Question";

  //Full Constructor 
  /* Full constructor builds object with all data for instance variables provided.
	 * If arguments are not valid, program, throws an illegal argument exception
	 *
	 * @param question A string question that describes the question   shown on the card 
	 */
 public WildCard(String question) throws IllegalArgumentException
    {
      if(!this.setQuestion(question)){
        throw new IllegalArgumentException("Invalid data for question: " + question);
      }
    }

  
  // Default constructor, builds default WildCard object as: WildCard Default Question
  public WildCard (){
    this(DEFAULT_QUESTION); 
  }

  /**
	 * Copy constructor builds object with all data from WildCard object provided. No
	 * changes made to original object, no shallow copying
	 *
	 *WildCard Object which describes a compllete Level Card withquestion
	 */
  public WildCard(WildCard original) throws IllegalArgumentException
    {
        if (original != null)
        {
            this.setQuestion(original.question);
        }
        else 
        { throw new IllegalArgumentException("Invalid data pass through copy constructor");
           
        }
}

 /**
	 * String of all instance variables from Card and WildCard
	 * Uses a string to designate the question that is asked alongside the level of the card
	 * 
	 * @return String that adds "Question: " before each questionString
	 */
  @Override
public String toString(){
  return "Question: " + this.question
    +"\n"; 
}


///SetFromCSV( String Info) This method gets a string that is a representation of each line in the csv file, error checks question is valid and throw exception if csvInfo did not set String correctly
  
 // @param String info represents line of each csv file
@Override
public void setFromCSV(String csvInfo){
if( csvInfo == null || csvInfo.length()<0){
      throw new IllegalArgumentException("No csv file given to method"); 
      
    }
    String question = csvInfo;
    if(setQuestion(question) != true){
      throw new IllegalArgumentException("invalid data given make sure question is a String"); 
    }
  
    
  
}






  
}