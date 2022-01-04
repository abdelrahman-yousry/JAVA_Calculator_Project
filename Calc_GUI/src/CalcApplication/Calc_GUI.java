/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
//package CalcApplication;
//
//import BaseMode.*;
//import ScientificMode.*;
//import java.nio.charset.StandardCharsets;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.MenuItem;
//import javafx.scene.image.Image;
//import javafx.stage.Stage;
//import jssc.SerialPort;
//import jssc.SerialPortException;
//import jssc.SerialPortList;
//
///**
// *
// * @author yomna
// */
//public class Calc_GUI extends Application {
//    FXMLLoader loader;
//    BaseMode controller;
//
//    @Override
//    public void init() throws Exception {
//    //    loader = new FXMLLoader(getClass().getResource("..//BaseMode/BaseModeNormal.fxml"));   
//            loader = new FXMLLoader(getClass().getResource("../BaseMode/BaseModeNormal.fxml"));   
////            loader = new FXMLLoader(getClass().getResource("..//ScientificMode/ScientificModeNormal.fxml"));   
//
//       }
//    
//    
//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = loader.load();
//        controller = loader.<BaseMode>getController();
//        
//        Scene scene = new Scene(root);
//        stage.getIcons().add(new Image(getClass().getResource("..//Style/Calculator-icon.png").toExternalForm()));
//        stage.setTitle("Calculator");      
//        stage.setScene(scene);
//        stage.show();
////        while(true){
//            detectPort();
////        }
//
//    }
//
//    @Override
//    public void stop() throws Exception {
////        portList.closePort();
//        super.stop(); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//    ObservableList<String> portList;
//    private void detectPort(){
//        
//        portList = FXCollections.observableArrayList();
//
//        String[] serialPortNames = SerialPortList.getPortNames();
//        for(String name: serialPortNames){
//            System.out.println(name);
//            portList.add(name);
//            controller.port_menu.getItems().add(new MenuItem(name));
//        }
//
//        // must be choosen by user
//        SerialPort serialPort = new SerialPort("COM3");
//
//        try {
//            if(!serialPort.isOpened())
//                serialPort.openPort();
//            serialPort.setParams(SerialPort.BAUDRATE_9600,    SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
//            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
////            PortReader portReader = new PortReader(serialPort);
//            while(true)
//            {
//                //Read 10 bytes from serial port
//                System.out.println("------------------");
//                byte[] buffer = serialPort.readBytes(1);
//                System.out.println(new String(buffer, StandardCharsets.UTF_8));
////                System.out.println(serialPort.readString(2));
//            }
////            serialPort.closePort();//Close serial port
//        
//        }
//        catch (SerialPortException ex) {}
//
//                    
//
//
//    }
//}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */

package CalcApplication;

import BaseMode.*;
import ConversionMode.Converter_FXMLController;
import ScientificMode.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.sound.sampled.Port;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author yomna
 */
public class Calc_GUI extends Application {
    FXMLLoader loader;
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
//        loader = new FXMLLoader(getClass().getResource("..//ConversionMode/Converter_FXML_Dark.fxml"));   

       }
    
    
    @Override
    public void start(Stage stage) throws Exception {

        
//        port = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                ardCom.detectPort();
//                while(!ardCom.selectPort());             
//                ardCom.readData();
//            }         
//        });
//        port.start();
                Parent root = loader.load();
     //   baseModeController = loader.<BaseMode>getController();   
//        switch(baseModeController.selectedMode)
//        {
//            case "Basic":
//                loader = new FXMLLoader(getClass().getResource("..//BaseMode/BaseModeNormal.fxml"));    
//                break;
//            case "Scientific":
//                loader = new FXMLLoader(getClass().getResource("..//ScientificMode/ScientificModeNormal.fxml"));    
//                break;
//        }
        
   
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResource("..//Style/Calculator-icon.png").toExternalForm()));
        stage.setTitle("Calculator");      
        stage.setScene(scene);
        stage.show();
        
    }



//    @Override
//    public void stop() throws Exception {
//
//        ardCom.stopConnection();
//        port.stop();
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
        
    }

}

