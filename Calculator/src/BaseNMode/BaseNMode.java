package BaseNMode;

/*
    This is the base-N mode file to handle conversions between modes and 
    the operations can be applied on the modes.
    
    authors:
            Nehal Amgad
            Yomna Al-Shaboury
*/

import CalcApplication.Calc_GUI;
import static CalcApplication.Calc_GUI.opValidation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


//Initializations are needed in the rest of functions  
public class BaseNMode implements Initializable {
    Alert alert;
    DialogPane dialogPane;
    static String text;
    static String oldInput = "|";
    static String oldRes = " ";
    static int state = 10;
    int prev_state;
    int next_state;
    int pos;
    
    @FXML
    public Label res;
    public TextField input;
    @FXML
    private Button b_equal;    
    @FXML
    private Button b_backspace;
    public  Menu port_menu;
    public String selectedMode ;
    
    @FXML
    private Button btn_0;    
    @FXML
    private Button btn_1; 
    @FXML
    private Button btn_2; 
    @FXML
    private Button btn_3; 
    @FXML
    private Button btn_4; 
    @FXML
    private Button btn_5; 
    @FXML
    private Button btn_6; 
    @FXML
    private Button btn_7; 
    @FXML
    private Button btn_8; 
    @FXML
    private Button btn_9; 
    @FXML
    private Button btn_A; 
    @FXML
    private Button btn_B; 
    @FXML
    private Button btn_C; 
    @FXML
    private Button btn_D; 
    @FXML
    private Button btn_E; 
    @FXML
    private Button btn_F; 
    @FXML
    private Button btn_Bin;
    @FXML
    private Button btn_Oct;
    @FXML
    private Button btn_Dec;
    @FXML
    private Button btn_Hex;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView normal;
    
    
    //Function to write numbers on the textfield
    @FXML
    public void write_number(ActionEvent event) {
        Button tmp = (Button)event.getSource(); //take the text from the button 
        pos = input.getText().indexOf("|");     //get the position of the text using the postion of caret
        String s = Calc_GUI.numberValidation(input.getText(), tmp.getText(), pos); // validation on numbers
        input.insertText(pos, s);              //insert the text from click on the button in the textfield in the position
    }
    
    //Function to handle the keyboard buttons to control on the textfield 
    //those buttons are only accepted to edit on textfield
    @FXML
    public void write_key(KeyEvent event) {

        String s;
        pos = input.getText().indexOf("|");
        
        //Those are the operations wrote when shift button is click
        if(event.isShiftDown()){
            switch(event.getCode()){
                case EQUALS:
                    s = opValidation(input.getText(), "+", pos);
                    input.setText(s);           //shift+(=)button -> + 
                    break;
                case DIGIT6:
                    s = opValidation(input.getText(), "^", pos);
                    input.setText(s);           //shift+6 button -> ^  
                    break;
                case DIGIT8:
                    s = opValidation(input.getText(), "×", pos);
                    input.setText(s);           //shift+8 button -> x 
                    break;               
                case DIGIT5:
                    s = opValidation(input.getText(), "%", pos);
                    input.setText(s);           //shift+5 button -> % 
                    break;
                case DIGIT9:
                    s = opValidation(input.getText(), "( )", pos);
                    input.setText(s);           //shift+9 button -> ()
                    break;
                case DIGIT0:
                    s = opValidation(input.getText(), "( )", pos);
                    input.setText(s);           //shift+0 button -> ()
                    break;
            }
        }
        //Those are the operations wrote when ctrl button is click
        else if(event.isControlDown()){
            switch(event.getCode()){
                case LEFT: //ctrl + left to move caret to the previous position to write on this position
                    if(pos>0){
                        input.setText(input.getText().replace("|", ""));
                        input.insertText(pos-1, "|");
                    }break;
                case RIGHT://ctrl + right to move caret to the next position to write on this position
                    if(pos<input.getText().length()-1){
                        input.setText(input.getText().replace("|", ""));
                        input.insertText(pos+1, "|");
                    }break;
            }
        }
        //Those are some buttons to click to wirte on the textfield directly
        else{
            switch(event.getCode()){
                
                case SLASH:
                    s = opValidation(input.getText(), "÷", pos);
                    input.setText(s);               // button slash to divide 
                    break;
                case DIVIDE:
                    s = opValidation(input.getText(), "÷", pos);
                    input.setText(s);               // button / (on computer keypad) to divide 
                    break;
                case MINUS:
                    s = opValidation(input.getText(), "-", pos);
                    input.setText(s);               // button - to minus 
                    break;
                case SUBTRACT:
                    s = opValidation(input.getText(), "-", pos);
                    input.setText(s);               //button - (on computer keypad) to subtract
                    break;
                case ADD:
                    s = opValidation(input.getText(), "+", pos);
                    input.setText(s);               // button + (on computer keypad) to add 
                    break;
                case MULTIPLY:
                    s = opValidation(input.getText(), "×", pos);
                    input.setText(s);               // button * (on computer keypad) to multiply
                    break;
                case EQUALS:
                    b_equal.fire();                 //equal button to do the functionality of equal button on GUI
                    break; 
                case ENTER:
                    b_equal.fire();                 //enter button to do the functionality of equal button on GUI
                    break; 
                case BACK_SPACE:
                    b_backspace.fire();             //backspace button to do the functionality of backspace button on GUI to remove the letter before the caret "|"
                    break;
                case HOME:
                    input.setText(input.getText().replace("|", "")); // home button to go to by the caret to first position in text
                    input.insertText(0, "|");
                    break;
                case END:
                    input.setText(input.getText().replace("|", "")); // end button to go to by the caret to last position in text
                    input.insertText(input.getText().length(), "|");
                    break;
            }
            
            //Change the color of the buttons on the gui depends on the mode to make the text is yellowgreen color
            //Activate only buttons can be clicked on keyboard depends on the mode
            if(btn_Bin.getTextFill()==javafx.scene.paint.Color.YELLOWGREEN)  //in the binary mode activate on 1,0 +opertaions
            {
                if(event.getCode().isDigitKey()&& ("01".contains(event.getText()))){
                    s = Calc_GUI.numberValidation(input.getText(),event.getText() , pos);
                    input.insertText(pos, s);
                }
            }
            else if(btn_Dec.getTextFill()==javafx.scene.paint.Color.YELLOWGREEN) //make it by default ,in the decimal mode activate from 0 to 9 numbers +opertaions
                
            {
                if(event.getCode().isDigitKey()){
                    s = Calc_GUI.numberValidation(input.getText(),event.getText() , pos);
                    input.insertText(pos, s);
                }
            }
            else if(btn_Oct.getTextFill()==javafx.scene.paint.Color.YELLOWGREEN) //in the octal mode activate from 0 to 7 digits +opertaions 
            {
                 if(event.getCode().isDigitKey()&& ("01234567".contains(event.getText()))){
                    s = Calc_GUI.numberValidation(input.getText(),event.getText() , pos);
                    input.insertText(pos, s);
                 }
            }
            else if(btn_Hex.getTextFill()==javafx.scene.paint.Color.YELLOWGREEN) //in the hex mode activate from 0 to 9 digits and A,B,C,D,E and F alphabets+opertaions 
            {
                if(event.getCode().isDigitKey()){
                    s = Calc_GUI.numberValidation(input.getText(),event.getText() , pos);
                    input.insertText(pos, s);
                }
                if(event.getCode()==event.getCode().A
                   ||event.getCode()==event.getCode().B
                   ||event.getCode()==event.getCode().C
                   ||event.getCode()==event.getCode().D
                   ||event.getCode()==event.getCode().E
                   ||event.getCode()==event.getCode().F)                
                {
                    s = Calc_GUI.numberValidation(input.getText(),event.getText() , pos);
                    input.insertText(pos, s.toUpperCase());
                }
            }
        }
    }
    
    //Function to write operations on the textfield
    @FXML
    public void operation(ActionEvent event) {   
        String s;
        Button tmp = (Button)event.getSource();         //take the text from the button 
        String op = tmp.getText();
        pos = input.getText().indexOf("|");             //determine the position to be written on
        s = opValidation(input.getText(),op , pos);     // operation validation
        input.setText(s);                               //add operation on the position
    }
   
    //Function to clear screen
    @FXML
    private void clearScr(ActionEvent event) {
        // clear button  --> clear screen
        input.clear();                              //clear the textfield 
        input.setText("|");                         //set the caret in the start of the text field
        res.setText("");                            //clear the result label
    }   
    
    //Function to delete the char 
    @FXML
    private void back_space(ActionEvent event) {
        pos = input.getText().indexOf("|");  //find the postion of caret
        if(pos != 0)
            input.deleteText(pos-1, pos); //clear the previous char of the caret
    } 
    
    //Function handles the equal button which handles the whole operations and conversions
    @FXML
    private void equal_op(ActionEvent event) {
        //fisrt part aims to split the input written on textfield to apply the operations a 
        int dec;
        String exp = input.getText().replace("|","");  //remove caret from the textfield
        String expression = "";
        String[] splitsMixed = exp.split("((?=\\+|\\-|\\×|\\÷|\\%|\\(|\\))|(?<=\\+|\\-|\\×|\\÷|\\%|\\(|\\)))"); //delimeters to split on
        for (String txt:splitsMixed)
        {
            try{
               dec=(int)Long.parseLong(txt,next_state); //convert from nextstate mode to decimal which maybe (bin,oct,hex)-(2,8,16)
               txt=Integer.toString(dec);
            }
            catch(Exception e){ }
            expression += txt;
        }
        if(!expression.isEmpty()){
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
            
            expression = expression.replace('×', '*'); //change the × which written on the textfield to * to apply multipliction
            expression = expression.replace('÷', '/'); //change the ÷ which written on the textfield to / to apply division           
            try {
                Double result = (Double) scriptEngine.eval(expression.replace("--", "+")); //replace if two - written convert it in the double numbers to + in the double case of result
                switch(next_state) // cases on the next state
                    {
                        case 2:   //convert to binary 
                            input.setText(Integer.toBinaryString(result.intValue()));
                            res.setText(Integer.toBinaryString(result.intValue()));
                            break;
                        case 8: //convert to octal
                            input.setText(Integer.toOctalString(result.intValue()));
                            res.setText(Integer.toOctalString(result.intValue()));
                            break;
                        case 10://convert to decimal
                            input.setText(Integer.toString(result.intValue()));
                            res.setText(Integer.toString(result.intValue()));
                            break;
                        case 16://convert to hex
                            input.setText(Integer.toHexString(result.intValue()).toUpperCase());
                            res.setText(Integer.toHexString(result.intValue()).toUpperCase());
                            break;
                    }
                    input.appendText("|"); //add caret on the textfield

            } catch (ScriptException ex) {
                res.setText("Math error");
            }
            catch(ArithmeticException ex){
                res.setText("Math error");
            }
            catch(Exception ex){
                try{
                    Integer result = (Integer) scriptEngine.eval(expression.replace("--", "+"));//replace if two - written convert it in the integer numbers to + in the integer case of result
                    switch(next_state)
                    {
                        case 2:         
                            input.setText(Integer.toBinaryString(result));
                            res.setText(Integer.toBinaryString(result));
                            break;
                        case 8:
                            input.setText(Integer.toOctalString(result));
                            res.setText(Integer.toOctalString(result));
                            break;
                        case 10:
                            input.setText(Integer.toString(result));
                            res.setText(Integer.toString(result));
                            break;
                        case 16:
                            input.setText(Integer.toHexString(result).toUpperCase());
                            res.setText(Integer.toHexString(result).toUpperCase());
                            break;
                    }
                    input.appendText("|");
                }catch(ScriptException e) {
                    res.setText("Math error");
                }
                catch(ArithmeticException e){
                res.setText("Math error");
                }
                catch(Exception e){ }
            }
        }
    }
    
    //Function handles the inversion sign ( convert from + to negative numbers)
    @FXML
    private void invert_sign(ActionEvent event) {
        String str = input.getText();
        pos = input.getText().indexOf("|");
       
        String[] arrOfStr = str.split("[\\×\\÷\\+\\-\\^\\√\\(\\)\\%]+"); //split on the deliemets
        for(String a:arrOfStr){
            //caret on which token
            if(a.contains("|")){
                int pos_relative = a.indexOf("|");
                // invert "(- number)"
                input.setText(input.getText().replace(a, ""));
                a = "(-" + a.replace("|", "") + ")";
                input.insertText(pos-pos_relative, a);
                input.insertText(pos+2, "|"); //add the caret 
                break;
            }
        }
    }
    
    //Function handles the baseN buttons (BIN,DEC,OCT,HEX)
    @FXML
    public void baseN(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        String base = tmp.getText();
        int dec;
        b_equal.fire(); //same as equal function while click on any button from them
        switch(base)
            {               
            case "BIN": // in binary case
                prev_state=next_state;
                next_state=2;
                if(!input.getText().replace("|", "").isEmpty()&&res.getText()!="Math error")
                {
                    dec=(int)Long.parseLong(input.getText().replace("|", ""),prev_state); //convert to decimal from the previous state
                    input.setText(Integer.toBinaryString(dec)); //convert from decimal to binary and show it on textfield
                    res.setText(Integer.toBinaryString(dec)); //show the result in binary
                }
                //buttons enabled and disabled in the mode
                //only one and zero is enabled + operations buttons
                btn_0.setDisable(false); 
                btn_1.setDisable(false);
                btn_2.setDisable(true);
                btn_3.setDisable(true);
                btn_4.setDisable(true);
                btn_5.setDisable(true);
                btn_6.setDisable(true);
                btn_7.setDisable(true);
                btn_8.setDisable(true);
                btn_9.setDisable(true);
                btn_A.setDisable(true);
                btn_B.setDisable(true);
                btn_C.setDisable(true);
                btn_D.setDisable(true);
                btn_E.setDisable(true);
                btn_F.setDisable(true);
                
                //change color of text of the clicked button (BIN)
                btn_Bin.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                btn_Oct.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Dec.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Hex.setTextFill(javafx.scene.paint.Color.WHITE);
                

                break;
            case "OCT":
                prev_state=next_state; 
                next_state=8;
                if(!input.getText().replace("|", "").isEmpty()&&res.getText()!="Math error")
                {
                    dec=(int)Long.parseLong(input.getText().replace("|", ""),prev_state); 
                    input.setText(Integer.toOctalString(dec));//convert from decimal to octal and show it on textfield
                    res.setText(Integer.toOctalString(dec));//show the result in binary
                }
                
                //buttons from 0 to 7 are enabled + operations buttons
                btn_0.setDisable(false);
                btn_1.setDisable(false);
                btn_2.setDisable(false);
                btn_3.setDisable(false);
                btn_4.setDisable(false);
                btn_5.setDisable(false);
                btn_6.setDisable(false);
                btn_7.setDisable(false);
                btn_8.setDisable(true);
                btn_9.setDisable(true);
                btn_A.setDisable(true);
                btn_B.setDisable(true);
                btn_C.setDisable(true);
                btn_D.setDisable(true);
                btn_E.setDisable(true);
                btn_F.setDisable(true);
                
                //change the text color of the oct button
                btn_Oct.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                btn_Bin.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Dec.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Hex.setTextFill(javafx.scene.paint.Color.WHITE);

                break;
            case "DEC":
                prev_state=next_state; 
                next_state=10;
                if(!input.getText().replace("|", "").isEmpty()&&res.getText()!="Math error")
                {
                    dec=(int)Long.parseLong(input.getText().replace("|", ""),prev_state); //convert to decimal from the previous state
                    input.setText(Integer.toString(dec));//show the decimal result in the textfield
                    res.setText(Integer.toString(dec)); //show the decimal result in the result label
                }
                
                //buttons from 0 to 9 and operations are enabled
                btn_0.setDisable(false);
                btn_1.setDisable(false);
                btn_2.setDisable(false);
                btn_3.setDisable(false);
                btn_4.setDisable(false);
                btn_5.setDisable(false);
                btn_6.setDisable(false);
                btn_7.setDisable(false);
                btn_8.setDisable(false);
                btn_9.setDisable(false);
                btn_A.setDisable(true);
                btn_B.setDisable(true);
                btn_C.setDisable(true);
                btn_D.setDisable(true);
                btn_E.setDisable(true);
                btn_F.setDisable(true);
                
                //change the text color in the dec button
                btn_Bin.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Oct.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Dec.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                btn_Hex.setTextFill(javafx.scene.paint.Color.WHITE);

                break;
            case "HEX":
                prev_state=next_state;
                next_state=16; 
                if(!input.getText().replace("|", "").isEmpty()&&res.getText()!="Math error")
                {
                    dec=(int)Long.parseLong(input.getText().replace("|", ""),prev_state); //convert to decimal from the prev state
                    input.setText(Integer.toHexString(dec).toUpperCase());//convert from decimal to hex and show it in the textfield
                    res.setText(Integer.toHexString(dec).toUpperCase());//show the hex result in the result label 
                }
                
                //buttons from 0 to 9 and alphabets A to F and operations are enabled
                btn_0.setDisable(false);
                btn_1.setDisable(false);
                btn_2.setDisable(false);
                btn_3.setDisable(false);
                btn_4.setDisable(false);
                btn_5.setDisable(false);
                btn_6.setDisable(false);
                btn_7.setDisable(false);
                btn_8.setDisable(false);
                btn_9.setDisable(false);
                btn_A.setDisable(false);
                btn_B.setDisable(false);
                btn_C.setDisable(false);
                btn_D.setDisable(false);
                btn_E.setDisable(false);
                btn_F.setDisable(false);
                        
                //change the text color of the hex button
                btn_Bin.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Oct.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Dec.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Hex.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);

                break;
        }
        if(!input.getText().replace("|","").isEmpty()&&res.getText()!="Math error")
            input.appendText("|");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeFadeOut();          // apply the fade out
        
        /*
            switch case to choose the mode (bin, oct, dec, or hex)
            by default : dec is choosen
            
            if state has changed between modes (normal, dark) 
                ->  state is saved and initialized here
        */
        switch(state){
            case 2:
                btn_Bin.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                btn_Bin.fire();
                break;
            case 8:
                btn_Oct.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                btn_Oct.fire();
                break;
            case 10:
                btn_Dec.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                btn_Dec.fire();
                break;
            case 16:
                btn_Hex.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                btn_Hex.fire();
                break;
        }
        
        // initialize prev and next states by state
        next_state = state;
        prev_state = state;
        
        // initialize the input, result
        input.setText(oldInput);
        input.setEditable(false);
        input.setFocusTraversable(false);
        res.setText(oldRes);

        // clearing style sheet for menuBar
        Calc_GUI.baseModeController.menuBar.getStylesheets().clear();
        // adding menuBar to the AnchorPane
        anchor.getChildren().add(Calc_GUI.baseModeController.menuBar);
        // bring menuBar to back to show (normal/dark)images on front
        Calc_GUI.baseModeController.menuBar.toBack();
        // set menu bar width to match the AnchorPane width
        Calc_GUI.baseModeController.menuBar.setPrefWidth(549);
        
        // add dark style to menuBar if mode is dark, and normal style if mode is normal
        if(Calc_GUI.darkFlag){
            Calc_GUI.baseModeController.menuBar.getStylesheets().add(getClass().getResource("..//Style/buttonStyleDark.css").toString());            
        }
        else{
            Calc_GUI.baseModeController.menuBar.getStylesheets().add(getClass().getResource("..//Style/buttonStyle.css").toString()); 
        }
    }    

    
    //Function to change from normal mode to dark and reverse
    @FXML
    private void changeMode(MouseEvent event) throws IOException {
        Parent root;
        Scene scene;
        if("normal".equals(((ImageView)event.getSource()).getId())) //incase of in the normal mode and click on the lamb button
        {
            state = next_state; //to save the base-N conversions between two modes
            oldInput = input.getText();
            oldRes = res.getText();
            Calc_GUI.darkFlag = true;
            root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeDark.fxml"));//convert scene to dark one

        }
        else
        {
            state = next_state; 
            oldInput = input.getText();   
            oldRes = res.getText();            
            Calc_GUI.darkFlag = false;
            root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeNormal.fxml")); //convert scene to normal one
        }
        scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();    
        
    }
    
   //fading out when change the mode (ex from basic to base-N ,etc..) 
    void makeFadeOut()
    {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(gridPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
       
}
