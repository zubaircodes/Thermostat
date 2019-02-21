
/**
 * Write a description of class HeatDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HeatDisplay
{
    // instance variables 
    private int currentTemperature, minTemperature, maxTemperature, increment;

    /**
     * Constructor for objects of class HeatDisplay
     */
    public HeatDisplay(int max, int min, int incr)
    {
        // initialise instance variables
        maxTemperature = max;
        minTemperature = min;
        increment = incr;
        currentTemperature = 20;

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getCurrentTemperature()
    {
        // put your code here
        return currentTemperature;

    }
    
    public int convertToFahrenheit()
    {
        // put your code here
        return (int)(currentTemperature * 1.8 + 32); 
    }
    
    public void warmerTemp()
    {
        // put your code here
        currentTemperature = currentTemperature + increment;
        if (currentTemperature >= maxTemperature){
            currentTemperature = maxTemperature; 
        }
    }
    
    public void coolerTemp()
    {
        // put your code here
        currentTemperature = currentTemperature - increment;
        if (currentTemperature <= minTemperature){
            currentTemperature = minTemperature; 
        }
        
    }
    
    public void setIncrement(int incr)
    {
        // put your code here
        if (incr >= 0){
            increment = incr  ;
        }
    }
    
    public String getTemperature(String c_or_f)
    {
        // put your code here
        if (c_or_f.equals("C")){
            return "" + getCurrentTemperature() + " C";
        }
        else if(c_or_f.equals("F")){
            return "" + convertToFahrenheit() + " F";
        }
        else{
            return "Please Input either C or F";
        }
    }
}
