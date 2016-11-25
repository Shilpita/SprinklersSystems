package project.backend.pkg;

public class WaterConsumptionLog {
	private String group;
	private String month;
	private long totalWaterConsump;
	
	public WaterConsumptionLog(){
		this.group = null;
		this.month = null;
		this.totalWaterConsump = 0;
	}

	public WaterConsumptionLog(String group, String month, long totalWaterConsump) {
		//super();
		this.group = group;
		this.month = month;
		this.totalWaterConsump = totalWaterConsump;
	}

	
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public long getTotalWaterConsump() {
		return totalWaterConsump;
	}

	public void setTotalWaterConsump(long totalWaterConsump) {
		this.totalWaterConsump = totalWaterConsump;
	}

	@Override
	public String toString() {
		return "WaterConsumptionLog [group=" + group + ", month=" + month + ", totalWaterConsump=" + totalWaterConsump
				+ "]";
	}

	
}
