
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */

package CalcApplication;

import BaseMode.*;
import java.awt.AWTException;
import java.awt.Robot;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author yomna
 */
public class Calc_GUI extends Application {
    FXMLLoader loader;
    Parent root;
    public static Scene scene;
    public static BaseMode baseModeController;
    public static boolean darkFlag = false;
    ArduinoCommunication ardCom = new ArduinoCommunication();
    Thread port;
    public static boolean isFirst = true;
    
    @Override
    public void init() throws Exception {
        
        loader = new FXMLLoader(getClass().getResource("..//BaseMode/BaseModeNormal.fxml")); 
        root = loader.load();
        baseModeController = loader.<BaseMode>getController();
        
       }
    
    
    @Override
    public void start(Stage stage) throws Exception {      
        port = new Thread(new Runnable(){
            @Override
            public void run() {

                Calc_GUI.baseModeController.portMenu.setOnShowing(new EventHandler<Event>(){
                    @Override
                    public void handle(Event t) {
                        ardCom.detectPort(Calc_GUI.baseModeController.portMenu);
                    }
                });           
                ardCom.detectPort(Calc_GUI.baseModeController.portMenu);
                while(true){
                    while(!ardCom.isPortSelected){
                        System.out.print("");
                    }
                    ardCom.readData(Calc_GUI.baseModeController.portMenu);
                }
            }
        });
        
        port.start();       
        scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResource("..//Style/Calculator-icon.png").toExternalForm()));
        stage.setTitle("Calculator");      
        stage.setScene(scene);
        stage.show();

        stage.resizableProperty().setValue(false);
        baseModeController.window = stage;
        
    }
    @Override
    public void stop() throws Exception {

        ardCom.stopConnection();
        port.stop();
        super.stop(); //To change body of generated methods, choose Tools | Templates.
    }

    public static void  invoke(String key){
        Robot r;
        try {
            r = new Robot();
            switch(key){
                case"/":
                    r.keyPress(java.awt.event.KeyEvent.VK_DIVIDE);
                    break;
                case"*":
                    r.keyPress(java.awt.event.KeyEvent.VK_SHIFT);                    
                    r.keyPress(java.awt.event.KeyEvent.VK_8);                    
                    r.keyRelease(java.awt.event.KeyEvent.VK_SHIFT);                    
                    break;
                case"-":
                    r.keyPress(java.awt.event.KeyEvent.VK_MINUS);                                  
                    break;
                case"+":
                    r.keyPress(java.awt.event.KeyEvent.VK_SHIFT);                    
                    r.keyPress(java.awt.event.KeyEvent.VK_EQUALS);                    
                    r.keyRelease(java.awt.event.KeyEvent.VK_SHIFT);                
                    break;
                case"1":                    
                    r.keyPress(java.awt.event.KeyEvent.VK_1);                    
                    break;
                case"2":
                    r.keyPress(java.awt.event.KeyEvent.VK_2);                    
                    break;
                case"3":
                    r.keyPress(java.awt.event.KeyEvent.VK_3);                    
                    break;
                case"4":
                    r.keyPress(java.awt.event.KeyEvent.VK_4);                    
                    break;
                case"5":
                    r.keyPress(java.awt.event.KeyEvent.VK_5);                    
                    break;
                case"6":
                    r.keyPress(java.awt.event.KeyEvent.VK_6);                    
                    break;
                case"7":
                    r.keyPress(java.awt.event.KeyEvent.VK_7);                    
                    break;
                case"8":
                    r.keyPress(java.awt.event.KeyEvent.VK_8);                    
                    break;
                case"9":
                    r.keyPress(java.awt.event.KeyEvent.VK_9);                    
                    break;
                case"0":
                    r.keyPress(java.awt.event.KeyEvent.VK_0);                    
                    break;
                case".":
                    r.keyPress(java.awt.event.KeyEvent.VK_PERIOD);                    
                    break;
                case"=":
                    r.keyPress(java.awt.event.KeyEvent.VK_ENTER);                            
                   break;
        ///////////////////////////////////
                case "A":
                    r.keyPress(java.awt.event.KeyEvent.VK_A);                    
                    break;
                case "B":
                    r.keyPress(java.awt.event.KeyEvent.VK_B);                    
                    break;
                case "C":
                    r.keyPress(java.awt.event.KeyEvent.VK_C);                    
                    break;
                case "D":
                    r.keyPress(java.awt.event.KeyEvent.VK_D);                    
                    break;
                case "E":
                    r.keyPress(java.awt.event.KeyEvent.VK_E);                    
                    break;
                case "F":
                    r.keyPress(java.awt.event.KeyEvent.VK_F);                    
                    break;
                case "c":
                    r.keyPress(java.awt.event.KeyEvent.VK_BACK_SPACE);                    
                    break;
                case "t":   // tab
                    r.keyPress(java.awt.event.KeyEvent.VK_TAB);                    
                    r.keyRelease(java.awt.event.KeyEvent.VK_TAB);                    
                    break;
                case "a":   // alt
                    r.keyPress(java.awt.event.KeyEvent.VK_ALT);                    
                    r.keyRelease(java.awt.event.KeyEvent.VK_ALT);                    
                    break;
                case "o":
                    Platform.runLater(() -> {
                        try {
                            ((Button)scene.focusOwnerProperty().get()).fire();
                        } catch (Exception ex) { }});
                    break;
                case "l":
                    r.keyPress(java.awt.event.KeyEvent.VK_LEFT);
                    break;
                case "r":
                    r.keyPress(java.awt.event.KeyEvent.VK_RIGHT);
                    break;
                case "u":
                    r.keyPress(java.awt.event.KeyEvent.VK_UP);
                    break;
                case "d":
                    r.keyPress(java.awt.event.KeyEvent.VK_DOWN);
                    break;
                case "<":
                    r.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
                    r.keyPress(java.awt.event.KeyEvent.VK_LEFT);
                    r.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
                    break;
                case ">":
                    r.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
                    r.keyPress(java.awt.event.KeyEvent.VK_RIGHT);
                    r.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
                    break;
            }
        } catch (AWTException ex) {}
        
    }

    public static String numberValidation(String str,String numberStr , int pos){
        if(!str.replace("|", "").isEmpty()){
            if(pos == 0){
                // check that after caret is valid "doesn't contain e, pi, (, sqrt"
                if(!"0123456789+-×%^÷). ABCDEF".contains(str.substring(pos+1, pos+2))){
                    return (numberStr + "×");
                }
            }
            else if(pos == str.length()-1){
                // check that before caret is valid "doesn't contain e, pi, ), sqrt"
                if("√".contains(str.substring(pos-1, pos))){
                    return"";
                }
                if(!"0123456789+-×%^÷(. ABCDEF".contains(str.substring(pos-1, pos))){
                    return("×" + numberStr);
                }                  
            }
            else{
                // check before and after the caret
                
                if("√".contains(str.substring(pos-1, pos))){
                    return"";
                }
                else if(!"0123456789+-×%^÷(. ABCDEF".contains(str.substring(pos-1, pos)) && !"0123456789+-×%^÷). ABCDEF".contains(str.substring(pos+1, pos+2))){
                    return("×" + numberStr + "×");
                }
                else if(!"0123456789+-×%^÷(. ABCDEF".contains(str.substring(pos-1, pos))){
                    return("×" + numberStr);
                } 
                else if(!"0123456789+-×%^÷). ABCDEF".contains(str.substring(pos+1, pos+2))){
                    return(numberStr + "×");
                }
            }
        }
        return numberStr;
    }
    

    public static String opValidation(String str,String opStr , int pos){
        StringBuilder sb = new StringBuilder(str);
        if("+-×%^÷".contains(opStr)){
            
            if(!str.replace("|", "").isEmpty()){
                if(pos == 0){
                    // check that after caret is valid "doesn't contain e, pi, (, sqrt"
                    return str;
                }
                else if(pos == str.length()-1){
                    // check that before caret is valid "doesn't contain e, pi, ), sqrt"
                    if("√".contains(str.substring(pos-1, pos))){
                        return sb.toString();
                    }
                    if("+-×%^÷".contains(str.substring(pos-1, pos)) && "+-×%^÷".contains(opStr)){
                        sb.replace(pos-1, pos, opStr);
                        return sb.toString();
                    }
                    else{
                        sb.insert(pos, opStr);
                        return sb.toString();
                    }
                }
                else{
                    // check before and after the caret
                    if("√".contains(str.substring(pos-1, pos))){
                        return sb.toString();
                    }
                    if(("+-×%^÷".contains(str.substring(pos-1, pos)) && "+-×%^÷".contains(opStr)) && ("+-×%^÷".contains(str.substring(pos+1, pos+2)) && "+-×%^÷".contains(opStr))){
                        sb.replace(pos-1, pos+2, opStr+"|");
                    }
                    else if("+-×%^÷".contains(str.substring(pos-1, pos)) && "+-×%^÷".contains(opStr)){
                        sb.replace(pos-1, pos, opStr);
                    } 
                    else if("+-×%^÷".contains(str.substring(pos+1, pos+2)) && "+-×%^÷".contains(opStr)){
                        sb.replace(pos+1, pos+2, opStr);
                    }
                    else{
                        if("( ".contains(str.substring(pos-1, pos)))
                            return sb.toString();
                        sb.insert(pos, opStr);
                    }
                    return sb.toString();  
                }
            }
            else
                return "|";
        }
        else{
            // rest of operations e pi ( ) sqrt
            if(!str.replace("|", "").isEmpty()){
                if(pos == 0){
                    // check that after caret is valid "doesn't contain e, pi, (, sqrt"
                    if("+-×%^÷".contains(str.substring(pos+1, pos+2))){
                        sb.insert(pos, opStr);
                        return sb.toString();
                    }
                    else{
                        sb.insert(pos, opStr+ "×");
                        return sb.toString();
                    }
                }
                else if(pos == str.length()-1){
                    // check that before caret is valid "doesn't contain e, pi, ), sqrt"
                    if("+-×%^÷".contains(str.substring(pos-1, pos))){
                        sb.insert(pos, opStr);
                        return sb.toString();
                    }
                    else{
                        sb.insert(pos,"×" + opStr);
                        return sb.toString();
                    }
                }
                else{
                    // check before and after the caret
                    if("+-×%^÷".contains(str.substring(pos-1, pos)) && "+-×%^÷".contains(str.substring(pos+1, pos+2))){
                        sb.insert(pos,opStr);
                        return sb.toString();
                    }
                    else if("+-×%^÷".contains(str.substring(pos-1, pos))){
                        sb.insert(pos, opStr + "×");
                        return sb.toString();
                    } 
                    else if("+-×%^÷".contains(str.substring(pos+1, pos+2))){
                        sb.insert(pos,"×" + opStr);
                        return sb.toString();
                    }
                    else{
                        sb.insert(pos, "×" + opStr + "×");
                        return sb.toString();
                    }
                }
            }
            else{
                return (opStr+"|");
            }
        }
    }
     

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

