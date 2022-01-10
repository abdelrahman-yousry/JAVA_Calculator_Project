/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalcApplication;

import java.nio.charset.StandardCharsets;
import java.util.ConcurrentModificationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
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
    static boolean isPortSelected = false;
    SerialPort serialPort;
    boolean isFound = false;

    public void detectPort(Menu m){
        // this method is invoked on application startup and when "Ports" menu is selected
        portList = FXCollections.observableArrayList();

        String[] serialPortNames = SerialPortList.getPortNames();
        for(String name: serialPortNames){
            isFound = false;
            portList.add(name);
            if(!m.getItems().isEmpty()){
                for(MenuItem i: m.getItems()){
                    if(i.getText().equals(name)){
                        isFound = true;
                        break;
                    }
                }
                if(!isFound)
                    m.getItems().add(new CheckMenuItem(name));
            }
            else
                m.getItems().add(new CheckMenuItem(name));
        }
        
        
        // update menu -- delete items that no longer exists
        CheckMenuItem j;
        for(int count = 1; count < m.getItems().size();count++){
            j = (CheckMenuItem)m.getItems().get(count);
            isFound = false;
            for(String name: serialPortNames){
                if(j.getText().equals(name)){
                    isFound = true;
                    break;
                }
                
            }
            if(!isFound)
                    m.getItems().remove(j);
        }
        
        
        for(MenuItem i: m.getItems()){
            i.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent ev) {
                    selectPort(m, ev);
                }
            });
        }
    }

    
    public void selectPort(Menu m, ActionEvent ev){
        isPortSelected = false;
        try{
            CheckMenuItem item = (CheckMenuItem)ev.getSource();
            if(item.isSelected()){
                CheckMenuItem j;
                for(int count = 1; count < m.getItems().size();count++){
                    j = (CheckMenuItem)m.getItems().get(count);
                    j.setSelected(false);

                    if(serialPort!= null){
                        if(serialPort.isOpened()){
                            try {
                                    serialPort.closePort();
                            } catch (SerialPortException ex) {        }
                        }
                    }
                }
                portName = item.getText();
                serialPort = new SerialPort(portName);
                if(!serialPort.isOpened())
                    try {
                        serialPort.openPort();
                        if(serialPort.isOpened()){
                            isPortSelected = true;
                            item.setSelected(true);
                        }
                } catch (SerialPortException ex) {}
            }
            else{
                try {
                    System.out.println("deselect");
                    serialPort.closePort();
                } catch (SerialPortException ex) {
                }
            }
        }
        catch(ConcurrentModificationException ex){
            // do nothing 
        }
    }
    
    public void readData(Menu m){
//        System.out.println("ooo");
        try {
            serialPort.setParams(SerialPort.BAUDRATE_9600,    SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
            while(true)
            {       
                //Read 1 byte from serial port
                byte[] buffer = serialPort.readBytes(1);
                String key = new String(buffer, StandardCharsets.UTF_8);
                int isConnected = key.charAt(0);
                
                if(isConnected == 0)
                {
                    serialPort.closePort();     // close port
                    isPortSelected = false;     // deselect port                                        
                    CheckMenuItem j;
                    for(int count = 1; count < m.getItems().size();count++){
                        j = (CheckMenuItem)m.getItems().get(count);
                        j.setSelected(false);
                    }
                    break;
                }
                System.out.println(key);
                Calc_GUI.invoke(key);
            }
        }
        catch (SerialPortException ex) {}                   
    }
    public void stopConnection(){
        if(serialPort!= null){
            if(serialPort.isOpened()){
                try {
                        serialPort.closePort();
                } catch (SerialPortException ex) {        }
            }
        }
    }
}
