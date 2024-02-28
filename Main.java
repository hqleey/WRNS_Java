//package replit;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane; 
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*; 
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application 
{ 
  /* UML CLASS DIAGRAM:
-----------------------------------------
MainJava extend Application
-----------------------------------------
  cardDeck CardDeck
  wildCardDeck[] WildCard
  level1Cards ArrayList<LevelCard>
  level2Cards ArrayList<LevelCard>
  level3Cards ArrayList<LevelCard>
  count1 int
  count2 int
  count3 int
  titleLabel Label 
  messageLabel Label 
  wildCardButton Button 
  questionScrollPane ScrollPane 
  drawCardButton Button 
  answerCompleteButton Button 
  questionLabel Text 
  historyText Text 
  answertf TextField 
  typeCardCB ComboBox
-----------------------------------------
+ typeCardCB(EventHandler<ActionEvent>)
+ drawCardButton(EventHandler<ActionEvent>)
+ answerCompleteButton(EventHandler<ActionEvent>)
-----------------------------------------
*/
  ///Creating CardDeck object that allows the following Arrays/ArrayLists to get filled with the data from the repspective csvFile
    CardDeck cardDeck = new CardDeck();
    WildCard[] wildCardDeck = cardDeck.getwcArray();
    LevelCard[] levelCardDeck = cardDeck.getlcArray();
    ArrayList<LevelCard> level1Cards = cardDeck.getLevel1ArrayList();
    ArrayList<LevelCard> level2Cards= cardDeck.getLevel2ArrayList();
    ArrayList<LevelCard> level3Cards= cardDeck.getLevel3ArrayList();

    
    
    
 
  ///Instance Variables
  int wcCount = 0;
  int count1 = 0;
  int count2 = 0;
  int count3 = 0;
  String stringHistory = "";

  //GUI scenes 
  Label titleLabel; 
  Label messageLabel;
  Button wildCardButton; 
  ScrollPane questionScrollPane;
  Button drawCardButton; 
  Button answerCompleteButton;
  Text questionLabel;
  Text historyText; 
  
  TextField answertf; 
  ComboBox typeCardCB = new ComboBox();

 

   public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) {

    //Variables for Layout of BorderPane and BorderPane itself
    VBox leftVbox; 
    VBox centerVbox; 
    HBox rightHbox;
    HBox wildCardHBox; 
    BorderPane borderPane; 
    Scene scene;
    LevelCard startCard = new LevelCard(); 

    //initializing start values 
    borderPane = new BorderPane(); 
    titleLabel = new Label("We Are Not Really Strangers");

    
    questionLabel = new Text(startCard.getQuestion());
    questionLabel.setX(200);
    questionLabel.setY(200); 
    questionLabel.setWrappingWidth(200);
    
    historyText = new Text("Here is the history");
    historyText.setWrappingWidth(100);
    
    questionScrollPane = new ScrollPane(); 
    questionScrollPane.setContent(historyText);
    

    messageLabel = new Label("Type text and click the button");
    
    drawCardButton = new Button("Draw new Card");
    drawCardButton.setAlignment(Pos.CENTER);

    answerCompleteButton = new Button ("Click when Complete"); 
    answertf = new TextField("Enter Response here"); 

    ComboBox typeCardCB = new ComboBox();
    typeCardCB.setPromptText("Choose Card");

    typeCardCB.getItems().addAll(
            "Level 1",
            "Level 2",
            "Level 3",
            "WildCard"
              
        );

    
////Setting up LeftVBOX for the Left BorderPane section
    leftVbox = new VBox(typeCardCB);
////Setting up WildCardHbox, to be added to centerVBox
    wildCardHBox = new HBox(answertf, answerCompleteButton);
    wildCardHBox.setVisible(false); 
///Setting up CenterVbox for the Center BorderPane
    centerVbox = new VBox(questionLabel, wildCardHBox);
    centerVbox.setAlignment(Pos.CENTER);
    centerVbox.setStyle("-fx-background-color: #FFFFFF;");

    
    //sortArray(levelCardDeck);
////Setting up Right Hbox for the Right BorderPane section
    rightHbox = new HBox( questionScrollPane); 


    ////EVENT-HANDLING COMBOBOX + BUTTONS

    typeCardCB.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        if(typeCardCB.getValue() == "WildCard"){
             questionLabel.setText("WildCard-Actions"); 
             centerVbox.setStyle("-fx-background-color: #C4270D;");
          }else{
              wildCardHBox.setVisible(false); 
              centerVbox.setStyle("-fx-background-color: #FFFFFF;");
          
        if(typeCardCB.getValue() == "Level 1"){
              questionLabel.setText("Level 1-Perception");
          }else if(typeCardCB.getValue() == "Level 2"){
              questionLabel.setText("Level 2- Connection");
          }else{
              questionLabel.setText("Level 3-Reflection");
            }
          }
        }
      });
    
    drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
         if(typeCardCB.getValue() == "WildCard"){
            wildCardHBox.setVisible(true); 
            centerVbox.setStyle("-fx-background-color: #C4270D;");
           
            if(wildCardDeck.length<=wcCount){
                 wildCardHBox.setVisible(false);
                questionLabel.setText("Ran out of WildCards! Choose another type of Card or Restart");
               }else{
          questionLabel.setText(wildCardDeck[wcCount].getQuestion());
              
                wcCount++;
               }
        }else{
            wildCardHBox.setVisible(false); 
            if(typeCardCB.getValue() == "Level 1"){
                if(level1Cards.size() <=count1){
                  questionLabel.setText("Ran out of Level 1 Cards! Choose another type of Card or Restart");
                  }else{
                  questionLabel.setText(level1Cards.get(count1).getQuestion());
                  stringHistory = stringHistory  +  level1Cards.get(count1) + "\n" ; 
                  historyText.setText(stringHistory);
                  count1++;
                   }
              }else if(typeCardCB.getValue() == "Level 2"){
                  if(level2Cards.size() <=count2){
                       
                      questionLabel.setText("Ran out of Level 2 Cards! Choose another type of Card or Restart");
                     }else{
                      questionLabel.setText(level2Cards.get(count2).getQuestion());
                      stringHistory = stringHistory  +  level2Cards.get(count2) + "\n" ; 
                      historyText.setText(stringHistory);
                      count2++;
                     }
            }else{
              if(level3Cards.size() <=count3){
                  questionLabel.setText("Ran out of Level 3 Cards! Choose another type of Card or Restart Program");
         }else{
                questionLabel.setText(level3Cards.get(count3).getQuestion());
                stringHistory = stringHistory  +  level3Cards.get(count3) + "\n" ; 
                         historyText.setText(stringHistory);
                 count3++;
              }
         }
        titleLabel.setText("Be Open. Dig Deep"); 
        }
      }
    });

        answerCompleteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        stringHistory = stringHistory + typeCardCB.getValue() + " " + questionLabel.getText() + " " + answertf.getText() + "\n";
        historyText.setText(stringHistory);
      }
    });
    
     
  
    borderPane.setTop(titleLabel);
    borderPane.setLeft(leftVbox);
    borderPane.setCenter(centerVbox);
    borderPane.setRight(rightHbox);
    borderPane.setBottom(drawCardButton);
    scene = new Scene(borderPane, 550, 300);

    //launching the stage 
    primaryStage.setTitle("We Are Not Really Strangers");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  
} 
