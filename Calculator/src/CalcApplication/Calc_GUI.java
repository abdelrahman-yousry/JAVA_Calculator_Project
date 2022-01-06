
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
    public static Scene scene;
    public static BaseMode baseModeController;
    public static boolean darkFlag = false;
 //   public static ScientificMode scientificModeController;
 //   public static Converter_FXMLController converterModeController;
   
    ArduinoCommunication ardCom = new ArduinoCommunication();
    Thread port;

    @Override
    public void init() throws Exception {
        
        loader = new FXMLLoader(getClass().getResource("..//BaseMode/BaseModeNormal.fxml"));   
        
      //      loader = new FXMLLoader(getClass().getResource("../BaseMode/BaseModeDark.fxml"));   
     //     loader = new FXMLLoader(getClass().getResource("..//ScientificMode/ScientificModeDark.fxml"));   
     //   loader = new FXMLLoader(getClass().getResource("..//ConversionMode/Converter_FXML_Dark.fxml"));   
    //loader = new FXMLLoader(getClass().getResource("..//GeometryMode/GeometryModeNormal.fxml"));
       }
    
    
    @Override
    public void start(Stage stage) throws Exception {


        Parent root = loader.load();

        baseModeController = loader.<BaseMode>getController();
        
        port = new Thread(new Runnable(){
            @Override
            public void run() {

                Calc_GUI.baseModeController.port_menu.setOnShowing(new EventHandler<Event>(){
                    @Override
                    public void handle(Event t) {
                        ardCom.detectPort();
                    }
                });           
                ardCom.detectPort();
                while(true){
                    while(!ardCom.isPortSelected){
                        System.out.print("");
                    }
                    ardCom.readData();
                }
            }
        });
        
        port.start();       
       
        scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResource("..//Style/Calculator-icon.png").toExternalForm()));
        stage.setTitle("Calculator");      
        stage.setScene(scene);
        stage.show();
        
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
                case "%":   // tab
                    r.keyPress(java.awt.event.KeyEvent.VK_TAB);                    
                    r.keyRelease(java.awt.event.KeyEvent.VK_TAB);                    
                    break;
                case "i":   // alt
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



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

