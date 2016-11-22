package project.backend.pkg;


/**
 *
 * @author shilpita_roy
 */
public class Sprinkler {
    protected String sprinklerId;
    protected String location;
    protected boolean funtionalStatus;
    protected boolean currentStatus;
    protected String waterConsumption;
    
    public Sprinkler(){
        this.sprinklerId = "NA";
        this.location    = "NotSet";
        this.funtionalStatus = false;
        this.currentStatus = false;
        this.waterConsumption = WaterFlow.LOW.toString();
    }
    
    public Sprinkler(String sprinklerId ,String location ,boolean funtionalStatus ,boolean currentStatus ,String waterConsumption){
        this.sprinklerId = sprinklerId;
        this.location    = location;
        this.funtionalStatus = funtionalStatus;
        this.currentStatus = currentStatus;
        this.waterConsumption = waterConsumption.toUpperCase();
    }

    //getter methods
    public String getSprinklerId() {
        return sprinklerId;
    }

    public String getLocation() {
        return location;
    }

    public boolean isFuntionalStatus() {
        return funtionalStatus;
    }

    public boolean isCurrentStatus() {
        return currentStatus;
    }

    public String getWaterConsumption() {
        return waterConsumption;
    }
    
    //setter methods
    public void setSprinklerId(String sprinklerId) {
        this.sprinklerId = sprinklerId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFuntionalStatus(boolean funtionalStatus) {
        this.funtionalStatus = funtionalStatus;
    }

    public void setCurrentStatus(boolean currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setWaterConsumption(String waterConsumption) {
        this.waterConsumption = waterConsumption.toUpperCase();
    }

    public String showSprinklerdetails(){
        return "SprinklerId: "+ getSprinklerId()
               +"\nLocation: "+ getLocation()
               +"\nFuntional Status: "+ isFuntionalStatus()
               +"\nCurrent Status: "+ isCurrentStatus()
               +"\nWaterConsumption: "+getWaterConsumption();
    }

    @Override
    public String toString() {
        return "Sprinkler{" + "sprinklerId=" + sprinklerId + ", location=" + location 
                + ", funtionalStatus=" + funtionalStatus + ", currentStatus=" + currentStatus 
                + ", waterConsumption=" + waterConsumption + '}';
    }
  
}
