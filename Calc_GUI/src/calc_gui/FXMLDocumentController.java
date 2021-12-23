/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package calc_gui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;


/**
 *
 * @author Nehal
 */
public class FXMLDocumentController implements Initializable {
    
//    @FXML
//    private Label label;
//    private Button button;
    @FXML
    private ToggleButton theme;
    private Label res;


     @FXML
    private void theme_change(ActionEvent event) {

        if(theme.isSelected()){
            theme.setStyle("-fx-background-image: url(file:./src/calc_gui/tst2.PNG);");        }
        else{
            theme.setStyle("-fx-background-image: url(file:./src/calc_gui/tst1.PNG);");
          
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
