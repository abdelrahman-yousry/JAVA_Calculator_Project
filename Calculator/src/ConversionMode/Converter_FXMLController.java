package ConversionMode;


import CalcApplication.Calc_GUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
* author Abdelrahman Yousry
*/

public class Converter_FXMLController implements Initializable {
    Alert alert;
    DialogPane dialogPane;
    static String text;
    static String oldInput = "|";
    static String oldRes = " ";
    static boolean darkFlag = false;
    String selectedMode;
    static String fromStr = null;
    static String toStr = null;
    String amount = null;
    int pos ;
    
    public TextField ta1,ta2;
    @FXML
    private GridPane b;
    @FXML
    private MenuButton unit_conv_mb,from_mb,to_mb;
    char c_to_clear;
    String ta1str;
    int conv_mode_detect=0,from=0,to=0;
    MenuItem AED,USD,BHD,BRL,EGP,KWD,LYD,MYR,SYP,TRY,SAR;
    MenuItem AED_to,USD_to,BHD_to,BRL_to,EGP_to,KWD_to,LYD_to,MYR_to,SYP_to,TRY_to,SAR_to;

    MenuItem hrs_to,mins_to,secs_to,msecs_to;
    MenuItem hrs_from,mins_from,secs_from,msecs_from;
    MenuItem feh_from,cel_from,kel_from,feh_to,cel_to,kel_to;
    MenuItem hz_from,radps_from,hz_to,radps_to;
    MenuItem deg_from,rad_from;
    MenuItem deg_to,rad_to;
    MenuItem mm_from,cm_from,m_from, km_from, mile_from ;
    MenuItem mm_to,cm_to, m_to,km_to, mile_to ;
    MenuItem m_p_s_from,km_p_h_from,mile_p_h_from ;
    MenuItem m_p_s_to,km_p_h_to,mile_p_h_to ;
    MenuItem bit_from, byte_from,kilo_byte_from, mega_byte_from ,giga_byte_from ; 
    MenuItem bit_to, byte_to,kilo_byte_to, mega_byte_to ,giga_byte_to ;
    public Label res;
    @FXML
    private Menu port_menu;
    @FXML
    private Label resLabel;
    @FXML
    private Button b_equal;
    @FXML
    private Button b_backspace;
    @FXML
    private Button b_period;
    @FXML
    private MenuItem currencyButton;
    @FXML
    private ImageView normal;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ta1.setText(oldInput);
        ta1.setEditable(false);
        ta1.setFocusTraversable(false);
        res.setText(oldRes);
        ta2.setEditable(false);
        ta2.setFocusTraversable(false);
    } 
    
    @FXML
    public void write_key(KeyEvent event) {
        pos = ta1.getText().indexOf("|");

        if(event.getCode().isDigitKey())
            ta1.insertText(pos, event.getText());
        else if(event.isControlDown()){
            switch(event.getCode()){
                case LEFT:
                    if(pos>0){
                        ta1.setText(ta1.getText().replace("|", ""));
                        ta1.insertText(pos-1, "|");
                    }break;
                case RIGHT:
                    if(pos<ta1.getText().length()-1){
                        ta1.setText(ta1.getText().replace("|", ""));
                        ta1.insertText(pos+1, "|");
                    }break;
            }
        }
        else{
            switch(event.getCode()){
                case E:
                    ta1.insertText(pos, "e");
                    break;
                case SLASH:
                    ta1.insertText(pos, "÷");
                    break;
                case DIVIDE:
                    ta1.insertText(pos, "÷");
                    break;
                case MINUS:
                    ta1.insertText(pos, "-");
                    break;
                case SUBTRACT:
                    ta1.insertText(pos, "-");
                    break;
                case ADD:
                    ta1.insertText(pos, "+");
                    break;
                case MULTIPLY:
                    ta1.insertText(pos, "×");
                    break;
                case EQUALS:
                    b_equal.fire();
                    break; 
                case ENTER:
                    b_equal.fire();
                    break; 
                case BACK_SPACE:
                    b_backspace.fire();
                    break;
                case PERIOD:
                    b_period.fire();
                    break;
                case DECIMAL:
                    b_period.fire();
                    break;
                case HOME:
                    ta1.setText(ta1.getText().replace("|", ""));
                    ta1.insertText(0, "|");
                    break;
                case END:
                    ta1.setText(ta1.getText().replace("|", ""));
                    ta1.insertText(ta1.getText().length(), "|");
                    break;
            }
        }
    }
    
    @FXML
    private void ta_write(ActionEvent event) {

        Button temp= (Button) event.getSource();
        pos = ta1.getText().indexOf("|");
        
        String bstr=temp.getText();
        String ta1_str=temp.getText();
        
 
        if(c_to_clear=='=')
        {
            ta1.setText("|");
            ta2.clear();
            c_to_clear=' ';
        }
        if(!bstr.equals("C") && !bstr.equals("⌫") && !bstr.equals("="))
            ta1.insertText(pos, ta1_str);

        else if(bstr.equals("C"))
        {
            // clear button  --> clear screen
            ta1.setText("|");
        }
        else if(bstr.equals("⌫"))
        {
            pos = ta1.getText().indexOf("|");
            if(pos != 0)
            ta1.deleteText(pos-1, pos);
        }
        if(bstr.equals("="))
        {
            c_to_clear='=';
            if(!"|".equals(ta1.getText()) && conv_mode_detect!=0)
            {
            String expression = ta1.getText();
            expression = expression.replace("|", "");
            Double dnum1=Double.parseDouble(expression);
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
                    case(6):
                    if(from==1 && to==2)
                    {
                        result=dnum1/8;
                    }
                    else if(from==1 && to==3)
                    {
                        result=dnum1*0.000000125; 
                    }
                    else if(from==1 && to==4)
                    {
                        result=dnum1*0.000000000125;
                    }
                    else if(from==2 && to==1)
                    {
                        result=dnum1*8;
                    }
                    else if(from==2 && to==3)
                    {
                        result=dnum1*0.000001;
                    }
                    else if (from==2 && to==4)
                    {
                        result=dnum1*0.000000001;
                    }
			        else if(from==3 && to==1)
                    {
                        result=dnum1*8000000;
                    }
                    else if(from==3 && to==2)
                    {
                        result=dnum1*1000000;
                    }
                    else if (from==3 && to==4)
                    {
                        result=dnum1/1000;
                    }
			        else if(from==4 && to==1)
                    {
                        result=dnum1*72000000;
                    }
                    else if(from==4 && to==2)
                    {
                        result=dnum1*9000000;
                    }
                    else if (from==4 && to==3)
                    {
                        result=dnum1*1000;
                    }					
                    break;
                    case(7):
                        if(from==1 && to==2)
                            result=dnum1*0.0174533;
                        else if(from==2 && to==1)
                            result=dnum1/0.0174533;
                        break;
                    case(8):
                        {
                            amount = ta1.getText();
                            amount = amount.replace("|", "");
                            if((fromStr==null) || (toStr==null ))
                                ta2.setText("Choose units");
                            else if("|".equals(ta1.getText()))
                                ta2.setText("Enter Numbers!");
                            else if(fromStr.equals(toStr))
                                ta2.setText("Choose Different units");                               
                            else
                            {
                                try {
                                    String [] res;
                                    res = CurrencyConv.call_me(fromStr, toStr, amount);
                                    ta2.appendText(res[0]);
                                    resLabel.setText("Updated "+res[1]);
                                } catch (Exception ex) {
                                    Logger.getLogger(Converter_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
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
        ta1.setText("|");
        ta2.clear();
        MenuItem conv_mi=(MenuItem)event.getSource();
        String s=conv_mi.getText();
        unit_conv_mb.setText(s);
        from=to=0;
        to_mb.getItems().removeAll(hrs_to,mins_to,secs_to,msecs_to,hz_to,radps_to,feh_to,
                cel_to,kel_to,deg_to,rad_to,mm_to,cm_to,m_to ,km_to, mile_to,m_p_s_to,
                km_p_h_to,mile_p_h_to,bit_to, byte_to,kilo_byte_to, mega_byte_to ,giga_byte_to,AED_to,USD_to,BHD_to,EGP_to,BRL_to,KWD_to,TRY_to,LYD_to,MYR_to,SYP_to,SAR_to);
        from_mb.getItems().removeAll(hrs_from,mins_from,secs_from,msecs_from,hz_from,radps_from,
                feh_from,cel_from,kel_from,deg_from,rad_from,mm_from,cm_from,m_from ,
                km_from, mile_from,m_p_s_from,km_p_h_from,mile_p_h_from,bit_from, byte_from,kilo_byte_from, 
                mega_byte_from ,giga_byte_from,AED,USD,BHD,EGP,BRL,KWD,TRY,LYD,MYR,SYP,SAR);
       
        from_mb.setText("     ");
        to_mb.setText("     ");

        if(s.equals("Currency Converter"))
        {
            ta1.setText("|");
            conv_mode_detect = 8;
            BHD = new MenuItem("Bahrain - Dinar");
            BHD_to = new MenuItem("Bahrain - Dinar");
            BRL = new MenuItem("Brazil - Real");
            BRL_to = new MenuItem("Brazil - Real");
            EGP = new MenuItem("Egypt - Pound");
            EGP_to = new MenuItem("Egypt - Pound");          
            KWD = new MenuItem("Kuwait - Dinar");
            KWD_to = new MenuItem("Kuwait - Dinar");
            USD = new MenuItem("USA - Dollar");
            USD_to = new MenuItem("USA - Dollar");          
            TRY = new MenuItem("Turkey - Lira");
            TRY_to = new MenuItem("Turkey - Lira");
            AED = new MenuItem("Emarites - Dirham");
            AED_to = new MenuItem("Emarites - Dirham");
            LYD = new MenuItem("Libya - Dinar");
            LYD_to = new MenuItem("Libya - Dinar");
            MYR = new MenuItem("Malaysia - Ringgit");
            MYR_to = new MenuItem("Malaysia - Ringgit");
            SYP = new MenuItem("Syria - Pound");
            SYP_to = new MenuItem("Syria - Pound");
            SAR = new MenuItem("Saudi Arabia - Riyal");
            SAR_to = new MenuItem("Saudi Arabia - Riyal");
            
            
            from_mb.getItems().addAll(AED,USD,BHD,EGP,BRL,KWD,TRY,LYD,MYR,SYP,SAR);
            to_mb.getItems().addAll(AED_to,USD_to,BHD_to,EGP_to,BRL_to,KWD_to,TRY_to,LYD_to,MYR_to,SYP_to,SAR_to);
 
            USD.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(USD.getText());
                fromStr = "USD";
                from =1;
            }
            });
            USD_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(USD_to.getText());
                toStr = "USD";
                to = 1;
            }
            });    
            EGP.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(EGP.getText());
                fromStr = "EGP";
                from =2;
            }
            });
            EGP_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(EGP_to.getText());
                toStr = "EGP";
                to = 2;
            }
            }); 
            BHD.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(BHD.getText());
                fromStr = "BHD";
                from =3;
            }
            });
            BHD_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(BHD_to.getText());
                toStr = "BHD";
                to = 3;
            }
            });  
            BRL.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(BRL.getText());
                fromStr = "BRL";
                from =4;
            }
            });
            BRL_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(BRL_to.getText());
                toStr = "BRL";
                to = 4;
            }
            });  
            KWD.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(KWD.getText());
                fromStr = "KWD";
                from =5;
            }
            });
            KWD_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(KWD_to.getText());
                toStr = "KWD";
                to = 5;
            }
            });  
            TRY.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(TRY.getText());
                fromStr = "TRY";
                from =6;
            }
            });
            TRY_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(TRY_to.getText());
                toStr = "TRY";
                to = 6;
            }
            });  
            AED.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(AED.getText());
                fromStr = "AED";
                from =7;
            }
            });
            AED_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(AED_to.getText());
                toStr = "AED";
                to = 7;
            }
            });  
            LYD.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(LYD.getText());
                fromStr = "LYD";
                from =8;
            }
            });
            LYD_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(LYD_to.getText());
                toStr = "LYD";
                to = 8;
            }
            });  
            MYR.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(MYR.getText());
                fromStr = "MYR";
                from =9;
            }
            });
            MYR_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(MYR_to.getText());
                toStr = "MYR";
                to = 9;
            }
            });  
            SYP.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(SYP.getText());
                fromStr = "BHD";
                from =10;
            }
            });
            SYP_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(SYP_to.getText());
                toStr = "BHD";
                to = 10;
            }
            });  
            SAR.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(SAR.getText());
                fromStr = "SAR";
                from =11;
            }
            });
            SAR_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            { 
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(SAR_to.getText());
                toStr = "SAR";
                to = 11;
            }
            });  

        }
        if(s.equals("Time Converter"))
        {
            ta1.setText("|");
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
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(hrs_from.getText());
                from=1;
            }
            });
            mins_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(mins_from.getText());
                from=2;
            }
            });
            secs_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(secs_from.getText());
                from=3;
            }
            });
            msecs_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(msecs_from.getText());
                from=4;
            }
            });
            hrs_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(hrs_to.getText());
                to=1;
            }
            });
            mins_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(mins_to.getText());
                to=2;
            }
            });
            secs_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(secs_to.getText());
                to=3;
            }
            });
            msecs_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(msecs_to.getText());
                to=4;
            }
            });
        }
        else if(s.equals("Temp Converter"))
        {
            ta1.setText("|");
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
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(feh_from.getText());
                from=1;
            }
            });
            cel_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(cel_from.getText());
                from=2;
            }
            });
            kel_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(kel_from.getText());
                from=3;
            }
            });
            feh_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(feh_to.getText());
                to=1;
            }
            });
            cel_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(cel_to.getText());
                to=2;
            }
            });
            kel_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(kel_to.getText());
                to=3;
            }
    });             
    }
    else if(s.equals("Freq Converter"))
    {
        ta1.setText("|");
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
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(hz_to.getText());
                to=1;
            }
            });
        radps_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(radps_to.getText());
                to=2;
            }
            });
        hz_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(hz_from.getText());
                from=1;
            }
            });
        radps_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(radps_from.getText());
                from=2;
            }
            });
    }
    else if(s.equals("Length Converter"))
    {
        ta1.setText("|");
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
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(mm_to.getText());
                to=1;
            }
            });
        cm_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(cm_to.getText());
                to=2;
            }
            });
        m_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(m_to.getText());
                to=3;
            }
            });
        km_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(km_to.getText());
                to=4;
            }
            });
        mile_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(mile_to.getText());
                to=5;
            }
            });
        mm_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(mm_from.getText());
                from=1;
            }
            });
        cm_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(cm_from.getText());
                from=2;
            }
            });
        m_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(m_from.getText());
                from=3;
            }
            });
        km_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(km_from.getText());
                from=4;
            }
            });
        mile_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(mile_from.getText());
                from=5;
            }
            });
    }else if(s.equals("Data Converter"))
    {
        ta1.setText("|");
        conv_mode_detect=6;
        bit_from=new MenuItem("Bit");
        byte_from=new MenuItem("Byte");
        mega_byte_from=new MenuItem("Megabyte");
        giga_byte_from=new MenuItem("Gigabyte");
        bit_to=new MenuItem("Bit");
        byte_to=new MenuItem("Byte");
        mega_byte_to=new MenuItem("Megabyte");
        giga_byte_to=new MenuItem("Gigabyte");
        from_mb.getItems().add(bit_from);
        from_mb.getItems().add(byte_from);
        from_mb.getItems().add(mega_byte_from);
        from_mb.getItems().add(giga_byte_from);
        to_mb.getItems().add(bit_to);
        to_mb.getItems().add(byte_to);
		to_mb.getItems().add(mega_byte_to);
        to_mb.getItems().add(giga_byte_to);
        bit_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(bit_to.getText());
                to=1;
            }
            });
        byte_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(byte_to.getText());
                to=2;
            }
            });
        mega_byte_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(mega_byte_to.getText());
                to=3;
            }
            });
        giga_byte_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(giga_byte_to.getText());
                to=4;
            }
            });
        bit_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(bit_from.getText());
                from=1;
            }
            });
        byte_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(byte_from.getText());
                from=2;
            }
            });	
        mega_byte_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(mega_byte_from.getText());
                from=3;
            }
            });	
        giga_byte_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(giga_byte_from.getText());
                from=4;
            }
            });
    }
    else if(s.equals("Angle Converter"))
    {
        ta1.setText("|");
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
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(deg_to.getText());
                to=1;
            }
            });
        rad_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(rad_to.getText());
                to=2;
            }
            });
        deg_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(deg_from.getText());
                from=1;
            }
            });
        rad_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(rad_from.getText());
                from=2;
            }
            });
    }
    else if(s.equals("Speed Converter"))
    {
        ta1.setText("|");
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
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(m_p_s_to.getText());
                to=1;
            }
            });
        km_p_h_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(km_p_h_to.getText());
                to=2;
            }
            });
        mile_p_h_to.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                to_mb.setText(mile_p_h_to.getText());
                to=3;
            }
            });
        m_p_s_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(m_p_s_from.getText());
                from=1;
            }
            });
        km_p_h_from.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(km_p_h_from.getText());
                from=2;
            }
            });
        mile_p_h_from.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {   
                ta1.setText("|");
                ta2.clear();
                from_mb.setText(mile_p_h_from.getText());
                from=3;
            }
            });
}
}    

    @FXML
    private void modesHandle(ActionEvent event) throws IOException {
        selectedMode = ((MenuItem)event.getSource()).getText();

        Parent root = null;
        Scene scene;
        
        switch(selectedMode)
        {
            case "Basic":
                if(!Calc_GUI.darkFlag)
                    root = FXMLLoader.load(getClass().getResource("..//BaseMode/BaseModeNormal.fxml"));
                else
                    root = FXMLLoader.load(getClass().getResource("..//BaseMode/BaseModeDark.fxml"));                   
                break;
            case "Scientific":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeNormal.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//ScientificMode/ScientificModeDark.fxml"));                   
                break;
            case "Conversion":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//ConversionMode/Converter_FXML.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//ConversionMode/Converter_FXML_Dark.fxml"));                  
                break;     
            case "Geometry":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//GeometryMode/GeometryModeNormal.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//GeometryMode/GeometryModeDark.fxml"));                  
                break;  
            case "Base-N":
                if(!Calc_GUI.darkFlag)    
                    root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeNormal.fxml"));            
                else
                    root = FXMLLoader.load(getClass().getResource("..//BaseNMode/BaseNModeDark.fxml"));                  
                break; 
        }

        scene = new Scene(root);
        Stage window = (Stage)(res.getScene().getWindow());
        window.setScene(scene);
        window.show();
        //return to default
        oldInput = "|";
        oldRes = " ";    
    }

    @FXML
    private void changeMode(MouseEvent event) throws IOException {
        Parent root;
        Scene scene;
        if("normal".equals(((ImageView)event.getSource()).getId()))
        {
            root = FXMLLoader.load(getClass().getResource("..//ConversionMode/Converter_FXML_Dark.fxml"));
            Calc_GUI.darkFlag = true;
        }
        else
        {           
            root = FXMLLoader.load(getClass().getResource("..//ConversionMode/Converter_FXML.fxml"));
            Calc_GUI.darkFlag = false;
        }
        scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();          
    } 
    
    @FXML
    private void helpHandle(ActionEvent event) {
        
        switch(((MenuItem)event.getSource()).getText())
        {
            case "Guide":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Guide");
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setContentText("\t\t----IMPORTANT SHORTCUTS----\t\t\n"
                        + "-----------------------------------------------------------\n"
                        + "1- Ctrl + ←  :  Move Cursor to Left\n"
                        + "2- Ctrl + → : Move Cursor to Right\n"
                        + "3- ← → ↑ ↓  :  Moving on the GUI\n"
                        + "4- Alt   :  Go to MenuBar\n"
                        + "5- Tab  :  Move out from the Text Field\n"
                        + "-----------------------------------------------------------\n"
                        + "NOTE  :  You can use the Keys on your Keyboard to\n\t\t\t  type what you need"); 
                
                dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                getClass().getResource("..//Style/Dialoge.css").toString());
                dialogPane.getStyleClass().add("myDialog");
                alert.showAndWait();
                break;
            case "About":
                Image logoITI = new Image(getClass().getResource("..//Style/ITI.png").toString());
                ImageView logo = new ImageView(logoITI);
                StackPane pane = new StackPane();
                pane.getChildren().add(logo);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText(null);
                alert.setContentText("\n\n\t\tCopyright © 2022 by Team 9\n\n Aya Adel - Youmna Al-Shaboury - Nehal Amgad\n     Abdelrahman Yousry - Mohammed Hosny\n\n\t\tintake42-Embedded System Track");  
                alert.setGraphic(pane);
                dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                getClass().getResource("..//Style/Dialoge.css").toString());
                dialogPane.getStyleClass().add("myDialog");
                alert.showAndWait();
                break;       
        }
    }

    @FXML
    private void editHandle(ActionEvent event) {
        switch(((MenuItem)event.getSource()).getText())
        {
            case "Copy":
               text = ta1.getSelectedText();
               text = text.replace("|", "");
                break;
            case "Cut":
                text = ta1.getSelectedText();
                ta1.deleteText(ta1.getSelection());
                break;
            case "Paste":
                ta1.insertText(ta1.getCaretPosition(),text);
                break;
            case "Delete":
                ta1.setText("|");
                break;
        }
    }
    
}