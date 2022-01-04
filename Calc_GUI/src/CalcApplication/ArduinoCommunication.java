/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalcApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author yomna
 */
public class ArduinoCommunication {
    ObservableList<String> portList;
    String portName;
    boolean isPortSelected = false;
    SerialPort serialPort;
    
    public void detectPort(){
        // this method is invoked on application startup and when "Ports" menu is selected
        portList = FXCollections.observableArrayList();

        String[] serialPortNames = SerialPortList.getPortNames();
        for(String name: serialPortNames){
//            System.out.println(name);
            portList.add(name);
            Calc_GUI.baseModeController.port_menu.getItems().add(new CheckMenuItem(name));
        }
    }
    
    public boolean selectPort(){
        
//        isPortSelected = false;
        for(MenuItem i: Calc_GUI.baseModeController.port_menu.getItems()){
            i.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    CheckMenuItem item = (CheckMenuItem)i;
                    if(item.isSelected()){
                        for(MenuItem i: Calc_GUI.baseModeController.port_menu.getItems()){
                            CheckMenuItem j = (CheckMenuItem)i;
                            j.setSelected(false);
                        }
                        item.setSelected(true);
                        portName = item.getText();
                        isPortSelected = true;
                    }
                }
            });
        }
        return isPortSelected;
    }
    
    public void readData(){

        serialPort = new SerialPort(portName);
        try {
            if(!serialPort.isOpened())
                serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,    SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
            while(true)
            {       
                //Read 10 bytes from serial port
                byte[] buffer = serialPort.readBytes(2);
                System.out.println(new String(buffer, StandardCharsets.UTF_8));
                if(new String(buffer, StandardCharsets.UTF_8).charAt(0) != 'k')
                {
                    serialPort.closePort();
                    isPortSelected = false;                    
                    while(!selectPort());
                    readData();
//                    break;
                }
            }
            //Close serial port
//            b = serialPort.closePort();
        }
        catch (SerialPortException ex) {
            System.out.println("CalcApplication.ArduinoCommunication.readData()");
        }                   
    }
    public void stopConnection(){
        try {
            serialPort.closePort();
        } catch (SerialPortException ex) { 
            System.out.println("CalcApplication.ArduinoCommunication.stopConnection()");
        }
        
    }
}
