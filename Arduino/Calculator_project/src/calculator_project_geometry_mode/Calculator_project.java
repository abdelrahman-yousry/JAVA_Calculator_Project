/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package calculator_project_geometry_mode;

import static calculator_project_geometry_mode.Calculator_project.arduinoPort;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jssc.SerialPort;
import static jssc.SerialPort.MASK_RXCHAR;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author U3
 */
public class Calculator_project extends Application {

    static ObservableList<String> portList;
    static SerialPort arduinoPort = null;
    TextField ta1;
    Label labelValue;
    final int NUM_OF_POINT = 50;
    XYChart.Series series;
    static String Recieved_string = null;
    static String s = "";
    static String st = "";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        detectPort();
        new Thread(() -> {   
            connectArduino(s);
        }).start();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    static public void detectPort() {

        portList = FXCollections.observableArrayList();

        String[] serialPortNames = SerialPortList.getPortNames();// full of coms h3mlha print w h5li 2l user yda5al input 
        for (String name : serialPortNames) {
            System.out.println(name);
            portList.add(name);
            s = name;
            
        }
        //scanner ll user lw da5al 1 yd5olloh 3la com w lw da5al 2 yd5ol 3la com 2 
    }

    public boolean connectArduino(String port) {

        System.out.println("connectArduino");

        boolean success = false;
        SerialPort serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setEventsMask(MASK_RXCHAR);

            serialPort.addEventListener((SerialPortEvent serialPortEvent) -> {//mkn 25li kol 2l gui hena
                if (serialPortEvent.isRXCHAR()) {
                    try {
                        st = serialPort.readString(serialPortEvent
                                .getEventValue());
                        System.out.println(st);
                        

                    } catch (SerialPortException ex) {
                        Logger.getLogger(Calculator_project.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }

                }
            });

            arduinoPort = serialPort;
            success = true;
        } catch (SerialPortException ex) {
            Logger.getLogger(Calculator_project.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("SerialPortException: " + ex.toString());
        }

        return success;
    }

    public static void main(String[] args) {
        

        launch(args);
    }

}
