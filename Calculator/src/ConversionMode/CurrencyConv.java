/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConversionMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author EngAya
 */
public class CurrencyConv {
    
    public static double call_me(String base,String to,String amount) throws Exception {
        String url;
        URL urlObj;
        HttpURLConnection con;
        int responseCode;
        BufferedReader in;
        String inputLine;
        StringBuffer response;
        JSONParser parser;
        JSONObject parse;

        url = url = "https://api.exchangerate.host/convert?from="+base+"&to="+to+"&amount="+amount ;
        urlObj = new URL(url); 
        con = (HttpURLConnection) urlObj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
     
        parser = new JSONParser();
        parse = (JSONObject)parser.parse(response.toString());
        
              System.out.println((Double)parse.get("result"));
        return (Double)parse.get("result");

}    
    
    
    
}
