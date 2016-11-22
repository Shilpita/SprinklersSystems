package project.backend.pkg;

/*
 * Class to set the threshold temperature.
 */


import java.util.*;

/**
 *
 * @author shilpita_roy
 */
public class Temperature {
  //  private float currentTemperature;
    private float minTemperature;
    private float maxTemperature;

    public Temperature() {
        this.minTemperature = 55;
        this.maxTemperature = 95; 
    //    this.currentTemperature = generateCurrentTemperature();
    }
    
    
    public Temperature(float currentTemperature, float minTemperature, float maxTemperature) {
     //   this.currentTemperature = currentTemperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
    
    private float generateCurrentTemperature(){
        	Random r = new Random();
		float low = minTemperature - 5;
		float high = maxTemperature +5;
		int result = (int) (r.nextInt((int) (high-low)) + low);
	//	currentTemperature = result;
                return  result;   //currentTemperature;
    }
/*
    public float getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(float currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
*/
    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    @Override
    public String toString() {
        return "Temperature{ minTemperature=" + minTemperature + ", maxTemperature=" + maxTemperature + '}';
    }
      
    
}

