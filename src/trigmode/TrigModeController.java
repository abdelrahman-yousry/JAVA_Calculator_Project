/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package trigmode;

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
/**
 *
 * @author EngAya
 */
public class TrigModeController implements Initializable {
    String expression;
    Double result;
    ScriptEngineManager scriptEngineManager;
    ScriptEngine scriptEngine;
    String [] trigOperations = {" sin("," cos("," tan("," sec("," csc("," cot(","asin(","acos(","atan(","asec(","acsc(","acot(",
    " sinh("," cosh("," tanh("," sech("," csch("," coth("};
    String [] arcOperations = {"sinֿ¹","cosֿ¹","tanֿ¹","secֿ¹","cscֿ¹","cotֿ¹","sinhֿ¹","coshֿ¹","tanhֿ¹","sechֿ¹","cschֿ¹","coth¹"};
    String [] hyperArcOperations = {"asinh(","acosh(","atanh(","asech(","acsch(","acoth("};

    /* Flags */
    boolean hypFlag = false;
    
    public AnchorPane anchor;
    public TextField txtField;
    public Label label;
    @FXML
    public ToggleButton theme;
    @FXML
    public ToggleButton radian;
    @FXML 
    public ToggleButton hyp;
    
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
    public Button ln;
    @FXML
    private Button equal;

    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if(hypFlag == false)
        {
            if(event.getSource() == sin)
                txtField.setText(txtField.getText()+" sin(");
            else if(event.getSource() == cos)
                txtField.setText(txtField.getText()+" cos(");
            else if(event.getSource() == tan)
                txtField.setText(txtField.getText()+" tan(");
            else if(event.getSource() == sec)
                txtField.setText(txtField.getText()+" sec");
            else if(event.getSource() == csc)
                txtField.setText(txtField.getText()+" csc(");
            else if(event.getSource() == cot)
                txtField.setText(txtField.getText()+" cot(");
            else if(event.getSource() == arcsin)
                txtField.setText(txtField.getText()+" sinֿ¹(");
            else if(event.getSource() == arccos)
                txtField.setText(txtField.getText()+" cosֿ¹(");
            else if(event.getSource() == arctan)
                txtField.setText(txtField.getText()+" tanֿ¹(");
            else if(event.getSource() == arcsec)
                txtField.setText(txtField.getText()+" secֿ¹(");
            else if(event.getSource() == arccsc)
                txtField.setText(txtField.getText()+" cscֿ¹(");
            else if(event.getSource() == arccot)
                txtField.setText(txtField.getText()+" cotֿ¹(");
        }
        else
        {
            if(event.getSource() == sin)
                txtField.setText(txtField.getText()+" sinh(");
            else if(event.getSource() == cos)
                txtField.setText(txtField.getText()+" cosh(");
            else if(event.getSource() == tan)
                txtField.setText(txtField.getText()+" tanh(");
            else if(event.getSource() == sec)
                txtField.setText(txtField.getText()+" sech(");
            else if(event.getSource() == csc)
                txtField.setText(txtField.getText()+" csch(");
            else if(event.getSource() == cot)
                txtField.setText(txtField.getText()+" coth(");
            else if(event.getSource() == arcsin)
                txtField.setText(txtField.getText()+" sinhֿ¹(");
            else if(event.getSource() == arccos)
                txtField.setText(txtField.getText()+" coshֿ¹(");
            else if(event.getSource() == arctan)
                txtField.setText(txtField.getText()+" tanhֿ¹(");
            else if(event.getSource() == arcsec)
                txtField.setText(txtField.getText()+" sechֿ¹(");
            else if(event.getSource() == arccsc)
                txtField.setText(txtField.getText()+" cschֿ¹(");
            else if(event.getSource() == arccot)
                txtField.setText(txtField.getText()+" cothֿ¹(");           
        }  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        scriptEngine.put("HyperMath", new HyperMath());      
    }    

    @FXML
    public void theme_change(ActionEvent event) {
        if(theme.isSelected()){
            anchor.setStyle("-fx-background-image: url(file:./src/trigmode/normal.png);");           
        }
        else{
            anchor.setStyle("-fx-background-image: url(file:./src/trigmode/dark.png);");
         //anchor.setStyle("-fx-background-color: black;");
        }
    }

    @FXML
    public void hypButtonHandle(ActionEvent event) {
        if(hyp.isSelected())
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
        
        if(radian.isSelected())
            radian.setText("Degree");
        else
            radian.setText("Radian");
    }

    @FXML
    private void equalButtonHandle(ActionEvent event) throws ScriptException {
            expression = txtField.getText();
            /* Preparing the Expression to be executed using Java Script Engine */
            expression = expression.replace('×', '*');
            expression = expression.replace('÷', '/');
            expression = expression.replace("π", "Math.PI");

            expression = expression.replace("e", "Math.E");
            expression = expression.replace("√", "Math.sqrt");
            expression = expression.replace("^", "Math.pow");
            
            /* Replace every sinֿ¹,cosֿ¹,..  --> asin,acos,.. */
            for(String s : arcOperations)
            {
                expression = expression.replace(s, "a"+s.replace("ֿ¹",""));
            }
            for(String s : hyperArcOperations)
            {
                expression = expression.replace(s, "HyperMath."+s);
            }
            /* Replace sin,cos,asin,.. --> Math.sin,Math.cos,Math.asin.. */
            for(String s : trigOperations)
            {
                expression = expression.replace(s, "java.lang.Math."+s);
            }
            result = (Double) scriptEngine.eval(expression);
            txtField.setText(result.toString());
    }

    @FXML
    private void write_number(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        txtField.appendText(tmp.getText());
    }

    @FXML
    private void dot_op(ActionEvent event) {
        // sets dot only one time per number
        // parse on operations
        String str = txtField.getText();
        String[] arrOfStr = str.split("-|×|÷|^|(|)|\\+|√|%", 0);
        // check if dot exists in tonkens
        if(!arrOfStr[arrOfStr.length-1].contains("."))
            txtField.appendText(".");        
    }

    @FXML
    private void operation(ActionEvent event) {
        // " + "
        // pi , e --> has space before and after
        Button tmp = (Button)event.getSource();
        String op = tmp.getText();
        if(op.equals("√")){
            op = "√( )";
        }
        txtField.appendText(op);
    }

    @FXML
    private void back_space(ActionEvent event) {
        // deletes the last char
        txtField.deleteText(txtField.getText().length()-1, txtField.getText().length());
    }

    @FXML
    private void clearScr(ActionEvent event) {
        // clear button  --> clear screen
        txtField.clear();       
    }
}
