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

/*
*author Abdelrahman Yousry
*
*/

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
    MenuItem mm_from,cm_from,m_from, km_from, mile_from ;
    MenuItem mm_to,cm_to, m_to,km_to, mile_to ;
    MenuItem m_p_s_from,km_p_h_from,mile_p_h_from ;
    MenuItem m_p_s_to,km_p_h_to,mile_p_h_to ;
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
                 case(4):
                        if(from==1 && to==2)
                        {
                            result=dnum1/10;
                        }
                        else if(from==1 && to==3)
                        {
                             result=dnum1/1000;
                        }
                        else if(from==1 && to==4)
                        {
                            result= dnum1/1000000;
                        }
                        else if(from==1 && to==5)
                        {
                            result=dnum1*0.00000062137;
                        }
                        else if(from==2 && to==1)
                        {
                            result=dnum1*10;
                        }
                        else if(from==2 && to==3)
                        {
                            result=dnum1/100;
                        }
                        else if(from==2 && to==4)
                        {
                            result=dnum1/100000;
                        }
                        else if(from==2 && to==5)
                        {
                            result=dnum1*0.0000062137;
                        }
                        else if(from==3 && to==1)
                        {
                            result=dnum1*1000;
                        }
                        else if(from==3 && to==2)
                        {
                            result=dnum1*100;
                        }   
                        else if(from==3 && to==4)
                        {
                            result=dnum1/1000;
                        }
                        else if(from==3 && to==5)
                        {
                            result=dnum1*0.000621371;
                        }
                        else if(from==4 && to==1)
                        {
                            result=dnum1/1000000;
                        }
                        else if(from==4 && to==2)
                        {
                            result=dnum1/100000;
                        }   
                        else if(from==4 && to==3)
                        {
                            result=dnum1*1000;
                        }
                        else if(from==4 && to==5)
                        {
                            result=dnum1*0.621371;
                        }
                        else if(from==5 && to==1)
                        {
                            result=dnum1/0.00000062137;
                        }
                        else if(from==5 && to==2)
                        {
                            result=dnum1/0.0000062137;
                        }
                        else if(from==5 && to==3)
                        {
                            result=dnum1/0.00062137;
                        }
                        else if(from==5 && to==4)
                        {
                            result=dnum1/0.62137;
                            
                        }
                    break;
                    case(5):
                    if(from==1 && to==2)
                    {
                        result=dnum1/36000000;
                    }
                    else if(from==1 && to==3)
                    {
                        result=dnum1*2.23694; 
                    }
                    else if(from==2 && to==1)
                    {
                        result=dnum1*0.277778;
                    }
                    else if(from==2 && to==3)
                    {
                        result=dnum1*0.621371;
                    }
                    else if(from==3 && to==1)
                    {
                        result=dnum1*0.44704;
                    }
                    else if (from==3 && to==2)
                    {
                        result=dnum1*1.60934;
                    }
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
        to_mb.getItems().removeAll(mm_to,cm_to,m_to ,km_to, mile_to,m_p_s_to,km_p_h_to,mile_p_h_to);
        from_mb.getItems().removeAll(mm_from,cm_from,m_to ,km_from, mile_from,m_p_s_from,km_p_h_from,mile_p_h_from);
        if(s.equals("Length Converter"))
    {
        conv_mode_detect=4;
        mm_from=new MenuItem("Milli-meter");
        cm_from=new MenuItem("Centimeter");
        m_from=new MenuItem("Meter");
        km_from=new MenuItem("Kilometer");
        mile_from=new MenuItem("Mile");
        mm_to=new MenuItem("Milli-meter");
        cm_to=new MenuItem("Centimeter");
        m_to=new MenuItem("Meter");
        km_to=new MenuItem("Kilometer");
        mile_to=new MenuItem("Mile");
        to_mb.getItems().add(mm_to);
        to_mb.getItems().add(cm_to);
        to_mb.getItems().add(m_to);
        to_mb.getItems().add(km_to);
        to_mb.getItems().add(mile_to);
        from_mb.getItems().add(mm_from);
        from_mb.getItems().add(cm_from);
        from_mb.getItems().add(m_from);
        from_mb.getItems().add(km_from);
        from_mb.getItems().add(mile_from);
        mm_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(mm_to.getText());
                to=1;
            }
            });
        cm_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(cm_to.getText());
                to=2;
            }
            });
        m_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(m_to.getText());
                to=3;
            }
            });
        km_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(km_to.getText());
                to=4;
            }
            });
        mile_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(mile_to.getText());
                to=5;
            }
            });
        mm_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(mm_from.getText());
                from=1;
            }
            });
        cm_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(cm_from.getText());
                from=2;
            }
            });
        m_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(m_from.getText());
                from=3;
            }
            });
        km_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(km_from.getText());
                from=4;
            }
            });
        mile_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(mile_from.getText());
                from=5;
            }
            });
    }
    else if(s.equals("Speed Converter"))
    {
        conv_mode_detect=5;
        m_p_s_from=new MenuItem("Meter/sec");
        km_p_h_from=new MenuItem("Km/hr");
        mile_p_h_from=new MenuItem("Mile/hr");
        m_p_s_to=new MenuItem("Meter/sec");
        km_p_h_to=new MenuItem("Km/hr");
        mile_p_h_to=new MenuItem("Mile/hr");
        to_mb.getItems().add(m_p_s_to);
        to_mb.getItems().add(km_p_h_to);
        to_mb.getItems().add(mile_p_h_to);
        from_mb.getItems().add(m_p_s_from);
        from_mb.getItems().add(km_p_h_from);
        from_mb.getItems().add(mile_p_h_from);
        m_p_s_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(m_p_s_to.getText());
                to=1;
            }
            });
        km_p_h_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(km_p_h_to.getText());
                to=2;
            }
            });
        mile_p_h_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                to_mb.setText(mile_p_h_to.getText());
                to=3;
            }
            });
        m_p_s_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(m_p_s_from.getText());
                from=1;
            }
            });
        km_p_h_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(km_p_h_from.getText());
                from=2;
            }
            });
        mile_p_h_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.clear();
                ta2.clear();
                from_mb.setText(mile_p_h_from.getText());
                from=3;
            }
            });
}
}
}