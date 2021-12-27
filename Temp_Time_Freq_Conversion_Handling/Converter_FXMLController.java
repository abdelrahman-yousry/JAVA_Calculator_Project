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

public class Converter_FXMLController implements Initializable {
    @FXML
    private ToggleButton theme;
    @FXML
    TextField ta1,ta2;
    @FXML
    private GridPane b;
    @FXML
    private MenuItem timeconv;
    @FXML
    private MenuButton unit_conv_mb,from_mb,to_mb;
    char c_to_clear;
    String ta1str;
    int conv_mode_detect=0,from=0,to=0;
    MenuItem hrs_to,mins_to,secs_to,msecs_to;
    MenuItem hrs_from,mins_from,secs_from,msecs_from;
    MenuItem feh_from,cel_from,kel_from,feh_to,cel_to,kel_to;
    MenuItem hz_from,radps_from,hz_to,radps_to;
    MenuItem deg_from,rad_from;
    MenuItem deg_to,rad_to;
    @FXML
    private MenuItem timeconv1;
    @FXML
    private MenuItem timeconv2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ta2.setEditable(false);
    }    
    @FXML
    private void theme_change() {
    }
    @FXML
    private void ta_write(ActionEvent event) {
        Button temp= (Button) event.getSource();
        String bstr=temp.getText();
        String ta1_str=temp.getText();
        System.out.println(bstr);
        if(c_to_clear=='=')
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
            if(!ta1.getText().isEmpty() && conv_mode_detect!=0)
            {
            Double dnum1=Double.parseDouble(ta1.getText());
            Double result=new Double(0);
            switch(conv_mode_detect)
            {
                case(1):
                    if(from==1 && to==2 || from==2 && to==3)
                    {
                        result=dnum1*60;
                    }
                    else if(from==1 && to==3)
                    {
                        result=dnum1*3600;
                    }
                    else if(from==1 && to==4)
                    {
                        result=dnum1*3600000;
                    }
                    else if(from==2 && to==1 || from==3 && to==2)
                    {
                        result=dnum1/60;
                    }
                    else if(from==2 && to==4)
                    {
                        result=dnum1*60000;
                    }
                    else if(from==3 && to==1)
                    {
                        result=dnum1/3600;
                    }
                    else if(from==3 && to==4)
                    {
                        result=dnum1*1000;
                    }
                    else if(from==4 && to==1)
                    {
                        result=dnum1/3600000;
                    }
                    else if(from==4 && to==2)
                    {
                        result=dnum1/60000;
                    }
                    else if(from==4 && to==3)
                    {
                        result=dnum1/1000;
                    }
                break;
                case(2):
                    if(from==1 && to==2)
                    {
                        result=(dnum1-32)*5/9;
                    }
                    else if(from==1 && to==3)
                    {
                        result=(dnum1-32)*(5/9)+273.15; 
                    }
                    else if(from==2 && to==1)
                    {
                        result=(dnum1*(9/5))+32;
                    }
                    else if(from==2 && to==3)
                    {
                        result=dnum1+273.15;
                    }
                    else if(from==3 && to==1)
                    {
                        result=(dnum1-273.15)*9/5+32;
                    }
                    else if(from==3 && to==2)
                    {
                        result=dnum1-273.15;
                    }
                    break;
                    case(3):
                        if(from==1 && to==2)
                        {
                            result=dnum1*6.2832;
                        }
                        else if(from==2 && to==1)       
                        {
                            result=dnum1/6.2832;
                        }
                        break;
                    case(7):
                        if(from==1 && to==2)
                            result=dnum1*0.0174533;
                        else if(from==2 && to==1)
                            result=dnum1/0.0174533;
                        break;
            }
            ta2.appendText(result.toString());
            if(from==to && to!=0)
            {
                ta2.setText("Choose Different units");
            }
            else if(from==0 || to==0)
            {
                ta2.setText("Choose units");
            }
            }
            else if(conv_mode_detect==0)
                ta2.setText("Enter Conversion mode");
            else
                ta2.setText("Enter Numbers!");
        }
    }
    @FXML
    private void conv_mode(ActionEvent event) {
        ta1.clear();
        ta2.clear();
        MenuItem conv_mi=(MenuItem)event.getSource();
        String s=conv_mi.getText();
        unit_conv_mb.setText(s);
        from=to=0;
        to_mb.getItems().removeAll(hrs_to,mins_to,secs_to,msecs_to,hz_to,radps_to,feh_to,cel_to,kel_to,deg_to,rad_to);
        from_mb.getItems().removeAll(hrs_from,mins_from,secs_from,msecs_from,hz_from,radps_from,feh_from,cel_from,kel_from,deg_from,rad_from);
        if(s.equals("Time Converter"))
        {
            conv_mode_detect=1;
            hrs_from=new MenuItem("Hours");
            mins_from=new MenuItem("Minutes");
            secs_from=new MenuItem("Seconds");
            msecs_from=new MenuItem("Milliseconds");
            hrs_to=new MenuItem("Hours");
            mins_to=new MenuItem("Minutes");
            secs_to=new MenuItem("Seconds");
            msecs_to=new MenuItem("Milliseconds");
            from_mb.getItems().add(hrs_from);
            from_mb.getItems().add(mins_from);
            from_mb.getItems().add(secs_from);
            from_mb.getItems().add(msecs_from);
            to_mb.getItems().add(hrs_to);
            to_mb.getItems().add(mins_to);
            to_mb.getItems().add(secs_to);
            to_mb.getItems().add(msecs_to);
            hrs_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(hrs_from.getText());
                from=1;
            }
            });
            mins_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(mins_from.getText());
                from=2;
            }
            });
            secs_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(secs_from.getText());
                from=3;
            }
            });
            msecs_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(msecs_from.getText());
                from=4;
            }
            });
            hrs_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(hrs_to.getText());
                to=1;
            }
            });
            mins_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(mins_to.getText());
                to=2;
            }
            });
            secs_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(secs_to.getText());
                to=3;
            }
            });
            msecs_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(msecs_to.getText());
                to=4;
            }
            });
        }
        else if(s.equals("Temp Converter"))
        {
            conv_mode_detect=2;
            feh_from=new MenuItem("Fahrenheit");
            cel_from=new MenuItem("Celsius");
            feh_to=new MenuItem("Fahrenheit");
            cel_to=new MenuItem("Celsius");
            kel_from=new MenuItem("Kelvin");
            kel_to=new MenuItem("Kelvin");
            to_mb.getItems().add(feh_to);
            to_mb.getItems().add(cel_to);
            to_mb.getItems().add(kel_to);
            from_mb.getItems().add(feh_from);
            from_mb.getItems().add(cel_from);
            from_mb.getItems().add(kel_from);
            feh_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(feh_from.getText());
                from=1;
            }
            });
            cel_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(cel_from.getText());
                from=2;
            }
            });
            kel_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(kel_from.getText());
                from=3;
            }
            });
            feh_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(feh_to.getText());
                to=1;
            }
            });
            cel_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(cel_to.getText());
                to=2;
            }
            });
            kel_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(kel_to.getText());
                to=3;
            }
    });             
    }
    else if(s.equals("Freq Converter"))
    {
        conv_mode_detect=3;
        hz_from=new MenuItem("Hz");
        radps_from=new MenuItem("Rad/Sec");
        radps_to=new MenuItem("Rad/Sec");
        hz_to=new MenuItem("Hz");
        to_mb.getItems().add(hz_to);
        to_mb.getItems().add(radps_to);
        from_mb.getItems().add(hz_from);
        from_mb.getItems().add(radps_from);
        hz_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(hz_to.getText());
                to=1;
            }
            });
        radps_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(radps_to.getText());
                to=2;
            }
            });
        hz_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(hz_from.getText());
                from=1;
            }
            });
        radps_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(radps_from.getText());
                from=2;
            }
            });
    }
    else if(s.equals("Angle Converter"))
    {
        conv_mode_detect=7;
        deg_from=new MenuItem("Degree");
        rad_from=new MenuItem("Radian");
        deg_to=new MenuItem("Degree");
        rad_to=new MenuItem("Radian");
        to_mb.getItems().add(deg_to);
        to_mb.getItems().add(rad_to);
        from_mb.getItems().add(deg_from);
        from_mb.getItems().add(rad_from);
        deg_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(deg_to.getText());
                to=1;
            }
            });
        rad_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(rad_to.getText());
                to=2;
            }
            });
        deg_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(deg_from.getText());
                from=1;
            }
            });
        rad_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(rad_from.getText());
                from=2;
            }
            });
    }
}
}