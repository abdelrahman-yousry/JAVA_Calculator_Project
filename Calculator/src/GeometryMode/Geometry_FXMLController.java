/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GeometryMode;

import CalcApplication.Calc_GUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mohamed Hosny
 */
public class Geometry_FXMLController implements Initializable {

    Alert alert;
    DialogPane dialogPane;
    static String text;
    public String selectedMode;
    String shape_detection = null;
    String calc;
    int dot_flag = 0;
    int status_area; // this varibale is a flag to let me know at which state I stand
    int status_perimeter;// this varibale is a flag to let me know at which state I stand
    String get_dnum;// this var. to get the numbers entered by the user
    int ta1_len;// this var. to get the length of the string in the text area
    static char c_to_clear;
    Double dnum1 = new Double(0);
    Double dnum2 = new Double(0);
    Double dnum3 = new Double(0);
    MenuItem circum_calc;
    MenuItem perimeter_calc;
    MenuItem area_mi;
    @FXML
    private TextField ta1;
    @FXML
    private TextField ta2;
    @FXML
    private MenuButton shape_mb;
    @FXML
    private MenuButton calc_mb;
    @FXML
    private Button b_period;
    @FXML
    private Button b_equal;
    @FXML
    private Button b_backspace;
    @FXML
    private Label Label_note;
    @FXML
    private Label res;
    static String old_ta1_text = null;
    static String old_ta2_text = null;
    static String old_Shape_Detection = null;
    static String old_Calc = null;
    @FXML
    MenuItem Circle_mi;
    @FXML
    MenuItem Square_mi;
    @FXML
    MenuItem Rectangle_mi;
    @FXML
    MenuItem Triangle_mi;
    @FXML
    MenuItem Rohmbus_mi;
    @FXML
    MenuItem Parallelogram_mi;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView dark;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Calc_GUI.baseModeController.menuBar.getStylesheets().clear();
        anchor.getChildren().add(Calc_GUI.baseModeController.menuBar);
        Calc_GUI.baseModeController.menuBar.toBack();
        Calc_GUI.baseModeController.menuBar.setPrefWidth(578);
        if (Calc_GUI.darkFlag) {
            Calc_GUI.baseModeController.menuBar.getStylesheets().add(getClass().getResource("..//Style/buttonStyleDark.css").toString());
        } else {
            Calc_GUI.baseModeController.menuBar.getStylesheets().add(getClass().getResource("..//Style/buttonStyle.css").toString());
        }
        makeFadeOut();
        Label_note.setVisible(false);
        ta1.setEditable(false);
        ta1.setFocusTraversable(false);
        ta2.setEditable(false);
        ta2.setFocusTraversable(false);
        if (old_Shape_Detection != null) {
            switch (old_Shape_Detection) {
                case "Circle":
                    Circle_mi.fire();
                    if (old_Calc != null) {
                        switch (old_Calc) {
                            case "Area":
                                area_mi.fire();
                                break;
                            case "Circumference":
                                circum_calc.fire();
                                break;
                        }
                    }
                    break;
                case "Square":
                    Square_mi.fire();
                    if (old_Calc != null) {
                        switch (old_Calc) {
                            case "Area":
                                area_mi.fire();
                                break;
                            case "Perimeter":
                                perimeter_calc.fire();
                                break;
                        }
                    }
                    break;
                case "Rectangle":
                    Rectangle_mi.fire();
                    if (old_Calc != null) {
                        switch (old_Calc) {
                            case "Area":
                                area_mi.fire();
                                break;
                            case "Perimeter":
                                perimeter_calc.fire();
                                break;
                        }
                    }
                    break;
                case "Triangle":
                    Triangle_mi.fire();
                    if (old_Calc != null) {
                        switch (old_Calc) {
                            case "Area":
                                area_mi.fire();
                                break;
                            case "Perimeter":
                                perimeter_calc.fire();
                                break;
                        }
                    }
                    break;
                case "Rohmbus":
                    Rohmbus_mi.fire();
                    if (old_Calc != null) {
                        switch (old_Calc) {
                            case "Area":
                                area_mi.fire();
                                break;
                            case "Perimeter":
                                perimeter_calc.fire();
                                break;
                        }
                    }
                    break;
                case "Parallelogram":
                    Parallelogram_mi.fire();
                    if (old_Calc != null) {
                        switch (old_Calc) {
                            case "Area":
                                area_mi.fire();
                                break;
                            case "Perimeter":
                                perimeter_calc.fire();
                                break;
                        }
                    }
                    break;
            }
            Label_note.setVisible(true);
        }
        ta1.setText(old_ta1_text);
        ta2.setText(old_ta2_text);
    }

//    @FXML
//    private void dot_op(ActionEvent event) {
//        // sets dot only one time per number
//        // parse on operations
//        String str = ta1.getText();
//        String[] arrOfStr = str.split("[\\×\\÷\\+\\-\\^\\√\\(\\)\\%]+");
//        int pos = ta1.getText().indexOf("|");
//        for (String a : arrOfStr) {
//            if (a.contains("|")) {
//                if (!a.contains(".")) {
//                    ta1.insertText(pos, ".");
//                    // check if pos of | doesn't has a number before it --> pos-2 = 0
//                    if (pos == 0 || !Character.isDigit(ta1.getText().charAt(pos - 1))) {
//                        ta1.insertText(pos, "0");
//                    }
//                }
//                break;
//            }
//        }
//    }

    /*
    *this handler fired if any button is pressed in the GUI
    *the input param is the event which is clicking on the button
    *the returning type is void
     */
    @FXML
    private void ta_write(ActionEvent event) {
        Button temp = (Button) event.getSource();// to know which button is pressed
        String bstr = temp.getText();
        /*
            clearing the screen when entering new number after the last 
            succesfull operation's result
         */
        if (c_to_clear == '=' && !ta2.getText().isEmpty()) {
            //if(!ta2.getText().isEmpty()&&ta2.getText().equals(""))
            if (!ta2.getText().equals("Enter Numbers!") || bstr.equals("⌫")) {
                ta1.clear();
                dot_flag = 0;
            }
            ta2.clear();
            c_to_clear = ' ';
        }

        /*
        here we switch case according to the button pressed, and according to the button
        we implement the logic of this button
         */
        switch (bstr) {//s switch 3la st mn 2l arduino
            case "C":// mean clearing the text area field 
                ta1.clear();
                dot_flag = 0;
                break;
            case "⌫"://mean deleting the last character

                if (!ta1.getText().isEmpty()) {
                    if (ta1.getText().charAt(ta1.getText().length() - 1)=='.') {
                        dot_flag = 0;
                    }
                    ta1.deleteText(ta1.getLength() - 1, ta1.getLength()); 
                }

                break;
            case ".":
                if (dot_flag == 0) {
                    dot_flag = 1;
                    ta1.appendText(bstr);
                }

                break;
            case "=":// mean calculating the operations

                c_to_clear = '=';
                if (ta1.getText() != null && !ta1.getText().isEmpty() && shape_detection != null && calc != null) {

                    Double result = new Double(0);
                    switch (shape_detection) {
                        case ("Circle"):
                            switch (calc) {
                                case ("Area"):
                                    dnum1 = Double.parseDouble(ta1.getText());
                                    result = 3.14 * dnum1 * dnum1;
                                    Label_note.setVisible(false);
                                    ta2.appendText(result.toString());
                                    dnum1 = 0.0;
                                    break;
                                case ("Circumference"):
                                    dnum1 = Double.parseDouble(ta1.getText());
                                    result = 2 * 3.14 * dnum1;
                                    Label_note.setVisible(false);
                                    ta2.appendText(result.toString());
                                    dnum1 = 0.0;
                                    break;
                            }
                            break;
                        case ("Square"):
                            switch (calc) {
                                case ("Area"):
                                    dnum1 = Double.parseDouble(ta1.getText());
                                    result = dnum1 * dnum1;
                                    ta2.appendText(result.toString());
                                    Label_note.setVisible(false);
                                    dnum1 = 0.0;
                                    break;
                                case ("Perimeter"):
                                    dnum1 = Double.parseDouble(ta1.getText());
                                    result = 4 * dnum1;
                                    ta2.appendText(result.toString());
                                    Label_note.setVisible(false);
                                    dnum1 = 0.0;
                                    break;
                            }
                            break;
                        case ("Rectangle"):

                            switch (calc) {
                                case ("Area"):
                                    if (dnum1 == 0) {
                                        dnum1 = Double.parseDouble(ta1.getText());
                                        ta1.clear();
                                        dot_flag = 0;
                                        ta1.setPromptText("Enter length:");
                                    } else {
                                        dnum2 = Double.parseDouble(ta1.getText());
                                        result = dnum1 * dnum2;
                                        ta2.appendText(result.toString());
                                        Label_note.setVisible(false);
                                        dnum1 = 0.0;
                                        dnum2 = 0.0;
                                    }
                                    break;
                                case ("Perimeter"):
                                    if (dnum1 == 0) {
                                        dnum1 = Double.parseDouble(ta1.getText());
                                        ta1.clear();
                                        dot_flag = 0;
                                        ta1.setPromptText(" Enter length: ");
                                    } else {
                                        dnum2 = Double.parseDouble(ta1.getText());
                                        result = 2 * (dnum1 + dnum2);
                                        ta2.appendText(result.toString());
                                        Label_note.setVisible(false);
                                        dnum1 = 0.0;
                                        dnum2 = 0.0;
                                    }
                                    break;
                            }
                            break;
                        case ("Triangle"):

                            switch (calc) {
                                case ("Area"):
                                    if (status_area == 0) {
                                        dnum1 = Double.parseDouble(ta1.getText());
                                        ta1.clear();
                                        dot_flag = 0;
                                        ta1.setPromptText("Enter the height ");
                                        status_area = 1;
                                    } else if (status_area == 1) {
                                        dnum2 = Double.parseDouble(ta1.getText());
                                        result = 0.5 * dnum1 * dnum2;
                                        ta2.setText("The area = ");
                                        ta2.setPromptText("");
                                        ta2.appendText(result.toString());
                                        Label_note.setVisible(false);
                                        status_area = 0;
                                    }

                                    break;
                                case ("Perimeter"):
                                    if (status_perimeter == 0) {
                                        dnum1 = Double.parseDouble(ta1.getText());
                                        ta1.clear();
                                        dot_flag = 0;
                                        ta1.setPromptText(" Enter side 2 = ");

                                        status_perimeter = 1;
                                    } else if (status_perimeter == 1) {
                                        dnum2 = Double.parseDouble(ta1.getText());
                                        ta1.clear();
                                        dot_flag = 0;
                                        ta1.setPromptText("Enter side 3 = ");
                                        status_perimeter = 2;
                                    } else if (status_perimeter == 2) {
                                        dnum3 = Double.parseDouble(ta1.getText());
                                        result = dnum1 + dnum2 + dnum3;
                                        ta2.setPromptText("The perimeter = ");
                                        ta2.setPromptText("");
                                        ta2.appendText(result.toString());
                                        Label_note.setVisible(false);
                                        status_perimeter = 0;
                                    }
                                    break;
                            }
                            break;
                        case ("Rohmbus"):
                            switch (calc) {
                                case ("Area"):
                                    if (status_area == 0) {
                                        get_dnum = ta1.getText();
                                        dnum1 = new Double(get_dnum);
                                        ta1.clear();
                                        dot_flag = 0;
                                        ta1.setPromptText("Enter the diagonal 2 length ");
                                        status_area = 1;
                                    } else if (status_area == 1) {
                                        get_dnum = ta1.getText();
                                        dnum2 = new Double(get_dnum);
                                        ta2.setPromptText("The area of Rohmbus = ");
                                        ta2.setPromptText("");
                                        result = (dnum1 * dnum2) / 2;
                                        ta2.appendText(result.toString());
                                        Label_note.setVisible(false);
                                        status_area = 0;
                                    }
                                    break;

                                case ("Perimeter"):
                                    if (status_perimeter == 0) {
                                        get_dnum = ta1.getText();
                                        dnum1 = new Double(get_dnum);
                                        result = 4 * dnum1;
                                        ta2.setPromptText("The perimeter of Rohmbus = ");
                                        ta2.setPromptText("");
                                        ta2.appendText(result.toString());
                                        Label_note.setVisible(false);
                                    }
                                    break;
                            }
                            break;

                        case ("Parallelogram"):
                            switch (calc) {
                                case ("Area"):
                                    if (status_area == 0) {
                                        get_dnum = ta1.getText();
                                        dnum1 = new Double(get_dnum);
                                        ta1.clear();
                                        dot_flag = 0;
                                        ta1.setPromptText(" Enter the height = ");
                                        status_area = 1;
                                    } else if (status_area == 1) {
                                        get_dnum = ta1.getText();
                                        dnum2 = new Double(get_dnum);
                                        ta2.setPromptText("The area of parallelogram = ");
                                        ta2.setPromptText("");
                                        result = dnum1 * dnum2;
                                        ta2.appendText(result.toString());
                                        Label_note.setVisible(false);
                                        status_area = 0;
                                    }
                                    break;

                                case ("Perimeter"):
                                    if (status_perimeter == 0) {

                                        get_dnum = ta1.getText();
                                        dnum1 = new Double(get_dnum);
                                        ta1.clear();
                                        dot_flag = 0;
                                        ta1.setPromptText(" Enter side 2 = ");
                                        status_perimeter = 1;
                                    } else if (status_perimeter == 1) {
                                        get_dnum = ta1.getText();
                                        dnum2 = new Double(get_dnum);
                                        ta2.setPromptText("The perimeter of parallelogram = ");
                                        ta2.setPromptText("");
                                        result = 2 * (dnum1 + dnum2);
                                        ta2.appendText(result.toString());
                                        Label_note.setVisible(false);
                                        status_perimeter = 0;
                                    }
                                    break;
                            }
                            break;
                    }
                } /*
                    if there is no shape or the type of calculation selected 
                    we print out message for the user to choose the shape and 
                    also choose the calculation type
                 */ else if (shape_detection == null) {
                    ta2.setText("Choose a shape");
                } else if (calc == null) {
                    ta2.setText("Choose from Calculations menu");
                } else {
                    ta2.setText("Enter Numbers!");
                }

                break;

            default:// this case is if the user select any number from the GUI
                ta1.appendText(bstr);
                break;
        }
    }

    /*
    * This handler is fired when the user clicked on the menu-button  
    * which has the menu of the geometry shapes
    * and according to the shape and the type of calculation 
    * we implement the logic
     */
    @FXML
    private void shape_geo(ActionEvent event) {
        ta1.clear();
        dot_flag = 0;
        ta2.clear();
        ta1.setPromptText("");
        Label_note.setVisible(false);
        shape_detection = null;
        calc = null;
        MenuItem shapes_mi = (MenuItem) event.getSource();
        String s = shapes_mi.getText();
        shape_mb.setText(s);
        calc_mb.getItems().removeAll(area_mi, perimeter_calc, circum_calc);
        calc_mb.setText("calc");
        if (s.equals("Circle")) {
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            circum_calc = new MenuItem("Circumference");
            calc_mb.getItems().add(circum_calc);
            area_mi.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter the radius: ");
                Label_note.setVisible(true);
                calc_mb.setText(area_mi.getText());// setting menubutton of the calc by the menuitem which has been selected
                //calc = ((MenuItem) event1.getSource()).getText();// flag for switch in the ta_write handler
                calc = calc_mb.getText();
            });
            circum_calc.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter the radius: ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(circum_calc.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });
        } else if (s.equals("Square")) {
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc = new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);
            perimeter_calc.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter side's length: ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(perimeter_calc.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });
            area_mi.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter side's length: ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(area_mi.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });
        } else if (s.equals("Rectangle")) {
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc = new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);
            perimeter_calc.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter width: ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(perimeter_calc.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });
            area_mi.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter width: ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(area_mi.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });

        } else if (s.equals("Triangle")) {
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc = new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);
            perimeter_calc.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter side 1 = ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(perimeter_calc.getText());
                calc = ((MenuItem) event1.getSource()).getText();
                System.out.println(calc);
            });
            area_mi.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter the base ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(area_mi.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });

        } else if (s.equals("Rohmbus")) {
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc = new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);

            perimeter_calc.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter the side length ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(perimeter_calc.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });
            area_mi.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter the diagonal 1 length ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(area_mi.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });

        } else if (s.equals("Parallelogram")) {
            shape_detection = s;
            area_mi = new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc = new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);

            perimeter_calc.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter side 1 =");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(perimeter_calc.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });
            area_mi.setOnAction((ActionEvent event1) -> {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                ta1.setPromptText("Enter the base = ");
                Label_note.setVisible(true);
                ta1_len = ta1.getLength();
                calc_mb.setText(area_mi.getText());
                calc = ((MenuItem) event1.getSource()).getText();
            });
        }
    }

// Hosny
    @FXML
    private void changeMode(MouseEvent event) throws IOException {
        Parent root;
        Scene scene;
        old_ta1_text = ta1.getText();
        old_ta2_text = ta2.getText();
        old_Shape_Detection = shape_detection;
        old_Calc = calc;
        if ("normal".equals(((ImageView) event.getSource()).getId())) {
            Calc_GUI.darkFlag = true;
            root = FXMLLoader.load(getClass().getResource("..//GeometryMode/GeometryModeDark.fxml"));
        } else {
            Calc_GUI.darkFlag = false;
            root = FXMLLoader.load(getClass().getResource("..//GeometryMode/GeometryModeNormal.fxml"));
        }
        scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void write_key(KeyEvent event) {
        if (event.getCode().isDigitKey()) {
            if (c_to_clear == '=') {
                ta1.clear();
                dot_flag = 0;
                ta2.clear();
                c_to_clear = ' ';
            }
            ta1.appendText(event.getText());
        } else {
            switch (event.getCode()) {

                case EQUALS:
                    b_equal.fire();
                    break;
                case ENTER:
                    b_equal.fire();
                    break;
                case BACK_SPACE:
                    b_backspace.fire();
                    break;
                case PERIOD:// for dot in del
                    b_period.fire();
                    break;
                case DECIMAL: // for dot in >
                    b_period.fire();
                    break;
            }
        }
    }

    void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(gridPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
