package project.backend.pkg;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Temperature extends Observable {
	private int min;
	private int max;
	private int currTemperature;
	private Timer timer;
	
	public Temperature(){
		this.min = 55;
		this.max = 90;
		Random rand = new Random();
		int upperRange = this.max+5;
		int lowerRange = this.min-5;
		this.currTemperature = rand.nextInt((upperRange - lowerRange) + 1) + lowerRange;
	}
	
	public int getCurrTemperature() {
		return currTemperature;
	}

	public void setCurrTemperature(int currTemperature) {
		this.currTemperature = currTemperature;
	}

	public void startNotifying(){
		timer = new Timer();
		timer.schedule(new TimerTask()
		{
			public void run() {
				if (currTemperature>=max){
					setChanged();
					notifyObservers("TOO HOT");
				}
				if (currTemperature<=min){
					setChanged();
					notifyObservers("TOO COLD");
				}
			}} , 0, 1000);
	}
	
	public void setMin(int min){
		this.min=min;
	}
	
	public void setMax(int max){
		this.max=max;
	}
	
	public int getMin(){
		return this.min;
	}
	
	public int getMax(){
		return this.max;
	}
}
