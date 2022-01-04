package BaseNMode;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


import CalcApplication.Calc_GUI;
import java.awt.Color;
import static java.awt.Color.RED;
import static java.awt.Color.black;
import java.io.IOException;
import static java.lang.Math.E;
import static java.lang.Math.PI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
/**
 *
 * @author yomna
 */
public class BaseNMode implements Initializable {
    static String oldInput = "|";
    static String oldRes = " ";
    @FXML
    public Label res;
    public TextField input;
    @FXML
    private Button b_equal;    
    @FXML
    private Button b_backspace;
    @FXML
    private Button b_period;
    public  Menu port_menu;
    public String selectedMode ;//= "Basic";
    
    int prev_state=10;
    int next_state=10;
    
    int pos;
    @FXML
    private MenuItem basic;
    @FXML
    private MenuItem scientific;
    @FXML
    private MenuItem baseN;
  
    @FXML
    private ImageView normal;
    
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
    public void theme_change(ActionEvent event) {

    }
        
    @FXML
    public void write_number(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        pos = input.getText().indexOf("|");
        input.insertText(pos, tmp.getText());
    }
    @FXML
    public void write_key(KeyEvent event) {
       
        pos = input.getText().indexOf("|");
        if(btn_Bin.getTextFill()==javafx.scene.paint.Color.BLUE)    
        {
            if(event.getCode()==event.getCode().DIGIT0
               ||event.getCode()==event.getCode().DIGIT1)
                    input.insertText(pos, event.getText());
        }
        else if(btn_Dec.getTextFill()==javafx.scene.paint.Color.BLUE) //make it by default
        {
            if(event.getCode().isDigitKey())
                input.insertText(pos, event.getText());
        }
        else if(btn_Oct.getTextFill()==javafx.scene.paint.Color.BLUE)    
        {
             if(event.getCode()==event.getCode().DIGIT0
               ||event.getCode()==event.getCode().DIGIT1
               ||event.getCode()==event.getCode().DIGIT2
               ||event.getCode()==event.getCode().DIGIT3
               ||event.getCode()==event.getCode().DIGIT4
               ||event.getCode()==event.getCode().DIGIT5
               ||event.getCode()==event.getCode().DIGIT6
               ||event.getCode()==event.getCode().DIGIT7)
                input.insertText(pos, event.getText());
        }
        else if(btn_Hex.getTextFill()==javafx.scene.paint.Color.BLUE)    
        {
            if(event.getCode().isDigitKey())
                input.insertText(pos, event.getText());
            if(event.getCode()==event.getCode().A
               ||event.getCode()==event.getCode().B
               ||event.getCode()==event.getCode().C
               ||event.getCode()==event.getCode().D
               ||event.getCode()==event.getCode().E
               ||event.getCode()==event.getCode().F)                
            {
                input.insertText(pos, event.getText());
            }
        }
        
        if(event.isShiftDown()){
            switch(event.getCode()){
                case EQUALS:
                    input.insertText(pos, "+");
                    break;
                case DIGIT6:
                    input.insertText(pos, "^");
                    break;
                case DIGIT8:
                    input.insertText(pos, "×");
                    break;               
                case DIGIT5:
                    input.insertText(pos, "%");
                    break;
                case DIGIT9:
                    input.insertText(pos, "( )");
                    break;
                case DIGIT0:
                    input.insertText(pos, "( )");
                    break;
            }
        }
        else if(event.isControlDown()){
            switch(event.getCode()){
                case LEFT:
                    if(pos>0){
                        input.setText(input.getText().replace("|", ""));
                        input.insertText(pos-1, "|");
                    }break;
                case RIGHT:
                    if(pos<input.getText().length()-1){
                        input.setText(input.getText().replace("|", ""));
                        input.insertText(pos+1, "|");
                    }break;
            }
        }
        else{
            switch(event.getCode()){
                case E:
                    input.insertText(pos, "e");
                    break;
                case SLASH:
                    input.insertText(pos, "÷");
                    break;
                case DIVIDE:
                    input.insertText(pos, "÷");
                    break;
                case MINUS:
                    input.insertText(pos, "-");
                    break;
                case SUBTRACT:
                    input.insertText(pos, "-");
                    break;
                case ADD:
                    input.insertText(pos, "+");
                    break;
                case MULTIPLY:
                    input.insertText(pos, "×");
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
                    input.setText(input.getText().replace("|", ""));
                    input.insertText(0, "|");
                    break;
                case END:
                    input.setText(input.getText().replace("|", ""));
                    input.insertText(input.getText().length(), "|");
                    break;
            }
        }
    }
    @FXML
    public void operation(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        String op = tmp.getText();
        if(op.equals("√")){
            op = "√( )";
        }
        pos = input.getText().indexOf("|");
        input.insertText(pos, op);
    }
   
    @FXML
    private void clearScr(ActionEvent event) {
        // clear button  --> clear screen
        input.clear();
        input.setText("|");
        res.setText("");
    }   
    @FXML
    private void back_space(ActionEvent event) {
        // deletes the char
        pos = input.getText().indexOf("|");
        if(pos != 0)
            input.deleteText(pos-1, pos);
    } 
    
    @FXML
    private void dot_op(ActionEvent event) {
        // sets dot only one time per number
        // parse on operations
        String str = input.getText();
        String[] arrOfStr = str.split("[\\×\\÷\\+\\-\\^\\√\\(\\)\\%]+");
        pos = input.getText().indexOf("|");
        for(String a:arrOfStr){
            if(a.contains("|")){
                if(!a.contains(".")){
                    input.insertText(pos, ".");
                    // check if pos of | doesn't has a number before it --> pos-2 = 0
                    if(pos == 0 || !Character.isDigit(input.getText().charAt(pos-1)))
                        input.insertText(pos, "0");
                }
                break;
            }
        }
    }
    
    @FXML
    private void equal_op(ActionEvent event) {
        String expression = input.getText();
//        int count = expression.length() - expression.replaceAll("^","").length();
//        int count = StringUtils.countMatches("engineering", "e");
        expression = expression.replace("|", "");
        if(!expression.isEmpty()){
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
            
            expression = expression.replace('×', '*');
            expression = expression.replace('÷', '/');
            expression = expression.replace("π", Double.toString(PI));

            expression = expression.replace("e",  Double.toString(E));
            expression = expression.replace("√", "Math.sqrt");
            
//            for(int i = 0; i < count; i++){
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
//            System.out.println(expression);
            
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
    private void invert_sign(ActionEvent event) {
        String str = input.getText();
        pos = input.getText().indexOf("|");
        //split
        String[] arrOfStr = str.split("[\\×\\÷\\+\\-\\^\\√\\(\\)\\%]+");
        for(String a:arrOfStr){
            //cursor on which token
            if(a.contains("|")){
                int pos_relative = a.indexOf("|");
                // invert "(- number)"
                input.setText(input.getText().replace(a, ""));
                a = "(-" + a.replace("|", "") + ")";
                input.insertText(pos-pos_relative, a);
                input.insertText(pos+2, "|");
                break;
            }
        }
    }
    
    
    @FXML
    public void baseN(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        String base = tmp.getText();
        int dec;
        switch(base)
        {
            case "BIN":
                prev_state=next_state;
                next_state=2;
                
                if(!input.getText().replace("|", "").isEmpty())
                {
                    dec=Integer.parseInt(input.getText().replace("|", ""),prev_state);
                    input.setText(Integer.toBinaryString(dec));
                    res.setText(Integer.toBinaryString(dec));
                }
                
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
                
                btn_Bin.setTextFill(javafx.scene.paint.Color.BLUE);
                btn_Oct.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Dec.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Hex.setTextFill(javafx.scene.paint.Color.WHITE);

                break;
            case "OCT":
                prev_state=next_state; 
                next_state=8;
                if(!input.getText().replace("|", "").isEmpty())
                {
                    dec=Integer.parseInt(input.getText().replace("|", ""),prev_state);
                    input.setText(Integer.toOctalString(dec));
                    res.setText(Integer.toOctalString(dec));
                }
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
                
                
                btn_Oct.setTextFill(javafx.scene.paint.Color.BLUE);
                btn_Bin.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Dec.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Hex.setTextFill(javafx.scene.paint.Color.WHITE);

                break;
            case "DEC":
                prev_state=next_state; 
                next_state=10;
                if(!input.getText().replace("|", "").isEmpty())
                {
                    dec=Integer.parseInt(input.getText().replace("|", ""),prev_state);
                    input.setText(Integer.toString(dec));
                    res.setText(Integer.toString(dec));
                }
                
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
                

                btn_Bin.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Oct.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Dec.setTextFill(javafx.scene.paint.Color.BLUE);
                btn_Hex.setTextFill(javafx.scene.paint.Color.WHITE);

                break;
            case "HEX":
                prev_state=next_state;
                next_state=16; 
                if(!input.getText().replace("|", "").isEmpty())
                {
                    dec=Integer.parseInt(input.getText().replace("|", ""),prev_state);
                    input.setText(Integer.toHexString(dec));
                    res.setText(Integer.toHexString(dec));
                }
                
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
                
                
                btn_Bin.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Oct.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Dec.setTextFill(javafx.scene.paint.Color.WHITE);
                btn_Hex.setTextFill(javafx.scene.paint.Color.BLUE);

                break;
        }
        if(!input.getText().replace("|","").isEmpty())
            input.appendText("|");
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_Dec.setTextFill(javafx.scene.paint.Color.BLUE);
        input.setText(oldInput);
        input.setEditable(false);
        input.setFocusTraversable(false);
        res.setText(oldRes);
        
    }    

    @FXML
    public void modesHandle(ActionEvent event) throws IOException {
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
            oldInput = input.getText();
            oldRes = res.getText();
            root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeDark.fxml"));
            Calc_GUI.darkFlag = true;

        }
        else
        {
            oldInput = input.getText();   
            oldRes = res.getText();            
            root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeNormal.fxml"));
            Calc_GUI.darkFlag = false;
        }
        scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();    
    }
       
}
