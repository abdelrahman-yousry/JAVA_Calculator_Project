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
import javafx.scene.control.ToggleButton;

/**
 *
 * @author EngAya
 */
public class TrigModeController implements Initializable {
    
    private Label label;
    @FXML
    private ToggleButton theme;
    @FXML
    private ToggleButton radian;
    @FXML 
    private ToggleButton hyp;
    
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
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void theme_change(ActionEvent event) {
    }

    @FXML
    private void hypButtonHandle(ActionEvent event) {
        if(hyp.isSelected())
        {
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
    private void radButtonHandle(ActionEvent event) {
        if(radian.isSelected())
            radian.setText("Degree");
        else
            radian.setText("Radian");
    }
    
}
