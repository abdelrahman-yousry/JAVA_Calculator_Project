/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package calculator_project_1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Abdelrahman Yousry
 */
public class FXMLDocumentController implements Initializable {

    String shape_detection = null;
    String calc;
    int status_area;
    int status_perimeter;
    String get_dnum;
    int get_length=0;
    Double dnum1 = new Double(0);
    Double dnum2 = new Double(0);
    Double dnum3 = new Double(0);
    MenuItem circum_calc;
    @FXML
    MenuItem perimeter_calc;
    @FXML
    MenuItem area_mi;
    @FXML
    private MenuButton unit_conv_mb;
    @FXML
    private ToggleButton theme;
    @FXML
    private TextField ta1;
    @FXML
    private TextField ta2;
    @FXML
    private MenuButton shape_mb;
    @FXML
    private MenuButton calc_mb;
    @FXML
    private MenuItem timeconv;
    @FXML
    private MenuItem circle_calc;
    @FXML
    private GridPane b;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void conv_mode(ActionEvent event) {
    }

    @FXML
    private void theme_change(ActionEvent event) {
    }

    @FXML
    private void ta_write(ActionEvent event) {
        Button temp = (Button) event.getSource();
        String bstr = temp.getText();
        //String ta1_str = temp.getText();
        switch (bstr) {//s switch 3la st mn 2l arduino
            case "C":
                ta1.clear();
                break;
            case "⌫":
                ta1.deleteNextChar();
                break;
            case "=":

                if (!ta1.getText().isEmpty() && shape_detection != null) {

                    Double result = new Double(0);
                    switch (shape_detection) {
                        case ("Triangle"):

                            switch (calc) {
                                case ("Area"):
                                    if (status_area == 0) {
                                        get_dnum = ta1.getText(15, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum1 = new Double(get_dnum);
                                        System.out.println(dnum1);
                                        ta1.appendText(" Enter the hieght ");
                                        get_length = ta1.getLength();
                                        status_area = 1;
                                    } else if (status_area == 1) {
                                        get_dnum = ta1.getText(get_length, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum2 = new Double(get_dnum);
                                        System.out.println(dnum2);
                                        result = 0.5 * dnum1 * dnum2;
                                        ta2.setText("The area = ");
                                        ta2.appendText(result.toString());
                                        status_area = 0;
                                        get_length=0;
                                    }

                                    break;
                                case ("Perimeter"):
                                    //System.out.println("calculator_project_1.FXMLDocumentController.ta_write()");
                                    if (status_perimeter == 0) {
                                        get_dnum = ta1.getText(16, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum1 = new Double(get_dnum);
                                        System.out.println(dnum1);
                                        ta1.setText(" Enter side 2 = ");
                                        get_length = ta1.getLength();
                                        status_perimeter = 1;
                                    } else if (status_perimeter == 1) {
                                        get_dnum = ta1.getText(get_length, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum2 = new Double(get_dnum);
                                        System.out.println(dnum2);
                                        ta1.setText("Enter side 3 = ");
                                        get_length = ta1.getLength();
                                        status_perimeter = 2;
                                    } else if (status_perimeter == 2) {
                                        get_dnum = ta1.getText(get_length, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum3 = new Double(get_dnum);
                                        System.out.println(dnum2);
                                        result = dnum1 + dnum2 + dnum3;
                                        ta2.setText("The perimeter = ");
                                        ta2.appendText(result.toString());
                                        status_perimeter = 0;
                                        get_length=0;
                                    }
                            }
                            break;
                        case ("Rohmbus"):
                            switch (calc) {
                                case ("Area"):
                                    if (status_area == 0) {
                                        get_dnum = ta1.getText(28, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum1 = new Double(get_dnum);
                                        System.out.println(dnum1);
                                        ta1.setText(" Enter the diagonal 2 length ");
                                        get_length = ta1.getLength();
                                        System.out.println(get_length);
                                        status_area = 1;
                                    } else if (status_area == 1) {
                                        get_dnum = ta1.getText(get_length, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum2 = new Double(get_dnum);
                                        System.out.println(dnum2);
                                        ta2.setText("The area of Rohmbus = ");
                                        result = (dnum1 * dnum2) / 2;
                                        ta2.appendText(result.toString());
                                        status_area = 0;
                                        get_length=0;
                                    }
                                    break;

                                case ("Perimeter"):
                                    if (status_perimeter == 0) {
                                        get_dnum = ta1.getText(22, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum1 = new Double(get_dnum);
                                        System.out.println(dnum1);
                                        result = 4 * dnum1;
                                        ta2.setText("The perimeter of Rohmbus = ");
                                        ta2.appendText(result.toString());
                                    }

                            }

                        case ("Parallelogram"):
                            switch (calc) {
                                case ("Area"):
                                    if (status_area == 0) {
                                        get_dnum = ta1.getText(17, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum1 = new Double(get_dnum);
                                        System.out.println(dnum1);
                                        ta1.setText(" Enter the hieght = ");
                                        get_length = ta1.getLength();
                                        status_area = 1;
                                    } else if (status_area == 1) {
                                        get_dnum = ta1.getText(get_length, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum2 = new Double(get_dnum);
                                        System.out.println(dnum2);
                                        ta2.setText("The area of parallelogram = ");
                                        result = dnum1 * dnum2;
                                        ta2.appendText(result.toString());
                                        status_area = 0;
                                        get_length=0;
                                    }
                                    break;

                                case ("Perimeter"):
                                    if (status_perimeter == 0) {
                                    
                                        get_dnum = ta1.getText(14, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum1 = new Double(get_dnum);
                                        System.out.println(dnum1);
                                        ta1.setText(" Enter side 2 = ");
                                        get_length = ta1.getLength();
                                        status_perimeter = 1;
                                    } else if (status_perimeter == 1) {
                                        get_dnum = ta1.getText(get_length, ta1.getLength());
                                        System.out.println(get_dnum);
                                        dnum2 = new Double(get_dnum);
                                        System.out.println(dnum2);
                                        ta2.setText("The perimeter of parallelogram = ");
                                        result = 2*(dnum1 + dnum2);
                                        ta2.appendText(result.toString());
                                        status_perimeter = 0;
                                        get_length=0;
                                    }
                                break ;
                            }
                            break;
                    }
                }
                break;

            default:
                ta1.appendText(bstr);
                break;
        }
    }

    @FXML
    private void shape_geo(ActionEvent event) {
        ta1.clear();
        ta2.clear();
        MenuItem shapes_mi = (MenuItem) event.getSource();
        String s = shapes_mi.getText();
        shape_mb.setText(s);
        calc_mb.getItems().removeAll(area_mi, perimeter_calc, circum_calc);
        if (s.equals("Triangle")) {
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc = new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);
            perimeter_calc.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int x;
                    ta1.clear();
                    ta2.clear();
                    ta1.setText("Enter side 1 = ");
                    x = ta1.getLength();
                    System.out.println(x);
                    calc_mb.setText(perimeter_calc.getText());
                    calc = ((MenuItem) event.getSource()).getText();
                    System.out.println(calc);
                }
            });
            area_mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int x;
                    ta1.clear();
                    ta2.clear();
                    ta1.setText("Enter the base ");
                    x = ta1.getLength();
                    System.out.println(x);

                    calc_mb.setText(area_mi.getText());
                    calc = ((MenuItem) event.getSource()).getText();

                }
            });

            ///////////////////////////////
        } else if (s.equals("Rohmbus")) {
            ////
            //shape_detection = ((MenuButton) event.getSource()).getText();
            ////
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc = new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);

            perimeter_calc.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int x;
                    ta1.clear();
                    ta2.clear();
                    ta1.setText("Enter the side length ");
                    x = ta1.getLength();
                    System.out.println(x);

                    calc_mb.setText(perimeter_calc.getText());
                    calc = ((MenuItem) event.getSource()).getText();
                }
            });
            area_mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int x;
                    ta1.clear();
                    ta2.clear();
                    ta1.setText("Enter the diagonal 1 length ");
                    x = ta1.getLength();
                    System.out.println(x);

                    calc_mb.setText(area_mi.getText());
                    calc = ((MenuItem) event.getSource()).getText();
                }
            });
        } else if (s.equals("Parallelogram")) {
            ////
            //shape_detection = ((MenuButton) event.getSource()).getText();
            ////
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc = new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);

            perimeter_calc.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int x;
                    ta1.clear();
                    ta2.clear();
                    ta1.setText("Enter side 1 =");
                    x = ta1.getLength();
                    System.out.println(x);

                    calc_mb.setText(perimeter_calc.getText());
                    calc = ((MenuItem) event.getSource()).getText();
                }
            });
            area_mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int x;
                    ta1.clear();
                    ta2.clear();
                    ta1.setText("Enter the base = ");
                    x = ta1.getLength();
                    System.out.println(x);

                    calc_mb.setText(area_mi.getText());
                    calc = ((MenuItem) event.getSource()).getText();
                }
            });
        }
    }

}
