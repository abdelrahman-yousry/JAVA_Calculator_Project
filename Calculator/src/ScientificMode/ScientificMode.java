/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package ScientificMode;

import CalcApplication.Calc_GUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import static java.lang.Math.E;
import java.lang.Math;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 *
 * @author EngAya
 */
public class ScientificMode implements Initializable {
    String expression;
    ScriptEngineManager scriptEngineManager;
    ScriptEngine scriptEngine;
    String [] trigOperations = {" sin("," cos("," tan("," sec("," csc("," cot(","asin(","acos(","atan(","asec(","acsc(","acot(",
    " sinh("," cosh("," tanh("};
    String [] arcOperations = {"sinֿ¹","cosֿ¹","tanֿ¹","secֿ¹","cscֿ¹","cotֿ¹","sinhֿ¹","coshֿ¹","tanhֿ¹","sechֿ¹","cschֿ¹","cothֿ¹"};
    String [] hyperOperations = {" sech("," csch("," coth(","asinh(","acosh(","atanh(","asech(","acsch(","acoth("};

    /* Flags */
    boolean hypFlag = false;
    boolean radianFlag = false;
    
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
    private ImageView normal;


    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        pos = txtField.getText().indexOf("|");
        if( !"|".equals(txtField.getText()) )  //if the text field is not Empty --> Check the last character before the curser
        {
            System.out.println("ScientificMode.ScientificMode.handleButtonAction()");
            Character lastChar = txtField.getText().charAt(pos-1);  
            //if the last character is one of the next cases --> add X before the trigonometric func.
            switch( lastChar )
            {
                case '0':
                case '1':
                case '2':        
                case '3':        
                case '4':        
                case '5':        
                case '6':        
                case '7':        
                case '8':        
                case '9':        
                case 'e':        
                case 'π':
                case ')':
                    txtField.insertText(pos,"×");
                    pos = txtField.getText().indexOf("|");;
                    break;
            } 
        }
        if(hypFlag == false)
        {
            if(event.getSource() == sin)
                txtField.insertText(pos," sin()");
            else if(event.getSource() == cos)
                txtField.insertText(pos," cos()");
            else if(event.getSource() == tan)
                txtField.insertText(pos," tan()");
            else if(event.getSource() == sec)
                txtField.insertText(pos," sec()");
            else if(event.getSource() == csc)
                txtField.insertText(pos," csc()");
            else if(event.getSource() == cot)
                txtField.insertText(pos," cot()");
            else if(event.getSource() == arcsin)
                txtField.insertText(pos," sinֿ¹()");
            else if(event.getSource() == arccos)
                txtField.insertText(pos," cosֿ¹()");
            else if(event.getSource() == arctan)
                txtField.insertText(pos," tanֿ¹()");
            else if(event.getSource() == arcsec)
                txtField.insertText(pos," secֿ¹()");
            else if(event.getSource() == arccsc)
                txtField.insertText(pos," cscֿ¹()");
            else if(event.getSource() == arccot)
                txtField.insertText(pos," cotֿ¹()");
        }
        else
        {
            if(event.getSource() == sin)
                txtField.insertText(pos," sinh()");
            else if(event.getSource() == cos)
                txtField.insertText(pos," cosh()");
            else if(event.getSource() == tan)
                txtField.insertText(pos," tanh()");
            else if(event.getSource() == sec)
                txtField.insertText(pos," sech()");
            else if(event.getSource() == csc)
                txtField.insertText(pos," csch()");
            else if(event.getSource() == cot)
                txtField.insertText(pos," coth()");
            else if(event.getSource() == arcsin)
                txtField.insertText(pos," sinhֿ¹()");
            else if(event.getSource() == arccos)
                txtField.insertText(pos," coshֿ¹()");
            else if(event.getSource() == arctan)
                txtField.insertText(pos," tanhֿ¹()");
            else if(event.getSource() == arcsec)
                txtField.insertText(pos," sechֿ¹()");
            else if(event.getSource() == arccsc)
                txtField.insertText(pos," cschֿ¹()");
            else if(event.getSource() == arccot)
                txtField.insertText(pos," cothֿ¹()");           
        }  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtField.setText(oldInput);
        txtField.setEditable(false);
        txtField.setFocusTraversable(false);
        res.setText(oldRes);
        scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        scriptEngine.put("HyperMath", new HyperMath()); 
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
            expression = expression.replace("|", "");
            if(!expression.isEmpty())
            {
                /* Preparing the Expression to be executed using Java Script Engine */
                expression = expression.replace("sech","tmp");   //Replace every sech with temporary name because the e will be replaced to Math.e
                expression = expression.replace('×', '*');
                expression = expression.replace('÷', '/');
                expression = expression.replace("π", "Math.PI");
                expression = expression.replace("e","Math.E");
                expression = expression.replace("√", "Math.sqrt");
                expression = expression.replace("log", "java.lang.Math.log10");

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

                if(radianFlag == true)
                {
                     for(String s : hyperOperations)
                    {
                        expression = expression.replace(s, "HyperMath."+s);
                    }
                    /* Replace sin,cos,asin,.. --> Math.sin,Math.cos,Math.asin.. */
                    for(String s : trigOperations)
                    {
                        expression = expression.replace(s, "java.lang.Math."+s);
                    }               
                }
                else
                {
                    for(String s : hyperOperations)
                    {
                        expression = expression.replace(s, "HyperMath."+s+"(Math.PI/180)*");
                    }
                    /* Replace sin,cos,asin,.. --> Math.sin,Math.cos,Math.asin.. */
                    for(String s : trigOperations)
                    {
                        expression = expression.replace(s, "java.lang.Math."+s+"(Math.PI/180)*");
                    }  
                }
                try {
                    Double result = (Double) scriptEngine.eval(expression);
                    res.setText(Double.toString(result));
                } catch (ScriptException ex) { }
                catch(Exception ex){
                    try{
                        Integer result = (Integer) scriptEngine.eval(expression);
                        res.setText(Integer.toString(result));
                    }
                    catch(ScriptException e) {
                    }
                }
            }
    }

    @FXML
    private void write_number(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        pos = txtField.getText().indexOf("|");
        txtField.insertText(pos, tmp.getText());
    }
    
    @FXML
    public void write_key(KeyEvent event) {
        pos = txtField.getText().indexOf("|");
        if(event.isShiftDown()){
            switch(event.getCode()){
                case EQUALS:
                    txtField.insertText(pos, "+");
                    break;
                case DIGIT6:
                    txtField.insertText(pos, "^");
                    break;
                case DIGIT8:
                    txtField.insertText(pos, "×");
                    break;
                
                case DIGIT5:
                    txtField.insertText(pos, "%");
                    break;
                case DIGIT9:
                    txtField.insertText(pos, "( )");
                    break;
                case DIGIT0:
                    txtField.insertText(pos, "( )");
                    break;
            }
        }
        else if(event.getCode().isDigitKey())
            txtField.insertText(pos, event.getText());
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
                    txtField.insertText(pos, "e");
                    break;
                case SLASH:
                    txtField.insertText(pos, "÷");
                    break;
                case DIVIDE:
                    txtField.insertText(pos, "÷");
                    break;
                case MINUS:
                    txtField.insertText(pos, "-");
                    break;
                case SUBTRACT:
                    txtField.insertText(pos, "-");
                    break;
                case ADD:
                    txtField.insertText(pos, "+");
                    break;
                case MULTIPLY:
                    txtField.insertText(pos, "×");
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
        System.out.println("ScientificMode.ScientificMode.operation()");
        Button tmp = (Button)event.getSource();
        String op = tmp.getText();
        if(op.equals("√")){
            op = "√( )";
        }
        pos = txtField.getText().indexOf("|");
        txtField.insertText(pos, op);
    }

    @FXML
    private void back_space(ActionEvent event) {
        // deletes the char
        pos = txtField.getText().indexOf("|");
        if(pos != 0)
            txtField.deleteText(pos-1, pos);
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
    private void modesHandle(ActionEvent event) throws IOException {
         selectedMode = ((MenuItem)event.getSource()).getText();

        Parent root = null;
        Scene scene;
        
        switch(selectedMode)
        {
            case "Basic":
                if(!Calc_GUI.darkFlag)
                    root = FXMLLoader.load(getClass().getResource("..//BaseMode/BaseModeNormal.fxml"));
                else
                    root = FXMLLoader.load(getClass().getResource("..//BaseMode/BaseModeDark.fxml"));                   
                break;
            case "Scientific":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeNormal.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeDark.fxml"));                   
                break;
            case "Conversion":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//ConversionMode/Converter_FXML.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//ConversionMode/Converter_FXML_Dark.fxml"));                  
                break; 
            case "Base-N":
               if(!Calc_GUI.darkFlag)
                   root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeNormal.fxml"));
               else
                   root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeDark.fxml"));                   
               break;
        }
        
        scene = new Scene(root);
        Stage window = (Stage)(res.getScene().getWindow());
        window.setScene(scene);
        window.show();
        //return to default
        oldInput = "|";
        oldRes = " ";
    }
    
    @FXML
    private void changeMode(MouseEvent event) throws IOException {
        Parent root;
        Scene scene;
        if("normal".equals(((ImageView)event.getSource()).getId()))
        {
            oldInput = txtField.getText();
            oldRes = res.getText();
            root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeDark.fxml"));
            Calc_GUI.darkFlag = true;

        }
        else
        {
            oldInput = txtField.getText();
            oldRes = res.getText();
            root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeNormal.fxml"));
            Calc_GUI.darkFlag = false;
        }
        scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();    
    }
    
}
