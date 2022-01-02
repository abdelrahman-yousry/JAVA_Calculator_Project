package calc_gui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class Geometry_FXMLController implements Initializable {
    @FXML
    TextField ta1,ta2;
    int ta1_len;
    char c_to_clear;
    String ta1str;
    int shape_detect=0,calc=0;
    @FXML
    MenuButton shape_mb;
    MenuItem circum_calc,perimeter_calc;
    MenuItem area_mi;
    Double dnum1=new Double(0);
    Double dnum2=new Double(0);
    Double result=new Double(0);
    @FXML
    private MenuButton calc_mb;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ta2.setEditable(false);
    }    
    @FXML
    private void ta_write(ActionEvent event) {
        Button temp= (Button) event.getSource();
        String bstr=temp.getText();
        String ta1_str=temp.getText();
        System.out.println(bstr);
        if(c_to_clear=='=' && !ta2.getText().isEmpty())
        {
            ta1.clear();
            ta2.clear();
            c_to_clear=' ';
        }
        if(!bstr.equals("C") && !bstr.equals("⌫") && !bstr.equals("="))
            ta1.appendText(ta1_str);
        else if(bstr.equals("C"))
            ta1.clear();
        else if(bstr.equals("⌫"))
            ta1.deleteNextChar();
        if(bstr.equals("="))
        {
            c_to_clear='=';
            if(!ta1.getText(ta1_len,ta1.getLength()).isEmpty() && shape_detect!=0)
            {
            switch(shape_detect)
            {
                case(1):
                    switch(calc)
                    {
                        case(1):
                            dnum1=Double.parseDouble(ta1.getText(17,ta1.getLength()));
                            result=3.14*dnum1*dnum1;
                            ta2.appendText(result.toString());
                            break;
                        case(2):
                            dnum1=Double.parseDouble(ta1.getText(17,ta1.getLength()));
                            result=2*3.14*dnum1;
                            ta2.appendText(result.toString());
                            break;    
                    }
                break;
                case(2):
                        switch(calc)
                        {
                                case(1):
                                    dnum1=Double.parseDouble(ta1.getText(20,ta1.getLength()));
                                    result=dnum1*dnum1;
                                    ta2.appendText(result.toString());
                                    break;
                                case(2):
                                    dnum1=Double.parseDouble(ta1.getText(20,ta1.getLength()));
                                    result=4*dnum1;
                                    ta2.appendText(result.toString());
                                    break;
                        }
                    break;
                case(3):
                    switch(calc)
                    {
                        case(1):
                            if(dnum1==0){
                            System.out.println(ta1.getLength());
                                dnum1=Double.parseDouble(ta1.getText(13,ta1.getLength()));
                                ta1_len=ta1.getLength();
                                ta1.appendText(" Enter length: ");
                            }
                            else
                            {
                                dnum2=Double.parseDouble(ta1.getText(ta1_len+15,ta1.getLength()));
                                result=dnum1*dnum2;
                                ta2.appendText(result.toString());
                                dnum1=0.0;
                                dnum2=0.0;
                                result=0.0;
                            }
                            break;
                        case(2):
                            if(dnum1==0){
                            System.out.println(ta1.getLength());
                                dnum1=Double.parseDouble(ta1.getText(13,ta1.getLength()));
                                ta1_len=ta1.getLength();
                                ta1.appendText(" Enter length: ");
                            }
                            else
                            {
                                dnum2=Double.parseDouble(ta1.getText(ta1_len+15,ta1.getLength()));
                                result=2*(dnum1+dnum2);
                                ta2.appendText(result.toString());
                                dnum1=0.0;
                                dnum2=0.0;
                                result=0.0;
                            }
                            break;
                    }
            }
        }
            if(shape_detect==0 && calc==0)
                ta2.setText("Enter Numbers!");
            else if(shape_detect==0)
                ta2.setText("Choose a shape");
            else if(calc==0)
                ta2.setText("Choose from Calculations menu");
}
    }
    @FXML
    private void shapes(ActionEvent event) {
        ta1.clear();
        ta2.clear();
        MenuItem shapes_mi=(MenuItem)event.getSource();
        String s=shapes_mi.getText();
        shape_mb.setText(s);
        shape_detect=calc=0;
        calc_mb.getItems().removeAll(area_mi,perimeter_calc,circum_calc);
        if(s.equals("Circle"))
        {
            shape_detect=1;
            area_mi=new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            circum_calc=new MenuItem("Circumference");
            calc_mb.getItems().add(circum_calc);
            area_mi.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                ta1.setText("Enter the radius: ");
                ta1_len=ta1.getLength();
                calc_mb.setText(area_mi.getText());
                calc=1;
            }
            });
            circum_calc.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                ta1.setText("Enter the radius: ");
                ta1_len=ta1.getLength();
                calc_mb.setText(circum_calc.getText());
                calc=2;
            }
            });
        }
        else if(s.equals("Square"))
        {
            shape_detect=2;
            area_mi=new MenuItem("Area");
            calc_mb.getItems().add(area_mi);
            perimeter_calc=new MenuItem("Perimeter");
            calc_mb.getItems().add(perimeter_calc);
            perimeter_calc.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                ta1.setText("Enter side's length: ");
                ta1_len=ta1.getLength();
                calc_mb.setText(perimeter_calc.getText());
                calc=2;
            }
            });
            area_mi.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                ta1.setText("Enter side's length: ");
                ta1_len=ta1.getLength();
                calc_mb.setText(area_mi.getText());
                calc=1;
            }
            });
    }
    else if(s.equals("Rectangle"))
    {
        shape_detect=3;
        area_mi=new MenuItem("Area");
        calc_mb.getItems().add(area_mi);
        perimeter_calc=new MenuItem("Perimeter");
        calc_mb.getItems().add(perimeter_calc);
        perimeter_calc.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                ta1.setText("Enter width: ");
                ta1_len=ta1.getLength();
                calc_mb.setText(perimeter_calc.getText());
                calc=2;
            }
            });
        area_mi.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                ta1.setText("Enter width: ");
                ta1_len=ta1.getLength();
                calc_mb.setText(area_mi.getText());
                calc=1;
            }
            });
    }
    else if(s.equals("Triangle"))
    {
        shape_detect=4;
        area_mi=new MenuItem("Area");
        calc_mb.getItems().add(area_mi);
        perimeter_calc=new MenuItem("Perimeter");
        calc_mb.getItems().add(perimeter_calc);
        perimeter_calc.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                calc_mb.setText(perimeter_calc.getText());
                calc=2;
            }
            });
        area_mi.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                ta1.setText("Enter the radius: ");
                calc_mb.setText(area_mi.getText());
                calc=1;
            }
            });
    }else if(s.equals("Rhombus"))
    {
        shape_detect=5;
        area_mi=new MenuItem("Area");
        calc_mb.getItems().add(area_mi);
        perimeter_calc=new MenuItem("Perimeter");
        calc_mb.getItems().add(perimeter_calc);
        perimeter_calc.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                calc_mb.setText(perimeter_calc.getText());
                calc=2;
            }
            });
        area_mi.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                ta1.setText("Enter the radius: ");
                calc_mb.setText(area_mi.getText());
                calc=1;
            }
            });
    }
}
}