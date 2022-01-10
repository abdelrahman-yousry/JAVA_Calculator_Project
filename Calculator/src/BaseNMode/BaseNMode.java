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
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.StringJoiner;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
/**
 *
 * @author yomna
 */
public class BaseNMode implements Initializable {
    Alert alert;
    DialogPane dialogPane;
    static String text;
    static String oldInput = "|";
    static String oldRes = " ";
    @FXML
    public Label res;
    public TextField input;
    @FXML
    private Button b_equal;    
    @FXML
    private Button b_backspace;
    public  Menu port_menu;
    public String selectedMode ;
    
    int prev_state=10;
    int next_state=10;
    
    int pos;
    
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
    private MenuItem BaseN;
    @FXML
    private ImageView dark;
    
    
    @FXML
    public void write_number(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        pos = input.getText().indexOf("|");
        input.insertText(pos, tmp.getText());
    }
    @FXML
    public void write_key(KeyEvent event) {

        pos = input.getText().indexOf("|");
        
        
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
                case HOME:
                    input.setText(input.getText().replace("|", ""));
                    input.insertText(0, "|");
                    break;
                case END:
                    input.setText(input.getText().replace("|", ""));
                    input.insertText(input.getText().length(), "|");
                    break;
            }
            
            
            if(btn_Bin.getTextFill()==javafx.scene.paint.Color.YELLOWGREEN)    
            {
                if(event.getCode()==event.getCode().DIGIT0
                   ||event.getCode()==event.getCode().DIGIT1)
                        input.insertText(pos, event.getText());
            }
            else if(btn_Dec.getTextFill()==javafx.scene.paint.Color.YELLOWGREEN) //make it by default
            {
                if(event.getCode().isDigitKey())
                    input.insertText(pos, event.getText());
            }
            else if(btn_Oct.getTextFill()==javafx.scene.paint.Color.YELLOWGREEN)    
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
            else if(btn_Hex.getTextFill()==javafx.scene.paint.Color.YELLOWGREEN)    
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
                    input.insertText(pos, event.getText().toUpperCase());
                }
            }

            
            
            
        }
    }
    @FXML
    public void operation(ActionEvent event) {        
        Button tmp = (Button)event.getSource();
        String op = tmp.getText();
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
    private void equal_op(ActionEvent event) {
        int dec;
        String exp = input.getText().replace("|","");
        String expression = "";
        String[] splitsMixed = exp.split("((?=\\+|\\-|\\×|\\÷|\\%|\\(|\\))|(?<=\\+|\\-|\\×|\\÷|\\%|\\(|\\)))");
        for (String txt:splitsMixed)
        {
            try{
               dec=(int)Long.parseLong(txt,next_state);
               txt=Integer.toString(dec);
            }
            catch(Exception e){ }
            expression += txt;
        }
        if(!expression.isEmpty()){
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
            
            expression = expression.replace('×', '*');
            expression = expression.replace('÷', '/');            
            try {
                Double result = (Double) scriptEngine.eval(expression.replace("--", "+"));
                switch(next_state)
                    {
                        case 2:         
                            input.setText(Integer.toBinaryString(result.intValue()));
                            res.setText(Integer.toBinaryString(result.intValue()));
                            break;
                        case 8:
                            input.setText(Integer.toOctalString(result.intValue()));
                            res.setText(Integer.toOctalString(result.intValue()));
                            break;
                        case 10:
                            input.setText(Integer.toString(result.intValue()));
                            res.setText(Integer.toString(result.intValue()));
                            break;
                        case 16:
                            input.setText(Integer.toHexString(result.intValue()).toUpperCase());
                            res.setText(Integer.toHexString(result.intValue()).toUpperCase());
                            break;
                    }
                    input.appendText("|");

            } catch (ScriptException ex) {
                res.setText("Math error");
            }
            catch(ArithmeticException ex){
                res.setText("Math error");
            }
            catch(Exception ex){
                try{
                    Integer result = (Integer) scriptEngine.eval(expression.replace("--", "+"));
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
        b_equal.fire();
        switch(base)
            {               
            case "BIN":
                prev_state=next_state;
                next_state=2;
                if(!input.getText().replace("|", "").isEmpty()&&res.getText()!="Math error")
                {
                    dec=(int)Long.parseLong(input.getText().replace("|", ""),prev_state);
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
                    dec=(int)Long.parseLong(input.getText().replace("|", ""),prev_state);
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
                btn_Dec.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                btn_Hex.setTextFill(javafx.scene.paint.Color.WHITE);

                break;
            case "HEX":
                prev_state=next_state;
                next_state=16; 
                if(!input.getText().replace("|", "").isEmpty()&&res.getText()!="Math error")
                {
                    dec=(int)Long.parseLong(input.getText().replace("|", ""),prev_state);
                    input.setText(Integer.toHexString(dec).toUpperCase());
                    res.setText(Integer.toHexString(dec).toUpperCase());
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
                btn_Hex.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);

                break;
        }
        if(!input.getText().replace("|","").isEmpty()&&res.getText()!="Math error")
            input.appendText("|");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        makeFadeOut();
        btn_Dec.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
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
            case "Geometry":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//GeometryMode/GeometryModeNormal.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//GeometryMode/GeometryModeDark.fxml"));                  
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
    
    @FXML
    private void helpHandle(ActionEvent event) {
        
        switch(((MenuItem)event.getSource()).getText())
        {
            case "Guide":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Guide");
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setContentText("\t\t----IMPORTANT SHORTCUTS----\t\t\n"
                        + "-----------------------------------------------------------\n"
                        + "1- Ctrl + ←  :  Move Cursor to Left\n"
                        + "2- Ctrl + → : Move Cursor to Right\n"
                        + "3- ← → ↑ ↓  :  Moving on the GUI\n"
                        + "4- Alt   :  Go to MenuBar\n"
                        + "5- Tab  :  Move out from the Text Field\n"
                        + "-----------------------------------------------------------\n"
                        + "NOTE  :  You can use the Keys on your Keyboard to\n\t\t\t  type what you need"); 
                
                dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                getClass().getResource("..//Style/Dialoge.css").toString());
                dialogPane.getStyleClass().add("myDialog");
                alert.showAndWait();
                break;
            case "About":
                Image logoITI = new Image(getClass().getResource("..//Style/ITI.png").toString());
                ImageView logo = new ImageView(logoITI);
                StackPane pane = new StackPane();
                pane.getChildren().add(logo);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText(null);
                alert.setContentText("\n\n\t\tCopyright © 2022 by Team 9\n\n Aya Adel - Youmna Al-Shaboury - Nehal Amgad\n     Abdelrahman Yousry - Mohammed Hosny\n\n\t\tintake42-Embedded System Track");  
                alert.setGraphic(pane);
                dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                getClass().getResource("..//Style/Dialoge.css").toString());
                dialogPane.getStyleClass().add("myDialog");
                alert.showAndWait();
                break;       
        }
    }

    @FXML
    private void editHandle(ActionEvent event) {
        switch(((MenuItem)event.getSource()).getText())
        {
            case "Copy":
               text = input.getSelectedText();
               text = text.replace("|", "");
                break;
            case "Cut":
                text = input.getSelectedText();
                input.deleteText(input.getSelection());
                break;
            case "Paste":
                input.insertText(input.getCaretPosition(),text);
                break;
            case "Delete":
                input.setText("|");
                break;
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
