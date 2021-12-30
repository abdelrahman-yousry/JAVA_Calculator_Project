package BaseMode;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


import static java.lang.Math.E;
import static java.lang.Math.PI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/**
 *
 * @author yomna
 */
public class BaseMode implements Initializable {
    
    @FXML
    private Label res;
    @FXML
    private TextField input;

    @FXML
    private Button b_0;
    @FXML
    private Button b_3;
    @FXML
    private Button b_2;
    @FXML
    private Button b_8;    
    @FXML
    private Button b_5;    
    @FXML
    private Button b_7;    
    @FXML
    private Button b_6;    
    @FXML
    private Button b_9;    
    @FXML
    private Button b_1;    
    @FXML
    private Button b_4;    
    
    
    public void theme_change(ActionEvent event) {

    }
        
    @FXML
    public void write_number(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        input.appendText(tmp.getText());
//        if (b_1.getText().equals("1"))
//            input.appendText("hii");
//        
    }
    @FXML
    public void operation(ActionEvent event) {
        // " + "
        // pi , e --> has space before and after
        Button tmp = (Button)event.getSource();
        String op = tmp.getText();
        if(op.equals("√")){
            op = "√( )";
        }
        input.appendText(op);
    }
   
    @FXML
    private void clearScr(ActionEvent event) {
        // clear button  --> clear screen
        input.clear();
    }   
    @FXML
    private void back_space(ActionEvent event) {
        // deletes the char
        input.deleteText(input.getText().length()-1, input.getText().length());
    } 
    ///////////////////////////////////////////////////////////////////////
    @FXML
    private void dot_op(ActionEvent event) {
        // sets dot only one time per number
        // parse on operations
        String str = input.getText();
//        String[] arrOfStr = str.split("[×÷+-^√()]", 0);
        String[] arrOfStr = str.split("-|×|÷|^|(|)|\\+|√|%", 0);
//        System.out.println(arrOfStr);
        // check if dot exists in tonkens
        if(!arrOfStr[arrOfStr.length-1].contains("."))
            input.appendText(".");
            
//        for (String a : arrOfStr){
//            System.out.println(a);
//            if(!a.contains(".")){
//                input.appendText(".");
//            }
//        }
    }
        
    @FXML
    private void equal_op(ActionEvent event) {
        // equal operaion --> parse text (str --> num) , operation has space before and after
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        String expression = input.getText();
//        String expression = "Math.sqrt(4)";


        expression = expression.replace('×', '*');
        expression = expression.replace('÷', '/');
        expression = expression.replace("π", Double.toString(PI));
        
        expression = expression.replace("e",  Double.toString(E));
        expression = expression.replace("√", "Math.sqrt");
        expression = expression.replace("^", "Math.pow");
//        System.out.println(expression);
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
    @FXML
    private void invert_sign(ActionEvent event) {
        // clear button  --> clear screen
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
