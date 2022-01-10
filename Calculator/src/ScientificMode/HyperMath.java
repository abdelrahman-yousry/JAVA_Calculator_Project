/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ScientificMode;
import java.lang.Math.*;
import static java.lang.Math.E;


/**
 *
 * @author EngAya
 */
public class HyperMath {
    public Double sinh(Double theta)
    {
        return java.lang.Math.sinh(theta);
    }
    public Double cosh(Double theta)
    {
        return java.lang.Math.cosh(theta);
    }
    public Double tanh(Double theta)
    {
        return java.lang.Math.tanh(theta);
    }
    public Double sech(Double theta)
    {
        return 1/(java.lang.Math.cosh(theta));
    }
    public Double csch(Double theta)
    {
        return 1/(java.lang.Math.sinh(theta));
    }
    public Double coth(Double theta)
    {
        return (java.lang.Math.cosh(theta)/(java.lang.Math.sinh(theta)));
        
    }
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
        return 0.5*Math.log( (1.0 + theta) / (1.0 - theta));    
    }
    public Double asech(Double theta)
    {
        return (Math.log((1+Math.sqrt(1-theta*theta))/theta))/Math.log(E);
    }
    public Double acsch(Double theta)
    {
        return (Math.log((1+Math.sqrt(1+theta*theta))/theta))/Math.log(E);
    }
    public Double acoth(Double theta)
    {
        return 0.5*(Math.log((theta+1)/(theta-1))/Math.log(E));
    }
    public Double ln(Double x)
    {
        return Math.log(x)/Math.log(E);
    }
}
