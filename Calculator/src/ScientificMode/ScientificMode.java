/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package ScientificMode;

import CalcApplication.Calc_GUI;
import static CalcApplication.Calc_GUI.opValidation;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author EngAya
 */
public class ScientificMode implements Initializable {
    Alert alert;
    DialogPane dialogPane;
    String expression;
    static String text;
    ScriptEngineManager scriptEngineManager;
    ScriptEngine scriptEngine;
    String [] trigOperations = {" sin("," cos("," tan("," sec("," csc("," cot(","asin(","acos(","atan(","asec(","acsc(","acot("};
    String [] arcOperations = {"sinֿ¹","cosֿ¹","tanֿ¹","secֿ¹","cscֿ¹","cotֿ¹","sinhֿ¹","coshֿ¹","tanhֿ¹","sechֿ¹","cschֿ¹","cothֿ¹"};
    String [] hyperOperations = {" sinh("," cosh("," tanh("," sech("," csch("," coth(","asinh(","acosh(","atanh(","asech(","acsch(","acoth("};

    /* Flags */
    static boolean hypFlag = false;
    static boolean radianFlag = false;
    
    public AnchorPane anchor;
    public TextField txtField;
    public Label res;
    String selectedMode;
    int pos;
    static String oldInput = "|";
    static String oldRes = " ";

    @FXML
    public Button radian;
    @FXML 
    public Button hyp;
    
    /* Trig Mode Buttons */
    public Button sin;
    public Button cos;
    public Button tan;
    public Button sec;
    public Button csc;
    public Button cot;
    public Button arcsin;
    public Button arccos;
    public Button arctan;
    public Button arcsec;
    public Button arccsc;
    public Button arccot;
    public Button xpower;
    public Button log;
    public Button mod;
    @FXML
    private Menu port_menu;
    @FXML
    private Button b_period;
    @FXML
    private Button b_backspace;
    @FXML
    private Button b_equal;
    @FXML
    private ImageView dark;
    @FXML
    private AnchorPane gridPane;


    
    @FXML
    // "| )" exception --> sin
    public void handleButtonAction(ActionEvent event) {
        String str = "";
        String s;
        pos = txtField.getText().indexOf("|");
        
        /* Check if the hyperpolic button is pressed or not , if pressed print
         * sinh, cosh,... , if not pressed print sin, cos,... on buttons 
         */
        if(hypFlag == false)
        {
            if(event.getSource() == sin)
                str= " sin( )";
            else if(event.getSource() == cos)
                str= " cos( )";
            else if(event.getSource() == tan)
                str= " tan( )";
            else if(event.getSource() == sec)
                str= " sec( )";
            else if(event.getSource() == csc)
                str= " csc( )";
            else if(event.getSource() == cot)
                str= " cot( )";
            else if(event.getSource() == arcsin)
                str= " sinֿ¹( )";
            else if(event.getSource() == arccos)
                str= " cosֿ¹( )";
            else if(event.getSource() == arctan)
                str= " tanֿ¹( )";
            else if(event.getSource() == arcsec)
                str= " secֿ¹( )";
            else if(event.getSource() == arccsc)
                str= " cscֿ¹( )";
            else if(event.getSource() == arccot)
                str= " cotֿ¹( )";
        }
        else
        {
            if(event.getSource() == sin)
                str= " sinh( )";
            else if(event.getSource() == cos)
                str= " cosh( )";
            else if(event.getSource() == tan)
                str= " tanh( )";
            else if(event.getSource() == sec)
                str= " sech( )";
            else if(event.getSource() == csc)
                str= " csch( )";
            else if(event.getSource() == cot)
                str= " coth( )";
            else if(event.getSource() == arcsin)
                str= " sinhֿ¹( )";
            else if(event.getSource() == arccos)
                str= " coshֿ¹( )";
            else if(event.getSource() == arctan)
                str= " tanhֿ¹( )";
            else if(event.getSource() == arcsec)
                str= " sechֿ¹( )";
            else if(event.getSource() == arccsc)
                str= " cschֿ¹( )";
            else if(event.getSource() == arccot)
                str= " cothֿ¹( )";           
        }  
        if("ln".equals(((Button)event.getSource()).getText()))
        {
                str= "ln( )";                      
        }
        else if("log".equals(((Button)event.getSource()).getText()))
        {
                str= "log( )";                      
        }
        else if ("|x|".equals(((Button)event.getSource()).getText()))
        {
                str= "abs( )";                                  
        }
        else if("eˣ".equals(((Button)event.getSource()).getText()))
        {
                str= "exp( )";                                             
        }
        s = opValidation(txtField.getText(), str, pos);
        txtField.setText(s);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtField.setText(oldInput);
        txtField.setEditable(false);
        txtField.setFocusTraversable(false);
        res.setText(oldRes);
        checkHypRad();   // Check if the hyp and radian buttons of the old scene when switching between normal and dark mode is pressed or not and change the required buttons 
        scriptEngineManager = new ScriptEngineManager();  
        scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        scriptEngine.put("HyperMath", new HyperMath());   // Add a class HyperMath - that solves the operations that are not found in java Math library such as ln, arccot,... - to the Script Engine to recognize them
        makeFadeOut();
        
        Calc_GUI.baseModeController.menuBar.getStylesheets().clear();
        anchor.getChildren().add(Calc_GUI.baseModeController.menuBar);
        Calc_GUI.baseModeController.menuBar.toBack();
        Calc_GUI.baseModeController.menuBar.setPrefWidth(794);
        if(Calc_GUI.darkFlag){
            Calc_GUI.baseModeController.menuBar.getStylesheets().add(getClass().getResource("..//Style/buttonStyleDark.css").toString());            
        }
        else{
            Calc_GUI.baseModeController.menuBar.getStylesheets().add(getClass().getResource("..//Style/buttonStyle.css").toString()); 
        }

    }    

    @FXML
    public void hypButtonHandle(ActionEvent event) {
        if(hypFlag == false)
        {
            hypFlag = true;
            sin.setText("sinh");
            cos.setText("cosh");
            tan.setText("tanh");
            sec.setText("sech");
            csc.setText("csch");
            cot.setText("coth");
            arcsin.setText("sinhֿ¹");
            arccos.setText("coshֿ¹");
            arctan.setText("tanhֿ¹");
            arcsec.setText("sechֿ¹");
            arccsc.setText("cschֿ¹");
            arccot.setText("cothֿ¹");
        }
        else
        {
            hypFlag = false;
            sin.setText("sin");
            cos.setText("cos");
            tan.setText("tan");
            sec.setText("sec");
            csc.setText("csc");
            cot.setText("cot");  
            arcsin.setText("sinֿ¹");
            arccos.setText("cosֿ¹");
            arctan.setText("tanֿ¹");
            arcsec.setText("secֿ¹");
            arccsc.setText("cscֿ¹");
            arccot.setText("cotֿ¹");
        }         
    }

    @FXML
    public void radButtonHandle(ActionEvent event) {
        
        if(radianFlag == false)
        {
            radianFlag = true;
            radian.setText("Degree");
        }
        else
        {
            radianFlag = false;
            radian.setText("Radian");
        }
    }

    @FXML
    private void equalButtonHandle(ActionEvent event) throws ScriptException {
            expression = txtField.getText();
            expression = expression.replace("|", "");   //Remove the cursor from the expression
            /* Check if the expression is empty or not, if not --> Do nothing */
            if(!expression.isEmpty())
            {
                /* Preparing the Expression to be executed using Java Script Engine */
                expression = expression.replace("sech","tmp");   //Replace every sech with temporary name because the e will be replaced to Math.e
                expression = expression.replace("exp", "xp");    //Replace every exp with temporary name because the e will be replaced to Math.e
                expression = expression.replace('×', '*');
                expression = expression.replace('÷', '/');
                expression = expression.replace("π", "Math.PI");
                expression = expression.replace("e","Math.E");
                expression = expression.replace("√", "Math.sqrt");
                expression = expression.replace("log", "java.lang.Math.log10");
                expression = expression.replace("abs(", "java.lang.Math.abs(");
                expression = expression.replace("xp", "exp");                
                expression = expression.replace("exp(", "java.lang.Math.exp(");
                                
            if(expression.contains("^")){
                // split string on all operators except ^
                String[] arrOfStr = expression.split("[\\×\\÷\\+\\-\\√\\(\\)\\%]+");
                for(String a:arrOfStr){
                    // get the substring that contains ^
                    if(a.contains("^")){
                        // replace the power part(base^exponent) by "^"
                        expression = expression.replace(a, "^");
                        // split substring on ^ to get the argumnets
                        String [] arg = a.split("\\^");
                        expression = expression.replace("^", "Math.pow(" + arg[0] + "," + arg[1] + ")");
                    }
                }
            }

                expression = expression.replace("tmp","sech");

                /* Replace every sinֿ¹,cosֿ¹,..  --> asin,acos,.. */
                for(String s : arcOperations)
                {
                    expression = expression.replace(s, "a"+s.replace("ֿ¹",""));
                }
                
                for(String s : hyperOperations)
               {
                   expression = expression.replace(s, "HyperMath."+s);
               }               

                if(radianFlag == true)
                {
                    /* Replace sin,cos,asin,.. --> Math.sin,Math.cos,Math.asin.. */
                    for(String s : trigOperations)
                    {
                        expression = expression.replace(s, "java.lang.Math."+s);
                    }               
                }
                else
                {
                    /* Replace sin,cos,asin,.. --> Math.sin,Math.cos,Math.asin.. */
                    for(String s : trigOperations)
                    {
                        expression = expression.replace(s, "java.lang.Math."+s+"(Math.PI/180)*");
                    }  
                }
                
                expression = expression.replace("ln", "HyperMath.ln");
                                
                
                try {
                    Double result = (Double) scriptEngine.eval(expression);
                    res.setText(Double.toString(result));
                } catch (ScriptException ex) {
                    res.setText("Math error");
                }
                catch(ArithmeticException ex){
                    res.setText("Math error");
                }
                catch(Exception ex){
                    try{
                        Integer result = (Integer) scriptEngine.eval(expression);
                        res.setText(Integer.toString(result));
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

    @FXML
    private void write_number(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        pos = txtField.getText().indexOf("|");
        String s = Calc_GUI.numberValidation(txtField.getText(),tmp.getText() , pos);
        txtField.insertText(pos, s);
    }
    
  @FXML
    public void write_key(KeyEvent event) {
        pos = txtField.getText().indexOf("|");
        String s;
        if(event.isShiftDown()){
            switch(event.getCode()){
                case EQUALS:
                    s = opValidation(txtField.getText(), "+", pos);
                    txtField.setText(s);
                    break;
                case DIGIT6:
                    s = opValidation(txtField.getText(), "^", pos);
                    txtField.setText(s);
                    break;
                case DIGIT8:
                    s = opValidation(txtField.getText(), "×", pos);
                    txtField.setText(s);
                    break;
                
                case DIGIT5:
                    s = opValidation(txtField.getText(), "%", pos);
                    txtField.setText(s);
                    break;
                case DIGIT9:
                    s = opValidation(txtField.getText(), "( )", pos);
                    txtField.setText(s);
                    break;
                case DIGIT0:
                    s = opValidation(txtField.getText(), "( )", pos);
                    txtField.setText(s);
                    break;
            }
        }
        else if(event.getCode().isDigitKey()){
            s = Calc_GUI.numberValidation(txtField.getText(),event.getText() , pos);
            txtField.insertText(pos, s);
        }
        else if(event.isControlDown()){
//        else if(event.getCode().isArrowKey()){
            switch(event.getCode()){
                case LEFT:
                    if(pos>0){
                        txtField.setText(txtField.getText().replace("|", ""));
                        txtField.insertText(pos-1, "|");
                    }break;
                case RIGHT:
                    if(pos<txtField.getText().length()-1){
                        txtField.setText(txtField.getText().replace("|", ""));
                        txtField.insertText(pos+1, "|");
                    }break;
            }
        }
        else{
            switch(event.getCode()){
                case E:
                    s = opValidation(txtField.getText(), "e", pos);
                    txtField.setText(s);
                    break;
                case SLASH:
                    s = opValidation(txtField.getText(), "÷", pos);
                    txtField.setText(s);
                    break;
                case DIVIDE:
                    s = opValidation(txtField.getText(), "÷", pos);
                    txtField.setText(s);
                    break;
                case MINUS:
                    s = opValidation(txtField.getText(), "-", pos);
                    txtField.setText(s);
                    break;
                case SUBTRACT:
                    s = opValidation(txtField.getText(), "-", pos);
                    txtField.setText(s);
                    break;
                case ADD:
                    s = opValidation(txtField.getText(), "+", pos);
                    txtField.setText(s);
                    break;
                case MULTIPLY:
                    s = opValidation(txtField.getText(), "×", pos);
                    txtField.setText(s);
                    break;
                case EQUALS:
                    b_equal.fire();
                    break; 
                case ENTER:
                    b_equal.fire();
                    break; 
                case BACK_SPACE:
                    b_backspace.fire();
                    break;
                case PERIOD:
                    b_period.fire();
                    break;
                case DECIMAL:
                    b_period.fire();
                    break;
                case HOME:
                    txtField.setText(txtField.getText().replace("|", ""));
                    txtField.insertText(0, "|");
                    break;
                case END:
                    txtField.setText(txtField.getText().replace("|", ""));
                    txtField.insertText(txtField.getText().length(), "|");
                    break;
            }
        }
    }
    
    @FXML
    private void dot_op(ActionEvent event) {
        // sets dot only one time per number
        // parse on operations
        String str = txtField.getText();
        String[] arrOfStr = str.split("[\\×\\÷\\+\\-\\^\\√\\(\\)\\%]+");
        pos = txtField.getText().indexOf("|");
        for(String a:arrOfStr){
            if(a.contains("|")){
                if(!a.contains(".")){
                    txtField.insertText(pos, ".");
                    // check if pos of | doesn't has a number before it --> pos-2 = 0
                    if(pos == 0 || !Character.isDigit(txtField.getText().charAt(pos-1)))
                        txtField.insertText(pos, "0");
                }
                break;
            }
        }     
    }

    @FXML
    private void operation(ActionEvent event) {
        String s;
        Button tmp = (Button)event.getSource();
        String op = tmp.getText();
        pos = txtField.getText().indexOf("|");
        if(op.equals("√")){
            op = "√( )";
        }
        s = opValidation(txtField.getText(),op , pos);
        txtField.setText(s);
    }

    @FXML
    private void back_space(ActionEvent event) {
        int operatorIndex [] = new int[5];
        int max = -1;
        // deletes the char
        pos = txtField.getText().indexOf("|");  //find index of cursor 
        if(pos != 0)  //if the txtfield is empty
        {
            if(txtField.getText().charAt(pos-1) == ')')   //if the last charactetr before the cursor is ) that means that we should remove the whole function--> find the index of last operator
            {
                operatorIndex[0] = txtField.getText().lastIndexOf("+");
                operatorIndex[1] = txtField.getText().lastIndexOf("×");
                operatorIndex[2] = txtField.getText().lastIndexOf("/");
                operatorIndex[3] = txtField.getText().lastIndexOf("-");
                operatorIndex[4] = txtField.getText().lastIndexOf("%");
            }
            max = Arrays.stream(operatorIndex).max().getAsInt();  //Find the max of indexs to find last operator, return 0 if no operator found
            if(max > 0)  //No operator is found
            {
                txtField.deleteText(max+1, pos);  //delete until the oprator
            }
            else 
            {
                txtField.deleteText(pos-1, pos);  //delete char by char
            }
        }
    }


    @FXML
    private void clearScr(ActionEvent event) {
        // clear button  --> clear screen
        txtField.clear();
        txtField.setText("|");
        res.setText("");      
    }
    
        @FXML
    private void invert_sign(ActionEvent event) {
        String str = txtField.getText();
        pos = txtField.getText().indexOf("|");
        //split
        String[] arrOfStr = str.split("[\\×\\÷\\+\\-\\^\\√\\(\\)\\%]+");
        for(String a:arrOfStr){
            //cursor on which token
            if(a.contains("|")){
                int pos_relative = a.indexOf("|");
                // invert "(- number)"
                txtField.setText(txtField.getText().replace(a, ""));
                a = "(-" + a.replace("|", "") + ")";
                txtField.insertText(pos-pos_relative, a);
                txtField.insertText(pos+2, "|");
                break;
            }
        }
    }

 
    @FXML
    private void changeMode(MouseEvent event) throws IOException {
        Parent root;
        Scene scene;
        if("normal".equals(((ImageView)event.getSource()).getId()))
        {
            oldInput = txtField.getText();
            oldRes = res.getText();
            Calc_GUI.darkFlag = true;
            root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeDark.fxml"));

        }
        else
        {
            oldInput = txtField.getText();
            oldRes = res.getText();
            Calc_GUI.darkFlag = false;
            root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeNormal.fxml"));
        }
        scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();    
    }
    
    public void checkHypRad ()
    {
        if(hypFlag)
        {
            sin.setText("sinh");
            cos.setText("cosh");
            tan.setText("tanh");
            sec.setText("sech");
            csc.setText("csch");
            cot.setText("coth");
            arcsin.setText("sinhֿ¹");
            arccos.setText("coshֿ¹");
            arctan.setText("tanhֿ¹");
            arcsec.setText("sechֿ¹");
            arccsc.setText("cschֿ¹");
            arccot.setText("cothֿ¹");            
            
        }
        else
        {
            sin.setText("sin");
            cos.setText("cos");
            tan.setText("tan");
            sec.setText("sec");
            csc.setText("csc");
            cot.setText("cot");  
            arcsin.setText("sinֿ¹");
            arccos.setText("cosֿ¹");
            arctan.setText("tanֿ¹");
            arcsec.setText("secֿ¹");
            arccsc.setText("cscֿ¹");
            arccot.setText("cotֿ¹");
           
        }
        
        if(!radianFlag)
        {
            radian.setText("Radian");  
        }
        else
        {
            radian.setText("Degree");
           
        } 
    }
    
 

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
