/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package CalcApplication;

import BaseMode.*;
import ScientificMode.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author yomna
 */
public class Calc_GUI extends Application {
    FXMLLoader loader;
    BaseMode controller;

    @Override
    public void init() throws Exception {
    //    loader = new FXMLLoader(getClass().getResource("..//BaseMode/BaseModeNormal.fxml"));   
    //        loader = new FXMLLoader(getClass().getResource("..//ScientificMode/ScientificModeNormal.fxml"));   
            loader = new FXMLLoader(getClass().getResource("..//ConversionMode/Converter_FXML_Dark.fxml"));   

        controller = loader.<BaseMode>getController();
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResource("..//Style/Calculator-icon.png").toExternalForm()));
        stage.setTitle("Calculator");      
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
