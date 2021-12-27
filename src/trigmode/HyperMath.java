/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trigmode;
import java.lang.Math.*;


/**
 *
 * @author EngAya
 */
public class HyperMath {
    public Double asinh(Double theta)
    {
        return Math.log(theta + Math.sqrt(theta*theta + 1.0));
    }
    public Double acosh(Double theta)
    {
        return Math.log(theta + Math.sqrt(theta*theta - 1.0));    
    }
    public Double atanh(Double theta)
    {
        return 0.5*Math.log( (theta + 1.0) / (theta - 1.0));    
    }
    public Double asech(Double theta)
    {
        return theta;
    }
    public Double acsch(Double theta)
    {
        return Math.log(theta + Math.sqrt(theta*theta + 1.0));
    }
}
