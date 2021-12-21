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
import javafx.scene.control.ToggleButton;


/**
 *
 * @author yomna
 */
public class FXMLDocumentController implements Initializable {
    
//    @FXML
//    private Label label;
//    private Button button;
    @FXML
    private ToggleButton theme;
    

     @FXML
    private void theme_change(ActionEvent event) {
//        System.out.println("yyyyyyyyyy!");
//        theme.getScene().getRoot().setEffect(new );
        if(theme.isSelected()){
            theme.setStyle("-fx-background-image: url(file:./src/calc_gui/tst2.PNG);");
//            theme.setText("yyy");
        }
        else{
            theme.setStyle("-fx-background-image: url(file:./src/calc_gui/tst1.PNG);");
//            theme.setText("xxx");            
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
