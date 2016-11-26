package project.backend.pkg;

/*
 * Enum for the water flow of sprinklers
 */


/**
 * @author shilpita_roy
 */
public enum WaterFlow {
    	LOW (1), MEDIUM(2),HIGH (3);
	private int waterLevel;
	private final int waterPerHr = 5;
        private final String unitMeasure ="gallon/hr";
	
	private WaterFlow(int waterLevel){
		this.waterLevel = waterLevel;
	}
	public int calFlowPerHr(){ return waterPerHr * waterLevel;}
	
	/**
	 * Function gets the total water consumption for complete schedule based on water flow
	 * @param waterFlow
	 * @param totalHrs
	 * @param totalDays
	 * @return total water consumption for schedule
	 */
	public static long getTotalWaterConsumption(String waterFlow, long totalHrs , long totalDays){
			int waterPerhr = WaterFlow.valueOf(waterFlow.toUpperCase()).calFlowPerHr();
			return (waterPerhr * totalHrs * totalDays);
	}

    @Override
    public String toString() {
        return "WaterFlow{" + "waterLevel=" + waterLevel + ", waterPerHr=" + waterPerHr + " " + unitMeasure + '}';
    }
        
}


